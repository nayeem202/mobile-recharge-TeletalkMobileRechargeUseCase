package net.celloscope.bill.mobileRecharge.shared.util;

public class Constants {
    public static final String OK = "OK";
    public static final String FAILED = "Failed";
    public static final String PRE_PROCESS = "PreProcess";
    public static final String PRE_PROCESS_FAILED = "PreProcessFailed";
    public static final String REQUEST_RECEIVED = "RequestReceived";
    public static final String PENDING = "Pending";
    public static final String WAITING_FOR_RECONCILE = "WaitingForReconcile";
    public static final Integer MINIMUM_ALLOWED_AMOUNT = 10;
    public static final Integer MAXIMUM_ALLOWED_AMOUNT = 1000;
    public static final int MAXIMUM_ALLOWED_TIME_FOR_REQUEST = 5;
    public static final float TELETALK_MAXIMUM_ALLOWED_TIME_FOR_REQUEST = 0.02f;
    public static final String TELETALK_SERVICE_UNAVAILABLE = "Teletalk service is unavailable";
    public static final String REQUEST_QUEUED = "Teletalk request is pending";
    public static final String TELETALK_SERVICE_RESPONSE_NOT_FOUND = "Response unavailable from Robi service";
    public static final String RECONCILE_MESSAGE = "Telatalk request is waiting for reconcile";
    public static final String TELETALK_PROCESS_UNSECCESSFUL = "Sorry, recharge to teletalk account was unsuccessful";
    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String TIME_OUT = "Time out occurred";
    public static final String DATA_NOT_FOUND = "No Data found";

/*    public static final String RESPONSE_NOT_FOUND_FROM_BKB = "Response not found from bkb api";*/
    public static final String REQUEST_MARSHALLING_ERROR = "Recharge request fails to serialize";
    public static final String RESPONSE_UNMARSHALLING_ERROR = "Recharge response fails to deserialize";
}
