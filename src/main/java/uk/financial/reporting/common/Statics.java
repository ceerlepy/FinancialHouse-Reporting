package uk.financial.reporting.common;

public interface Statics{
	
	public interface Status {
	
	    public static String APPROVED = "APPROVED";
	    public static String DECLINED = "DECLINED";
	    public static String WAITING = "WAITING";
	    public static String ERROR = "ERROR";
	}
	
	public interface OPERATION {
	
	    public static String DIRECT = "DIRECT";
	    public static String REFUND = "REFUND";
	    public static String THREED = "3D";
	    public static String THREEDAUTH = "3DAUTH";
	    public static String STORED = "STORED";
	}
	
	public interface PAYMENT_METHOD {
	
	    public static String CREDITCARD = "CREDITCARD";
	    public static String CUP = "CUP";
	    public static String IDEAL = "IDEAL";
	    public static String GIROPAY = "GIROPAY";
	    public static String MISTERCASH = "MISTERCASH";
	    public static String STORED = "STORED";
	    public static String PAYTOCARD = "PAYTOCARD";
	    public static String CEPBANK = "CEPBANK";
	    public static String CITADEL = "CITADEL";
	}
	
	public interface ERROR_CODE {
	
	    public static String DO_NOT_HONOUR = "Do​ ​not​ ​honor";
	    public static String INVALID_TRANSACTION = "Invalid ​ ​Transaction";
	    public static String INVALID_CARD = "Invalid​ ​Card";
	    public static String NP_SUFFICIENT_FUNDS = "Not​ ​sufficient​ ​funds";
	    public static String INCORRECT_PIN = "Incorrect​ ​PIN";
	    public static String INVALID_COUNTRY_ASSOCIATION = "Invalid​ ​country​ ​association";
	    public static String CURRECNY_NOT_ALLOWED = "Currency​ ​not​ ​allowed";
	    public static String THREED_SECURE_TRANSPORT_ERROR = "3-D​ ​Secure​ ​Transport​ ​Error";
	    public static String TRANSACTION_NOT_PERMITTED_TO_CARDHOLDER = "Transaction​ ​not permitted​ ​to​ ​cardholder";
	}
	
	public interface FILTER_FILED {
	
	    public static String TRANSACTION_UUID = "Transaction​ ​UUID";
	    public static String CUSTOMER_EMAIL = "Customer​ ​Email";
	    public static String REFERANS_NO = "Reference​ ​No";
	    public static String CUSTOM_DATA = "Custom​ ​Data";
	    public static String CARD_PAN = "Card​ ​PAN";
	}

}