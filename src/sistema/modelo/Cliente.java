package sistema.modelo;

public class Cliente {
	protected int cpf; 
	protected String nome;
	protected Conta conta;
	protected int cpf2;
	protected String nome2;
	
	
	public Cliente(int cpf, String nome, Conta conta){
		this.cpf = cpf;
		this.nome = nome;
		this.conta = conta;
		
	}
	
	public Cliente(int cpf2, String nome2) {
		this.cpf2 = cpf2;
		this.nome2 = nome2;
	}

	public int getCpf() {
		return this.cpf;
	}
	
	public void setCpf(int cpf) {
        this.cpf = cpf;
    }
	
	public Conta getConta() {
		return this.conta;
	}
	
	public void setConta(Conta con) {
		this.conta = con;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
        this.nome = nome;
    }
	
	public String toString() {
	    return "Cliente: " + nome + ", CPF: " + cpf + ", ID da Conta: " + conta.id;
	}
}
