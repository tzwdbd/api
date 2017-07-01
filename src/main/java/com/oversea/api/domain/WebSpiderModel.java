package com.oversea.api.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.oversea.api.enums.ApiCode;
import com.oversea.common.view.goods.GoodsAbridged;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WebSpiderModel extends BaseModel {
	
	private static final long serialVersionUID = 6292614632969617104L;
	
	private String productId;
	private String productImage;
	private String productTitle;
	private String price;
	private String mallName;
	private String mallCountry;
	private String mallLogo;
	private String key;
	private String status;
	
	private GoodsAbridged goods;
	
	public WebSpiderModel() {}
	
	public WebSpiderModel(ApiCode apiCode) {
		super(apiCode);
	}
	
	public WebSpiderModel(String message) {
		super(message);
	}
	
	public WebSpiderModel(String code, String message) {
		super(code, message);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getMallCountry() {
		return mallCountry;
	}

	public void setMallCountry(String mallCountry) {
		this.mallCountry = mallCountry;
	}

	public String getMallLogo() {
		return mallLogo;
	}

	public void setMallLogo(String mallLogo) {
		this.mallLogo = mallLogo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public GoodsAbridged getGoods() {
		return goods;
	}

	public void setGoods(GoodsAbridged goods) {
		this.goods = goods;
	}
}
