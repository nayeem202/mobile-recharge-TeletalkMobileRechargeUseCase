package net.celloscope.bill.mobileRecharge.application.port.out;

import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import reactor.core.publisher.Flux;

import java.util.List;

public interface LoadTeletalkMobileRecharge {
    Flux<List<TeletalkRecharge>> findByMobileNoAndAmount(String mobileNo, Double amount) throws ExceptionHandlerUtil;

}
