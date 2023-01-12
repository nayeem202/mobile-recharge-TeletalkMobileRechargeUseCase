package net.celloscope.bill.mobileRecharge.application.port.out;

import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import reactor.core.publisher.Flux;

public interface SaveTeletalkRechargeInStore {
    //TeletalkRecharge save(TeletalkRecharge rechargeTransaction) throws ExceptionHandlerUtil;
    Flux<TeletalkRecharge> save(TeletalkRecharge rechargeTransaction) throws ExceptionHandlerUtil;
}
