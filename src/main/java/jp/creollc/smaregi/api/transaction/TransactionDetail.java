package jp.creollc.smaregi.api.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * TransactionDetail class
 *
 * <p>
 * TransactionDetail table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionDetail {
    public String transactionHeadId;
    public String transactionDateTime;
    public String transactionDetailId;
    public String parentTransactionDetailId;
    public String transactionDetailDivision;
    public String productId;
    public String productCode;
    public String productName;
    public String taxDivision;
    public String price;
    public String salesPrice;
    public String unitDiscountPrice;
    public String unitDiscountRate;
    public String unitDiscountDivision;
    public String cost;
    public String quantity;
    public String unitNonDiscountSum;
    public String unitDiscountSum;
    public String unitDiscountedSum;
    public String costSum;
    public String categoryId;
    public String categoryName;
    public String discriminationNo;
    public String salesDivision;
    public String productDivision;
    public String pointNotApplicable;
    public String calcDiscount;
    public String taxFreeDivision;
    public String taxFreeCommodityPrice;
    public String taxFree;
    public String productBundleGroupId;
    public String discountPriceProportional;
    public String discountPointProportional;
    public String taxIncludeProportional;
    public String taxExcludeProportional;
    public String productBundleProportional;
    public String staffDiscountProportional;
    public String bargainDiscountProportional;
    public String roundingPriceProportional;
    public String inventoryReservationDivision;
    public String groupCode;
    public String updDateTime;
    public String productStaffDiscountRate;
    public String staffRank;
    public String staffRankName;
    public String staffDiscountRate;
    public String staffDiscountDivision;
    public String applyStaffDiscountRate;
    public String applyStaffDiscountPrice;
    public String bargainId;
    public String bargainName;
    public String bargainDivision;
    public String bargainValue;
    public String applyBargainValue;
    public String applyBargainDiscountPrice;
    public String taxRate;
    public String standardTaxRate;
    public String modifiedTaxRate;
    public String reduceTaxId;
    public String reduceTaxName;
    public String reduceTaxRate;
    public String color;
    public String size;
}
