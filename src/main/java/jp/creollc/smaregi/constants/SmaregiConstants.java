package jp.creollc.smaregi.constants;

/**
 * Constants for ClientBase APIs
 *
 * @author Yasuyuki Takeo
 */
public class SmaregiConstants {
    public static final int INCLUDE_LIMIT = 1000;
    public static final int UPDATE_LIMIT = 500;

    // Search Order
    public static final String SEARCH_ORDER_DESC = "desc";
    public static final String SEARCH_ORDER_ASC = "asc";

    public static final String DEFAULT_REQUEST_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String DEFAULT_RESPONSE_CONTENT_TYPE = "application/json; charset=UTF-8";

    public static final String SMAREGI_API_URI = "https://webapi.smaregi.jp/access/";

    public static final String X_CONTACT_ID = "X_contract_id";
    public static final String X_ACCESS_TOKEN = "X_access_token";
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String PROC_NAME = "proc_name";
    public static final String PARAMS = "params";

    // Update Command
    public static final String CMD_CATEGORY_UPD = "category_upd";
    public static final String CMD_PRODUCT_UPD = "product_upd";
    public static final String CMD_CUSTOMER_UPD = "customer_upd";
    public static final String CMD_STOCK_UPD = "stock_upd";
    public static final String CMD_TRANSACTION_UPD = "transaction_upd";
    public static final String CMD_BARGAIN_UPD = "bargain_upd";
    public static final String CMD_POINT_UPD = "point_upd";

    // Reference Command
    public static final String CMD_CATEGORY_REF = "category_ref";
    public static final String CMD_PRODUCT_REF = "product_ref";
    public static final String CMD_CUSTOMER_REF = "customer_ref";
    public static final String CMD_STOCK_REF = "stock_ref";
    public static final String CMD_TRANSACTION_REF = "transaction_ref";
    public static final String CMD_STORE_REF = "store_ref";
    public static final String CMD_DAILY_SUM_REF = "daily_sum_ref";
    public static final String CMD_BARGAIN_REF = "bargain_ref";

    // Process Type
    public static final String PROCESS_UPDATE = "U";
    public static final String PROCESS_DELETE = "D";

    // Process Division
    public static final String PROCESS_DIVISION_ABSOLUTE = "1";
    public static final String PROCESS_DIVISION_RELATIVE = "2";

}
