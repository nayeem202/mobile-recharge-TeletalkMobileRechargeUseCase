package net.celloscope.bill.mobileRecharge.domain;

import lombok.*;
import net.celloscope.bill.mobileRecharge.shared.ChannelName;
import net.celloscope.bill.mobileRecharge.shared.ConnectionType;
import net.celloscope.bill.mobileRecharge.shared.Operator;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MobileRecharge {
    private String mobileNo;

    private Operator operator;
    private ConnectionType connectionType;
    private Integer amount;
    private String requestId;
    private String traceId;
    private String remarks;
    private String userId;
    private String bankOid;
    private String bankName;
    private ChannelName channelName;


}
