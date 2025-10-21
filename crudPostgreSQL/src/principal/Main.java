package principal;

import controllers.FuncionarioController;

public class Main {

	public static void main(String[] args) {
		
		try {
			System.out.println("\n*** SISTEMA DE CRUD DE FUNCIONÀRIOS: ***\n");
			
			//Instanciando a classe de controle do funcionário
			var funcionarioController = new FuncionarioController();
			
			//realizar o cadastro do funcionário
			funcionarioController.gerenciarFuncionarios();
			
			
		}
		catch(Exception e) {
			System.out.println("\nERRO: " + e.getMessage());
		}

	}

}
