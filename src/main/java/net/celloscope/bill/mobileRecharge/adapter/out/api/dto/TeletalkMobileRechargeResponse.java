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
@Getter
@Setter
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
}