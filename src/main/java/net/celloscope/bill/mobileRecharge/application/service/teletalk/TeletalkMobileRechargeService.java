/*
package net.celloscope.bill.mobileRecharge.application.service.teletalk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeRequest;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeResponse;
import net.celloscope.bill.mobileRecharge.application.port.in.teletalk.TeletalkMobileRechargeUseCase;
import net.celloscope.bill.mobileRecharge.application.port.out.LoadTeletalkMobileRecharge;
import net.celloscope.bill.mobileRecharge.application.port.out.SaveTeletalkRechargeInStore;
import net.celloscope.bill.mobileRecharge.application.port.out.TeletalkMobileRecharge;
import net.celloscope.bill.mobileRecharge.application.port.out.UpdateTeletalkMobileRecharge;
import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.ConnectionType;
import net.celloscope.bill.mobileRecharge.shared.model.TeletalkTransactionIntermediateStatus;
import net.celloscope.bill.mobileRecharge.shared.util.Amount;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static net.celloscope.bill.mobileRecharge.shared.util.Constants.*;


@RequiredArgsConstructor
@Slf4j
@Service
public class TeletalkMobileRechargeService  implements TeletalkMobileRechargeUseCase {

    //private final LoadTeletalkMobileRecharge loadTeletalkMobileRecharge;
    //private final UpdateTeletalkMobileRecharge updateTeletalkMobileRecharge;

    private final ModelMapper mapper;
    private final SaveTeletalkRechargeInStore saveTeletalkRechargeInstore;
    private final TeletalkMobileRecharge teletalkMobileRecharge;
    private final UpdateTeletalkMobileRecharge updateTeletalkMobileRecharge;
    private final LoadTeletalkMobileRecharge loadTeletalkMobileRecharge;


  */
/*  public TeletalkMobileRechargeService(ModelMapper mapper, TeletalkRecharge teletalkRecharge, SaveTeletalkRechargeInStore saveTeletalkRechargeInstore, TeletalkMobileRecharge teletalkMobileRecharge) {
        this.mapper = mapper;
        this.teletalkRecharge = teletalkRecharge;
        this.saveTeletalkRechargeInstore = saveTeletalkRechargeInstore;
        this.teletalkMobileRecharge = teletalkMobileRecharge;
    }*//*




*/
/*    @Override
    public Mono<RechargeResponse> recharge(RechargeRequest request) throws ExceptionHandlerUtil {
        log.info("Request Received for Banglalink Mobile Recharge");
        TeletalkRecharge teletalkRecharge = mapRechargeRequestToDomain(request).block();
        teletalkRecharge.setTransStatus(REQUEST_RECEIVED);

        return saveTeletalkRechargeInstore.save(teletalkRecharge)
                .flatMap(saveTeletalkRechargeRequest -> {
                    if (!checkRequestAmountValidation(saveTeletalkRechargeRequest.getConnectionType(), saveTeletalkRechargeRequest.getAmount())) {
                        saveTeletalkRechargeRequest.setTransStatus(PRE_PROCESS_FAILED);
                        try {
                            return updateTeletalkMobileRecharge.updateState(saveTeletalkRechargeRequest)
                                    .then(Mono.error(new ExceptionHandlerUtil(HttpStatus.BAD_REQUEST, "Invalid Request Amount.")));
                        } catch (ExceptionHandlerUtil e) {
                            System.out.println(e.getLocalizedMessage());
                        }
                    }
                    try {
                        if (!teletalkRecharge.isSameRequestWithinTimeBound(
                                teletalkRechargeTransactionListByMobileNoAndAmount(teletalkRecharge.getMobileNo(), teletalkRecharge.getAmount()).block())) {
                            log.error("Request received within 0.02 minute for requestId: {}", teletalkRecharge.getOriginatorConversationId());
                            saveTeletalkRechargeRequest.setTransStatus(PRE_PROCESS_FAILED);
                            return updateTeletalkMobileRecharge.updateState(saveTeletalkRechargeRequest)
                                    .then(Mono.error(new ExceptionHandlerUtil(HttpStatus.TOO_MANY_REQUESTS, "Request Limit Exceeded.")));
                        }
                    } catch (ExceptionHandlerUtil e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                    log.info("Recharge amount and Time validation successful for teletalk mobile recharge");
                    saveTeletalkRechargeRequest.setTransStatus(PRE_PROCESS);
                    log.info("Pre-processing stage is saving for Teletalk Mobile Recharge");
                    try {
                        return updateTeletalkMobileRecharge.updateState(saveTeletalkRechargeRequest)
                                .flatMap(saveMobileRechargeTransactionPreProcessSate -> makeRechargeCall(teletalkRecharge)
                                        .flatMap(response -> checkResponseStatusUpdateDomain(response, saveMobileRechargeTransactionPreProcessSate)
                                                .flatMap(updatedRechargeResponseStateUpdatedIntoDomain -> {
                                                    log.info("updatedRechargeResponseStateUpdatedIntoDomain - {}", updatedRechargeResponseStateUpdatedIntoDomain);
                                                    Mono<RechargeResponse> rechargeResponseMono = null;
                                                    try {
                                                        rechargeResponseMono = updateTeletalkMobileRecharge.updateState(updatedRechargeResponseStateUpdatedIntoDomain)
                                                                .thenReturn(response);
                                                    } catch (ExceptionHandlerUtil e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                    return rechargeResponseMono;
                                                })));
                    } catch (ExceptionHandlerUtil e) {
                       System.out.println(e.getLocalizedMessage());
                    }
                    return response;
                });
    }*//*



@Override
public Mono<RechargeResponse> recharge(RechargeRequest request) throws ExceptionHandlerUtil {
    log.info("Request Received for Banglalink Mobile Recharge");
    TeletalkRecharge teletalkRecharge = mapRechargeRequestToDomain(request).block();
    teletalkRecharge.setTransStatus(REQUEST_RECEIVED);
    return saveTeletalkRechargeInstore.save(teletalkRecharge)
            .flatMap(saveTeletalkRechargeRequest -> {
                if (!checkRequestAmountValidation(teletalkRecharge.getConnectionType(), teletalkRecharge.getAmount())) {
                    saveTeletalkRechargeRequest.setTransStatus(PRE_PROCESS_FAILED);
                    try {
                        return updateTeletalkMobileRecharge.updateState(saveTeletalkRechargeRequest)
                                .then(Mono.error(new ExceptionHandlerUtil(HttpStatus.BAD_REQUEST, "Invalid Request Amount.")));
                    } catch (ExceptionHandlerUtil e) {
                        System.out.println(e.getCode());

                    }
                }
                try {
                    if (!teletalkRecharge.isSameRequestWithinTimeBound(
                            teletalkRechargeTransactionListByMobileNoAndAmount(teletalkRecharge.getMobileNo(), teletalkRecharge.getAmount()).block())) {
                        log.error("Request received within 0.02 minute for requestId: {}", teletalkRecharge.getOriginatorConversationId());
                        saveTeletalkRechargeRequest.setTransStatus(PRE_PROCESS_FAILED);
                        try {
                            return updateTeletalkMobileRecharge.updateState(saveTeletalkRechargeRequest)
                                    .then(Mono.error(new ExceptionHandlerUtil(HttpStatus.TOO_MANY_REQUESTS, "Request Limit Exceeded.")));
                        } catch (ExceptionHandlerUtil e) {
                            return reactor.core.publisher.Flux.error(new RuntimeException(e));
                        }
                    }
                } catch (ExceptionHandlerUtil e) {
                    return reactor.core.publisher.Flux.error(new RuntimeException(e));
                }
                log.info("Recharge amount and Time validation successful for Banglalink mobile recharge");
                saveTeletalkRechargeRequest.setTransStatus(PRE_PROCESS);
                log.info("Pre-processing stage is saving for Banglalink Mobile Recharge");
                try {
                    return updateTeletalkMobileRecharge.updateState(saveTeletalkRechargeRequest)
                            .flatMap(saveMobileRechargeTransactionPreProcessSate -> makeRechargeCall(teletalkRecharge)
                                    .flatMap(response -> checkResponseStatusUpdateDomain(response, saveMobileRechargeTransactionPreProcessSate))
                                    .doOnSuccess(updatedRechargeResponseStateUpdatedIntoDomain -> {
                                        log.info("updatedRechargeResponseStateUpdatedIntoDomain - {}", updatedRechargeResponseStateUpdatedIntoDomain);
                                        try {
                                            updateTeletalkMobileRecharge.updateState(updatedRechargeResponseStateUpdatedIntoDomain);
                                        } catch (ExceptionHandlerUtil e) {
                                            throw new RuntimeException(e);
                                        }
                                    }));
                } catch (ExceptionHandlerUtil e) {
                    return reactor.core.publisher.Flux.error(new RuntimeException(e));
                }
            });
}




    private Mono<TeletalkRecharge> mapRechargeRequestToDomain(RechargeRequest rechargeRequest) {
        return Mono.fromCallable(() -> {
            TeletalkRecharge banglalinkRecharge = mapper.map(rechargeRequest, TeletalkRecharge.class);
            banglalinkRecharge.setIntermediateStatus(buildDefaultTeletalkIntermediateStatus(true, false, false, false, false, false).block());
            log.info("Mapped Request Body into Domain {}", banglalinkRecharge);
            return banglalinkRecharge;
        });
    }

    private Mono<TeletalkTransactionIntermediateStatus> buildDefaultTeletalkIntermediateStatus(Boolean isRequestReceivedSuccessful, Boolean isRequestReceivedFailed, Boolean isRequestProcessSuccessful, Boolean isRequestProcessFailed, Boolean isRequestProcessPending, Boolean isRequestProcessReconciled) {
        return Mono.fromCallable(() -> new TeletalkTransactionIntermediateStatus(isRequestReceivedSuccessful, isRequestReceivedFailed, isRequestProcessSuccessful, isRequestProcessFailed, isRequestProcessPending, isRequestProcessReconciled));
    }

    //************************    Check Request Amount Validation   *********************************
    private boolean checkRequestAmountValidation(ConnectionType connectionType, Integer amount) {
        if (connectionType.equals(ConnectionType.PREPAID)) {
            return amount >= Integer.parseInt(Amount.TELETALK_PREPAID_MINIMUM.getValue())
                    && amount <= Integer.parseInt(Amount.TELETALK_PREPAID_MAXIMUM.getValue());
        }
        return amount >= Integer.parseInt(Amount.TELETALK_POSTPAID_MINIMUM.getValue())
                && amount <= Integer.parseInt(Amount.TELETALK_POSTPAID_MAXIMUM.getValue());
    }



    private Mono<List<TeletalkRecharge>> teletalkRechargeTransactionListByMobileNoAndAmount(String mobileNo, Integer amount) throws ExceptionHandlerUtil {
        log.info("Getting MobileRecharge data for mobile no: {}, amount: {}", mobileNo, amount);
        return loadTeletalkMobileRecharge.findByMobileNoAndAmount(mobileNo, Double.valueOf(amount));
    }

    private Mono<RechargeResponse> makeRechargeCall(TeletalkRecharge teletalkRecharge) {
        log.info("Request Operator and Connection Type: {}, {}", teletalkRecharge.getOperator(), teletalkRecharge.getConnectionType());
        return Mono.fromCallable(() -> teletalkMobileRecharge.makeRequest(teletalkRecharge))
                .onErrorMap(e -> new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())).block();
    }

    private Mono<TeletalkRecharge> checkResponseStatusUpdateDomain(Mono<RechargeResponse> responseMono, TeletalkRecharge teletalkRecharge) {
        return responseMono.flatMap(response -> {
            log.info("Checking teletalk mobile recharge response {}", response);
            if (response.getStatus().equalsIgnoreCase(OK)) {
                teletalkRecharge.setTransStatus(OK);
                teletalkRecharge.setTelatalkTransactionId(response.getTransactionId());
                teletalkRecharge.setTeletalkTransactionDate((Timestamp.valueOf(LocalDateTime.now())));
                teletalkRecharge.getIntermediateStatus().setIsRequestProcessSuccessful(true);
            } else {
                teletalkRecharge.setTransStatus(FAILED);
                teletalkRecharge.getIntermediateStatus().setIsRequestProcessFailed(true);
                teletalkRecharge.setErrorCode(response.getErrorCode());
                teletalkRecharge.setErrorMessage(response.getErrorMessage());
            }
            log.info("Checking Mapped BanglalinkMobileRechargeResponse into RechargeResponse{}", teletalkRecharge);
            return Mono.just(teletalkRecharge);
        });
    }






}
*/
