package jp.creollc.smaregi;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.creollc.smaregi.api.ProcInfo;
import jp.creollc.smaregi.api.UpdData;
import jp.creollc.smaregi.api.UpdParams;
import jp.creollc.smaregi.constants.SmaregiConstants;
import jp.creollc.smaregi.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Update Request
 *
 * @author Yasuyuki Takeo
 */
public class UpdRequestBuilder {

    public static UpdRequestBuilderBase builder() {
        return new UpdRequestBuilderBase();
    }

    public static class UpdRequestBuilderBase {
        private String url;
        private String procName;
        private String proc_division;
        private String proc_detail_division;
        private List<UpdData> data = new ArrayList<>();

        UpdRequestBuilderBase() {
        }

        public UpdRequestBuilderBase url(String url) {
            this.url = url;
            return this;
        }

        public UpdRequestBuilderBase procName(String procName) {
            this.procName = procName;
            return this;
        }

        public UpdRequestBuilderBase procDivision(String proc_division) {
            this.proc_division = proc_division;
            return this;
        }

        public UpdRequestBuilderBase procDetailDivision(String proc_detail_division) {
            this.proc_detail_division = proc_detail_division;
            return this;
        }

        public UpdRequestBuilderBase data(List<UpdData> data) {
            this.data.addAll(data);
            return this;
        }

        public UpdRequestBuilderBase data(UpdData updData) {
            this.data.add(updData);
            return this;
        }

        public SmaregiRequest build() throws JsonProcessingException {
            ProcInfo procInfo = new ProcInfo(this.proc_division, this.proc_detail_division);
            UpdParams updParams = new UpdParams(procInfo, data);

            String params = JsonUtil.toJSON(updParams);

            return new SmaregiRequest(url, procName, params);
        }

        public String toString() {
            return "UpdRequestBuilder.UpdRequestBuilderBase(url=" + this.url + ", procName=" + this.procName + ", procDivision=" + this.proc_division + ", procDetailDivision=" + this.proc_detail_division + ", data=" + this.data + ")";
        }
    }
}
