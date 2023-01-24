package net.celloscope.bill.mobileRecharge.adapter.out.api;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.celloscope.bill.mobileRecharge.adapter.out.api.dto.TeletalkMobileRechargeRequest;
import net.celloscope.bill.mobileRecharge.adapter.out.api.dto.TeletalkMobileRechargeResponse;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeResponse;
import net.celloscope.bill.mobileRecharge.application.port.out.TeletalkMobileRecharge;
import net.celloscope.bill.mobileRecharge.domain.MobileRecharge;
import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
@Component

public class TeletalkMobileRechargeAdapter implements TeletalkMobileRecharge {
    private final TeletalkRestClient teletalkRestClient;

    @Override
    public Mono<RechargeResponse> makeRequest(MobileRecharge recharge) throws ExceptionHandlerUtil {
        return null;
    }


    public Mono<TeletalkMobileRechargeRequest> buildRechargeRequest(MobileRecharge mobileRecharge){
       return Mono.just((
                TeletalkMobileRechargeRequest.builder()
                        .MSISDN2(mobileRecharge.getMobileNo())
                        .AMOUNT(mobileRecharge.getAmount() * 100)
                        .DATE(convertDateTime())
                        .EXTREFNUM(mobileRecharge.getTraceId())
                        .build()
        ));
    }


    private String convertDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

   /* RechargeResponse buildRechargeResponse(BanglalinkMobileRechargeResponse response) {
        log.info("Transaction Status: {}", response.getTXNSTATUS());
        RechargeResponse banglalinkResponse = RechargeResponse.builder()
                .status(OK)
                .userMessage(response.getMESSAGE())
                .reference(response.getEXTREFNUM())
                .transactionId(response.getTXNID())
//                .transactionDate(Timestamp.valueOf(response.getDATE().toString()))
                .build();

        if (!String.valueOf(response.getTXNSTATUS()).equals("200")) {
            banglalinkResponse.setStatus(FAILED);
            banglalinkResponse.setErrorCode(String.valueOf(response.getTXNSTATUS()));
            banglalinkResponse.setErrorMessage(response.getMESSAGE());
        }
        return banglalinkResponse;
    }*/
    public Mono<RechargeResponse> buildRechargeResponse(TeletalkMobileRechargeResponse response){
        log.info("Transaction status: {}", response.getTXNSTATUS());
        Mono.justOrEmpty(
                RechargeResponse teletalkResponse = RechargeResponse.builder()
                        .status(Ok)
                        .userMessage(response.getMESSAGE())
                        .reference(response.getEXTREFNUM())
                        .transactionId(response.getTXNID())
                        .transactionDate(Timestamp.valueOf(response.getDATE().toString())).build());
            )
    }



}
