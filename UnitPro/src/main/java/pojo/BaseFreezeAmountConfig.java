package pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by zhenbiao.shen on 2016/5/31.
 */
public class BaseFreezeAmountConfig {
    private String paycenterAccount;

    private BigDecimal amount;

    private String operator;
    /**
     * 抵押金
     */
    private BigDecimal pledgeAmount;

    /**
     * 提现次数
     */
    private int fetchTimes;
    /**
     * 剩余抵押金
     */
    private BigDecimal residualAmount;

    /**
     * 贷款
     */
    private BigDecimal loanAmount;

    /**
     * 更新贷款的时间戳
     */
    private Timestamp updateLoanTimestamp;

    public Timestamp getUpdateLoanTimestamp() {
        return updateLoanTimestamp;
    }

    public void setUpdateLoanTimestamp(final Timestamp updateLoanTimestamp) {
        this.updateLoanTimestamp = updateLoanTimestamp;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(final BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(BigDecimal residualAmount) {
        this.residualAmount = residualAmount;
    }

    public BigDecimal getPledgeAmount() {
        return pledgeAmount;
    }

    public void setPledgeAmount(BigDecimal pledgeAmount) {
        this.pledgeAmount = pledgeAmount;
    }

    public int getFetchTimes() {
        return fetchTimes;
    }

    public void setFetchTimes(int fetchTimes) {
        this.fetchTimes = fetchTimes;
    }

    public String getPaycenterAccount() {
        return paycenterAccount;
    }

    public void setPaycenterAccount(String paycenterAccount) {
        this.paycenterAccount = paycenterAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseFreezeAmountConfig{");
        sb.append("paycenterAccount='").append(paycenterAccount).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", pledgeAmount=").append(pledgeAmount);
        sb.append(", fetchTimes=").append(fetchTimes);
        sb.append(", residualAmount=").append(residualAmount);
        sb.append(", loanAmount=").append(loanAmount);
        sb.append(", updateLoanTimestamp=").append(updateLoanTimestamp);
        sb.append('}');
        return sb.toString();
    }
}
