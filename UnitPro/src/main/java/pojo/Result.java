package pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhenbiao.shen on 2016/5/30.
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -7296986611689814053L;
    protected StatusEnum status;
    protected String errorCode;
    protected String errorMsg;
    protected Map<String, String> ext;
    protected String merchantCode;

    public Result() {
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Map<String, String> getExt() {
        return this.ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
}
