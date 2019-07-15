package jp.creollc.smaregi.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * RefParams
 *
 * @author Yasuyuki Takeo
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RefParams {
    protected List<String> fields;

    @JsonProperty(required = true)
    protected List<Map<String, String>> conditions;

    protected List<String> order;

    protected long limit;

    protected long page;

    @JsonProperty(required = true)
    protected String table_name;
}
