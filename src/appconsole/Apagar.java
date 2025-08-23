package appconsole;

import sistema.modelo.Cliente;
import sistema.modelo.Conta;
import sistema.repositorio.Repositorio;

public class Apagar {

	public Apagar() {
		try {
			Repositorio.lerObjetos();
			String chave = "chave2";
			System.out.println("-------------");
			
			Conta conta = Repositorio.localizarConta(chave);
			System.out.println("entrou aqui 2222");
			System.out.println(conta.getCliente().getCpf());
			System.out.println(conta.getSaldo());
			System.out.println("chegpou aqui");
			if (conta == null) 
				throw new Exception(chave+" n�o encontrada");
			
			if(conta.getSaldo() != 0) 
				throw new Exception("Conta com saldo n�o pode ser apagada ");
			
			
			Cliente cliente = conta.getCliente();
			System.out.println("Entrou cPFFF");
			System.out.println(cliente.getCpf());
			//conta.setCliente(null);
			//cliente.setConta(null);
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
