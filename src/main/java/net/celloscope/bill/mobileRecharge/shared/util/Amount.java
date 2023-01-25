package net.celloscope.bill.mobileRecharge.shared.util;

import lombok.Getter;

@Getter
public enum Amount {
    MINIMUM("20"),
    MAXIMUM("1000"),

    TELETALK_PREPAID_MINIMUM("20"),
    TELETALK_PREPAID_MAXIMUM("1000"),
    TELETALK_POSTPAID_MINIMUM("20"),
    TELETALK_POSTPAID_MAXIMUM("100000");

    private final String value;

    Amount(String value) {
        this.value = value;
    }
}
