package jp.creollc.smaregi;

import jp.creollc.smaregi.api.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmaregiResponse {
    protected String resultJSON;
    protected ErrorResponse errorResponse;
    @Builder.Default
    protected boolean successful = true;
}
