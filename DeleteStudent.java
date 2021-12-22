package com.atguigu.jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/DeleteStudent")
public class DeleteStudent extends HttpServlet {
	
	StudentService studentService = new StudentService();
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
	  
	  Integer id = Integer.valueOf(request.getParameter("id"));
	  
      String result = studentService.deleteStudent(id);

      out.println("{\"result\":" + result + "}");
      out.flush();
      out.close();
   }
}
