package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplete {

	public void executeUpdate(String sql, Object... parameters){
		
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            
            for (int i = 0; i < parameters.length; i++) {
				Object param = parameters[i];
				pstmt.setObject(i+1, param);
			}
            
            pstmt.executeUpdate();
        }catch (Exception e) {
        	throw new DataAccessException(e);
        } finally {
        	try{
	            if (pstmt != null) {
	                pstmt.close();
	            }
	
	            if (con != null) {
	                con.close();
	            }
        	}catch (Exception e) {
        		throw new DataAccessException(e);
			}
        }
    }
	
	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameters){
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
              
            for (int i = 0; i < parameters.length; i++) {
				Object param = parameters[i];
				pstmt.setObject(i+1, param);
			}

            rs = pstmt.executeQuery();
            return rm.mapRow(rs);
        }catch (Exception e) {
        	throw new DataAccessException(e);
        } finally {
        	try{
	            if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        }catch (Exception e) {
	        	throw new DataAccessException(e);
	        }
        }
	}
}
