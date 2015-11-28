package com.sample.ejb;

import java.util.List;

import com.sample.jpa.entities.Complex;

public interface ComplexService {

	public int addComplex(Complex complex);
    public String echo(String s);
	public List<Complex> getComplex(Complex complex);
	public int updComplex(Complex complex);
	public int remComplex(Complex complex);
	public List<Complex> getComplexToDelete(int Id);

}
