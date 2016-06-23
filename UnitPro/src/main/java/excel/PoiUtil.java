package excel;

import com.google.common.collect.Lists;
import db.DbUtilTest;
import infoEncrypt.InfoEncrypt;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.XcdInfo;

import java.io.*;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenbiao.shen on 2016/5/11.
 */
public class PoiUtil {

    private static Logger logger = LoggerFactory.getLogger(PoiUtil.class);

    private static Logger errLogger = LoggerFactory.getLogger("ErrorLog");

    public static void main(String[] args) {
        File disrect = new File("D://data.xls");
//        String saveFile="D://data.xls";
        if (disrect.isDirectory()) {
            File[] cvs = disrect.listFiles();
            for (File file : cvs) {
                System.out.println("===============file:" + file.getName() + "=======");
//                File savefile=new File(saveFile+"/"+file.getName());
                writerTofile(disrect, disrect);
            }
        }
    }

    private static void writerTofile(File file, File savefile) {
        try {
            InputStream inputStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

            //循环工作表sheet
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {
                    continue;
                }
                //循环行row
                for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                    HSSFRow row = sheet.getRow(j);
                    try {
                        if (null == row || null == row.getCell(0) || StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
                            break;
                        }
//                      String card=row.getCell(11).toString();
                        String telsave = null;
                        try {
                            String tel = row.getCell(2).getStringCellValue();
                            //                    String cardsave=InfoEncrypt.encrypt(card,"3");
                            telsave = InfoEncrypt.encrypt(tel, "1");
                        } catch (Exception e) {
                            System.out.println("解密错误：" + row.getCell(2) + "," + e);
                            telsave = "";
                        }
                        //                    if(StringUtils.isNotEmpty(cardsave)){
                        //                        row.getCell(11).setCellValue(" "+cardsave.split(",")[1]);
                        //                    }else{
                        //                        row.getCell(11).setCellValue("");
                        //                    }
                        try {
                            if (StringUtils.isNotEmpty(telsave)) {
                                row.getCell(2).setCellValue("'" + telsave.split(",")[1]);
                            } else {
                                row.getCell(2).setCellValue("");
                            }
                        } catch (Exception e) {
                            System.out.println("设置值错误：" + j + "," + e);
                        }
                        System.out.println("执行记录：" + j);
                        if (j % 4 == 0) {
                            Thread.sleep(500);
                        }
                    } catch (Exception e) {
                        System.out.println("执行失败：" + j + "," + e);
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readfileTodb(File file) {
        List<XcdInfo> list=Lists.newArrayList();
        InputStream inputStream =null;
        try {
            inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            int count = 0;
            int j = 1;
            //循环工作表sheet
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {
                    continue;
                }
                int blankCount = 0;
                //循环行row
                for (; j <= sheet.getLastRowNum(); j++) {
                    XSSFRow row = sheet.getRow(j);
                    try {
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        if (null == row || null == row.getCell(0) || StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
                            if (blankCount > 5) {
                                break;
                            }
                            continue;
                        }
                        blankCount = 0;
                        String ContactName = row.getCell(0).getStringCellValue();
                        String ContactMobile = row.getCell(1).getStringCellValue();
                        String ContactAddress = row.getCell(2).getStringCellValue();
                        String ContactCity = row.getCell(3).getStringCellValue();
                        XcdInfo xcdInfo = new XcdInfo();
                        xcdInfo.setContactAddress(ContactAddress);
                        xcdInfo.setContactCity(ContactCity);
                        xcdInfo.setContactMobile(ContactMobile);
                        xcdInfo.setContactName(ContactName);
                        if (isMath(xcdInfo)) {
                            list.add(xcdInfo);
                            if(list.size()%100==0){
                                Connection con = DbUtilTest.init();
                                int co = DbUtilTest.insert(con, list);
                                count+=co;
                                DbUtilTest.close(con);
                                list.clear();
                            }
                        } else {
                            logger.info("数据不合法：" + xcdInfo);
                        }
                    } catch (Exception e) {
                        logger.error("执行失败：" + j + "," + e);
                    }
                }
            }
            logger.info("应该执行数据量：" + j + ",实际插入量：" + count);
        } catch (Exception e) {
            logger.error("readfileTodb error:", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("",e);
            }
        }
    }
    public static void readfile(File file) {
        List<String> list=Lists.newArrayList();
        InputStream inputStream =null;
        try {
            inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            int count = 0;
            int j = 1;
            //循环工作表sheet
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                if (null == sheet) {
                    continue;
                }
                int blankCount = 0;
                //循环行row
                for (; j <= sheet.getLastRowNum(); j++) {
                    XSSFRow row = sheet.getRow(j);
                    try {
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        if (null == row || null == row.getCell(0) || StringUtils.isBlank(row.getCell(0).getStringCellValue())) {
                            if (blankCount > 2) {
                                break;
                            }
                            continue;
                        }
                        blankCount = 0;
                        String ContactMobile = row.getCell(0).getStringCellValue();

                        if (telMath(ContactMobile)) {
                            list.add(ContactMobile);
                            if(list.size()%100==0){
                                DbUtilTest.delete(list);
                                list.clear();
                            }
                        } else {
                            logger.info("数据不合法：" + ContactMobile);
                        }
                    } catch (Exception e) {
                        logger.error("执行失败：" + j + "," + e);
                    }
                }
            }
            DbUtilTest.delete(list);
            logger.info("应该执行数据量：" + j + ",实际插入量：" + count);
        } catch (Exception e) {
            logger.error("readfileTodb error:", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("",e);
            }
        }
    }

    private static boolean isMath(XcdInfo xcdInfo) {
        if (xcdInfo != null) {
            String name = xcdInfo.getContactName();
            String tel = xcdInfo.getContactMobile();
            String addr = xcdInfo.getContactAddress();
            String city = xcdInfo.getContactCity();

            if (!nameMatch(name)) {
                return false;
            }
            if (!addrMatch(addr)) {
                return false;
            }
            if (!telMath(tel)) {
                return false;
            }
            if (!cityMatch(city,xcdInfo)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private static boolean nameMatch(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(name.trim());
        if (isNum.matches()) {
            return false;
        }

        if (StringUtils.contains(name, "携程")) {
            return false;
        }

        return true;
    }

    private static boolean addrMatch(String addr) {
        if (StringUtils.isEmpty(addr)) {
            return false;
        }


        if (StringUtils.contains(addr, "携程") || StringUtils.contains(addr, "酒店") ||
                StringUtils.contains(addr, "公安") || StringUtils.contains(addr, "派出所") ||
                StringUtils.contains(addr, "部队") || StringUtils.contains(addr, "军区") || StringUtils.contains(addr, "法院") ||
                StringUtils.contains(addr, "记者") || StringUtils.contains(addr, "快递") || StringUtils.contains(addr, "行程单")
                || StringUtils.contains(addr, "发票") || StringUtils.contains(addr, "自提") || StringUtils.contains(addr, "律师")
                || StringUtils.contains(addr, "政府")
                ) {
            return false;
        }

        return true;
    }


    private static boolean telMath(String tel) {
        if (StringUtils.isEmpty(tel)) {
            return false;
        }
        Pattern p = Pattern.compile("^((13\\d{9}$)|(17\\d{9}$)|(15[0-9]\\d{8}$)|(18[0-9]\\d{8}$)|(147\\d{8})$)");
        Matcher m = p.matcher(tel.trim());
        if (!m.matches()) {
            return false;
        }
        return true;
    }

    private static boolean cityMatch(String city,XcdInfo xcdInfo) {
        if (StringUtils.isEmpty(city)) {
            errLogger.info("城市为空：{}",xcdInfo);
            return false;
        }
        if (StringUtils.contains(city, "黄山") || StringUtils.contains(city, "新疆") || StringUtils.contains(city, "西藏")) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        Matcher matcher = pattern.matcher(city.trim());
        if (!matcher.find()) {
            errLogger.info("城市不是中文：{}",xcdInfo);
            return false;
        }
        return true;
    }


    public static int writeToDb(XcdInfo xcdInfo) {
        try {
            Connection con = DbUtilTest.init();
            int co = DbUtilTest.insert(con, null);
            return co;
        } catch (Exception e) {
            logger.error("writeToDb error:", e);
        }
        return 0;
    }
}
