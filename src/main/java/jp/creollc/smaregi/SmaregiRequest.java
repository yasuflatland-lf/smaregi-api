package jp.creollc.smaregi;

import jp.creollc.smaregi.constants.SmaregiConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Smaregi Request
 *
 * @author Yasuyuki Takeo
 */
@Data
@NoArgsConstructor
public class SmaregiRequest {
    protected String url;
    protected String procName;
    protected String params;

    public SmaregiRequest(String url, String procName, String params) {
        this.url = (null == url) ? SmaregiConstants.SMAREGI_API_URI : url;
        this.procName = procName;
        this.params = params;
    }
}
