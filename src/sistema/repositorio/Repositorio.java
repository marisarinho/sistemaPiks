package sistema.repositorio;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import sistema.modelo.Cliente;
import sistema.modelo.Conta;
import sistema.modelo.ContaEspecial;
import sistema.modelo.Lancamento;

public class Repositorio {
	private static TreeMap<String,Conta> contasPIKS = new TreeMap<>();
	private static TreeMap<Integer,Cliente> clientesCPF = new TreeMap<>();
	
	public static Cliente getCliente(int cpf) {
		return clientesCPF.get(cpf);
	}
	
	public static void adicionarConta(Conta cta) {
		String chave = cta.getChavePiks();
		contasPIKS.put(chave, cta);
	}
	
	public static void removerConta(Conta cta) {
        if(cta.getSaldo() == 0) {
        	System.out.println("cta getl cliente");
        	System.out.println(cta.getSaldo());
          //  System.out.println(cta.getCliente().getCpf());
            System.out.println(cta);
            
        	contasPIKS.remove(cta.getChavePiks());
        	Cliente clienteConta = getClienteByContaId(cta.getId());
        	if (clienteConta != null) {
        		int idCliente = clienteConta.getCpf();
        		
        		Integer obj = Integer.valueOf(idCliente);

        		clientesCPF.remove(idCliente);
        		Repositorio.gravarObjetos();
        	}
        //	clientesCPF.remove(getClienteByContaId(cta.getId().getCpf());
        } else {
            throw new RuntimeException("Não é possível remover conta com saldo diferente de zero.");
        }
    }
	
	public static Conta localizarConta(String chavepiks) {
		System.out.println("entrou noo lcoalizar conta");
		for (Cliente cli : clientesCPF.values()) {
            System.out.println(cli.getNome());
        }
		
		for (Conta conta : contasPIKS.values()) {
			System.out.println("for da conta");
            System.out.println(conta.getCliente().getCpf());
        }
		System.out.println("essa linha");
		System.out.println(contasPIKS.get(chavepiks).getCliente().getCpf());
		
		return contasPIKS.get(chavepiks);
		
	}
	
	public static void adicionarCliente(Cliente cli) {
		Integer cpf = cli.getCpf();
		clientesCPF.put(cpf, cli); 
	}
	
	public static void removerCliente(Cliente cli ) {
		Integer cpf = cli.getCpf();
		clientesCPF.remove(cpf); 
	}
	
	public static Cliente localizarCliente(int cpf) {
		return clientesCPF.get(cpf); 
	}
	
	
	
	public static ArrayList<Cliente> getClientes() {
        return new ArrayList<>(clientesCPF.values());
    }
    
    public  static ArrayList<Conta> getContas() {
        return new ArrayList<>(contasPIKS.values());
    }
    
    public static Cliente getClienteByContaId(int id) {
    	for (Cliente cli : clientesCPF.values()) {
            //System.out.println(cli.getNome());
    		if (cli.getConta().getId() == id ) {
    			return cli;
    		}
        }
    	return null; 
    }
	
    public static void lerObjetos() {
		try {
			// caso os arquivos nao existam, serao criados vazios
			File f1 = new File(new File(".\\contasPIKS.csv").getCanonicalPath());
			File f2 = new File(new File(".\\lancamentos.csv").getCanonicalPath());
			if (!f1.exists() || !f2.exists()) {
				System.out.println("criando arquivo .csv vazio");
				FileWriter arqconta = new FileWriter(f1);
				FileWriter arqlancamento = new FileWriter(f2);
				arqconta.close();
				arqlancamento.close();
				return;
			}
		} catch (Exception ex) {
			throw new RuntimeException("criacao dos arquivos vazios:" + ex.getMessage());
		}

		try {
			int id;
			double saldo, limite;
			String linha;
			String[] partes;
			Cliente cliente;
			Conta conta;
			String chave, nome;
			int cpf;

			File f1 = new File(new File(".\\contasPIKS.csv").getCanonicalPath());
			Scanner arqconta = new Scanner(f1);
			System.out.println("Repositorio - lendo objetos...");
			while (arqconta.hasNextLine()) {
				linha = arqconta.nextLine().trim();
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				id = Integer.parseInt(partes[0]);
				chave = partes[1];
				saldo = Double.parseDouble(partes[2]);
				limite = Double.parseDouble(partes[3]); // <>0=ContaEspecial
				cpf = Integer.parseInt(partes[4]);
				nome = partes[5];
				//cliente = getCliente(cpf);
				cliente = new Cliente(cpf, nome);
				
				if (limite == 0.0)
					conta = new Conta(id, chave, saldo);
				else
					conta = new ContaEspecial(id, chave, saldo, limite);

				cliente.setConta(conta);
				conta.setCliente(cliente);
				adicionarConta(conta);
				adicionarCliente(cliente);
			}
			arqconta.close();
			
			Lancamento lanc ;
			LocalDateTime datahora;
			double valor;
			String tipo;
			File f2 = new File(new File(".\\lancamentos.csv").getCanonicalPath());
			Scanner arqlan = new Scanner(f2);
			while (arqlan.hasNextLine()) {
				linha = arqlan.nextLine().trim();
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				chave = partes[0];
				datahora = LocalDateTime.parse(partes[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
				valor = Double.parseDouble(partes[2]);
				tipo = partes[3];
				lanc = new Lancamento(valor,tipo);
				conta = localizarConta(chave);
				conta.adicionar(lanc);
			}
			arqlan.close();
		} catch (Exception ex) {
			throw new RuntimeException("leitura arquivo de contasPIKS:" + ex.getMessage());
		}

	}

	public static void gravarObjetos() {
		// gravar nos arquivos csv os objetos que est�o no reposit�rio
		try {
			File f1 = new File(new File(".\\contasPIKS.csv").getCanonicalPath());
			FileWriter arqconta = new FileWriter(f1);
			File f2 = new File(new File(".\\lancamentos.csv").getCanonicalPath());
			FileWriter arqlan = new FileWriter(f2);
			String linha;
			Double limite;
			System.out.println("Repositorio - gravando objetos...");
			for (Conta cta : contasPIKS.values()) {
				if (cta instanceof ContaEspecial esp)
					limite = esp.getLimite();
				else
					limite = 0.0;
				linha = cta.getId()+ ";" + cta.getChavePiks() + ";" + cta.getSaldo() + ";" + limite + ";" +
						cta.getCliente().getCpf() + ";"	+ cta.getCliente().getNome();
				arqconta.write(linha + "\n");
				// System.out.println("linha="+linha);

				if (!cta.getLancamento().isEmpty())
					for (Lancamento lan : cta.getLancamento()) {
						String s = lan.getDatahora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
						arqlan.write(cta.getChavePiks()+";" +s+ ";"+ lan.getValor()+";"+lan.getTipo() +"\n");
					}
				
			}
			arqconta.close();
			arqlan.close();
			
		} catch (Exception e) {
			throw new RuntimeException("problema na cria��o do arquivo  contasPIKS " + e.getMessage());
		}

	}
	
	
}
