package net.celloscope.bill.mobileRecharge.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class TeletalkRechargeTest {

    @Test
    void test() {
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListOfCurrentTime());
        StepVerifier.create(res)
                .expectNext(true)
                .verifyComplete();

    }

    @Test
    void test2(){
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListForLastOneMinute());
    }



    public Flux<List<TeletalkRecharge>> buildRechargeListOfCurrentTime() {
        List<TeletalkRecharge> rechargeList = List.of(
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(), null, null, null, null, null, null, null),
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(), null, null, null, null, null, null, null)
        );
        return Flux.fromIterable(List.of(rechargeList));
    }

    public Flux<List<TeletalkRecharge>> buildRechargeListForLastOneMinute() {
        List<TeletalkRecharge> rechargeList = List.of(
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(System.currentTimeMillis() - 60 * 1000), null, null, null, null, null, null, null)
        );
        return Flux.fromIterable(List.of(rechargeList));
    }



    }
