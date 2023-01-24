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
import reactor.core.scheduler.Schedulers;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


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

    public TeletalkRecharge(String mobileNo, Operator operator, ConnectionType connectionType, Integer amount, String originatorConversationId, String requestId, String traceId, String remarks, String userId, String bankOid, String bankName, ChannelName channelName, Double charge, Double vat, Date teletalkTransactionDate, String transStatus, TeletalkTransactionIntermediateStatus intermediateStatus, String failureReason, String telatalkTransactionId, String currency, String errorCode, String errorMessage) {
        super(mobileNo, operator, connectionType, amount, originatorConversationId, requestId, traceId, remarks, userId, bankOid, bankName, channelName);
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

    public TeletalkRecharge() {

    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

    public Mono<Boolean> isSameRequestWithinTimeBound(Flux<List<TeletalkRecharge>> teletalkRechargeList) {
        return teletalkRechargeList
                .flatMapIterable(Function.identity())
                .map(rechargeTransaction -> {
                    if (rechargeTransaction.getTeletalkTransactionDate() == null) return true;
                    Long diffInMilliSeconds = new Date().getTime() - rechargeTransaction.getTeletalkTransactionDate().getTime();
                    Long diffInMinutes = TimeUnit.MINUTES.convert(diffInMilliSeconds, TimeUnit.MILLISECONDS);
                    //log.info("++ Difference in minutes: {}", diffInMinutes);
                    //log.info();
                     if(diffInMinutes < Constants.TELETALK_MAXIMUM_ALLOWED_TIME_FOR_REQUEST){
                        return false;
                    };
                    return true;
                })
                .defaultIfEmpty(true)
                .any(withinTimeBound -> !withinTimeBound)
                .map(withinTimeBound -> !withinTimeBound);
    }



}
