package sistema.modelo;

import java.util.ArrayList;

public class Conta{
	protected int id;
	protected String chavepiks;
	protected double saldo;
	private Cliente cliente;
	protected ArrayList<Lancamento> lancamento;
	
	private String chave;
	
	//protected Double limite;
	
	
	public Conta (int id, String chavepiks, double saldo, Cliente cliente, ArrayList<Lancamento> lancamento) {
		this.id = id;
		this.chavepiks = chavepiks;
		this.saldo = saldo;
		this.cliente = cliente;
		if (lancamento == null) {
		    this.lancamento = new ArrayList<>();
		} else {
		    this.lancamento = lancamento;
		}

		//limite = this.limite;
	}
	
	public Conta(int id2, String chave, double saldo2) {
		this.id = id2;
		this.chave = chave;
		this.saldo = saldo2;
	}
	
	public Conta(int id2, String chave) {
		this.id = id2;
		this.chave = chave;
		//this.saldo = 0;
	}
	public Conta(int id, String chavepiks, Double saldo) {
        this(id, chavepiks, saldo, null, new ArrayList<>());
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
	
	//public Double getLimite() {
      //  return limite;
   // }

   // public void setLimite(Double limite) {
    //    this.limite = limite;
   // }
	
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

	public ArrayList<Lancamento> getLancamentos() {
		return lancamento;
	}
	
}



