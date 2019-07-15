package jp.creollc.smaregi.api.bargain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Bargain class
 *
 * <p>
 * Bargain table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Bargain {
    public String bargainId;
    public String bargainName;
    public String termStart;
    public String termEnd;
}
