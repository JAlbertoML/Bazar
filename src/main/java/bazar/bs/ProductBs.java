package bazar.bs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import bazar.dao.ProductDao;
import bazar.pojos.Product;

public class ProductBs {
	public static ArrayList<Product> getAllProducts() {
		return ProductDao.getAllProducts();
	}
	
	private static Boolean isAllRequiredFiled(Map<String, String[]> parameterMap) {
		Boolean correct = true;
		if (parameterMap.get("nameTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("priceTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("quantityTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("categoryTxt")[0].equals("")) {
			correct = false;
		} else if (parameterMap.get("descriptionTxt")[0].equals("")) {
			correct = false;
		} 
		return correct;
	}
	
	public static Boolean areDiscountValid(Double price, Double discout) {
		Boolean correct = false;
		if(discout < 100) {
			correct = true;
		}
		return correct;
	}
	
	public static String getAddProductMessage(Map<String, String[]> parameterMap) {
		String message = null;
		if (isAllRequiredFiled(parameterMap)) {
			if(!parameterMap.get("discountTxt")[0].equals("")) {
				if(areDiscountValid(Double.parseDouble(parameterMap.get("priceTxt")[0]), Double.parseDouble(parameterMap.get("discountTxt")[0]))) {
					message = null;
				}
				else {
					message = "El descuento no puede ser mayor o igual al 100%";
				}
			}
		} else {
			message = "Asegurate de llenar todos los campos de las secciones obligatorias.";
		}
		return message;
	}
	
	public static Boolean addProduct(Map<String, String[]> parameterMap, String photo) {
		Boolean correct = false;
		String name = parameterMap.get("nameTxt")[0];
		BigDecimal price = new BigDecimal(parameterMap.get("priceTxt")[0]);
		BigDecimal discount;
		if(parameterMap.get("discountTxt")[0].equals(""))
			discount = new BigDecimal("0");
		else
			discount = new BigDecimal(new BigDecimal(parameterMap.get("priceTxt")[0]).subtract((new BigDecimal(parameterMap.get("priceTxt")[0]).multiply(new BigDecimal(parameterMap.get("discountTxt")[0]).divide(new BigDecimal(100))))).toString());
		Integer quantity = Integer.parseInt(parameterMap.get("quantityTxt")[0]);
		Integer category = Integer.parseInt(parameterMap.get("categoryTxt")[0]);
		String description = parameterMap.get("descriptionTxt")[0];
		Product product = new Product(0, name, price, description, photo, quantity, category, 0, discount);
		if(ProductDao.addProduct(product)) {
			correct = true;
		}
		return correct;
	}
	
	public static Boolean deleteProduct(Integer id) {
		return ProductDao.deleteProduct(id);
	}
	
	public static Product getProductById(Integer id) {
		return ProductDao.getProductById(id);
	}
	
	public static Boolean editProduct(Map<String, String[]> parameterMap, String photo, Integer id) {
		Boolean correct = false;
		String name = parameterMap.get("nameTxt")[0];
		BigDecimal price = new BigDecimal(parameterMap.get("priceTxt")[0]);
		BigDecimal discount;
		if(parameterMap.get("discountTxt")[0].equals(""))
			discount = new BigDecimal("0");
		else
			discount = new BigDecimal(new BigDecimal(parameterMap.get("priceTxt")[0]).subtract((new BigDecimal(parameterMap.get("priceTxt")[0]).multiply(new BigDecimal(parameterMap.get("discountTxt")[0]).divide(new BigDecimal(100))))).toString());
		if(photo == null) {
			Product product = getProductById(id);
			photo = product.getPhoto();
		}
		Integer quantity = Integer.parseInt(parameterMap.get("quantityTxt")[0]);
		Integer category = Integer.parseInt(parameterMap.get("categoryTxt")[0]);
		String description = parameterMap.get("descriptionTxt")[0];
		Product product = new Product(id, name, price, description, photo, quantity, category, 0, discount);
		if(ProductDao.editProduct(product)) {
			correct = true;
		}
		return correct;
	}
	
	public static Boolean editQuantities(Product product) {
		return ProductDao.editQuantities(product);
	}
}