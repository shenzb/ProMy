package pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhenbiao.shen on 2016/5/31.
 */
public class FreezeRecord implements Serializable {

    /**
     * 主键.
     */
    private Long id;

    private String payCenterAccount;

    private String freezeNo="";

    private String unFreezeNo="";

    private BigDecimal freezeAmount;

    private Integer freezeStatus;

    private Date updateTime;

    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayCenterAccount() {
        return payCenterAccount;
    }

    public void setPayCenterAccount(String payCenterAccount) {
        this.payCenterAccount = payCenterAccount;
    }

    public String getFreezeNo() {
        return freezeNo;
    }

    public void setFreezeNo(String freezeNo) {
        this.freezeNo = freezeNo;
    }

    public String getUnFreezeNo() {
        return unFreezeNo;
    }

    public void setUnFreezeNo(String unFreezeNo) {
        this.unFreezeNo = unFreezeNo;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public Integer getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "FreezeRecord{" +
                "id=" + id +
                ", payCenterAccount='" + payCenterAccount + '\'' +
                ", freezeNo='" + freezeNo + '\'' +
                ", unFreezeNo='" + unFreezeNo + '\'' +
                ", freezeAmount=" + freezeAmount +
                ", freezeStatus=" + freezeStatus +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
