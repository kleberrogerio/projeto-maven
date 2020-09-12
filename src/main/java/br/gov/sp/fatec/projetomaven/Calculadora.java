package br.gov.sp.fatec.projetomaven;

public class Calculadora {
	
	private Double n1,n2;
	
	public Calculadora(Double n1,Double n2) {
		this.n1=n1;
		this.n2=n2;
	}
	
	public Calculadora() {
		this(1.0,2.0);
	}
	public Double soma() {
		return n1+n2;
	}
	public Double subtracao() {
		return n1-n2;
	}
	public Double divisao() {
		return n1/n2;
	}
	public Double multiplicacao() {
		return n1*n2;
	}

}
