package it.unitn.andone.Servlets;

import it.unitn.andone.Spreadsheet.Cell;
import it.unitn.andone.Spreadsheet.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HighlightServlet", value = "/HighlightServlet")
public class HighlightServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get parameter (the id of the selected cell) the session and the servlet context
        String id = request.getParameter("idCell");
        HttpSession session = request.getSession(true);
        ServletContext ctx = getServletContext();
        SSEngine engine = (SSEngine) ctx.getAttribute("engine");

        //get the cell
        Cell c = engine.getCell(id);
        session.setAttribute("currentCell",id);

        //return json
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("{\"idCell\": \"" + c.getId() + "\"," + "\"formulaCell\": \"" + c.getFormula() + "\"," + "\"valueCell\": \"" + c.getValue() + "\"}");
        }
    }

}
