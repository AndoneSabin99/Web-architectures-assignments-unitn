package it.unitn.andone.assignment_app.assignment_4_app;

import it.unitn.andone.assignment_4.Course;
import it.unitn.andone.assignment_4.StudentBeanIF;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getting the matriculation number through the request
        String matriculation = request.getParameter("matr");

        response.setContentType("text/html");
        Context ctx = null;
        StudentBeanIF student=null;
        Collection<Course> courses = null;
        System.out.println("Up to here it works");
        try {
            ctx = new InitialContext();
            String name="java:module/StudentBean!it.unitn.andone.assignment_4.StudentBeanIF";
            student= (StudentBeanIF) ctx.lookup(name);
            courses = student.getCourses(matriculation);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>" + matriculation + "</title></head><body>");
        out.println("<h1>Name: " + student.getName(matriculation) + "</h1><br>");
        out.println("<h1>Surname: " + student.getSurname(matriculation) + "</h1><br>");
        out.println("<h1>Courses: </h1><br>");

        for (Course c : courses) {
            out.println("<p>"+c.getName()+" </p><br>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
