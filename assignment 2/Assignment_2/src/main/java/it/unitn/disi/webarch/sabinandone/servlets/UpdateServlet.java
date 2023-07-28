package it.unitn.disi.webarch.sabinandone.servlets;

import it.unitn.disi.webarch.sabinandone.utilities.FlagBean;
import it.unitn.disi.webarch.sabinandone.utilities.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the three answers
        int firstValue = Integer.parseInt(request.getParameter("firstValue"));
        int secondValue = Integer.parseInt(request.getParameter("secondValue"));
        int thirdValue = Integer.parseInt(request.getParameter("thirdValue"));
        //System.out.println(firstValue + " " + secondValue + " " + thirdValue);

        HttpSession session = request.getSession();

        //get the three flags, so we can compare the answers
        FlagBean firstNation = (FlagBean) session.getAttribute("firstFlag");
        FlagBean secondNation = (FlagBean) session.getAttribute("secondFlag");
        FlagBean thirdNation = (FlagBean) session.getAttribute("thirdFlag");

        //System.out.println(firstNation.getCapital() + " " + secondNation.getCapital() + " " + thirdNation.getCapital());

        ArrayList<String> capitals = new ArrayList<String>();
        capitals = (ArrayList<String>)session.getAttribute("capitals");

        //System.out.println(capitals.get(firstValue - 1) + " " + capitals.get(secondValue - 1) + " " + capitals.get(thirdValue - 1));

        //do the checks of the answers
        boolean checkFirst = firstNation.getCapital().equals(capitals.get(firstValue - 1));
        boolean checkSecond = secondNation.getCapital().equals(capitals.get(secondValue - 1));
        boolean checkThird = thirdNation.getCapital().equals(capitals.get(thirdValue - 1));

        //System.out.println(checkFirst + " " + checkSecond + " " + checkThird);

        UserBean u = (UserBean) session.getAttribute("UserBean");
        Integer points = (Integer)session.getAttribute("points");

        //update the points
        if (checkFirst && checkSecond && checkThird){
            int newPointValue = points + 3;
            request.getSession().setAttribute("points",newPointValue);
            System.out.println("You win");
        }else{
            int newPointValue = points - 1;
            request.getSession().setAttribute("points",newPointValue);
            System.out.println("You lose");
        }

        session.setAttribute("UserBean",u);
        //System.out.println(u.getPoints());

        response.sendRedirect(request.getContextPath() + "/InitializeServlet");

    }

}
