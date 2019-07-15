package jp.creollc.smaregi.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jp.creollc.smaregi.api.UpdRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * UpdData
 *
 * @author Yasuyuki Takeo
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdData {
    protected String table_name;
    protected List<UpdRow> rows;

    public UpdData() {
    }

    public UpdData(String table_name, List<UpdRow> rows) {
        this.table_name = table_name;
        this.rows = rows;
    }

    public UpdData(String table_name, Map<String, String> rows) {
        this.table_name = table_name;
        UpdRow updRow = new UpdRow(rows);
        this.rows = new ArrayList<>();
        this.rows.add(updRow);
    }

    @JsonProperty("table_name")
    public String getTableName() {
        return this.table_name;
    }

    public List<UpdRow> getRows() {
        return this.rows;
    }

    @JsonProperty("table_name")
    public void setTableName(String table_name) {
        this.table_name = table_name;
    }

    public void setRows(List<UpdRow> rows) {
        this.rows = rows;
    }

    public void setRows(UpdRow row) {
        this.rows.add(row);
    }

    public String toString() {
        return "UpdData(table_name=" + this.getTableName() + ", rows=" + this.getRows() + ")";
    }
}
