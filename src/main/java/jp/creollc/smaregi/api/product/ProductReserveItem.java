package jp.creollc.smaregi.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ProductReserveItem class
 *
 * <p>
 * ProductPrice table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductReserveItem {
    public String productId;
    public String no;
    public String value;
}
