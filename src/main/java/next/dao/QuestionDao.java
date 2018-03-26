package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Question;
import next.model.User;

public class QuestionDao {

    public List<Question> qnaList() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS";
        
        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        return jdbcTemplate.query(sql, rm);
    }
    
    public Question qnaDetail(long questionId) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS where questionId = ?";
    	
    	RowMapper<Question> rm = new RowMapper<Question>() {
    		@Override
    		public Question mapRow(ResultSet rs) throws SQLException {
    			return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
    					rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
    		}
    	};
    	
    	return jdbcTemplate.queryForObject(sql, rm, questionId);
    }
}