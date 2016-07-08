package ticket;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by zhenbiao.shen on 2016/6/12.
 */
public class ticketDb {

    public static String getTicketNumTableIndex(String ticketNum) {
        // ticketNum MD5 除以1024取模，确定查询那哪张表
        String ticketNummd5 = DigestUtils.md5Hex(ticketNum).toUpperCase();
        BigInteger ticketNumi = new BigInteger(ticketNummd5, 16);
        BigInteger[] ss = ticketNumi.divideAndRemainder(new BigInteger("1024"));
        return String.valueOf(ss[1]);
    }

    public static void main(String[] args) {

        ticketDb ticketDb=new ticketDb();
        Boolean isflag=false;
        Test test=new Test();
        ticketDb.getResponse(test);

        System.out.println(test);
    }

    private void getResponse(Test test){
        test.setFlag(true);
//        return null;
    }

   static class Test{
       boolean isFlag;

        public boolean isFlag() {
            return isFlag;
        }

        public void setFlag(boolean flag) {
            isFlag = flag;
        }

       @Override
       public String toString() {
           return "Test{" +
                   "isFlag=" + isFlag +
                   '}';
       }
   }
}
