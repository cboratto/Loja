/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.session;

import bean.exceptions.DupValOnIndexException;
import entity.bean.Carrinho;
import entity.bean.CarrinhoItem;
import entity.bean.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author caioboratto
 */
@Stateful(mappedName = "CarrinhoBean")
public class CarrinhoBean implements CarrinhoBeanRemote {

    List<CarrinhoItem> listaCarrinhoItem;
    Carrinho carrinho;

    @Override
    public Object getObject() {
        return carrinho;
    }

    @Override
    public void setObjet(Carrinho e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Carrinho> getObjectList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Carrinho e) throws DupValOnIndexException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addItemCarrinho(Produto produto) {
        CarrinhoItem carrinhoItem = new CarrinhoItem();
        //inicializa o carrinho
        if (carrinho == null) {
            carrinho = new Carrinho();
            listaCarrinhoItem = new ArrayList<CarrinhoItem>();

            //adiona        
            carrinhoItem.setIdProduto(produto);
            carrinhoItem.setIdCarrinho(carrinho);

            listaCarrinhoItem.add(carrinhoItem);
            carrinho.setCarrinhoList(listaCarrinhoItem);
            //caso carrinho já foi inicializado
        } else {
            for (int i = 0; i < listaCarrinhoItem.size(); i++) {
                if (listaCarrinhoItem.get(i).getIdProduto().getIdProduto().equals(produto.getIdProduto())) {
                    listaCarrinhoItem.get(i).addQtdItem();
                    break;
                } else {

                    carrinhoItem.setIdProduto(produto);
                    carrinhoItem.setIdCarrinho(carrinho);

                    listaCarrinhoItem.add(carrinhoItem);
                }

            }

        }
    }
}
