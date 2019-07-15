package jp.creollc.smaregi.api.stock;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * StockHistory class
 *
 * <p>
 * StockHistory table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StockHistory {
    public String id;
    public String updDateTime;
    public String targetDateTime;
    public String productId;
    public String storeId;
    public String amount;
    public String stockAmount;
    public String stockDivision;
    public String fromStoreId;
    public String toStoreId;
}
