package net.celloscope.bill.mobileRecharge.application.port.in.dto;
import com.google.gson.GsonBuilder;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RechargeResponse {

    private String status;

    private String errorMessage;

    private String errorCode;

    private String transactionId;

    private Date transactionDate;

    private String reference;

    private String userMessage;

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

}
