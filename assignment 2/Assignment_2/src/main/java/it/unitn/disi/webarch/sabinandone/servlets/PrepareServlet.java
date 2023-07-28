package it.unitn.disi.webarch.sabinandone.servlets;

import it.unitn.disi.webarch.sabinandone.utilities.FlagBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "PrepareServlet", value = "/PrepareServlet")
public class PrepareServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<String> nations = new ArrayList<>();
        ArrayList<String> capitals = new ArrayList<>();

        System.out.println(getServletContext().getContextPath());

        //add nations
        nations.add("Algeria.jpg");
        nations.add("Armenia.jpg");
        nations.add("Chad.jpg");
        nations.add("Czech Republic.jpg");
        nations.add("Djibouti.jpg");
        nations.add("Gabon.jpg");
        nations.add("Indonesia.jpg");
        nations.add("Lithuania.jpg");
        nations.add("Malta.jpg");
        nations.add("Ukraine.jpg");

        //add capitals
        capitals.add("Algiers");
        capitals.add("Yerevan");
        capitals.add("N'Djamena");
        capitals.add("Prague");
        capitals.add("Djibouti");
        capitals.add("Libreville");
        capitals.add("Jakarta");
        capitals.add("Vilnius");
        capitals.add("La Valletta");
        capitals.add("Kiev");

        HttpSession session = request.getSession();

        //index list, so we can choose 3 random nations by using their indexes
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<10; i++) list.add(i);
        Collections.shuffle(list);

        int first = list.get(0);
        int second = list.get(1);
        int third = list.get(2);

        //create the three flagbean instances
        FlagBean firstFlag = new FlagBean(nations.get(first), capitals.get(first));
        FlagBean secondFlag = new FlagBean(nations.get(second), capitals.get(second));
        FlagBean thirdFlag = new FlagBean(nations.get(third), capitals.get(third));

        //set flags and lists into the session
        session.setAttribute("firstFlag",firstFlag);
        session.setAttribute("secondFlag",secondFlag);
        session.setAttribute("thirdFlag",thirdFlag);
        session.setAttribute("nations",nations);
        session.setAttribute("capitals",capitals);

        response.sendRedirect(request.getContextPath() + "/LoadServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
