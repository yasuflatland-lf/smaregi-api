package jp.creollc.smaregi;

import jp.creollc.smaregi.api.UpdData;
import jp.creollc.smaregi.api.UpdRow;

import java.util.ArrayList;
import java.util.List;

/**
 * UpdData builder
 *
 * @author Yasuyuki Takeo
 */
public class UpdDataBuilder {

    public static UpdDataBuilderBase builder() {
        return new UpdDataBuilderBase();
    }

    /**
     * Builder class
     */
    public static class UpdDataBuilderBase {
        private List<UpdData> updData;

        UpdDataBuilderBase() {
            updData = new ArrayList<>();
        }

        /**
         * UpdData builder
         *
         * <p>
         * This method is a suger syntax of creating updData structure.
         * </p>
         *
         * @param table_name Smaregi API table_name
         * @param params     key and value pairs consist of rows data for each UpdData object
         * @return this
         */
        public UpdDataBuilderBase updData(String table_name, String... params) {
            UpdData updData = new UpdData();

            // Create new UpdData with new rows
            updData.setTableName(table_name);
            List<UpdRow> rows = SmaregiClient.UpdRowBuilder.builder().createRows(params).build();
            updData.setRows(rows);

            // Search existing UpdData with the target table_name
            int index = getUpdDataIndex(table_name, this.updData);

            if (-1 == index) {
                // If a UpdRow with the target table_name exists, merge the rows and update the target UpdRow
                this.updData.add(updData);

            } else {
                // If Add rows to the existing UpdRow
                UpdData tmpUpdData = this.updData.get(index);
                tmpUpdData.getRows().addAll(rows);
                this.updData.set(index, tmpUpdData);
            }

            return this;
        }


        /**
         * Search existing UpdData in the list
         *
         * <p>
         * Search existing UpdData in the list
         * </p>
         *
         * @param table_name Target UpdData table_name
         * @return index of UpdData in the list. Returns -1 if an appropriate object is not found.
         */
        protected int getUpdDataIndex(String table_name, List<UpdData> targetList) {

            UpdData updTarget
                = targetList.stream()
                      .filter(updData -> updData.getTableName().contains(table_name))
                      .findAny()
                      .orElse(null);

            if (null == updTarget) {
                return -1;
            }

            return targetList.indexOf(updTarget);
        }

        /**
         * Build
         *
         * <p>
         * Generate UpdData List for Smaregi API update request
         * </p>
         *
         * @return UpdData List object
         */
        public List<UpdData> build() {
            return updData;
        }

        public String toString() {
            return "UpdDataBuilder.UpdDataBuilderBase(updData=" + this.updData + ")";
        }
    }
}
