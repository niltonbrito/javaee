package model;

import java.sql.*;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/dbagenda";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "";


	/** The conexao. */
	Connection conexao = null;
	
	/** The pst. */
	PreparedStatement pst = null;
	
	/** The result. */
	ResultSet result = null;

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {

		try {
			// Indica o caminho da classe Driver na biblioteca do jdbc
			Class.forName(driver);
			// Faz a conexão com o banco de dados
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, "Erro ao se conectar com o banco de
			// dados.", "Aviso de Falha", 0);
			System.out.println(e);
			return null;
		} catch (ClassNotFoundException e) {
			// JOptionPane.showMessageDialog(null, "Classe JDBC Driver nao encontrada.",
			// "Aviso de Falha", 0);
			System.out.println(e);
			return null;
		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null,"Ocorreu um erro de conex\u00e3o.
			// Verifique a Base de Dados indicada!" + e.getMessage(), "Conexão", 3);
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email)values(?,?,?)";
		try {
			// Abrir Conexão com banco de dados
			conexao = conectar();
			// Preparar a Query para execução no banco de dados
			PreparedStatement pst = conexao.prepareStatement(create);
			// Substituir ps parâmentros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a Query
			pst.executeUpdate();
			// Finalizar conexão com banco de dados
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	/**
	 * Listar contato.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContato() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			// Abrir Conexão com banco de dados
			conexao = conectar();
			// Preparar a Query para execução no banco de dados
			pst = conexao.prepareStatement(read);
			// Executar a Query
			result = pst.executeQuery();
			// o laço de repetição abaixo será executado enquanto houver contatos
			while (result.next()) {
				// Variáveis de apoio que recebem os dados do banco de dados
				String idcon = result.getString(1);
				String nome = result.getString(2);
				String fone = result.getString(3);
				String email = result.getString(4);
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			// Finalizar conexão com banco de dados
			conexao.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			conexao = conectar();
			pst = conexao.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			result = pst.executeQuery();
			// Setar as variáveis JavaBeans
			while (result.next()) {
				contato.setIdcon(result.getString(1));
				contato.setNome(result.getString(2));
				contato.setFone(result.getString(3));
				contato.setEmail(result.getString(4));
			}
			// Finalizar conexão com banco de dados
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}


	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		String update = "update contatos set nome=?, fone=?, email=? where idcon=?";
		try {
			conexao = conectar();
			pst = conexao.prepareStatement(update);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			// Finalizar conexão com banco de dados
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon = ?";
		try {
			conexao = conectar();
			pst = conexao.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			// Finalizar conexão com banco de dados
			conexao.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
