package pojo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by zhenbiao.shen on 2016/5/18.
 */
public class XcdInfo implements Serializable {
    private String ContactName;
    private String ContactAddress;
    private String ContactMobile;
    private String ContactCity;

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        if(StringUtils.isNotEmpty(contactName)){
             ContactName = contactName.trim();
        }else{
            ContactName="";
        }
    }

    public String getContactAddress() {
        return ContactAddress;
    }

    public void setContactAddress(String contactAddress) {
        if(StringUtils.isNotEmpty(contactAddress)){
            ContactAddress = contactAddress.trim();
        }else{
            ContactAddress="";
        }
    }

    public String getContactMobile() {
        return ContactMobile;
    }

    public void setContactMobile(String contactMobile) {
        if(StringUtils.isNotEmpty(contactMobile)){
         ContactMobile = contactMobile.trim();
        }else{
            ContactMobile="";
        }
    }

    public String getContactCity() {
        return ContactCity;
    }

    public void setContactCity(String contactCity) {
        if(StringUtils.isNotEmpty(contactCity)){
            ContactCity = contactCity.trim();
        }else{
            ContactCity="";
        }
    }

    @Override
    public String toString() {
        return "XcdInfo{" +
                "ContactName='" + ContactName + '\'' +
                ", ContactAddress='" + ContactAddress + '\'' +
                ", ContactMobile='" + ContactMobile + '\'' +
                ", ContactCity='" + ContactCity + '\'' +
                '}';
    }
}
