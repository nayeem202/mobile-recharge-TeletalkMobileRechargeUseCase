package net.celloscope.bill.mobileRecharge.domain;

import lombok.ToString;
import net.celloscope.bill.mobileRecharge.shared.ChannelName;
import net.celloscope.bill.mobileRecharge.shared.ConnectionType;
import net.celloscope.bill.mobileRecharge.shared.Operator;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ToString
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

  /*  @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }*/

    public boolean isSameRequestWithinTimeBound(List<BanglalinkRecharge> banglalinkRechargeList) {
        if (banglalinkRechargeList.isEmpty()) return true;
        Date now = new Date();
        DecimalFormat df = new DecimalFormat("0.00");
        for (TeletalkRecharge rechargeTransaction : banglalinkRechargeList) {
            if (!rechargeTransaction.getTransStatus().equals("OK")) continue;
            long diffInMilliSeconds = now.getTime() - rechargeTransaction.getBanglalinkTransactionDate().getTime();
            float diffInMinutes = TimeUnit.MINUTES.convert(diffInMilliSeconds, TimeUnit.MILLISECONDS);
            diffInMinutes = Float.valueOf(df.format(diffInMinutes));
            System.out.println("+++++++++++++++++ Difference in minutes: " + diffInMinutes);
            if (diffInMinutes < Constants.BANGLALINK_MAXIMUM_ALLOWED_TIME_FOR_REQUEST) {
                return false;
            }
        }
        return true;
    }
}
