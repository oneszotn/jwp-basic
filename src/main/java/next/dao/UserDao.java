package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplete;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user){
    	JdbcTemplete temp = new JdbcTemplete();
    	String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	temp.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
   }

    public void update(User user){
    	JdbcTemplete temp = new JdbcTemplete();
    	String sql = "UPDATE USERS SET PASSWORD = ?, NAME = ?, EMAIL = ? WHERE USERID = ?";
    	temp.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    
    public List<User> findAll(){
		RowMapper<List<User>> rm = new RowMapper<List<User>>() {
			
			@Override
			public List<User> mapRow(ResultSet rs) throws SQLException {
				List<User> users = new ArrayList<User>();
				User user = null;
				if (rs.next()) {
					user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
	                users.add(user);
					return users;
		        }
				return null;
			}
		};
		
    	JdbcTemplete temp = new JdbcTemplete();
    	String sql = "SELECT userId, password, name, email FROM USERS";
    	return temp.executeQuery(sql, rm);
    	
    }

    public User findByUserId(String userId){
    	RowMapper<User> rm = rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
    	JdbcTemplete temp = new JdbcTemplete();
    	String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
    	return temp.executeQuery(sql, rm, userId);
    	
    }

    
}
