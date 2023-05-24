package bdlivraria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class InsereLivros {
	
	private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
	private final String user = "postgres";
	private final String password = "Gustavo@";
	Connection conn = null;
	
	private static final String INSERE = "insert into livro (id_isbn, nm_titulo, vl_preco) values (?,?,?)";
	
	public Connection connect() {
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			
			if(conn!=null) {
				System.out.println("Conexão com PostGreSQL estabelecida com sucesso!");
			}else {
				System.out.println("Falha na conexão com o PostGreSQL");
			}
			Statement statement = conn.createStatement(); // Criando instancia do objeto que representa um canal de comunicação com banco de dados
			ResultSet resultSet = statement.executeQuery("SELECT VERSION()");//Esta consulta obtem a versão do PostGreSQL
			
			if(resultSet.next()) {
				System.out.printf(resultSet.getString(1));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	public void insereDados(int ID_isbn, String titulo, Double preco)throws SQLException {
		System.out.println(INSERE);
		
		try (Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement(INSERE)){//final do TRY
			
			preparedStatement.setInt(1,ID_isbn);
			preparedStatement.setString(2,titulo);
			preparedStatement.setDouble(3, preco);
			
			System.out.println("Inserindo o livro:" + ID_isbn + " " + titulo + " " + preco);
			
			preparedStatement.executeUpdate();
				 
	}catch(SQLException e) {
		e.getMessage();
		
	}
	}
	
	/*public static void main(String[]args)throws SQLException {
		InsereLivros insereLivro = new InsereLivros();
		insereLivro.insereDados(123456, "Harry Potter", 100.0 );
	}*/
	
}

	