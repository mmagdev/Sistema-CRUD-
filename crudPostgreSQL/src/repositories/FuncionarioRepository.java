package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Funcionario;
import factories.ConnectionFactory;

public class FuncionarioRepository {

	// Atributos
	private ConnectionFactory connectionFactory = new ConnectionFactory();

	/*
	 * Método para inserir um funcionário no banco de dados Comando SQL: INSERT
	 */
	public void inserir(Funcionario funcionario) throws Exception {

		// Obtendo a conexão do banco
		var connection = connectionFactory.getConnection();

		// Escrevendo o comando SQL para ser executado no Banco de Dados
		var sql = """
				INSERT INTO funcionario(id, nome, matricula, cpf, salario)
				VALUES(?,?,?,?,?)
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(sql);
		statement.setObject(1, funcionario.getId());
		statement.setString(2, funcionario.getNome());
		statement.setString(3, funcionario.getMatricula());
		statement.setString(4, funcionario.getCpf());
		statement.setDouble(5, funcionario.getSalario());
		statement.execute();

		connection.close();// Fechar a conexão

	}

	/*
	 * Método para atualizar um funcionário no Banco de Dados Comando SQL: UPDATE
	 * 
	 */
	public boolean atualizar(Funcionario funcionario) throws Exception {

		// Obtendo a conexão do banco
		var connection = connectionFactory.getConnection();

		// Escrevendo o comando SQL para ser executado no banco de dados
		var sql = """
				UPDATE funcionario
				SET
					nome = ?,
					cpf = ?,
					matricula = ?,
					salario = ?
				WHERE
					id = ?
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(sql);
		statement.setString(1, funcionario.getNome());
		statement.setString(2, funcionario.getMatricula());
		statement.setString(3, funcionario.getCpf());
		statement.setDouble(4, funcionario.getSalario());
		statement.setObject(5, funcionario.getId());
		var rowsAffected = statement.executeUpdate();

		connection.close(); // Fechar a conexão

		// Retornando true se o resultado for > que zero
		return rowsAffected > 0;
	}

	/*
	 * Método para excluir um funcionário do banco de dados Comando SQL: DELETE
	 * 
	 */

	public boolean excluir(UUID id) throws Exception {

		// Obtendo a conexão
		var connection = connectionFactory.getConnection();

		// Escrevendo o comando SQL para ser executado no banco de dados
		var sql = """
					DELETE FROM funcionario
					WHERE id = ?
				""";

		// Executando o comando SQL no banco de dados
		var statement = connection.prepareStatement(sql);
		statement.setObject(1, id);
		var rowsAffected = statement.executeUpdate();

		connection.close(); // Fechar a conexão

		// Retornando true se o resultado for maior que 0
		return rowsAffected > 0;
	}

	/*
	 * Método para consultar funcionários
	 * Comando SQL: SELECT
	 */
	public List<Funcionario> consultarPorNome(String nome) throws Exception {
		// Obtendo a conexão
		var connection = connectionFactory.getConnection();

		// Escrevendo o comando SQL para ser executado no banco de dados
		var sql = """
				SELECT * FROM funcionario
				WHERE nome ILIKE ?
				""";

		// Executando o comando SQL
		var statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		var result = statement.executeQuery();

		// Criando uma lista para armazenar funcionários
		var lista = new ArrayList<Funcionario>();

		// Ler cada registro obtido do banco de dados
		while (result.next()) {
			var funcionario = new Funcionario();

			funcionario.setId(UUID.fromString(result.getString("id")));
			funcionario.setNome(result.getString("nome"));
			funcionario.setCpf(result.getString("cpf"));
			funcionario.setMatricula(result.getString("matricula"));
			funcionario.setSalario(result.getDouble("salario"));

			lista.add(funcionario); // Adicionando o funcionário dentro da lista
		}

		connection.close(); // Fechar a conexão

		return lista;

	}

}
