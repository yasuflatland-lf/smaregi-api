package jp.creollc.smaregi.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error Response
 *
 * @author Yasuyuki Takeo
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    public String error_code;
    public String error;
    public String error_description;

    public ErrorResponse() {
    }

    @JsonProperty("error_code")
    public String getErrorCode() {
        return this.error_code;
    }

    @JsonProperty("error")
    public String getError() {
        return this.error;
    }

    @JsonProperty("error_description")
    public String getErrorDescription() {
        return this.error_description;
    }

    @JsonProperty("error_code")
    public void setErrorCode(String error_code) {
        this.error_code = error_code;
    }

    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
    }

    @JsonProperty("error_description")
    public void setErrorDescription(String error_description) {
        this.error_description = error_description;
    }

    public String toString() {
        return "ErrorResponse(error_code=" + this.getErrorCode() + ", error=" + this.getError() + ", error_description=" + this.getErrorDescription() + ")";
    }
}
