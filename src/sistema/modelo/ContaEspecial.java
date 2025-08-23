package sistema.modelo;

import java.util.ArrayList;

public class ContaEspecial extends Conta {
    private Double limite; // só existe aqui

    // Construtor completo
    public ContaEspecial(int id, String chavepiks, Double saldo, Cliente cliente, Double limite) {
        super(id, chavepiks, saldo, cliente, new ArrayList<Lancamento>());
        this.limite = limite;
    }

    // Construtor sem cliente
    public ContaEspecial(int id, String chave, Double saldo, Double limite) {
        super(id, chave, saldo, null, new ArrayList<Lancamento>());
        this.limite = limite;
    }

    // Construtor só com id, chave e limite (saldo começa 0)
    public ContaEspecial(int id, String chave, Double limite) {
        super(id, chave, 0.0, null, new ArrayList<Lancamento>());
        this.limite = limite;
    }

   

    public void setLimite(Double limite) {
    	this.limite = limite;
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
