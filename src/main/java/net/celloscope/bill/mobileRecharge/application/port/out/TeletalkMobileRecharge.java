package net.celloscope.bill.mobileRecharge.application.port.out;

import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeResponse;
import net.celloscope.bill.mobileRecharge.domain.MobileRecharge;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import reactor.core.publisher.Mono;

public interface TeletalkMobileRecharge {
    Mono<RechargeResponse> makeRequest(MobileRecharge recharge) throws ExceptionHandlerUtil;
}
