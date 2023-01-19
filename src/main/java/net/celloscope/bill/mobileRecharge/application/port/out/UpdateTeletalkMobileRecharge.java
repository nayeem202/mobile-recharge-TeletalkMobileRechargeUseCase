package net.celloscope.bill.mobileRecharge.application.port.out;

import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import reactor.core.publisher.Mono;

public interface UpdateTeletalkMobileRecharge {
    Mono<TeletalkRecharge> updateState(TeletalkRecharge rechargeTransaction) throws ExceptionHandlerUtil;
}
