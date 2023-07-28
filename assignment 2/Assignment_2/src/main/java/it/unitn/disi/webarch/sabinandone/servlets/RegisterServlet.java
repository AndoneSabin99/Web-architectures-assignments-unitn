package it.unitn.disi.webarch.sabinandone.servlets;

//import it.unitn.disi.webarch.sabinandone.utilities.LoginBean;
import it.unitn.disi.webarch.sabinandone.utilities.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public Boolean register(String path, UserBean u, HttpSession session) throws IOException {
        Boolean canReg = true;
        ArrayList<UserBean> userlist = new ArrayList<>();
        userlist = (ArrayList<UserBean>)session.getAttribute("userlist");

        //check if the user is already registered in the system or not, i.e. if an account with the same username
        //aready exists or not. If it turns out that the username is already used, then canReg becomes false, thus
        //the registration will not happen
        for (UserBean userbean: userlist){
            if (u.getUser().equals(userbean.getUser())){
                canReg = false;
            }
        }

        if(canReg){
            //writing into users.txt
            FileOutputStream f = new FileOutputStream(new File(path));
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write users to file
            o.writeObject(u);

            for (UserBean userbean: userlist){
                o.writeObject(userbean);
            }
            o.close();
            f.close();
            userlist.add(u);
        }
        return canReg;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        String rp = request.getParameter("confirmedPassword");

        //check if password and confirmed password are equal
        if (!p.equals(rp)){
            String message = "Confirmed password is not equal to password";
            request.setAttribute("message", message);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        UserBean user = new UserBean();
        user.setUser(u);
        user.setPassword(p);

        String path = getServletContext().getRealPath("/resources/users.txt");
        //System.out.println(path);

        HttpSession session = request.getSession();

        //register is the method that checks if the user can register into the system or not
        Boolean logged = register(path, user, session);
        if(logged != null){
            response.sendRedirect(request.getContextPath() + "/InitializeServlet");
        } else {
            String message = "Cannot do the registration!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}
