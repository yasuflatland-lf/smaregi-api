package jp.creollc.smaregi;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.creollc.smaregi.api.RefParams;
import jp.creollc.smaregi.util.JsonUtil;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reference Request Builder
 *
 * <p>
 * Request object for reference inquiry for Smaregi API
 * </p>
 *
 * @author Yasuyuki Takeo
 */
public class RefRequestBuilder {

    /**
     * Builder Class
     *
     * @return
     */
    public static RefRequestBuilderBase builder() {
        return new RefRequestBuilderBase();
    }

    /**
     * Internal Builder Class
     */
    public static class RefRequestBuilderBase {
        private List<String> fields;
        private List<Map<String, String>> conditions;
        private List<String> order;
        private long limit;
        private long page = 1;
        private String table_name;
        private String url;
        private String procName;
        private String params;

        RefRequestBuilderBase() {
            conditions = new ArrayList<>();
            fields = new ArrayList<>();
            order = new ArrayList<>();
        }

        /**
         * Field setting
         *
         * <p>
         * Configure field by List. If there are existing field settings, they will be overwritten.
         * </p>
         *
         * @param fields field setting strings by List
         * @return this
         */
        public RefRequestBuilderBase fields(List<String> fields) {
            this.fields = fields;
            return this;
        }

        /**
         * Field setting
         *
         * <p>
         * Field setting by string. This method can be called multiple times, and each field will be added to the List.
         * </p>
         *
         * @param field field strings
         * @return this
         */
        public RefRequestBuilderBase field(String field) {
            this.fields.add(field);
            return this;
        }

        /**
         * Condition setting
         *
         * <p>
         * Condition settings by List and Map at once. If there are existing Condition settings, they will be overwritten.
         * </p>
         *
         * @param conditions condition settings
         * @return this
         */
        public RefRequestBuilderBase conditions(List<Map<String, String>> conditions) {
            this.conditions = conditions;
            return this;
        }

        /**
         * Condition setting
         *
         * <p>
         * Condition settings by Map. This method can be called multiple times, and each Condition will be added to the List.
         * </p>
         *
         * @param condition condition Key and value pair. The order of parameter should be key1, value1, key2, value2, key3....
         * @return this
         */
        public RefRequestBuilderBase conditions(String... condition) {
            if (0 != (condition.length % 2)) {
                throw new InvalidParameterException("Parameter should be even number. total params are <" + String.valueOf(condition.length) + ">");
            }

            for (int i = 0; i < condition.length - 1;) {
                Map map = new ConcurrentHashMap();
                map.put(condition[i], condition[i + 1]);
                this.conditions.add(map);
                i += 2;
            }

            return this;
        }

        /**
         * Condition setting
         *
         * <p>
         * Condition setting by string. This method can be called multiple times, and each Condition will be added to the List.
         * </p>
         *
         * @param key   Condition's map key
         * @param value Condition's map value
         * @return this
         */
        public RefRequestBuilderBase condition(String key, String value) {
            this.conditions.add(new HashMap() {{
                put(key, value);
            }});
            return this;
        }

        /**
         * Order setting
         *
         * <p>
         * Order settings by List at once. If there are existing Order settings, they will be overwritten.
         * </p>
         *
         * @param order
         * @return this
         */
        public RefRequestBuilderBase orders(List<String> order) {
            this.order = order;
            return this;
        }

        /**
         * Order setting
         *
         * <p>
         * Order setting by string. This method can be called multiple times, and each Order will be added to the List.
         * </p>
         *
         * @param order
         * @return this
         */
        public RefRequestBuilderBase order(String order) {
            this.order.add(order);
            return this;
        }

        /**
         * Limit setting
         *
         * @param limit limit setting. If this value exceed the Smaregi API's limit, the record will be limited by their max value.
         * @return this
         */
        public RefRequestBuilderBase limit(long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Page setting
         *
         * @param page page offset. the max value of this offset will be changed by the size of limit. The default is 1
         * @return this
         */
        public RefRequestBuilderBase page(long page) {
            this.page = page;
            return this;
        }

        /**
         * Table name setting
         *
         * @param table_name The target table name for fetching data
         * @return this
         */
        public RefRequestBuilderBase tableName(String table_name) {
            this.table_name = table_name;
            return this;
        }

        /**
         * Url setting
         *
         * @param url Smaregi API endopoint url. This is usually not necessarily to be configured.
         * @return this
         */
        public RefRequestBuilderBase url(String url) {
            this.url = url;
            return this;
        }

        /**
         * Proc Name setting
         *
         * @param procName proc name for retrieving each table data. Please see the Smaregi API document for more details.
         * @return this
         */
        public RefRequestBuilderBase procName(String procName) {
            this.procName = procName;
            return this;
        }

        public SmaregiRequest build() throws JsonProcessingException {
            RefParams refParams = new RefParams(fields, conditions, order, limit, page, table_name);

            String params = JsonUtil.toJSON(refParams);

            return new SmaregiRequest(url, procName, params);
        }

        public String toString() {
            return "RefRequestBuilder.RefRequestBuilderBase(fields=" + this.fields + ", conditions=" + this.conditions + ", orders=" + this.order + ", limit=" + this.limit + ", page=" + this.page + ", tableName=" + this.table_name + ", url=" + this.url + ", procName=" + this.procName + ", params=" + this.params + ")";
        }
    }
}
