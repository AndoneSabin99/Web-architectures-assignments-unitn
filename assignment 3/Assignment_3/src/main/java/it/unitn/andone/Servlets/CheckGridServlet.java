package it.unitn.andone.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckGridServlet", value = "/CheckGridServlet")
public class CheckGridServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the time parameter
        String date = request.getParameter("t");

        //get servlet context
        ServletContext ctx = getServletContext();
        String updatedTimestamp = (String) ctx.getAttribute("updatedTimestamp");

        //check if the timestamp of the last modification to the SSEngine is the same as the actual timestamp
        //if it is true it means that a modification has just been done, thus we need to say that we need to
        //update the grid for the client
        boolean update = false;
        if (updatedTimestamp != null){
            if (updatedTimestamp.equals(date)){
                update = true;
                //System.out.println("Update is " + update + " at time " + date);
            }
        }
        System.out.println("Update is " + update + " at time " + date);

        //send json
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("{\"up\": \"" + update + "\"}");
        }
    }
}
