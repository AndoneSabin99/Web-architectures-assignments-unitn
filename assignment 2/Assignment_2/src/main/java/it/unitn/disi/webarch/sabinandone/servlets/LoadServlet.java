package it.unitn.disi.webarch.sabinandone.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoadServlet", value = "/LoadServlet")
public class LoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/banner.jsp").include(request, response);
        request.getRequestDispatcher("/gamepage.jsp").include(request, response);
    }

}
