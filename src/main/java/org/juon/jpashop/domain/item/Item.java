package org.juon.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.juon.jpashop.domain.CategoryItem;
import org.juon.jpashop.exception.NotEnoughStockException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;
	private Double price;
	
	@Column(name = "STOCK_QUANTITY")
	private int stockQuantity;
	
	private String filename;
	private String orgFilename;
	
	@OneToMany(mappedBy="item", cascade = CascadeType.ALL)
	private List<CategoryItem> categoryItems = new ArrayList<>();
	
	//== Bussiness Method ==//
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("not enough!");
		}
		this.stockQuantity = restStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getOrgFilename() {
		return orgFilename;
	}
	
	public void setOrgFilename(String orgFilename) {
		this.orgFilename = orgFilename;
	}

	
	public List<CategoryItem> getCategoryItems() {
		return categoryItems;
	}

	public void setCategoryItems(List<CategoryItem> categoryItems) {
		this.categoryItems = categoryItems;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Item [id=").append(id).append(", name=").append(name).append(", price=").append(price)
				.append(", stockQuantity=").append(stockQuantity).append(", filename=").append(filename)
				.append(", orgFilename=").append(orgFilename).append("]").toString();
		
	}
}
