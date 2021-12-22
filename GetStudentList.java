package com.atguigu.jdbc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/GetStudentList")
public class GetStudentList extends HttpServlet {
	
	StudentService studentService = new StudentService();
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
	  
	  //构造对象
	  Student student = new Student();
	  //student.setId(Integer.valueOf(request.getParameter("id")));
	  //student.setName(request.getParameter("name"));
	  //student.setAge(Integer.valueOf(request.getParameter("age")));
	  //执行
      List<Student> stuList = studentService.getStudentList(student);
      Gson gson = new Gson();
      String json = gson.toJson(stuList, new TypeToken<List<Student>>() {
      }.getType());
      out.println(json);
      out.flush();
      out.close();
   }
}
