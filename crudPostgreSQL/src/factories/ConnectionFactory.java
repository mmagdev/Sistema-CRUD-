package factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	/*
	 * Criando um método para abrir e retornar uma conexão com o banco de dados da
	 * aplicação
	 */

	public Connection getConnection() throws Exception {

		// Informações para conexão com banco de dados
		var host = "jdbc:postgresql://localhost:5432/bd_funcionarios";
		var user = "postgres";
		var pass = "coti";

		// Abrindo conexão com o banco de dados
		var connection = DriverManager.getConnection(host, user, pass);

		return connection;
	}

}
