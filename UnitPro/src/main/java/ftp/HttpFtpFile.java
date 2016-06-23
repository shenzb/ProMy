package ftp;

import sun.net.www.protocol.ftp.FtpURLConnection;

import java.io.*;
import java.net.URL;

/**
 * Created by zhenbiao.shen on 2016/4/18.
 */
public class HttpFtpFile {

    public static void main(String[] args) {
        try {
            HttpFtpFile httpFtpFile = new HttpFtpFile();
            String url = "";
            String locationFile = "D://bb.txt";
            Boolean ret = WGet(url, locationFile);
//            String context= HttpUtil.get(url);
            System.out.println("====================" + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean WGet(String url, String location
                              ) {
        boolean ret = true;
        String retData = getContent(url);
        System.out.println(url + ":return:" + retData);
        if (retData == null) {
            ret = false;
            return ret;
        }
        retData = retData.trim();
        if (retData.length() == 0) {
            System.out.println("系统发生错误!接口返回数据为空.");
            ret = false;
            return ret;
        }
        String errormsg = "";
        if ((retData.indexOf("error") > -1) || (retData.indexOf("Exception") > -1)) {
            ret = false;
            return ret;
        }
        try {
            File temp = new File(location);
            if (temp.exists()) {
                temp.delete();
                temp=new File(location);
            }
            File filedir = new File(temp.getParent());
            if (!filedir.exists()) {
                filedir.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(temp);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(retData);
            writer.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return ret;
    }

    /**
     * 从某个接口取返回数据内容
     *
     * @param url
     * @return
     */
    public static String getContent(String url) {
        String content = null;
        int retry = 3; // 重试次数
        for (int i = 0; i < retry; i++) {
            content = tryGetContent(url);
            if (content != null) {
                break;
            }
        }
        return content;
    }

    /**
     * 从某个接口取返回数据内容
     *
     * @param url
     * @return
     */
    public static String tryGetContent(String url) {
        try {
            return fileGetContents(url);
        } catch (IOException e) {
            System.out.println("Error getURL:" + url);
        }
        return null;
    }

    /**
     * 从某个接口取返回数据内容
     *
     * @return
     */
    public static String fileGetContents(String strUrl) throws IOException {
        URL url1 = null;
        BufferedReader reader = null;
        FtpURLConnection connection = null;
        InputStreamReader ins = null;
        try {
            url1 = new URL(strUrl);
            connection = (FtpURLConnection) url1.openConnection();
            connection.setConnectTimeout(2 * 1000);
            connection.setReadTimeout(2 * 1000);
            connection.connect();
            ins = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(ins);
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println("Error getURL:" + strUrl);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }


}
