package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {
	public Test get(Student student,Subject subject,School school,int no) throws Exception {
		Test test = new Test();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from test where student_no=? and subject_cd=? and school_cd=? and no=?");
			statement.setString(1, student.getNo());
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4,no);
			ResultSet rSet = statement.executeQuery();
			StudentDao studentDao = new StudentDao();
			SubjectDao subjectDao = new SubjectDao();
			SchoolDao schoolDao = new SchoolDao();
			if (rSet.next()) {
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setSubject(subjectDao.get(school.getCd(),rSet.getString("subject_cd")));
				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
			} else {
				test = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return test;
	}
	private List<Test> postFilter(ResultSet rSet,School school) throws Exception {
		List<Test> list = new ArrayList<>();
		try {
			StudentDao studentDao = new StudentDao();
			SubjectDao subjectDao = new SubjectDao();
			SchoolDao schoolDao = new SchoolDao();
			while (rSet.next()) {
				Test test = new Test();
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setSubject(subjectDao.get(school.getCd(),rSet.getString("subject_cd")));
				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Test> filter(int entYear,String classNum,Subject subject,int num,School school) throws Exception {
		List<Test> list = new ArrayList<>();
		List<Student> students = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		try {
			statement = connection.prepareStatement("select t.* from test t join student s on t.student_no = s.no where s.ent_year=? and t.class_num=? and t.subject_cd=? and t.no=? and t.school_cd=?");
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setInt(4, num);
			statement.setString(5, school.getCd());
			rSet = statement.executeQuery();
			list = postFilter(rSet, school);
			StudentDao sDao = new StudentDao();
			students = sDao.filter(school,entYear, classNum,true);
			for (Student stu : students) {
	            boolean late = false;
	            for (Test t : list) {
	                if (t.getStudent().getNo().equals(stu.getNo())) {
	                    late = true;
	                    break;
	                }
	            }
	            if (!late) {
	                Test test = new Test();
	                test.setStudent(stu);
	                test.setSubject(subject);
	                test.setSchool(school);
	                test.setNo(num);
	                test.setPoint(0);
	                test.setClassNum(classNum);
	                list.add(test);
	            }
	        }
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
				statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
	public boolean save(List<Test> list) throws Exception {
		Connection connection = getConnection();
		int count = 0;
		try {
			for (Test test : list) {
				if (save(test,connection)) {
					count ++;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	private boolean save(Test test,Connection connection) throws Exception {
		PreparedStatement statement = null;
		int count = 0;
		try {
			statement = connection.prepareStatement("update test set point=? where student_no=? and subject_cd=? and school_cd=? and no=? and class_num=? ");
			statement.setInt(1, test.getPoint());
			statement.setString(2, test.getStudent().getNo());
			statement.setString(3, test.getSubject().getCd());
			statement.setString(4, test.getSchool().getCd());
			statement.setInt(5, test.getNo());
			statement.setString(6, test.getClassNum());
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
				statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
}
