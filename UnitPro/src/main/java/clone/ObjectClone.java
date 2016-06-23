package clone;

import pojo.TicketNum;
import util.JsonUtils;

/**
 * Created by zhenbiao.shen on 2016/6/14.
 */
public class ObjectClone {

    public static void main(String[] args) {
        TicketNum ticketNum=new TicketNum();
        ticketNum.setClientId("a");
        ticketNum.seteTicketNum("b");
        TicketNum ticketNum1=(TicketNum) ticketNum.clone();
        ticketNum1.setClientId("c");
        TicketNum ticketNum2=(TicketNum) ticketNum.clone();
        ticketNum2.setClientId("d");
        System.out.println(JsonUtils.toJsonString(ticketNum));
        System.out.println(JsonUtils.toJsonString(ticketNum1));
        System.out.println(JsonUtils.toJsonString(ticketNum2));

    }
}
