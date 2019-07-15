package jp.creollc.smaregi.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

/**
 * ProcInfo
 *
 * @author Yasuyuki Takeo
 */
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcInfo {
    protected String proc_division;
    protected String proc_detail_division;

    public ProcInfo() {
    }

    @JsonProperty("proc_division")
    public String getProcDivision() {
        return this.proc_division;
    }

    @JsonProperty("proc_detail_division")
    public String getProcDetailDivision() {
        return this.proc_detail_division;
    }

    @JsonProperty("proc_division")
    public void setProcDivision(String proc_division) {
        this.proc_division = proc_division;
    }

    @JsonProperty("proc_detail_division")
    public void setProcDetailDivision(String proc_detail_division) {
        this.proc_detail_division = proc_detail_division;
    }

    public String toString() {
        return "ProcInfo(proc_division=" + this.getProcDivision() + ", proc_detail_division=" + this.getProcDetailDivision() + ")";
    }
}
