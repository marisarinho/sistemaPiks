package sistema.modelo;

import java.util.ArrayList;

public class Conta{
	protected int id;
	protected String chavepiks;
	protected Double saldo;
	private Cliente cliente;
	protected ArrayList<Lancamento> lancamento;
	private int id2;
	private String chave;
	private double saldo2;
	protected Double limite;
	
	public Conta (int id, String chavepiks, Double saldo, Cliente cliente, ArrayList<Lancamento> lancamento, Double limite) {
		this.id = id;
		this.chavepiks = chavepiks;
		this.saldo = saldo;
		this.cliente = cliente;
		//this.lancamento = lancamento;
		this.lancamento = (lancamento == null) ? new ArrayList<>() : lancamento;
		this.limite;
	}
	
	public Conta(int id2, String chave, double saldo2) {
		this.id2 = id2;
		this.chave = chave;
		this.saldo2 = saldo2;
	}

	public void creditar(Double valor) {
		saldo += valor;
		adicionar(new Lancamento(valor, "+"));
	}
	
	public void debitar(Double valor) {
		if(valor <= saldo){
			saldo -= valor;
			adicionar(new Lancamento(valor, "-"));
		}else {
			throw new RuntimeException("Saldo insuficiente!");
		}
		
	}
	
	public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }
	
	public int getId() {
		return this.id;
	}
	
	public String getChavePiks() {
		return this.chavepiks;
	}
	
	public Double getSaldo() {
		return this.saldo;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Cliente cli) {
		this.cliente = cli ;
	}
	
	public ArrayList<Lancamento> getLancamento(){
		return this.lancamento;
	}
	
	public void setSaldo(Double sal) {
		this.saldo = sal;
	}
	
	public void setChavePiks(String chave) {
		this.chavepiks = chave; 
	}
	
	public void adicionar(Lancamento lanc) {
		lancamento.add(lanc);
	}
	
	public void transferir(Double valor, Conta destino) {
        if(this == destino) {
            throw new RuntimeException("Não é possível transferir para a mesma conta!");
        }
        this.debitar(valor);
        destino.creditar(valor);
    }
	
	
	public String toString() {
	    return "Conta: ID=" + id +
	           ", ChavePiks=" + chavepiks +
	           ", Saldo=" + saldo +
	           ", Cliente=" + cliente.nome +
	           ", CPF=" + cliente.cpf;
	}
	
}



