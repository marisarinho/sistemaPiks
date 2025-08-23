package sistema.modelo;
import sistema.repositorio.Repositorio;

import java.util.ArrayList;

public class Conta {
    protected int id;
    protected String chavepiks;
    protected double saldo;
    protected Cliente cliente;
    protected ArrayList<Lancamento> lancamentos;
    
    
     public Conta(int id, String chavepiks, double saldo) {
        this.id = id;
        this.chavepiks = chavepiks;
        this.saldo = saldo;
        this.cliente = cliente;
       	this.lancamentos = new ArrayList<>(); 

    }

    public Conta(int id, String chavepiks) {
        this.id = id;
        this.chavepiks = chavepiks;
        this.cliente = cliente;
        this.lancamentos = new ArrayList<>(); 
    }

    public void creditar(double valor) {
        this.saldo += valor;
        this.adicionar(new Lancamento(valor, "-"));
       
    }

    public void debitar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        	this.adicionar(new Lancamento(valor,"-"));
        }else {
        	throw new RuntimeException("Saldo insuficiente");
        }
    }

    public int getId() {
        return this.id;
    }

    public String getChavePiks() {
        return this.chavepiks;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public ArrayList<Lancamento> getLancamento() {
        return this.lancamentos;
    }

    public void setCliente(Cliente cli) {
        this.cliente = cli;
    }

    public void setSaldo(double valor) {
        this.saldo = valor;
    }

    public void setChavePiks(String chave) {
        this.chavepiks = chave;
    }

    public void adicionar(Lancamento lanc) {
        this.lancamentos.add(lanc);
    }

    public void transferir(double valor, Conta destino) {
        this.debitar(valor);
        destino.creditar(valor);
    }
    
    @Override
    public String toString() {
        String clienteInfo = (cliente != null) ? cliente.getNome() + " (CPF: " + cliente.getCpf() + ")" : "Sem cliente";
        StringBuilder lancamentosInfo = new StringBuilder();
        if (lancamentos.isEmpty()) {
            lancamentosInfo.append("Nenhum lançamento");
        } else {
            for (Lancamento l : lancamentos) {
                lancamentosInfo.append(l.toString()).append("; ");
            }
        }

        return "Conta [ID: " + id + 
               ", ChavePIKS: " + chavepiks + 
               ", Saldo: " + saldo + 
               ", Cliente: " + clienteInfo + 
               ", Lançamentos: " + lancamentosInfo.toString() + "]";
    }

    
}
