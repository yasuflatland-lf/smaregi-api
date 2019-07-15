package jp.creollc.smaregi.test.util

import jp.creollc.smaregi.constants.SmaregiConstants

class TestUtil {
    static def DS = System.getProperty("file.separator");

    // Credentials for ClientBase API
    static def X_ACCESS_TOKEN
    static def X_CONTACT_ID

    // Test Product Id for Smaregi API
    static def PRODUCT_ID_OKINAWA_01 = "8426"
    static def PRODUCT_ID_OKINAWA_02 = "8427"
    static def PRODUCT_ID_OKINAWA_03 = "8428"
    static def PRODUCT_ID_OKINAWA_04 = "8429"
    static def PRODUCT_ID_OKINAWA_05 = "8430"
    static def PRODUCT_ID_OKINAWA_06 = "8431"
    static def PRODUCT_ID_OKINAWA_07 = "8432"
    static def PRODUCT_ID_OKINAWA_08 = "8433"
    static def PRODUCT_ID_OKINAWA_09 = "8434"
    static def PRODUCT_ID_OKINAWA_10 = "8435"

    // Test Category for Test
    static def CATEGORY_ID_TEST = "26"

    // Store ID
    // 九州物産展花坂商店E
    static def STORE_ID_TEST = "17"

    static {
        def access_token = System.getenv(SmaregiConstants.X_ACCESS_TOKEN)
        X_ACCESS_TOKEN = (null == access_token) ? "dummy" : access_token

        def contact_id = System.getenv(SmaregiConstants.X_CONTACT_ID)
        X_CONTACT_ID = (null == contact_id) ? "dummy" : contact_id
    }

    /**
     * Temporally path generator
     *
     * Depending on the environment, the path format is different.
     * This method standardize the end of path ends with delimiter.
     *
     * @return path with delimiter if it's not ended with it.
     */
    static public def getTempPath() {
        def retStr = System.getProperty("java.io.tmpdir");
        if (!retStr.endsWith(DS)) {
            retStr = retStr + DS;
        }
        return retStr;
    }
}
