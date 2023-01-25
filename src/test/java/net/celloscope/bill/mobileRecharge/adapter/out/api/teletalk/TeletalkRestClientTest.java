
package net.celloscope.bill.mobileRecharge.adapter.out.api.teletalk;

import net.celloscope.bill.mobileRecharge.adapter.out.api.TeletalkRestClient;
import net.celloscope.bill.mobileRecharge.adapter.out.api.dto.TeletalkMobileRechargeResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.xml.bind.JAXBException;

@ExtendWith(SpringExtension.class)
class TeletalkRestClientTest {

    @Test
    void givenStringInXMLFormat_whenUnmarshalResponseIsCalled_thenItShouldReturnMappedPOJO()

    {
        String xmlStringResponseFoundInAPITest = "<?xml version=\"1.0\"?>\n" +
                "<!DOCTYPE COMMAND PUBLIC \"-//Ocam//DTD XML Command 1.0//EN\" \"xml/command.dtd\">\n" +
                "<COMMAND>\n" +
                "\t<TYPE>EXTPPBRESP</TYPE>\n" +
                "\t<TXNSTATUS>200</TXNSTATUS>\n" +
                "\t<DATE>25/09/2022 13:19:30</DATE>\n" +
                "\t<EXTREFNUM></EXTREFNUM>\n" +
                "\t<TXNID>P220925.1319.210001</TXNID>\n" +
                "\t<MESSAGE>U:Recharge Request of TK 20 for mobile no 1902798185, transaction ID P220925.1319.210001 is successful. your account balance is TK 5653.</MESSAGE>\n" +
                "</COMMAND>";

        String xmlStringResponseFoundInAPIDoc = "<?xml version=\"1.0\"?>\n" +
                "<COMMAND>\n" +
                "\t<TYPE>EXTPPBRESP</TYPE>\n" +
                "\t<TXNSTATUS>200</TXNSTATUS>\n" +
                "\t<DATE>25/09/2022 13:19:30</DATE>\n" +
                "\t<EXTREFNUM></EXTREFNUM>\n" +
                "\t<TXNID>P220925.1319.210001</TXNID>\n" +
                "\t<MESSAGE>U:Recharge Request of TK 20 for mobile no 1902798185, transaction ID P220925.1319.210001 is successful. your account balance is TK 5653.</MESSAGE>\n" +
                "</COMMAND>";
        //when

        TeletalkRestClient restClient = new TeletalkRestClient();

        Mono<TeletalkMobileRechargeResponse> teletalkMobileRechargeResponseMono;

        {
            try {
                teletalkMobileRechargeResponseMono = restClient.unmarshalResponse(xmlStringResponseFoundInAPIDoc);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
            StepVerifier.create(teletalkMobileRechargeResponseMono)
                    .expectNextMatches(response -> response != null)
                    .verifyComplete();
        }
    }



}
