package it.unitn.disi.webarch.sabinandone.servlets;

//import it.unitn.disi.webarch.sabinandone.utilities.LoginBean;
import it.unitn.disi.webarch.sabinandone.utilities.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    //function to verify that the user has inserted his/her credentials correctly
        public boolean validate(UserBean userbean, HttpSession session) throws FileNotFoundException, IOException, ClassNotFoundException {

        boolean valid = false;
        ArrayList<UserBean> userlist = new ArrayList<>();
        userlist = (ArrayList<UserBean>)session.getAttribute("userlist");

        for (UserBean u: userlist){
            //System.out.println("User: " + u.toString());
            if (u.getUser().equals(userbean.getUser())){
                if (u.getPassword().equals(userbean.getPassword())){
                    valid = true;
                }
            }
        }
        return valid;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get the parameters from login page
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //create new userbean
        UserBean userbean = new UserBean();
        userbean.setUser(username);
        userbean.setPassword(password);

        HttpSession session = request.getSession();

        try {
            //validate is the method that checks if the credentials are correct or not
            if (!this.validate(userbean,session)) {
                String message = "Wrong username or password!";
                request.setAttribute("message", message);
                request.getSession().setAttribute("logged", false);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        request.getSession().setAttribute("logged", true);
        request.getSession().setAttribute("UserBean", userbean);
        request.getSession().setAttribute("points",0);
        response.sendRedirect("InitializeServlet");
    }
}
