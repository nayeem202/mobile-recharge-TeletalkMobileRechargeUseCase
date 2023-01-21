package net.celloscope.bill.mobileRecharge.adapter.out.persistence;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/*
@Repository
public interface TeletalkMobileRechargeRepository extends JpaRepository<BanglalinkMobileRechargeEntity,Object> {
    List<BanglalinkMobileRechargeEntity> findByMobileNoAndAmount(String mobileNo, Double robiRechargeAmount);
    @NotFound(action = NotFoundAction.IGNORE)
    BanglalinkMobileRechargeEntity findByOriginatorConversationId(String originatorConversationId);
}
*/

@Repository
public interface TeletalkMobileRechargeRepository extends ReactiveCrudRepository<TeletalkMobileRechargeEntity,Object> {
    Flux<TeletalkMobileRechargeEntity> findByMobileNoAndAmount(String mobileNo, Double teletalkRechargeAmount);
    @NotFound(action = NotFoundAction.IGNORE)
    Mono<TeletalkMobileRechargeEntity> findByOriginatorConversationId(String originatorConversationId);
}
