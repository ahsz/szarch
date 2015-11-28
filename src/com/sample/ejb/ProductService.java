package com.sample.ejb;

import java.util.List;

import com.sample.jpa.entities.Product;

public interface ProductService {

	public int addProduct(Product product);
    public String echo(String s);
	public List<Product> getProduct(Product product);
	public int updProduct(Product product);
	public int remProduct(Product product);
	public Product getProduct(String name);
	public Product getProduct(int Id);
	public String[] getAllProductNames();
}
