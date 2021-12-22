package com.atguigu.jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/GetStudentById")
public class GetStudentById extends HttpServlet {
	static final String REDIS_URL = "180.76.230.115";
	StudentService studentService = new StudentService();
	
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
	  Integer id = Integer.valueOf(request.getParameter("id"));

		/*Jedis jedis = new Jedis(REDIS_URL);
		String json = jedis.get(request.getParameter("id"));
		if (json == null) {
            Student stu = studentService.getStudentById(id);

            Gson gson = new Gson();
            json = gson.toJson(stu, new TypeToken<Student>() {
            }.getType());

            jedis.set(request.getParameter("id"), json);
            out.println(json);

        } else {
            out.println(json);
        }
		*/
       Student stu = studentService.getStudentById(id);
       out.println(stu);
      out.flush();
      out.close();
   }
}
