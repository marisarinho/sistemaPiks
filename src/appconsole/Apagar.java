package appconsole;

import sistema.modelo.Cliente;
import sistema.modelo.Conta;
import sistema.repositorio.Repositorio;

public class Apagar {

	public Apagar() {
		try {
			String chave = "chave3";
			Conta conta = Repositorio.localizarConta(chave);
			if (conta == null) 
				throw new Exception(chave+" n�o encontrada");
			
			if(conta.getSaldo() != 0) 
				throw new Exception("Conta com saldo n�o pode ser apagada ");
			
			Cliente cliente = conta.getCliente();
			conta.setCliente(null);
			cliente.setConta(null);
			Repositorio.removerConta(conta);	
			Repositorio.removerCliente(cliente);	
			Repositorio.gravarObjetos();
			System.out.println("apagou conta da chave "+chave);
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}	
	}

	public static void main(String[] args) {
		new Apagar();
	}
}
