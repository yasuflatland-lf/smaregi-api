package jp.creollc.smaregi.api


import jp.creollc.smaregi.api.UpdRow
import jp.creollc.smaregi.api.stock.constants.StockConstants
import jp.creollc.smaregi.util.JsonUtil
import spock.lang.Specification

class UpdDataTest extends Specification {
    def "Smoke Test" () {
        when:
        List<UpdRow> updRows = new ArrayList() {{
            add(new UpdRow( new HashMap<String, String>() {{
                put(StockConstants.PRODUCT_ID, "113");
            }}))
        }}
        def updData = new UpdData(StockConstants.TABLE_NAME, updRows)
        def jsonstr = JsonUtil.toJSON(updData);

        then:
        jsonstr != null

    }
}
