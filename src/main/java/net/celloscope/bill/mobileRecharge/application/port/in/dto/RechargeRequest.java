package net.celloscope.bill.mobileRecharge.application.port.in.dto;

import lombok.*;
import net.celloscope.bill.mobileRecharge.shared.SelfValidating;


import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RechargeRequest extends SelfValidating<RechargeRequest> {
    @NotNull(message = "Mobile no can not be null")
    @NotEmpty(message = "Mobile no can not be empty")
    @Pattern(regexp = "^8801[3-9]\\d{8}$", message = "Mobile no is not valid")
    private String mobileNo;

    @NotNull(message = "Operator can't be empty.")
    @Pattern(regexp = "ROBI|GP|BANGLALINK|TELETALK|AIRTEL", message = "Invalid Operator.")
    private String operator;

    @NotNull(message = "Connection Type can't be empty.")
    @Pattern(regexp = "PREPAID|POSTPAID", message = "Invalid Connection Type.")
    private String connectionType;

    @NotNull(message = "Amount can't be empty.")
    @Positive(message = "Invalid Amount.")
    private Integer amount;

    @NotNull(message = "Currency can not be null")
    private String currency;

    @NotNull(message = "OriginatorConversationId can not be null")
    @NotEmpty(message = "OriginatorConversationId can not be empty")
    private String originatorConversationId;

    @NotNull(message = "userId can not be null")
    private String userId;

    @NotNull(message = "bank name is required")
    @NotEmpty(message = "bank name can not be empty")
    private String bankName;

    @NotNull(message = "bankOid is required")
    @NotEmpty(message = "bankOid can not be empty")
    private String bankOid;

    @NotNull(message = "Connection Type can't be empty.")
    @Pattern(regexp = "IBANKING|MOBILEBANKING|AGENTBANKING", message = "Invalid Channel Name.")
    private String channelName;
    private String requestId;
    private String traceId;
    private String remarks;


    public RechargeRequest(String mobileNo, String operator, String connectionType, Integer amount, String currency, String originatorConversationId, String userId, String bankName, String bankOid, String channelName, String requestId, String traceId, String remarks) {
        this.mobileNo = mobileNo;
        this.operator = operator;
        this.connectionType = connectionType;
        this.amount = amount;
        this.currency = currency;
        this.originatorConversationId = originatorConversationId;
        this.userId = userId;
        this.bankName = bankName;
        this.bankOid = bankOid;
        this.channelName = channelName;
        this.requestId = requestId;
        this.traceId = traceId;
        this.remarks = remarks;
    }

//    @Override
//    public String toString() {
//        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
//    }
}
