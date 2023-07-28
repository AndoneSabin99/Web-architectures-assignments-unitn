package it.unitn.andone.Servlets;

import it.unitn.andone.Spreadsheet.Cell;
import it.unitn.andone.Spreadsheet.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetAllCellsServlet", value = "/GetAllCellsServlet")
public class GetAllCellsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the servlet context
        ServletContext ctx = getServletContext();
        SSEngine engine = (SSEngine) ctx.getAttribute("engine");

        ArrayList<Cell> cellList = new ArrayList<>();

        //add into the cellList all the cells from the engine
        for (int i = 1; i<= engine.getRows(); i++){
            for (String a : engine.getColumns()) {
                String id=a+i;
                Cell c= engine.getCell(id);
                cellList.add(c);
            }
        }

        //create the json structure for the loadAllCells function
        StringBuilder ret = new StringBuilder("[");
        String prefix = "";
        for(int i=0; i<cellList.size(); i++) {
            Cell c = cellList.get(i);
            ret.append(prefix);
            prefix = ",";
            ret.append("{\"id\":\"").append(c.getId()).append("\",");
            ret.append("\"formula\":\"").append(c.getFormula()).append("\",");
            ret.append("\"value\":\"").append(c.getValue()).append("\"}");
        }
        ret.append("]");
        //System.out.println(ret.toString());

        //Return JSON
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(ret.toString());
        }

    }
}
