package net.celloscope.bill.mobileRecharge.adapter.out.api.teletalk;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@Slf4j
class TeletalkMobileRechargeAdapterTest {
    @Test
    void givenDateTimeFormatterSample_shouldReturnDateTimeInThatSpecificFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        //when
        Mono<String> formattedLocalDateTimeMono = Mono.fromCallable(() -> LocalDateTime.now().format(dateTimeFormatter));
        //then
        Mono<String> targetDateTimeMono = Mono.fromCallable(() -> LocalDateTime.now().format(dateTimeFormatter));
        StepVerifier.create(formattedLocalDateTimeMono.zipWith(targetDateTimeMono, (formatted, target) -> {
                   //log.info("formatted {} || targeted {}", formatted, target);
                    log.info("formatted target");
                    return formatted.equals(target);
                }))
                .expectNext(true)
                .verifyComplete();
    }
}