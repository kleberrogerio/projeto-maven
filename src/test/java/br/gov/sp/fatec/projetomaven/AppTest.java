package br.gov.sp.fatec.projetomaven;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigourous Test :-)
     */
	@Test
    public void testSoma()
    {
        Calculadora calc = new Calculadora();
		assertTrue(calc.soma()==3);		
    }
	@Test
    public void testSubtracao()
    {
        Calculadora calc = new Calculadora();
		assertTrue(calc.subtracao()==4);		
    }
	@Test
    public void testDivisao()
    {
        Calculadora calc = new Calculadora();
		assertTrue(calc.divisao()==0.7);	
    }
	@Test
    public void testMultiplicacao()
    {
        Calculadora calc = new Calculadora();		
		assertTrue(calc.multiplicacao()==2);
    }
}
