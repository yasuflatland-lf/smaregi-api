package jp.creollc.smaregi.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ProductInventoryReservation class
 *
 * <p>
 * ProductInventoryReservation table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductInventoryReservation {
    public String productId;
    public String reservationProductId;
    public String reservationAmount;
}
