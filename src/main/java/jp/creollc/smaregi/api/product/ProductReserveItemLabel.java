package jp.creollc.smaregi.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ProductReserveItemLabel class
 *
 * <p>
 * ProductReserveItemLabel table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductReserveItemLabel {
    public String no;
    public String label;

}
