package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealItem {

    private long productId;
    private long sellerId;
    private String oriMinPrice;
    private String oriMaxPrice;
    private long promotionId;
    private String startTime;
    private String endTime;
    private byte phase;
    private String productTitle;
    private String minPrice;
    private String maxPrice;
    private String discount;
    private String totalStock;
    private String stock;
    private String orders;
    private boolean soldout;
    private String productImage;
    private String productDetailUrl;
    private String trace;
    private String totalTranpro3;
    private String productPositiveRate;
    private String productAverageStar;
    private int itemEvalTotalNum;
    private String gmtCreate;
}
