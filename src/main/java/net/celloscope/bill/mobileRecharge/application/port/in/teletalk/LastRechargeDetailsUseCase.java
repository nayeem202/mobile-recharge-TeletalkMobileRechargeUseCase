package net.celloscope.bill.mobileRecharge.application.port.in.teletalk;

import net.celloscope.bill.mobileRecharge.application.port.in.dto.LastRechargeDetailsResponse;
import reactor.core.publisher.Mono;

public interface LastRechargeDetailsUseCase {
    Mono<LastRechargeDetailsResponse> getLastRechargeDetails(String userId);
}
