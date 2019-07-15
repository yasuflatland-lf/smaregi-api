package jp.creollc.smaregi.api.store;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Store class
 *
 * <p>
 * Store table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Store {
    public String storeId;
    public String storeCode;
    public String storeName;
    public String storeAbbr;
    public String division;
    public String postCode;
    public String address;
    public String phoneNumber;
    public String faxNumber;
    public String mailAddress;
    public String homepage;
    public String tempTranMailAddress;
    public String priceChangeFlag;
    public String sellDivision;
    public String sumProcDivision;
    public String sumDateChangeTime;
    public String sumRefColumn;
    public String pointNotApplicable;
    public String taxFreeDivision;
    public String maxBundleProductCoun;
    public String roundingDivision;
    public String discountRoundingDivision;
    public String pauseFlag;
    public String pointUseDivision;
    public String insDateTime;
    public String updDateTime;
}
