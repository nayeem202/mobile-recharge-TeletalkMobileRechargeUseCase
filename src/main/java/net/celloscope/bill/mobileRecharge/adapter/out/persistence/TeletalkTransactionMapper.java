package net.celloscope.bill.mobileRecharge.adapter.out.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.celloscope.bill.mobileRecharge.domain.TeletalkRecharge;
import net.celloscope.bill.mobileRecharge.shared.model.TeletalkTransactionIntermediateStatus;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class TeletalkTransactionMapper {
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public Mono<TeletalkRecharge> mapToDomainEntity(TeletalkMobileRechargeEntity teletalkMobileRechargeEntity) throws ExceptionHandlerUtil {
        modelMapper.typeMap(TeletalkMobileRechargeEntity.class, TeletalkRecharge.class)
                .addMappings(mapper -> mapper.map(TeletalkMobileRechargeEntity::getCreatedBy, TeletalkRecharge::setUserId));
        TeletalkRecharge teletalkRecharge = modelMapper.map(teletalkMobileRechargeEntity, TeletalkRecharge.class);
        try {
            teletalkRecharge.setIntermediateStatus(objectMapper.readValue(teletalkMobileRechargeEntity.getIntermediateStatus(), TeletalkTransactionIntermediateStatus.class));
        } catch (JsonProcessingException e) {
            log.error("Error occurred during converting intermediate status to domain object", e);
            throw new ExceptionHandlerUtil(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
        return Mono.just(teletalkRecharge);
    }


  /*  public Mono<TeletalkMobileRechargeEntity> mapToJpaEntity(Provider<TeletalkMobileRechargeEntity> teletalkMobileRechargeEntityProvider, TeletalkRecharge teletalkRecharge) throws ExceptionHandlerUtil {
         Mono.fromCallable(() -> {
            log.info("banglalinkRecharge mapToJpaEntity - {}", teletalkRecharge);

            modelMapper.typeMap(TeletalkRecharge.class, TeletalkMobileRechargeEntity.class).setProvider(teletalkMobileRechargeEntityProvider);
            TeletalkMobileRechargeEntity teletalkMobileRechargeEntity = modelMapper.map(teletalkRecharge, TeletalkMobileRechargeEntity.class);

            teletalkMobileRechargeEntity.setCreatedOn(teletalkMobileRechargeEntity.getCreatedOn() == null ? Timestamp.valueOf(LocalDateTime.now()) : teletalkMobileRechargeEntity.getCreatedOn());
            teletalkMobileRechargeEntity.setEditedOn(Timestamp.valueOf(LocalDateTime.now()));
            teletalkMobileRechargeEntity.setCreatedBy(teletalkRecharge.getUserId());
            teletalkMobileRechargeEntity.setTeletalkTransactionDate(teletalkRecharge.getTeletalkTransactionDate() ==null ? null: teletalkRecharge.getTeletalkTransactionDate());
            teletalkMobileRechargeEntity.setTeletalkTransactionId(teletalkRecharge.getTelatalkTransactionId());
            teletalkMobileRechargeEntity.setTransStatus(objectMapper.writeValueAsString(teletalkRecharge.getIntermediateStatus()));
            return teletalkRecharge;
            );
        });
    }*/

    public Flux<TeletalkRecharge> mapJpaListToDomainEntityList(List<TeletalkMobileRechargeEntity> teletalkMobileRechargeEntityList) {
        return Flux.fromIterable(teletalkMobileRechargeEntityList)
                .map(banglalinkMobileRechargeEntity -> {
                    TeletalkRecharge teletalkRecharge = new TeletalkRecharge();
                    BeanUtils.copyProperties(banglalinkMobileRechargeEntity, teletalkRecharge);
                    try {
                        teletalkRecharge.setIntermediateStatus(objectMapper.readValue(banglalinkMobileRechargeEntity.getIntermediateStatus(), TeletalkTransactionIntermediateStatus.class));
                    } catch (JsonProcessingException e) {
                        log.error("Error occurred during converting intermediate status to domain object", e);
                        try {
                            throw new ExceptionHandlerUtil(HttpStatus.BAD_REQUEST, "Something went wrong");
                        } catch (ExceptionHandlerUtil ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    return teletalkRecharge;
                });
    }
}
