package loja.controller.frontcontroller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontControllerServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // obtém o nome do controlador
            String controller = request.getParameter("control");
            // instancia a classe do controlador
            ApplicationController control;
            control = ApplicationControllerFactory.getControllerByFullClassName(controller);
            // inicializa o controlador
            control.init(request);
            
            control.execute();
            RequestDispatcher requestDispatcher;
            requestDispatcher = getServletContext().getRequestDispatcher(control.getReturnPage());
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
