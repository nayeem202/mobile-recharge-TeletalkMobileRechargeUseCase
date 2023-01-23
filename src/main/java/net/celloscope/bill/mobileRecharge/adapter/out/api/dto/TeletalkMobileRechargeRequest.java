package net.celloscope.bill.mobileRecharge.adapter.out.api.dto;

import lombok.*;

import javax.xml.bind.annotation.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder =
        {"TYPE", "DATE", "EXTNWCODE", "MSISDN", "PIN", "LOGINID", "PASSWORD", "EXTCODE", "EXTREFNUM", "MSISDN2", "AMOUNT", "LANGUAGE1", "LANGUAGE2", "SELECTOR"}
)
public class TeletalkMobileRechargeRequest {
    @XmlElement(name = "TYPE")
    private final String TYPE = "EXPPBREQ";

    private String DATE;

    @XmlElement(name = "EXTNWCODE")
    private final String EXTNWCODE = "BD";

    @XmlElement(name = "MSISDN")
    private final String MSISDN = "1409919136";

    @XmlElement(name = "PIN")
    private final String PIN = "1936";

    @Builder.Default
    private String LOGINID = "";

    @Builder.Default
    private String PASSWORD = "";

    @Builder.Default
    private String EXTCODE = "";

    private String EXTREFNUM;

    private String MSISDN2;

    private Integer AMOUNT;

    @Builder.Default
    private String LANGUAGE1 = "0";

    @Builder.Default
    private String LANGUAGE2 = "0";

    @Builder.Default
    private Integer SELECTOR = 1;
}
