package sistema.modelo;

import java.util.ArrayList;


public class ContaEspecial extends Conta {
  private double limite;
  
  public ContaEspecial(int id, String chave, double saldo, double limite, Cliente cliente) {
    super(id, chave, saldo);   
    this.limite = limite;
    this.setCliente(cliente);  
}

  public ContaEspecial(int id, String chave, double limite) throws Exception {
    super(id, chave);

    this.limite = limite;
  }

  public ContaEspecial(int id, String chave, double saldo, double limite) throws Exception {
    this(id, chave, limite);

    setSaldo(saldo);
  }
  
  @Override
  public void debitar(double valor) {
    if ((getSaldo() + limite) < valor)
      throw new RuntimeException("Saldo e/ou limite insuficiente");

    setSaldo(getSaldo() - valor);
  }

  public double getLimite() {
    return limite;
  }

  public void setLimite(double limite) {
    this.limite = limite;
  }

  @Override
  public String toString() {
    return "{id: " + getId() + ", chavepiks: " + getChavePiks() + ", saldo: " + getSaldo() + ", limite: " + limite + ", cpf: " + getCliente().getCpf() + ", nome: " + getCliente().getNome() + "}";
  }
}