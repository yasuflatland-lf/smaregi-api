package jp.creollc.smaregi.api.bargain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * BargainProduct class
 *
 * <p>
 * BargainProduct table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BargainProduct {
    public String bargainProductId;
    public String bargainId;
    public String targetDivision;
    public String targetId;
    public String division;
    public String value;
}
