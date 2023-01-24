package net.celloscope.bill.mobileRecharge.adapter.out.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.celloscope.bill.mobileRecharge.adapter.out.api.dto.TeletalkMobileRechargeRequest;
import net.celloscope.bill.mobileRecharge.adapter.out.api.dto.TeletalkMobileRechargeResponse;
import net.celloscope.bill.mobileRecharge.shared.util.ExceptionHandlerUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.Duration;
import static net.celloscope.bill.mobileRecharge.shared.util.Constants.*;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class TeletalkRestClient {


    private String TELETALK_RECHARGE_BASE_URL;

    WebClient client = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.APPLICATION_XML))
            .defaultHeader(HttpHeaders.ACCEPT, String.valueOf(MediaType.TEXT_HTML))
            .defaultHeader(HttpHeaders.ACCEPT_CHARSET, "UTF-8")
            .build();

    public Mono<TeletalkMobileRechargeResponse> getTeletalkRechargeResponse(TeletalkMobileRechargeRequest request) throws JAXBException {
        log.info("Requesting recharge to url: {}", TELETALK_RECHARGE_BASE_URL);
        String xmlPayload = String.valueOf(convertRequestToXMLString(request));
        log.info("Prepaid Recharge Request Sent to Teletalk: {}", xmlPayload);

        return client.post()
                .uri(TELETALK_RECHARGE_BASE_URL)
                .body(Mono.just(xmlPayload), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(60))
                .doOnError(WebClientRequestException.class, ex -> log.error("{}", ex.getMessage()))
                .doOnError(RuntimeException.class, ex -> log.error("{}", ex.getMessage()))
                .onErrorResume(WebClientRequestException.class, ex -> (Mono<? extends String>) getTeletalkTimeoutResponse())
                .onErrorResume(RuntimeException.class, ex -> (Mono<? extends String>) getTeletalkRuntimeExceptionResponse())
                .map(responseXml -> {
                    log.info("Response Sent from Teletalk: {}", responseXml);
                    responseXml = responseXml.replace("<!DOCTYPE COMMAND PUBLIC \"-//Ocam//DTD XML Command 1.0//EN\" \"xml/command.dtd\">","");
                    TeletalkMobileRechargeResponse response = null;
                    try {
                        response = unmarshalResponse(responseXml).block();
                    } catch (JAXBException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("Unmarshal Response from Teletalk: {}", response);
                    if (response != null && response.getTXNSTATUS() == 408)
                        new ExceptionHandlerUtil(HttpStatus.REQUEST_TIMEOUT, TIME_OUT);
                    else if (response != null && response.getTXNSTATUS() == 500)
                       new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
                    return response;
                });
    }

    private Mono<String> convertRequestToXMLString(TeletalkMobileRechargeRequest request) throws JAXBException {
        StringWriter writer = new StringWriter();
        writer.append("<?xml version=\"1.0\"?>");
        JAXBContext context = JAXBContext.newInstance(TeletalkMobileRechargeRequest.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        return Mono.fromCallable(() -> {
            try {
                marshaller.marshal(request, writer);
                return writer.toString();
            } catch (JAXBException e) {
                //log.error("Marshalling Error: {}", e.getMessage());
                throw new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, REQUEST_MARSHALLING_ERROR);
            }
        }).onErrorMap(JAXBException.class, ex -> new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, REQUEST_MARSHALLING_ERROR));
    }



    private Mono<TeletalkMobileRechargeResponse> unmarshalResponse(String xmlResponse) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TeletalkMobileRechargeResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return Mono.fromCallable(() -> {
            try {
                return (TeletalkMobileRechargeResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));
            } catch (JAXBException e) {
                //log.error("Unmarshall Error: {}", e.getMessage());
                throw new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, RESPONSE_UNMARSHALLING_ERROR);
            }
        }).onErrorMap(JAXBException.class, ex -> new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, RESPONSE_UNMARSHALLING_ERROR));
    }


    private Mono<?> getTeletalkTimeoutResponse() {
        return Mono.just("<?xml version=\"1.0\"?><COMMAND><TXNSTATUS>408</TXNSTATUS></COMMAND>");
    }

    private Mono<?> getTeletalkRuntimeExceptionResponse() {
        return Mono.just("<?xml version=\"1.0\"?><COMMAND><TXNSTATUS>500</TXNSTATUS></COMMAND>");
    }

}
