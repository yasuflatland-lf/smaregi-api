package jp.creollc.smaregi.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ProductStore class
 *
 * <p>
 * ProductStore table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductStore {
    public String productId;
    public String storeId;
    public String assignDivision;
}
