/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.controller.app;

import bean.session.ClienteBeanRemote;
import bean.session.ProdutoBeanRemote;
import entity.bean.Cliente;
import entity.bean.Login;
import entity.bean.Produto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import loja.controller.frontcontroller.AbstractApplicationController;
import util.JNDIUtil;
import javax.persistence.NoResultException;

/**
 *
 * @author caioboratto
 */
public class LoginController extends AbstractApplicationController {

    @Override
    public void execute() {
        try {
            Context context = JNDIUtil.getCORBAInitialContext();
            ClienteBeanRemote clienteBean = (ClienteBeanRemote) context.lookup("ClienteBean");
            ProdutoBeanRemote produtoBean = (ProdutoBeanRemote) context.lookup("ProdutoBean");

            Login buscaLogin = new Login();
            Cliente cliente;

            buscaLogin.setNamLogin(this.getRequest().getParameter("usuario"));
            buscaLogin.setDesPassword(this.getRequest().getParameter("senha"));

            cliente = clienteBean.getDAOClientePorLogin(buscaLogin);
            this.getRequest().getSession().setAttribute("usuario", cliente);

            this.setReturnPage("/FrontControllerServlet?control=Loja");
        } catch (Exception ex) {
            Logger.getLogger(CadastrarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
