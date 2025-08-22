package sistema.modelo;

import java.util.ArrayList;

public class ContaEspecial extends Conta {
    private Double limite;
	private String chave;
	private double limite2;
    
    public ContaEspecial(int id, String chavepiks, Double saldo, Cliente cliente, Double limite) {
        super(id, chavepiks, saldo, cliente, new ArrayList<Lancamento>(), limite);
        this.limite = limite;
    }
    
   
    public ContaEspecial(int id, String chave, Double saldo, Double limite2) {
        super(id, chave, saldo, null, new ArrayList<Lancamento>(), limite2); 
        this.limite2 = limite2;
    }


	public void debitar(Double valor) {
        if (valor <= saldo + limite) {
            saldo -= valor;
            adicionar(new Lancamento(valor, "-"));
        } else {
            throw new RuntimeException("Saldo insuficiente para realizar o débito.");
        }
    }
    
    
    public String toString() {
        return super.toString() + ", Limite=" + limite;
    }


	public Double getLimite() {
		return this.limite;
	}
}
