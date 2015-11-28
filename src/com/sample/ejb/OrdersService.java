package com.sample.ejb;

import java.util.List;

import com.sample.jpa.entities.Orders;

public interface OrdersService {

	public int addOrders(Orders orders);
    public String echo(String s);
	public List<Orders> getOrders(Orders orders);
	public int updOrders(Orders orders);
	public int remOrders(Orders orders);
	public List<Orders> getOrdersToDelete(int Id);
}
