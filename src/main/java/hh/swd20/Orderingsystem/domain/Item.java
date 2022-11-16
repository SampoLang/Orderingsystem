package hh.swd20.Orderingsystem.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Item_List")
public class Item {
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long itemId;
	@NotNull
	@Size(min=2, max=30)
	private String title;
	@Size(max=100)
	private String description;
	private String imgUrl;
	@NotEmpty(message="Must not be null or empty")
	private String itemCode;
	@Min(value = 0, message="Value must be a number greater than 0")
	private double price;
	
	@OneToMany(mappedBy="items",cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH} )
	private Set<Order> orders = new HashSet<>();
	public Item() {
		super();
		}
	
	public Item(String title, String description, String imgUrl, String itemCode, double price) {
		super();
		this.title = title;
		this.description = description;
		this.imgUrl = imgUrl;
		this.itemCode = itemCode;
		this.price = price;
	}

	public Item(String title, String description, String imgUrl, String itemCode, double price, Set<Order> orders) {
		super();
		this.title = title;
		this.description = description;
		this.imgUrl = imgUrl;
		this.itemCode = itemCode;
		this.price = price;
		this.orders = orders;
	}

	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", title=" + title + ", description=" + description + ", imgUrl=" + imgUrl
				+ ", itemCode=" + itemCode + ", price=" + price + "]";
	}
	}
