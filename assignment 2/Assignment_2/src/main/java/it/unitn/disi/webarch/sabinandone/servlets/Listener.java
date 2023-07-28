package it.unitn.disi.webarch.sabinandone.servlets;

import it.unitn.disi.webarch.sabinandone.utilities.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    ArrayList<UserBean> userlist;
    HashMap<String,Integer> connectedUserlist;

    public Listener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println("context created");
        userlist = new ArrayList<>();
        connectedUserlist = new HashMap<>();

        //load all the users from txt
        String filename = sce.getServletContext().getRealPath("/resources/users.txt");

        FileInputStream fi = null;
        try {
            fi = new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(fi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserBean u = null;
        boolean keepReading = true;
        try {
            while(keepReading) {
                u = (UserBean)oi.readObject();
                userlist.add(u);
                System.out.println(u.toString());
            }
        }catch(EOFException e) {
            keepReading = false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (Exception ex) {
            ex.printStackTrace();

        }

        try {
            oi.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fi.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        System.out.println("context destroyed");

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        System.out.println("session created");
        HttpSession session = se.getSession();

        //put userlist and connectedUserlist in the session
        session.setAttribute("connectedUserlist",connectedUserlist);
        session.setAttribute("userlist",userlist);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session destroyed");
        HttpSession session = se.getSession();
        /*
        here we should write all the userlist in the users.txt file
         */
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
