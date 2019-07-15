package jp.creollc.smaregi.api.stock.constants;

/**
 * Stock Constants
 *
 * <p>
 *     Constants for Stock class reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
public class StockConstants {
    public static final String TABLE_NAME = "Stock";
    // Smaregi API stock division default. Smaregi recomend to set 15, Smaregi API integration
    public static final String STOCK_DIVISION_DEFAULT = "15";

    public static final String STORE_ID = "storeId";
    public static final String PRODUCT_ID = "productId";
    public static final String STOCK_AMOUNT = "stockAmount";
    public static final String STOCK_DIVISION = "stockDivision";
    public static final String FROM_STORE_ID = "fromStoreId";
    public static final String TO_STORE_ID = "toStoreId";
    public static final String MEMO = "memo";
}
