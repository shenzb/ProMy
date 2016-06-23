package pojo;

import java.io.Serializable;

/**
 * Created by zhenbiao.shen on 2016/5/30.
 */
public class MerchantResult  extends Result implements Serializable {

    private static final long serialVersionUID = -1250070453421906560L;
    private String requestNo;

    public MerchantResult() {
    }

    public String getRequestNo() {
        return this.requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
