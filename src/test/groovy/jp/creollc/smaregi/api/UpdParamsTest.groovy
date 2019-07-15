package jp.creollc.smaregi.api

import groovy.json.JsonSlurper
import jp.creollc.smaregi.api.UpdParams
import jp.creollc.smaregi.api.UpdRow
import jp.creollc.smaregi.api.stock.constants.StockConstants
import jp.creollc.smaregi.util.JsonUtil
import spock.lang.Specification

class UpdParamsTest extends Specification {
    def "Smoke test" () {
        when:
        ProcInfo procInfo = new ProcInfo("dummy_proc_division","proc_detail_division")
        List<UpdRow> updRows = new ArrayList() {{
            add(new UpdRow( new HashMap<String, String>() {{
                put(StockConstants.PRODUCT_ID, "113");
            }}))
        }}
        UpdData updData = new UpdData(StockConstants.TABLE_NAME, updRows)
        def updParams = new UpdParams(procInfo, new ArrayList<UpdData>() {{
            add(updData);
        }});
        def jsonstr = JsonUtil.toJSON(updParams);
        def json = new JsonSlurper().parseText(jsonstr)

        then:
        jsonstr != null
    }
}

