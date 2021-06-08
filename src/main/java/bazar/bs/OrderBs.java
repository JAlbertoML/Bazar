package bazar.bs;

import bazar.dao.OrderDao;
import bazar.pojos.Order;

public class OrderBs {
	public static Boolean addOrder(Order order) {
		return OrderDao.addOrder(order);
	}
}
