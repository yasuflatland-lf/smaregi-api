package jp.creollc.smaregi.api.stock;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Stock class
 *
 * <p>
 * Stock table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Stock {
    public String storeId;
    public String productId;
    public String stockAmount;
    public String stockDivision;
    public String fromStoreId;
    public String toStoreId;
    public String memo;
}
