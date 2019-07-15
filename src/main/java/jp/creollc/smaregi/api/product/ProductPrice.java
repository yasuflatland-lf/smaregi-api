package jp.creollc.smaregi.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ProductPrice class
 *
 * <p>
 * ProductPrice table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductPrice {
    public String productId;
    public String storeId;
    public String priceDivision;
    public String startDate;
    public String endDate;
    public String price;
}
