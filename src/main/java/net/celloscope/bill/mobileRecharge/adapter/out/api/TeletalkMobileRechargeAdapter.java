package net.celloscope.bill.mobileRecharge.adapter.out.api;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeResponse;
import net.celloscope.bill.mobileRecharge.application.port.out.TeletalkMobileRecharge;
import net.celloscope.bill.mobileRecharge.domain.MobileRecharge;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import reactor.core.publisher.Mono;

@NoArgsConstructor
@AllArgsConstructor

public class TeletalkMobileRechargeAdapter implements TeletalkMobileRecharge {

    private final TeletalkRestClient teletalkRestClient;

    @Override
    public Mono<RechargeResponse> makeRequest(MobileRecharge recharge) throws ExceptionHandlerUtil {
        return null;
    }
}
