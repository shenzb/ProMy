package db;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.XcdInfo;

import java.sql.*;
import java.util.List;

/**
 * Created by zhenbiao.shen on 2016/5/18.
 */
public class DbUtilTest {
    private static Logger logger= LoggerFactory.getLogger(DbUtilTest.class);

    public static Connection init(){
        try {
             new com.mysql.jdbc.Driver();
//            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("成功加载MySQL驱动程序");

            String url="";
            Connection con = DriverManager.getConnection(url);
            return  con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int insert(Connection con, List<XcdInfo> xcdInfos){
        try {

            PreparedStatement stmt = con.prepareStatement("insert into test1(ContactName,ContactAddress,ContactMobile,ContactCity)values(?,?,?,?)");
            for(XcdInfo xcdInfo:xcdInfos){
                stmt.setString(1,xcdInfo.getContactName());
                stmt.setString(2,xcdInfo.getContactAddress());
                stmt.setString(3,xcdInfo.getContactMobile());
                stmt.setString(4,xcdInfo.getContactCity());
                stmt.addBatch();
            }
            int[] count= stmt.executeBatch();
//            logger.info("成功："+xcdInfo);
            return count.length;
        } catch (Exception e) {
            logger.error("insert error:",e);
            return 0;
        }
    }
    public void update(){

    }
    public static int delete(List<String> tels){
        Connection con=init();
        try {
            if(CollectionUtils.isNotEmpty(tels)){
                PreparedStatement stmt = con.prepareStatement("DELETE  FROM test1 where ContactMobile=?");
                for(String tel:tels){
                    stmt.setString(1,tel);
                    stmt.addBatch();
                }
                int[] count=stmt.executeBatch();
//            logger.info("成功："+xcdInfo);
                return count.length;
            }
        } catch (Exception e) {
            logger.error("insert error:",e);
            return 0;
        }finally {
            close(con);
        }
        return 0;
    }

    public void select(){

    }

    public static void close(Connection con){
        try {
            con.close();
        } catch (Exception e) {
            logger.error("close error:",e);
        }
    }


}
