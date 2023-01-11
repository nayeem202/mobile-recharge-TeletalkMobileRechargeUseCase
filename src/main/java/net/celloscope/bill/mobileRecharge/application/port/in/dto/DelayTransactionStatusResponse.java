package net.celloscope.bill.mobileRecharge.application.port.in.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DelayTransactionStatusResponse {
    private String originatorConversationId;
    private String status;
    private String requestStatus;
    private String transactionId;
    private Date transactionDate;
    private String reference;
    private String userMessage;

    private String errorMessage;

    private String errorCode;

}
