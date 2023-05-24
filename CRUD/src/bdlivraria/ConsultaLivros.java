package bdlivraria;

import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConsultaLivros {	

		private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
		private final String user = "postgres";
		private final String password = "Gustavo@";
		Connection conn = null;
		
		private static final String QUERY_PRECO = "select * from livro where vl_preco >= ?";
		private static final String QUERY_NOME = "SELECT * FROM livro WHERE nm_titulo LIKE ?";
		
		//estabelecendo conexão com servidor PostGre
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
		
		public void getLivrosPorPreco() {
			
			try {
				Connection connection = DriverManager.getConnection(url,user,password);
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PRECO);
				preparedStatement.setDouble(1, Double.parseDouble(JOptionPane.showInputDialog("Entre com o preço minimo que deseja consultar: >=")));
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					String titulo = rs.getString("nm_titulo");
					Double preco = rs.getDouble("vl_preco");
					System.out.println(titulo + " - " + preco + "\n");
				}
			}catch(SQLException e) {
				printSQLException(e);
			}
			
		}
		
		public void getLivrosPorNome() {
			
			try {
				Connection connection = DriverManager.getConnection(url,user,password);
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_NOME);
				System.out.println(preparedStatement);
				preparedStatement.setString(1, JOptionPane.showInputDialog("Entre com o titulo:") + "%");
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					String titulo = rs.getString("nm_titulo");
					Double preco = rs.getDouble("vl_preco");
					System.out.println(titulo + " - " + preco + "\n");
				}
			}catch(SQLException e) {
				printSQLException(e);
			}
		}
		
	private String toLowerCase(String nome) {
			// TODO Auto-generated method stub
			return null;
		}

	public static void printSQLException(SQLException ex) {
    	//imprime detalhadamente os erros ocorridos
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
	}
	
	/*public static void main(String[]args) {
		
		ConsultaLivros app = new ConsultaLivros();
		
		app.connect();
		System.out.printf("\nRealizando consulta na tabela Livro por preco");
		app.getLivrosPorPreco(125.0);
		System.out.printf("\nRealizando consulta na tabela Livro por titulo");
		app.getLivrosPorNome("Sistemas");
		
	}*/
	
}	

	
