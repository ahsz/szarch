package com.sample.ejb;

import java.util.List;

import com.sample.jpa.entities.Component;

public interface ComponentService {
	public int addComponent(Component component);
    public String echo(String s);
	public List<Component> getComponent(Component component);
	public int updComponent(Component component);
	public int remComponent(Component component);
}
