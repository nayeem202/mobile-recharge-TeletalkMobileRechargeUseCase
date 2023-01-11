package net.celloscope.bill.mobileRecharge.application.port.in.teletalk;

import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeRequest;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeResponse;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import reactor.core.publisher.Mono;

public interface TeletalkMobileRechargeUseCase {
    Mono<RechargeResponse> recharge(RechargeRequest command) throws ExceptionHandlerUtil;
}
