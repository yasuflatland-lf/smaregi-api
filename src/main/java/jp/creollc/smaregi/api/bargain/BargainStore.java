package jp.creollc.smaregi.api.bargain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * BargainStore class
 *
 * <p>
 * BargainStore table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BargainStore {
    public String bargainStoreId;
    public String bargainId;
    public String storeId;
}
