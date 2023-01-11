package net.celloscope.bill.mobileRecharge.application.port.in.teletalk;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeletalkMobileRechargeUseCaseTest {
    private TeletalkMobileRechargeUseCase teletalkMobileRechargeUseCase;

    @BeforeAll
    void setUp(){
       teletalkMobileRechargeUseCase =  Mockito.mock(TeletalkMobileRechargeUseCase.class);
    }

    @Test
    void contextLoading(){
        Assertions.assertNotNull(TeletalkMobileRechargeUseCase.class);
        Assertions.assertNotNull(teletalkMobileRechargeUseCase);
    }

    @Test
    void recharge() {
    }
}