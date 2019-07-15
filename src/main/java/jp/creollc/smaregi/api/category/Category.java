package jp.creollc.smaregi.api.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Category class
 *
 * <p>
 * Category table mapping object for reference / update
 * </p>
 *
 * @author Yasuyuki Takeo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Category {
    public String categoryId;
    public String categoryCode;
    public String categoryName;
    public String categoryAbbr;
    public String categoryGroupId;
    public String parentCategoryId;
    public String level;
    public String displaySequence;
    public String displayFlag;
    public String pointNotApplicable;
    public String taxFreeDivision;
    public String reduceTaxId;
    public String color;
    public String tag;
    public String insDateTime;
    public String updDateTime;
}
