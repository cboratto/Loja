/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.controller.app;

import bean.session.CarrinhoBeanRemote;
import bean.session.ProdutoBeanRemote;
import entity.bean.Carrinho;
import entity.bean.Cliente;
import entity.bean.Produto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import loja.controller.frontcontroller.AbstractApplicationController;
import util.JNDIUtil;

/**
 *
 * @author caioboratto
 */
public class LojaController extends AbstractApplicationController {

    @Override
    public void execute() {
        String origem;

        //seleciona o parametro caso estiver setado
        try {
            origem = this.getRequest().getParameter("orig");
            if (origem == null) {
                origem = "inicio";
            }
        } catch (Exception e) {
            origem = "inicio";
        }

        if (origem.equals("adicionaItem")) {
            //produto selecionado na tela
            String idProduto = this.getRequest().getParameter("idProduto");
            try {
                //busca bean
                Context context = JNDIUtil.getCORBAInitialContext();
                ProdutoBeanRemote produtoBean = (ProdutoBeanRemote) context.lookup("ProdutoBean");

                Integer id = Integer.parseInt(idProduto);
                Produto produtoSelecionadoNatela = produtoBean.getProdutoSelecionado(id);

                //produto selecionado com sucesso. 
                CarrinhoBeanRemote carrinhoBean;

                //valida se o usuario tem carrinho na sesssao                
                carrinhoBean = (CarrinhoBeanRemote) this.getRequest().getSession().getAttribute("carrinho");
                if (carrinhoBean == null) //se nao existir na sessao, cria um
                {
                    carrinhoBean = (CarrinhoBeanRemote) context.lookup("CarrinhoBean");
                }
                //neste ponto sempre temos o carrinho, portanto, tentamos vincular ao usuário
                Cliente cliente = (Cliente) this.getRequest().getSession().getAttribute("usuario");
                if (cliente != null) {
                    //cliente esta logado, portanto vincular ele ao carrinho
                    cliente.getLogin().addCarrinho((Carrinho) carrinhoBean.getObject());
                    ((Carrinho)carrinhoBean.getObject()).setIdCliente(cliente.getLogin());
                }

                //Agora temos de adicionar ao carrinho da sessao 
                carrinhoBean.addItemCarrinho(produtoSelecionadoNatela);

                //adiciona a sessao do usuario
                this.getRequest().getSession().setAttribute("carrinho", carrinhoBean);

                //Produto adicionado redireciona para loja                                
                this.setReturnPage("/FrontControllerServlet?control=Loja&orig=inicio");
            } catch (Exception ex) {
                Logger.getLogger(CadastrarController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {

                Context context = JNDIUtil.getCORBAInitialContext();
                ProdutoBeanRemote produtoBean = (ProdutoBeanRemote) context.lookup("ProdutoBean");

                //teste para subida de produtos                
                List<Produto> listaProdutos = produtoBean.getObjectList();
                this.getRequest().setAttribute("produtos", listaProdutos);
                this.setReturnPage("/loja/inicio.jsp");

            } catch (Exception ex) {
                Logger.getLogger(CadastrarController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
