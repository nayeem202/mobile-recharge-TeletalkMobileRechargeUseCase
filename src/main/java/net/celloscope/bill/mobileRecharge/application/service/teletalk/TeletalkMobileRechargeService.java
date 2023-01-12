package net.celloscope.bill.mobileRecharge.application.service.teletalk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeRequest;
import net.celloscope.bill.mobileRecharge.application.port.in.dto.RechargeResponse;
import net.celloscope.bill.mobileRecharge.application.port.in.teletalk.TeletalkMobileRechargeUseCase;
import net.celloscope.bill.mobileRecharge.application.port.out.SaveTeletalkRechargeInStore;
import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.model.TeletalkTransactionIntermediateStatus;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Slf4j
@Service
@RequiredArgsConstructor
public class TeletalkMobileRechargeService  implements TeletalkMobileRechargeUseCase {

    //private final LoadTeletalkMobileRecharge loadTeletalkMobileRecharge;
    //private final UpdateTeletalkMobileRecharge updateTeletalkMobileRecharge;
    private final TeletalkRecharge teletalkRecharge;
    private final ModelMapper mapper;
    private final SaveTeletalkRechargeInStore saveTeletalkRechargeInstore;

  /*  public TeletalkMobileRechargeService(ModelMapper mapper, TeletalkRecharge teletalkRecharge, SaveTeletalkRechargeInStore saveTeletalkRechargeInstore) {
        this.mapper = mapper;
        this.teletalkRecharge = teletalkRecharge;
        this.saveTeletalkRechargeInstore = saveTeletalkRechargeInstore;
    }*/



    @Override
    public Mono<RechargeResponse> recharge(RechargeRequest command) throws ExceptionHandlerUtil {
        return null;
    }


/*    private BanglalinkRecharge mapRechargeRequestToDomain(RechargeRequest rechargeRequest) {
        BanglalinkRecharge banglalinkRecharge = mapper.map(rechargeRequest, BanglalinkRecharge.class);
        banglalinkRecharge.setIntermediateStatus(buildDefaultBanglalinkIntermediateStatus(true, false, false, false, false, false));
        log.info("Mapped Request Body into Domain {}", banglalinkRecharge);
        return banglalinkRecharge;
    }*/

    private Mono<TeletalkRecharge> mapRechargeRequestToDomain(RechargeRequest rechargeRequest){
        return Mono.just(mapper.map(rechargeRequest, TeletalkRecharge.class));
    }
    private Flux<TeletalkTransactionIntermediateStatus> buildDefaultTeletalkIntermediateStatus(Boolean isRequestReceivedSuccessful, Boolean isRequestReceivedFailed, Boolean isRequestProcessSuccessful, Boolean isRequestProcessFailed, Boolean isRequestProcessPending, Boolean isRequestProcessReconciled) {
        return Flux.just(new TeletalkTransactionIntermediateStatus(isRequestReceivedSuccessful,isRequestReceivedFailed,isRequestProcessFailed,isRequestProcessSuccessful,isRequestProcessPending, isRequestProcessReconciled));
    }

}
