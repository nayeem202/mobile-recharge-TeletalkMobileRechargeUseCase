package net.celloscope.bill.mobileRecharge.application.port.out;

import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LoadTeletalkMobileRecharge {
    Mono<List<TeletalkRecharge>> findByMobileNoAndAmount(String mobileNo, Double amount) throws ExceptionHandlerUtil;

}
