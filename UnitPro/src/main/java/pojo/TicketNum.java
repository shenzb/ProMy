package pojo;

/**
 * Created by zhenbiao.shen on 2016/6/14.
 */
public class TicketNum{
    private long orderId;
    private String eTicketNum;
    private String orderNo;
    private String dispatchOrderNo;
    private String name;
    private int passengerKey;
    private String clientId;



    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getPassengerKey() {
        return passengerKey;
    }

    public void setPassengerKey(int passengerKey) {
        this.passengerKey = passengerKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String geteTicketNum() {
        return eTicketNum;
    }

    public void seteTicketNum(String eTicketNum) {
        this.eTicketNum = eTicketNum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDispatchOrderNo() {
        return dispatchOrderNo;
    }

    public void setDispatchOrderNo(String dispatchOrderNo) {
        this.dispatchOrderNo = dispatchOrderNo;
    }
    public Object clone()
    {
        Object o=null;
        try
        {
            o=(TicketNum)this.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return o;
    }
}
