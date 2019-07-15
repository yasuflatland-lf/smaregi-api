package jp.creollc.smaregi.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jp.creollc.smaregi.api.ProcInfo;
import jp.creollc.smaregi.api.UpdData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * UpdParams
 *
 * @author Yasuyuki Takeo
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdParams {
    @JsonProperty(required = true)
    protected ProcInfo proc_info;

    @JsonProperty(required = true)
    protected List<UpdData> data;
}
