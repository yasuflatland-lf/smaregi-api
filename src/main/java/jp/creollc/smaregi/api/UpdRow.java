package jp.creollc.smaregi.api;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UpdRow
 *
 * @author Yasuyuki Takeo
 */
public class UpdRow {
    protected Map<String, String> row = new ConcurrentHashMap<>();

    public UpdRow(Map<String, String> row) {
        this.row = row;
    }

    public UpdRow() {
    }

    public void addRow(String key, String value) {
        this.row.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, String> getRow() {
        return this.row;
    }

    @JsonAnySetter
    public void setRow(String key, String value) {
        this.row.put(key, value);
    }

    public String toString() {
        return "UpdRow(row=" + this.getRow() + ")";
    }
}
