package hh.swd20.Orderingsystem.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="OrderList")
public class Order{
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderId;
	
	  @ManyToMany
	  @JoinTable(name = "Order_Row", 
	  joinColumns = @JoinColumn(name = "order_id"),
	  inverseJoinColumns = @JoinColumn(name = "item_id")
	  )
    Set<Item> items = new HashSet<>();
	  
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private Date orderDate;
	private double total;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;
	
	public Order() {}
	
	public Order(Set<Item> items, Date orderDate, User user) {
		super();
		this.items = items;
		this.orderDate = orderDate;
		this.user = user;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", items=" + items + ", orderDate=" + orderDate + "]";
	}
	public String listItems() {
		String strItems = "";
		for(Item item: items) {
			strItems +=item.getItemId() + " " + item.getTitle() + ", ";
		}
		return strItems;
		
	}
	

}
