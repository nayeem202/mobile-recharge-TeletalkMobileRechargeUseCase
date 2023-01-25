package net.celloscope.bill.mobileRecharge.adapter.out.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.celloscope.bill.mobileRecharge.adapter.out.api.dto.TeletalkMobileRechargeRequest;
import net.celloscope.bill.mobileRecharge.adapter.out.api.dto.TeletalkMobileRechargeResponse;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeResponse;
import net.celloscope.bill.mobileRecharge.application.port.out.TeletalkMobileRecharge;
import net.celloscope.bill.mobileRecharge.domain.MobileRecharge;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static net.celloscope.bill.mobileRecharge.shared.util.Constants.FAILED;
import static net.celloscope.bill.mobileRecharge.shared.util.Constants.OK;

@RequiredArgsConstructor
@Slf4j
@Component

public class TeletalkMobileRechargeAdapter implements TeletalkMobileRecharge {

    private final TeletalkRestClient teletalkRestClient;
    @Override
    public Mono<RechargeResponse> makeRequest(MobileRecharge mobileRecharge) {
        return buildRechargeRequest(mobileRecharge)
                .flatMap(teletalkRestClient::getTeletalkRechargeResponse)
                .flatMap(this::buildRechargeResponse);
    }


 /*   @Override
    public Mono<RechargeResponse> makeRequest(MobileRecharge mobilerecharge)  {
        return buildRechargeRequest(mobilerecharge)
                .flatMap(teletalkRestClient::getTeletalkRechargeResponse)
                .flatMap(this::buildRechargeResponse);
    }*/


    public Mono<TeletalkMobileRechargeRequest> buildRechargeRequest(MobileRecharge mobileRecharge) {
        return convertDateTime().map(date ->
                TeletalkMobileRechargeRequest.builder()
                        .MSISDN2(mobileRecharge.getMobileNo())
                        .AMOUNT(mobileRecharge.getAmount() * 100)
                        .DATE(date)
                        .EXTREFNUM(mobileRecharge.getTraceId())
                        .build()
        );
    }

    private Mono<String> convertDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return Mono.just(LocalDateTime.now().format(formatter));
    }

    Mono<RechargeResponse> buildRechargeResponse(TeletalkMobileRechargeResponse response) {
        log.info("Transaction Status: {}", response.getTXNSTATUS());
        RechargeResponse banglalinkResponse = RechargeResponse.builder()
                .status(OK)
                .userMessage(response.getMESSAGE())
                .reference(response.getEXTREFNUM())
                .transactionId(response.getTXNID())
                .build();

        if (!String.valueOf(response.getTXNSTATUS()).equals("200")) {
            banglalinkResponse.setStatus(FAILED);
            banglalinkResponse.setErrorCode(String.valueOf(response.getTXNSTATUS()));
            banglalinkResponse.setErrorMessage(response.getMESSAGE());
        }
        return Mono.just(banglalinkResponse);
    }



}


/*
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





    Mono<String> convertDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return Mono.just(LocalDateTime.now().format(formatter));
    }


    */
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
    }*//*

   Mono<RechargeResponse> buildRechargeResponse(TeletalkMobileRechargeResponse response) {
       log.info("Transaction Status: {}", response.getTXNSTATUS());
       RechargeResponse teletalkResponse = RechargeResponse.builder()
               .status(OK)
               .userMessage(response.getMESSAGE())
               .reference(response.getEXTREFNUM())
               .transactionId(response.getTXNID())
               .build();

       if (!String.valueOf(response.getTXNSTATUS()).equals("200")) {
           teletalkResponse.setStatus(FAILED);
           teletalkResponse.setErrorCode(String.valueOf(response.getTXNSTATUS()));
           teletalkResponse.setErrorMessage(response.getMESSAGE());
       }
       return Mono.just(teletalkResponse);
   }




}
*/
