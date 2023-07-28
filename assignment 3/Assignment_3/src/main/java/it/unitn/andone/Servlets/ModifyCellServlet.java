package it.unitn.andone.Servlets;

import it.unitn.andone.Spreadsheet.Cell;
import it.unitn.andone.Spreadsheet.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "ModifyCellServlet", value = "/ModifyCellServlet")
public class ModifyCellServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get parameters (formula and date), session and servlet context
        String formula = request.getParameter("value");
        String date = request.getParameter("t");

        HttpSession session = request.getSession(true);
        String id = (String)session.getAttribute("currentCell");

        ServletContext ctx = getServletContext();
        SSEngine engine = (SSEngine) ctx.getAttribute("engine");

        //this is necessary since the '+' char is not considered in the value parameter
        //if the formula does not contain a '+' operator, the replace function does not change formula
        formula = formula.replace(' ','+');

        //set cell used to contain all the affected cells
        Set<Cell> sc;

        //create a new cell so it is possible to check if it has circular dependences
        Cell cd = new Cell(id,formula);
        if(cd.isCircularDependent()){
            //modify the cell and get the list of all cells that have been affected by the modification
            sc = engine.modifyCell(id,formula);
        }else{
            //System.out.println("circular dependences detected");
            //since we detected a circular dependency, we need to trigger the error case where the value is 0
            String newFormula = formula + " ";
            sc = engine.modifyCell(id,newFormula);
        }

        //System.out.println("The length of sc is " + sc.size());

        ctx.setAttribute("updatedTimestamp",date);
        ctx.setAttribute("engine",engine);

        //create the json structure
        StringBuilder ret = new StringBuilder("[");
        String prefix = "";
        for (Cell c : sc){
            //System.out.println(c.toString());
            ret.append(prefix);
            prefix = ",";
            ret.append("{\"id\":\"").append(c.getId()).append("\",");
            ret.append("\"formula\":\"").append(c.getFormula()).append("\",");
            ret.append("\"value\":\"").append(c.getValue()).append("\"}");
        }
        ret.append("]");

        //return json
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(ret.toString());
        }
    }
}
