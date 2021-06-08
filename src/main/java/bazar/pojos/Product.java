package bazar.pojos;

import java.math.BigDecimal;

public class Product {
	public static final Integer CAT_MEN = 0;
	public static final Integer CAT_WOMEN = 1;
	public static final Integer CAT_OTHER = 2;
	
	private Integer id;
	private String name;
	private BigDecimal price;
	private String description;
	private String photo;
	private Integer quantity;
	private Integer Category;
	private Integer quantitySold;
	private Boolean inCart;
	private BigDecimal discount;
	
	public Product(Integer id, String name, BigDecimal price, String description, String photo, Integer quantity,
			Integer category, Integer quantitySold, BigDecimal discount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.photo = photo;
		this.quantity = quantity;
		Category = category;
		this.quantitySold = quantitySold;
		inCart = false;
		this.discount = discount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCategory() {
		return Category;
	}

	public void setCategory(Integer category) {
		Category = category;
	}

	public Integer getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(Integer quantitySold) {
		this.quantitySold = quantitySold;
	}

	public Boolean getInCart() {
		return inCart;
	}

	public void setInCart(Boolean inCart) {
		this.inCart = inCart;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
