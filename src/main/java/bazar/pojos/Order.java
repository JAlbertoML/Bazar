package bazar.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
	public static final Integer STATUS_PENDING = 0;
	public static final Integer STATUS_SENT = 1;
	public static final Integer STATUS_COMPLETE = 2;
	
	private Integer id;
	private LocalDate date;
	private ArrayList<ProductInCart> products;
	private BigDecimal amount;
	private String status;
	private Integer clientId;
	
	public Order(Integer id, LocalDate date, ArrayList<ProductInCart> products, BigDecimal amount, String status,
			Integer clientId) {
		super();
		this.id = id;
		this.date = date;
		this.products = products;
		this.amount = amount;
		this.status = status;
		this.clientId = clientId;
	}	

	public Order(LocalDate date, ArrayList<ProductInCart> products, BigDecimal amount, String status,
			Integer clientId) {
		super();
		this.date = date;
		this.products = products;
		this.amount = amount;
		this.status = status;
		this.clientId = clientId;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<ProductInCart> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductInCart> products) {
		this.products = products;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
}
