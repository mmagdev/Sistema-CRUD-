package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Funcionario;
import repositories.FuncionarioRepository;

public class FuncionarioController {

	// Atributo
	private Scanner scanner = new Scanner(System.in);
	private FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

	/*
	 * Método para verificar qual opção o usuário deseja executar:
	 */

	public void gerenciarFuncionarios() throws Exception {

		System.out.println("(1) CADASTRAR FUNCIONÀRIO");
		System.out.println("(2) ATUALIZAR FUNCIONÀRIO");
		System.out.println("(3) EXCLUIR FUNCIONÀRIO");
		System.out.println("(4) CONSULTAR FUNCIONÀRIO");

		System.out.print("\nESCOLHA UMA OPÇÃO: ");
		var opcao = Integer.parseInt(scanner.nextLine());

		switch (opcao) {
		case 1:
			cadastrarFuncionario();
			break;

		case 2:
			atualizarFuncionario();
			break;
			
		case 3:
			excluirFuncionario();
			break;
			
		case 4:
			consultarFuncionarios();
			break;
			
		default:
			System.out.println("\n OPÇÃO INVÁLIDA!");
		}
		System.out.print("\nDESEJA CONTINUAR? (S/N): ");
		var continuar = scanner.nextLine();

		if (continuar.toUpperCase().equals("S")) {
			gerenciarFuncionarios(); // recursividade
		} else {
			System.out.println("\nFIM DO PROGRAMA!");
		}

	}
	/*
	 * Método para permitir que o usuário preencha os dados de um funcionário e
	 * depois enviar o mesmo para o reposítório gravar no banco de dados (chamar o
	 * método inserir)
	 */

	public void cadastrarFuncionario() throws Exception {
		System.out.println("*** CADASTRO DE FUNCIONÁRIO: ***");

		var funcionario = new Funcionario(); // criando um funcionario
		funcionario.setId(UUID.randomUUID()); // Gerando o ID do funcionário

		System.out.print("NOME DO FUNCIONÀRIO....: ");
		funcionario.setNome(scanner.nextLine());

		System.out.print("MATRÍCULA............: ");
		funcionario.setMatricula(scanner.nextLine());

		System.out.print("CPF..................: ");
		funcionario.setCpf(scanner.nextLine());

		System.out.print("SALÁRIO..............: ");
		funcionario.setSalario(Double.parseDouble(scanner.nextLine()));

		// Salvando o funcionário no banco de dados
		funcionarioRepository.inserir(funcionario);

		System.out.println("\nFUNCIONÁRIO CADASTRADO COM SUCESSO!");

	}

	public void atualizarFuncionario() throws Exception {

		System.out.println("*** ATUALIZAÇÃO DE FUNCIONÁRIO: ***");

		var funcionario = new Funcionario(); // criando um funcionario

		System.out.print("ID DO FUNCIONÀRIO....: ");
		funcionario.setId(UUID.fromString(scanner.nextLine()));

		System.out.print("NOME DO FUNCIONÀRIO....: ");
		funcionario.setNome(scanner.nextLine());

		System.out.print("MATRÍCULA............: ");
		funcionario.setMatricula(scanner.nextLine());

		System.out.print("CPF..................: ");
		funcionario.setCpf(scanner.nextLine());

		System.out.print("SALÁRIO..............: ");
		funcionario.setSalario(Double.parseDouble(scanner.nextLine()));

		// Atualizando o funcionário no banco de dados
		if (funcionarioRepository.atualizar(funcionario)) {
			System.out.println("\nFUNCIONÁRIO ATUALIZADO COM SUCESSO!");
		} else {
			System.out.println("\nNENHUM REGISTRO FOI ALTERADO. VERIFIQUE O ID INFORMADO.");
		}

	}

	/*
	 * Método para excluir um funcionário do banco de dados
	 * 
	 */

	public void excluirFuncionario() throws Exception {
		System.out.println("*** EXCLUSÃO DE FUNCIONÁRIO ***");

		System.out.print("ID DO FUNCIONÁRIO......: ");
		var id = UUID.fromString(scanner.nextLine());
		
		//Excluindo o funcionário do BD
		if(funcionarioRepository.excluir(id)) {
			System.out.println("\nFUNCIONÁRIO EXCLUÍDO COM SUCESSO!");
		}
		else {
			System.out.println("\nNENHUM FUNCIONÁRIO EXCLUÍDO. VERIFIQUE O ID INFORMADO.");
			
		}
		
	}
	
	/*
	 * Método para consultar os funcionários
	 */
	public void consultarFuncionarios() throws Exception {
		System.out.println("*** CONSULTA DE FUNCIONÁRIOS ***");
		
		System.out.print("*** CONSULTE O NOME DESEJADO ***");
		var nome = scanner.nextLine();
		
		//Consultando os funcionários no banco de dados pelo nome
		var lista =  funcionarioRepository.consultarPorNome(nome);
		
		//Imprimindo os resultados
		System.out.println("\nQUANTIDADE DE FUNCIONÁRIOS ENCONTRADOS: " + lista.size());
		
		//Imprimir cada funcionário 
		for (var funcionario : lista) { //for each = para cada
			
			System.out.println("..");
			System.out.println("ID........: " + funcionario.getId());
			System.out.println("NOME......: " + funcionario.getNome());
			System.out.println("CPF.......: " + funcionario.getCpf());
			System.out.println("MATRÍCULA.: " + funcionario.getMatricula());
			System.out.println("SALÁRIO...: " + funcionario.getSalario());
		}
	}

}




















