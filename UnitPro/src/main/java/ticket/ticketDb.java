package ticket;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;

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

        String ticket="8222325294544";
        System.out.println(getTicketNumTableIndex(ticket));

    }
}
