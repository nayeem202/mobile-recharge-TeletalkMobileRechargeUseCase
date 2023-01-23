package net.celloscope.bill.mobileRecharge.adapter.out.persistence;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "robimobilerecharge")
public class TeletalkMobileRechargeEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "teletalkmobilerechargeoid", updatable = false, nullable = false)
    private String teletalkMobileRechargeOid;
    @Column(name = "teletalkrechargeamount")
    private Double amount;

    @Column(name = "chargeamount")
    private Double charge;

    @Column(name = "vatamount")
    private Double vat;

    @Column(name = "mobileno")
    private String mobileNo;

    @Column(name = "teletalktransactiondate")
    private Date teletalkTransactionDate;

    @Column(name = "transstatus")
    private String transStatus;

    @Column(name = "intermediatestatus")
    private String intermediateStatus;

    @Column(name = "failurereason")
    private String failureReason;

    @Column(name = "requestid")
    private String requestId;

    @Column(name = "teltalktransactionid")
    private String robiTransactionId;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "traceid")
    private String traceId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "createdon")
    private Date createdOn;

    @Column(name = "bankoid")
    private String bankOid;

    @Column(name = "bankname")
    private String bankName;

    @Column(name = "channelname")
    private String channelName;

    @Column(name = "editedby")
    private String editedBy;

    @Column(name = "editedon")
    private Date editedOn;

    @Column(name = "originatorconversationid")
    private String originatorConversationId;
    @Column(name = "connectiontype")
    private String connectionType;
    @Column(name = "operatortype")
    private String operator;
    @Column(name = "errorcode")
    private String errorCode;
    @Column(name = "errormessage")
    private String errorMessage;
    @javax.persistence.Id
    @GeneratedValue
    private Long id;

}
