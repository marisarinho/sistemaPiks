package sistema.modelo;


import sistema.repositorio.Repositorio;

public class Cliente {
	protected int cpf; 
	protected String nome;
	protected Conta conta;

	
	public Cliente(int cpf, String nome, Conta conta){
		this.cpf = cpf;
		this.nome = nome;
		this.conta = conta;
	
	}
	
	public Cliente(int cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

    // Getters e Setters
    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos do diagrama
    public void setConta(Conta cta) {
        this.conta = cta;
    }

    public Conta getConta() {
        return conta;
    }

    @Override
    public String toString() {
        return "Cliente [cpf=" + cpf + ", nome=" + nome + "]";
    }
}
