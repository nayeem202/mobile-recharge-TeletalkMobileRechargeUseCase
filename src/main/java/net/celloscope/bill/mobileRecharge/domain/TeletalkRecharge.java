package net.celloscope.bill.mobileRecharge.domain;

import com.google.gson.GsonBuilder;
import lombok.*;
import net.celloscope.bill.mobileRecharge.shared.ChannelName;
import net.celloscope.bill.mobileRecharge.shared.ConnectionType;
import net.celloscope.bill.mobileRecharge.shared.Operator;
import net.celloscope.bill.mobileRecharge.shared.model.TeletalkTransactionIntermediateStatus;
import net.celloscope.bill.mobileRecharge.shared.util.Constants;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TeletalkRecharge extends MobileRecharge{
    private Double charge;
    private Double vat;
    private Date teletalkTransactionDate;
    private String transStatus;
    private TeletalkTransactionIntermediateStatus intermediateStatus;
    private String failureReason;
    private String telatalkTransactionId;
    private String currency;
    private String errorCode;
    private String errorMessage;


    public TeletalkRecharge(String mobileNo, Operator operator, ConnectionType connectionType, Integer amount, String requestId, String traceId, String remarks, String userId, String bankOid, String bankName, ChannelName channelName, Double charge, Double vat, Date teletalkTransactionDate, String transStatus, TeletalkTransactionIntermediateStatus intermediateStatus, String failureReason, String telatalkTransactionId, String currency, String errorCode, String errorMessage) {
        super(mobileNo, operator, connectionType, amount, requestId, traceId, remarks, userId, bankOid, bankName, channelName);
        this.charge = charge;
        this.vat = vat;
        this.teletalkTransactionDate = teletalkTransactionDate;
        this.transStatus = transStatus;
        this.intermediateStatus = intermediateStatus;
        this.failureReason = failureReason;
        this.telatalkTransactionId = telatalkTransactionId;
        this.currency = currency;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

/*    public boolean isSameRequestWithinTimeBound(List<TeletalkRecharge> teletalkRechargeList) {
        if (teletalkRechargeList.isEmpty()) return true;
        Date now = new Date();
        DecimalFormat df = new DecimalFormat("0.00");
        for (TeletalkRecharge rechargeTransaction : teletalkRechargeList) {
            if (!rechargeTransaction.getTransStatus().equals("OK")) continue;
            long diffInMilliSeconds = now.getTime() - rechargeTransaction.getTeletalkTransactionDate().getTime();
            float diffInMinutes = TimeUnit.MINUTES.convert(diffInMilliSeconds, TimeUnit.MILLISECONDS);
            diffInMinutes = Float.valueOf(df.format(diffInMinutes));
            System.out.println("+++++++++++++++++ Difference in minutes: " + diffInMinutes);
            if (diffInMinutes < Constants.BANGLALINK_MAXIMUM_ALLOWED_TIME_FOR_REQUEST) {
                return false;
            }
        }
        return true;
    }*/

    public static boolean isSameRequestWithinTimeBound(List<TeletalkRecharge> teletalkRechargeList) {
        return Flux.fromIterable(teletalkRechargeList)
                .filter(transaction -> transaction.getTransStatus().equals("OK"))
                .map(transaction -> Duration.between(transaction.getTeletalkTransactionDate().toInstant(), Instant.now()).toMinutes())
                .any(minutes -> minutes < Constants.BANGLALINK_MAXIMUM_ALLOWED_TIME_FOR_REQUEST)
                .block();
    }
}
