package net.celloscope.bill.mobileRecharge.shared.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorModel {
    public Date timestamp;
    public int status;
    public String error;
    public String message;
    public String path;
}