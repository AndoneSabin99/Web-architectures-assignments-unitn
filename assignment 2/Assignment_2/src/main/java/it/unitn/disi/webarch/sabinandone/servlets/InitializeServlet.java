package it.unitn.disi.webarch.sabinandone.servlets;

import it.unitn.disi.webarch.sabinandone.utilities.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "InitializeServlet", value = "/InitializeServlet")
public class InitializeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        //get some attributes from session, one of them being logged, necessary to redirect correctly the user
        Boolean logged = (Boolean)session.getAttribute("logged");
        UserBean userbean = (UserBean)session.getAttribute("UserBean");
        Integer points = (Integer)session.getAttribute("points");
        if (logged == null){
            request.getRequestDispatcher("/login.jsp").include(request, response);
            return;
        }
        else{

            //the user is connected, so we either put him into the connected users list or we update
            //his/her points
            HashMap<String,Integer> connectedUserlist = new HashMap<>();
            connectedUserlist = (HashMap<String,Integer>)session.getAttribute("connectedUserlist");

            connectedUserlist.put(userbean.getUser(),points);
            session.setAttribute("connectedUserlist",connectedUserlist);

            //check if admin
            if(userbean.getUser().equals("admin")){
                request.getRequestDispatcher("/banner.jsp").include(request, response);
                request.getRequestDispatcher("/controlpage.jsp").include(request, response);
            }else{
                request.getRequestDispatcher("/banner.jsp").include(request, response);
                request.getRequestDispatcher("/mainpage.jsp").include(request, response);
            }
            }
    }

}
