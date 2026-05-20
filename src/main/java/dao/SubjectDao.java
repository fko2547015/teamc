package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	
	//1件取得
	public Subject get(String schoolcd, String cd) throws Exception {
		Subject subject = null;
		
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from subject where school_cd=? and cd=?");
			statement.setString(1, schoolcd);
			ResultSet rSet = statement.executeQuery();
			
			if (rSet.next()) {
				subject = new Subject();
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
	
			}
		} finally {
			if(statement != null)statement.close();
			if(connection != null)connection.close();
		}
		return subject;
	}
	
	//一覧取得
	public java.util.List<Subject> filter(School school) throws Exception {
		java.util.List<Subject> list = new java.util.ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from subject where school_cd=? order by cd");
			ResultSet rSet = statement.executeQuery();
			while (rSet.next()) {
				Subject subject = new Subject();
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				list.add(subject);
		    }
		}finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		return list;
	}
	
	//保存、登録、更新
	public boolean save(School school, Subject subject) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement= null;
		int count = 0;
		try {
			Subject old = get(school.getCd(), subject.getCd());
			if(old == null) {
				statement = connection.prepareStatement("insert into subject(school_cd, cd, name) values(?, ?, ?)");
				statement.setString(1, school.getCd());
				statement.setString(2, subject.getCd());
				statement.setString(3, subject.getName());
				
			}else {
				//更新
				statement = connection.prepareStatement("update subject set name=? where school_cd=? and cd=?");
				statement.setString(1, subject.getName());
				statement.setString(2, school.getCd());
				statement.setString(3, subject.getCd());
			}
			count = statement.executeUpdate();
			
		}finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		
		return count > 0;
	}
	
	//削除
	public boolean delete(School school, Subject subject) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try {
			statement = connection.prepareStatement("delete from subject where school_cd=? and cd=?");
			statement.setString(1, school.getCd());
			statement.setString(1, subject.getCd());
			count = statement.executeUpdate();
		}finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		
		return count >0;
	}
}

