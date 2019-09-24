package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	private Connection connection = null;
	public DAO() {
		try {
			String url = System.getenv("mysql_url");
			String user = System.getenv("mysql_user");
			String password = System.getenv("mysql_password");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Form> AllForms(){
		List<Form> forms = new ArrayList<Form>();
		PreparedStatement stmt = null;
		try {
			stmt = ((java.sql.Connection) connection).    
					prepareStatement("SELECT * FROM Form");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while(rs.next()){
				Form form = new Form();
				form.setId(rs.getInt("id"));
				form.setUserId(rs.getInt("userId"));
				form.setAnswer(rs.getString("answer"));
				form.setQuestion(rs.getString("question"));
				form.setAutor(rs.getString("username"));
				forms.add(form);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forms;
		}
	
	public List<Form> getMyPosts(User user){
		List<Form> forms = new ArrayList<Form>();
		PreparedStatement stmt = null;
		try {
			stmt = ((java.sql.Connection) connection).    
					prepareStatement("SELECT * FROM Form WHERE userId = ?");
			stmt.setInt(1, user.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while(rs.next()){
				Form form = new Form();
				form.setUserId(rs.getInt("userId"));
				form.setAnswer(rs.getString("answer"));
				form.setQuestion(rs.getString("question"));
				forms.add(form);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forms;
		}
	
	public User getLogged() {
		List<User> logged = new ArrayList<User>();
		PreparedStatement stmt = null;
		User ninguem = new User();
		logged.add(ninguem);
		try {
			String sql ="SELECT * FROM ActiveUser Where id=?";
			stmt = ((java.sql.Connection) connection).    
					prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.execute();
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			if (rs == null){
				
			}else {
				while(rs.next()){
					User user = new User();
					user.setUsername(rs.getString("userActive"));
					user.setPassword(rs.getString("password"));
					user.setId(rs.getInt("confirmation"));
					System.out.println(rs.getInt("confirmation")+"hhhhhhhhhhhhhhhhhhhhhhh");
					logged.set(0,user);
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return logged.get(0);
	}
	
	public void setLogged(String username,String password,int confirmation) {
		PreparedStatement stmt = null;
		try {
			String sql ="UPDATE ActiveUser SET " + "userActive=?, password=?,confirmation=? WHERE id=?";
			stmt = ((java.sql.Connection) connection).    
					prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setInt(3, confirmation);
			stmt.setInt(4, 0);
			stmt.execute();
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Boolean VerificaUser(User user){
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt = null;
		try {
			stmt = ((java.sql.Connection) connection).    
					prepareStatement("SELECT * FROM User Where username=? AND password=?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			
		while (rs.next()) {
			User UserFromDB = new User();
			UserFromDB.setUsername(rs.getString("username"));
			UserFromDB.setPassword(rs.getString("password")); 
			users.add(UserFromDB);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if (users.isEmpty()) {
			return false;
		}
		else;
			return true;
	}
	
	public void AdicionaUser(User user){
		PreparedStatement stmt = null;
		try {
			stmt = ((java.sql.Connection) connection).prepareStatement("INSERT INTO User (username,password) VALUES (?,?)");
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.execute();
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void adiciona(String question, int userId, String username){
		PreparedStatement stmt = null;
		try {
			stmt = ((java.sql.Connection) connection).    
					prepareStatement("INSERT INTO Form (question,userId,username) VALUES (?,?,?)");
			stmt.setString(1, question);
			stmt.setInt(2, userId);
			stmt.setString(3, username);
			stmt.execute();
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void edita(String answer,int id){
		String sql ="UPDATE Form SET " + "answer=? WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = ((java.sql.Connection) connection).prepareStatement(sql);
			stmt.setString(1, answer);
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int pegaId(String user){
		String sql ="SELECT * FROM User Where username=?";
		PreparedStatement stmt = null;
		int userId = -1;
		try {
			stmt = ((java.sql.Connection) connection).prepareStatement(sql);
			stmt.setString(1, user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while (rs.next()) {
				userId = rs.getInt("id");
				System.out.println("UserId:"+userId);
				}
				rs.close();
				stmt.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return userId;
		
	}
	
	public void remove(int id){
		PreparedStatement stmt = null;
		try {
			stmt = ((java.sql.Connection) connection).prepareStatement("DELETE FROM Form WHERE id=?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
