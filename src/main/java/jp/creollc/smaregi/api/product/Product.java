package jp.creollc.smaregi.api.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Product class
 *
 * <p>
 * Product table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product {
    public String productId;
    public String categoryId;
    public String productCode;
    public String productName;
    public String productKana;
    public String taxDivision;
    public String productPriceDivision;
    public String price;
    public String customerPrice;
    public String cost;
    public String attribute;
    public String description;
    public String catchCopy;
    public String size;
    public String color;
    public String tag;
    public String groupCode;
    public String url;
    public String displaySequence;
    public String salesDivision;
    public String stockControlDivision;
    public String displayFlag;
    public String division;
    public String productOptionGroupId;
    public String pointNotApplicable;
    public String taxFreeDivision;
    public String supplierProductNo;
    public String staffDiscountRate;
    public String reduceTaxId;
    public String appStartDateTime;
}
