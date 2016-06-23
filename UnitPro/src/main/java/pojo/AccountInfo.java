package pojo;

import com.google.common.base.Objects;

import java.math.BigDecimal;

/**
 * User: xping.zong Date: 13-12-30 Time: 下午5:47
 */
public class AccountInfo {

    /**
     * 支付中心账户
     */
    private String payCenterAccount;

    /**
     * 总押款金额
     */
    private BigDecimal sumFreezeAmount;

    public AccountInfo() {
    }

    public AccountInfo(String payCenterAccount, BigDecimal sumFreezeAmount) {
        this.payCenterAccount = payCenterAccount;
        this.sumFreezeAmount = sumFreezeAmount;
    }

    public BigDecimal getSumFreezeAmount() {
        return sumFreezeAmount;
    }

    public void setSumFreezeAmount(BigDecimal sumFreezeAmount) {
        this.sumFreezeAmount = sumFreezeAmount;
    }

    public String getPayCenterAccount() {
        return payCenterAccount;
    }

    public void setPayCenterAccount(String payCenterAccount) {
        this.payCenterAccount = payCenterAccount;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("payCenterAccount", payCenterAccount)
                .add("sumFreezeAmount", sumFreezeAmount)
                .toString();
    }
}
