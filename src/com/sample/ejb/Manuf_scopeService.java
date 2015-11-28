package com.sample.ejb;

import java.util.List;

import com.sample.jpa.entities.Manuf_scope;

public interface Manuf_scopeService {
	public int addManuf_scope(Manuf_scope manuf_scope);
    public String echo(String s);
	public List<Manuf_scope> getManuf_scope(Manuf_scope manuf_scope);
	public int updManuf_scope(Manuf_scope manuf_scope);
	public int remManuf_scope(Manuf_scope manuf_scope);
	public Manuf_scope getManuf_scope(String name);
	public String[] getAllManuf_scopeNames();
}
