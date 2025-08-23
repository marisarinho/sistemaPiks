package appconsole;
/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da Fachada
 * 
 */

import sistema.modelo.Cliente;
import sistema.modelo.Conta;
import sistema.modelo.Lancamento;
import sistema.repositorio.Repositorio;

public class Listar {

	public Listar() {
		try {
			System.out.println("\n---------listagem de contas-----");
			for(Conta c : Repositorio.getContas()) { 
				System.out.println(c);
				for(Lancamento lan : c.getLancamento()) 
					System.out.println("   "+lan);
			}

			System.out.println("\n---------listagem de clientes ----");
			for(Cliente e : Repositorio.getClientes()) 
				System.out.println(e);
			
			
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}	
	}

	public static void main (String[] args) 
	{
		new Listar();
	}
}


