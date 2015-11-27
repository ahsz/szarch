package com.sample.ejb;

import java.util.List;

import com.sample.jpa.entities.Contain;

public interface ContainService {

	public int addContain(Contain contain);
    public String echo(String s);
	public List<Contain> getContain(Contain contain);
	public int updContain(Contain contain);
	public int remContain(Contain contain);
}
