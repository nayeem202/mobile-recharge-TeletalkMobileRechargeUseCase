package net.celloscope.bill.mobileRecharge.shared.model;
import com.google.gson.GsonBuilder;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
public class TeletalkTransactionIntermediateStatus {
    private Boolean isRequestReceivedSuccessful;
    private Boolean isRequestReceivedFailed;
    private Boolean isRequestProcessSuccessful;
    private Boolean isRequestProcessFailed;
    private Boolean isRequestProcessPending;
    private Boolean isRequestProcessReconciled; // is this necessary?
    @Override
    public String toString(){
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
