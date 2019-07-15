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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ErrorResponse)) return false;
        final ErrorResponse other = (ErrorResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$error_code = this.getErrorCode();
        final Object other$error_code = other.getErrorCode();
        if (this$error_code == null ? other$error_code != null : !this$error_code.equals(other$error_code))
            return false;
        final Object this$error = this.getError();
        final Object other$error = other.getError();
        if (this$error == null ? other$error != null : !this$error.equals(other$error)) return false;
        final Object this$error_description = this.getErrorDescription();
        final Object other$error_description = other.getErrorDescription();
        if (this$error_description == null ? other$error_description != null : !this$error_description.equals(other$error_description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ErrorResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $error_code = this.getErrorCode();
        result = result * PRIME + ($error_code == null ? 43 : $error_code.hashCode());
        final Object $error = this.getError();
        result = result * PRIME + ($error == null ? 43 : $error.hashCode());
        final Object $error_description = this.getErrorDescription();
        result = result * PRIME + ($error_description == null ? 43 : $error_description.hashCode());
        return result;
    }

    public String toString() {
        return "ErrorResponse(error_code=" + this.getErrorCode() + ", error=" + this.getError() + ", error_description=" + this.getErrorDescription() + ")";
    }
}
