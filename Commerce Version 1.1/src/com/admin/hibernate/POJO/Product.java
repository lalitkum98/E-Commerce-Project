package com.admin.hibernate.POJO;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="product")
public class Product {
	@Id
	private String productId;
	private String productName;
	private String productDescription;
	private String productBrand;
	private String productImgBind;

	public Product() {
	
	}

	public Product(String productId, String productName,
			String productDescription, String productBrand, String productImgBind) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productBrand = productBrand;
		this.productImgBind = productImgBind;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductImgBind() {
		return productImgBind;
	}

	public void setProductImgBind(String productImgBind) {
		this.productImgBind = productImgBind;
	}

}
