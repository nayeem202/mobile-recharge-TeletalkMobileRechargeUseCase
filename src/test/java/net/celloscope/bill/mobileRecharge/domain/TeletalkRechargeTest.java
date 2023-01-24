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
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListOfCurrentTime());
        StepVerifier.create(res)
                .expectNext(false)
                .verifyComplete();

    }

    @Test
    void test2(){
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListForLastOneMinute());
        StepVerifier.create(res)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void test3(){
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListForLastOneSecond());
        StepVerifier.create(res)
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void test4(){
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListForLastOnePointTwoSecond());
        StepVerifier.create(res)
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void test5(){
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListForLastOnePointThreeSecond());
        StepVerifier.create(res)
                .expectNext(false)
                .verifyComplete();
    }


    @Test
    void test6(){
        TeletalkRecharge tl = new TeletalkRecharge();
        Mono<Boolean> res = tl.isSameRequestWithinTimeBound(buildRechargeListForLastFiveSecond());
        StepVerifier.create(res)
                .expectNext(false)
                .verifyComplete();
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

    public Flux<List<TeletalkRecharge>> buildRechargeListForLastOneSecond(){
        List<TeletalkRecharge> rechargeList = List.of(
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(System.currentTimeMillis() - 1000), null, null, null, null, null, null, null)
        );
        return Flux.fromIterable(List.of(rechargeList));
    }

    public Flux<List<TeletalkRecharge>> buildRechargeListForLastOnePointTwoSecond(){
        List<TeletalkRecharge> rechargeList = List.of(
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(System.currentTimeMillis() - 1200), null, null, null, null, null, null, null)
        );
        return Flux.fromIterable(List.of(rechargeList));
    }

    public Flux<List<TeletalkRecharge>> buildRechargeListForLastOnePointThreeSecond(){
        List<TeletalkRecharge> rechargeList = List.of(
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(System.currentTimeMillis() - 1300), null, null, null, null, null, null, null)
        );
        return Flux.fromIterable(List.of(rechargeList));
    }

    public Flux<List<TeletalkRecharge>> buildRechargeListForLastFiveSecond(){
        List<TeletalkRecharge> rechargeList = List.of(
                new TeletalkRecharge(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Date(System.currentTimeMillis() - 5000), null, null, null, null, null, null, null)
        );
        return Flux.fromIterable(List.of(rechargeList));
    }




}
