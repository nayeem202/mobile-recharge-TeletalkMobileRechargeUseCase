package net.celloscope.bill.mobileRecharge.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class TeletalkRechargeTest {

    @Test
    void test() {
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound((List<TeletalkRecharge>) buildRechargeListOfCurrentTime());
        StepVerifier.create(res)
                .expectNext(false)
                .verifyComplete();

    }

    public Flux<TeletalkRecharge> buildRechargeListOfCurrentTime() {
        return Flux.fromIterable(List.of(
              new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date() ,null, null, null, null, null, null, null),
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(), null, null, null, null, null, null, null)
        ));
    }

    /*public Mono<TeletalkRecharge> buildRechargeListOfCurrentTime() {
        return Mono.just(new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date() ,null, null, null, null, null, null, null));
    }*/


}
