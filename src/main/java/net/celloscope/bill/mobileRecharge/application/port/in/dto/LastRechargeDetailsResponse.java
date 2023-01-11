package net.celloscope.bill.mobileRecharge.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.celloscope.bill.mobileRecharge.domain.MobileRecharge;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LastRechargeDetailsResponse {
    private String status;
    private MobileRecharge data;
}
