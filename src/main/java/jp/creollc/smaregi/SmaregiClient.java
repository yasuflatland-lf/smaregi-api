package jp.creollc.smaregi;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.creollc.smaregi.api.ErrorResponse;
import jp.creollc.smaregi.api.UpdRow;
import jp.creollc.smaregi.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.activity.InvalidActivityException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Smaregi Client Implementation
 *
 * @author Yasuyuki Takeo
 */
@Slf4j
public class SmaregiClient {

    public SmaregiClient(String contactId, String accessToken) {

        _smaregiIO = new SmaregiIO(contactId, accessToken);
    }

    /**
     * Execute sending a request
     *
     * @return JSON String returns from the API server
     */
    public SmaregiResponse execute(SmaregiRequest request)
        throws InvalidActivityException, JsonProcessingException, IOException {

        String result
            = _smaregiIO.post(
            request.getUrl(), request.getProcName(), request.getParams());

        SmaregiResponse.SmaregiResponseBuilder builder
            = SmaregiResponse.builder();

        if (!_smaregiIO.isSuccessful()) {

            ErrorResponse errorResponse = null;

            try {
                errorResponse = JsonUtil.toObject(result, ErrorResponse.class);

                StringBuffer sb = new StringBuffer();
                sb
                    .append("Error Code <")
                    .append(errorResponse.getErrorCode())
                    .append("> ")
                    .append(errorResponse.getError())
                    .append(" ")
                    .append(errorResponse.getErrorDescription());

                log.error(sb.toString());

            } catch (IOException e) {

                // When 404 error, Smaregi is not necessarily returning json response.
                // In that case, return empty object instead.
                errorResponse = new ErrorResponse();

                StringBuffer sb = new StringBuffer();
                sb
                    .append("An error occurs while converting JSON string. Wrong credentials or connection failure may be the cause. Returned JSON was <")
                    .append((null == result) ? "null" : result)
                    .append("> ");
                log.error(sb.toString());
            }

            builder.errorResponse(errorResponse).successful(false);
        }

        builder.resultJSON(result);

        return builder.build();
    }

    protected SmaregiIO _smaregiIO;

    /**
     * UpdRow Builder
     *
     * @author Yasuyuki Takeo
     */
    public static class UpdRowBuilder {

        public static UpdRowBuilderBase builder() {
            return new UpdRowBuilderBase();
        }

        public static class UpdRowBuilderBase {
            private List<UpdRow> updRows;

            UpdRowBuilderBase() {
                updRows = new ArrayList<>();
            }

            public UpdRowBuilderBase updRows(List<UpdRow> updRows) {
                this.updRows = updRows;
                return this;
            }

            /**
             * Create rows
             *
             * <p>
             * Create rows from given variable length parameters
             * </p>
             *
             * @param params variable length parameters consist of rows
             * @return this
             */
            public UpdRowBuilderBase createRows(String... params) {

                if (0 != (params.length % 2)) {
                    throw new InvalidParameterException(
                        "Parameter should be even number. total params are <"
                            + String.valueOf(params.length) + ">");
                }

                UpdRow updRow = new UpdRow();
                for (int i = 0; i < params.length - 1; ) {
                    updRow.addRow(params[i], params[i + 1]);
                    i += 2;
                }

                this.updRows.add(updRow);
                return this;
            }

            public List<UpdRow> build() {
                return updRows;
            }

            public String toString() {
                return "UpdRowBuilder.UpdRowBuilderBase(updRows=" + this.updRows + ")";
            }
        }
    }
}
