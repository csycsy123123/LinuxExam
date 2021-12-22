package com.atguigu.jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	
	StudentService studentService = new StudentService();
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
	  //构造对象
	  Student student = new Student();
	  student.setId(Integer.valueOf(request.getParameter("id")));
	  student.setName(request.getParameter("name"));
	  student.setAge(Integer.valueOf(request.getParameter("age")));
	  //执行
      String result = studentService.updateStudent(student);
	  
      out.println("{\"result\":" + result + "}");
      out.flush();
      out.close();
   }
}
