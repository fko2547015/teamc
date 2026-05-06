package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Subject;

public class SubjectDao extends Dao {
	
	//1件取得
	public Subject get(String subjectCd) throws Exception {
		Subject subject = null;
		
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from subject where subject_cd=?");
			statement.setString(1, subjectCd);
			ResultSet rSet = statement.executeQuery();
			
			if (rSet.next()) {
				subject = new Subject();
				subject.setCd(rSet.getString("subject_cd"));
				subject.setName(rSet.getString("subject_name"));
	
			}
		} finally {
			if(statement != null)statement.close();
			if(connection != null)statement.close();
		}
		return subject;
	}
	
	//一覧取得
	public java.util.List<Subject> filter() throws Exception {
		java.util.List<Subject> list = new java.util.ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from subject order by subjec_cd");
			ResultSet rSet = statement.executeQuery();
			while (rSet.next()) {
				Subject subject = new Subject();
				subject.setCd(rSet.getString("subject_cd"));
				subject.setName(rSet.getString("subject_name"));
				list.add(subject);
		    }
		}finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		return list;
	}
	
	//保存、登録、更新
	public boolean save(Subject subject) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement= null;
		int count = 0;
		try {
			Subject old = get(subject.getCd());
			if(old == null) {
				statement = connection.prepareStatement("insert into subject(subject_cd, subject_name) values(?,?)");
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
				
			}else {
				//更新
				statement = connection.prepareStatement("update subject set subject_name=? where subject_cd=?");
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getCd());
			}
			count = statement.executeUpdate();
			
		}finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		
		return count > 0;
	}
	
	//削除
	public boolean delete(Subject subject) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try {
			statement = connection.prepareStatement("delete from subject where subject_cd=?");
			statement.setString(1, subjectCd);
			count = statement.executeUpdate();
		}finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		
		return count >0;
	}
}
