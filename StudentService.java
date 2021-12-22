package com.atguigu.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String URL = "jdbc:mysql://180.76.239.115:3306/student_info";
    final static String USER = "root";
    final static String PASS = "csy123456@";
    static Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public void closeConn() {

        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询学生信息
     */
    public List<Student> getStudentList(Student student) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Student> stuList = new ArrayList<Student>();
        List<Object> args = new ArrayList<Object>();
        try {
            String sql = "select * from student where 1=1 ";
            if (student.getId() != null) {
                sql += "and id=?";
                args.add(student.getId());
            }
            if (student.getName() != null) {
                sql += "and name=?";
                args.add(student.getName());
            }
            if (student.getAge() != null) {
                sql += "and age=?";
                args.add(student.getAge());
            }
            stmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.size(); i++) {
                stmt.setObject(i + 1, args.get(i));
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getInt("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                stuList.add(stu);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return stuList;
    }

    /**
     * 查询单个学生信息
     */
    public Student getStudentById(Integer id) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Student stu = new Student();
        try {
            String sql = "select * from student where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stu.setId(rs.getInt("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return stu;
    }

    /**
     * 添加一个学生
     */
    public String addStudent(Student student) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String sql = "insert into student(id,name,age) values(default,?,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            int row = stmt.executeUpdate();

            if (row > 0) {
                return "success";
            }


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return "failed";
    }

    /**
     * 删除一个学生
     */
    public String deleteStudent(Integer id) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            String sql = "delete from student where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int row = stmt.executeUpdate();

            if (row > 0) {
                return "success";
            }


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return "failed";
    }

    /**
     * 修改一个学生
     */
    public String updateStudent(Student student) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (student.getId() == null || (student.getName() == null && student.getAge() == null)) {
                return "failed";
            }
            List<Object> args = new ArrayList<Object>();
            String sql = "update student set ";

            if (student.getName() != null) {
                sql += "name=?,";
                args.add(student.getName());
            }
            if (student.getAge() != null) {
                sql += "age=?,";
                args.add(student.getAge());
            }
            args.add(student.getId());
            sql = sql.substring(0, sql.length() - 1) + " where id = ?";
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < args.size(); i++) {
                stmt.setObject(i + 1, args.get(i));
            }

            int row = stmt.executeUpdate();

            if (row > 0) {
                return "success";
            }


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return "failed";
    }
}