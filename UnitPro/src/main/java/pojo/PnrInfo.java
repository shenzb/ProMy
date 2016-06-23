package pojo;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhenbiao.shen on 2016/5/9.
 */
public class PnrInfo {

    private static final long serialVersionUID = 8618059597001115844L;
    public static final byte PNR_PAS_TYPE_CHD = 1;
    public static final byte PNR_PAS_TYPE_ADT = 0;
    public static final char PNR_STATUS_NEW = 'a';
    public static final char PNR_STATUS_BOUND = 'b';
    public static final char PNR_STATUS_ABANDON = 'c';
    public static final char PNR_STATUS_USED = 'd';
    public static final char PNR_STATUS_CANCELED = 'e';
    public static final char PNR_STATUS_TICKETOK = 'f';
    public static final byte PNR_STATUS_NEW_CODE_NEW = 0;
    public static final byte PNR_STATUS_BOUND_CODE_BOUND = 1;
    public static final byte PNR_STATUS_ABANDON_CODE_ABANDON = 2;
    public static final byte PNR_STATUS_CODE_ERROR = 3;
    public static final byte PNR_STATUS_USED_CODE_USED = 4;
    public static final byte PNR_STATUS_CANCELED_CODE_CANCELED = 5;
    public static final byte PNR_STATUS_TICKETOK_CODE_TICKETOK = 6;
    public static final long TWENTY_SIX_DBA = 1000L;
    public static Timestamp DEFUALT_TIME_TO_LIVE = new Timestamp(1000L);
    public static final int EXTEND_TIME = 10;
    public static final String CREATOR_SYS = "sys";
    public static final String CREATOR_AGENT = "agent";
    public static final String RESPONSE_PNR = "responsePnr";
    public static final String RESPONSE_ERROR = "responseError";
    private Long id;
    private String pnr;
    private Character status;
    private Byte statusCode;
    private Byte type;
    private String source;
    private Timestamp createTime;
    private Byte numOfPassenger;
    private Byte numOfSegment;
    private Timestamp timeToLive;
    private Timestamp maxTimeToLive;
    private String pidstatus;
    private String officeId;
    private String sessionId;
    private String originalResponse;
    private String actionCode;
    private String creator;
    private Timestamp updateTime;
    private String exeType;
    private String exeOfficeId;
    private String supplierTel;
    private String contactTel;
    private Integer riskLevel = Integer.valueOf(-1);
    private String timeLimit;
    private int tryCancelCount;




    public String getPidstatus() {
        return this.pidstatus;
    }

    public void setPidstatus(String pidstatus) {
        this.pidstatus = pidstatus;
    }


    public String getOriginalResponse() {
        return this.originalResponse;
    }

    public void setOriginalResponse(String originalResponse) {
        this.originalResponse = originalResponse;
    }

    public int getTryCancelCount() {
        return this.tryCancelCount;
    }

    public void setTryCancelCount(int tryCancelCount) {
        this.tryCancelCount = tryCancelCount;
    }

    public String getExeType() {
        return this.exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }

    public String getExeOfficeId() {
        return this.exeOfficeId;
    }

    public void setExeOfficeId(String exeOfficeId) {
        this.exeOfficeId = exeOfficeId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPnr() {
        return this.pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Character getStatus() {
        return this.status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Byte getType() {
        return this.type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Byte getNumOfPassenger() {
        return this.numOfPassenger;
    }

    public void setNumOfPassenger(Byte numOfPassenger) {
        this.numOfPassenger = numOfPassenger;
    }

    public Byte getNumOfSegment() {
        return this.numOfSegment;
    }

    public void setNumOfSegment(Byte numOfSegment) {
        this.numOfSegment = numOfSegment;
    }

    public Timestamp getTimeToLive() {
        return this.timeToLive;
    }

    public void setTimeToLive(Timestamp timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getOfficeId() {
        return this.officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getSupplierTel() {
        return this.supplierTel;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
    }

    public String getContactTel() {
        return this.contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public Integer getRiskLevel() {
        return this.riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Byte getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Byte statusCode) {
        this.statusCode = statusCode;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getMaxTimeToLive() {
        return this.maxTimeToLive;
    }

    public void setStatusAndCode(Character status, Byte statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getActionCode() {
        return this.actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public void setMaxTimeToLive(Timestamp maxTimeToLive) {
        this.maxTimeToLive = maxTimeToLive;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isNew() {
        return this.status.charValue() == 97;
    }

    public boolean isBound() {
        return this.status.charValue() == 98;
    }

    public boolean isUsed() {
        return this.status.charValue() == 100;
    }

    public boolean isInvalid() {
        return this.status.charValue() == 99;
    }

    public boolean isInvalidButHasError() {
        return this.status.charValue() == 99 && this.statusCode.byteValue() == 3;
    }

    public boolean isCanceled() {
        return this.status.charValue() == 101;
    }

    public boolean isCanceledButHasError() {
        return this.status.charValue() == 101 && this.statusCode.byteValue() == 3;
    }

    public boolean hasError() {
        return this.statusCode.byteValue() == 3;
    }

    public boolean isAvailable() {
        boolean b = false;

        try {
            b = this.status.charValue() == 97 || this.status.charValue() == 98;
        } catch (Exception var3) {
            b = false;
        }

        return b;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("PnrInfos{");
        sb.append("pnr=\'").append(this.pnr).append('\'');
        sb.append(", status=").append(this.status);
        sb.append(", statusCode=").append(this.statusCode);
        sb.append(", type=").append(this.type);
        sb.append(", source=\'").append(this.source).append('\'');
        sb.append(", createTime=").append(this.createTime);
        sb.append(", numOfPassenger=").append(this.numOfPassenger);
        sb.append(", numOfSegment=").append(this.numOfSegment);
        sb.append(", timeToLive=").append(this.timeToLive);
        sb.append(", maxTimeToLive=").append(this.maxTimeToLive);
        sb.append(", officeId=\'").append(this.officeId).append('\'');
        sb.append(", sessionId=\'").append(this.sessionId).append('\'');
        sb.append(", originalResponse=\'").append(this.originalResponse).append('\'');
        sb.append(", actionCode=\'").append(this.actionCode).append('\'');
        sb.append(", creator=\'").append(this.creator).append('\'');
        sb.append(", updateTime=").append(this.updateTime);
        sb.append(", pidstatus=").append(this.pidstatus);
        sb.append(", exeType=\'").append(this.exeType).append('\'');
        sb.append(", exeOfficeId=\'").append(this.exeOfficeId).append('\'');
        sb.append(", supplierTel=\'").append(this.supplierTel).append('\'');
        sb.append(", contactTel=\'").append(this.contactTel).append('\'');
        sb.append(", riskLevel=").append(this.riskLevel);
        sb.append(", timeLimit=\'").append(this.timeLimit).append('\'');
        sb.append('}');
        return sb.toString();
    }



    public int hashCode() {
        int result = this.id != null?this.id.hashCode():0;
        result = 31 * result + (this.pnr != null?this.pnr.hashCode():0);
        result = 31 * result + (this.status != null?this.status.hashCode():0);
        result = 31 * result + (this.statusCode != null?this.statusCode.hashCode():0);
        result = 31 * result + (this.type != null?this.type.hashCode():0);
        result = 31 * result + (this.source != null?this.source.hashCode():0);
        result = 31 * result + (this.createTime != null?this.createTime.hashCode():0);
        result = 31 * result + (this.numOfPassenger != null?this.numOfPassenger.hashCode():0);
        result = 31 * result + (this.numOfSegment != null?this.numOfSegment.hashCode():0);
        result = 31 * result + (this.officeId != null?this.officeId.hashCode():0);
        result = 31 * result + (this.exeType != null?this.exeType.hashCode():0);
        result = 31 * result + (this.exeOfficeId != null?this.exeOfficeId.hashCode():0);
        return result;
    }
}
