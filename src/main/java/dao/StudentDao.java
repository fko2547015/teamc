package dao;

import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
	private String baseSql;
	public Student get(String no) throws Exception {
		return
	}
	private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
		return
	}
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		return
	}
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		return
	}
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		return
	}
	public boolean save(Student student) throws Exception {
		return
	}
}
