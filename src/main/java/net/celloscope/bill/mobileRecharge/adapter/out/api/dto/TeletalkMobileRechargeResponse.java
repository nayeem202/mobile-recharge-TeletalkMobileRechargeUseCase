package net.celloscope.bill.mobileRecharge.adapter.out.api.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"TYPE", "TXNSTATUS", "DATE", "EXTREFNUM", "TXNID", "MESSAGE"})
public class TeletalkMobileRechargeResponse {
//    @XmlElement
    private String TYPE;
//    @XmlElement
    private int TXNSTATUS;
//    @XmlElement
    private Date DATE;
//    @XmlElement
    private String EXTREFNUM;
//    @XmlElement
    private String TXNID;
//    @XmlElement
    private String MESSAGE;


    public String getTYPE() {
        return TYPE;
    }

    public int getTXNSTATUS() {
        return TXNSTATUS;
    }

    public Date getDATE() {
        return DATE;
    }

    public String getEXTREFNUM() {
        return EXTREFNUM;
    }

    public String getTXNID() {
        return TXNID;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public void setTXNSTATUS(int TXNSTATUS) {
        this.TXNSTATUS = TXNSTATUS;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }

    public void setEXTREFNUM(String EXTREFNUM) {
        this.EXTREFNUM = EXTREFNUM;
    }

    public void setTXNID(String TXNID) {
        this.TXNID = TXNID;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }
}