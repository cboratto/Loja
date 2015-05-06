/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.session;

import bean.exceptions.DupValOnIndexException;
import entity.bean.Cliente;
import entity.bean.Login;
import java.util.List;
import javax.ejb.Stateless;
import loja.dao.ClienteDAO;
import loja.dao.ClienteDAOImpl;

/**
 *
 * @author caioboratto
 */
@Stateless(mappedName = "ClienteBean")
public class ClienteBean implements ClienteBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    Cliente cliente;
    ClienteDAOImpl clienteDao = new ClienteDAOImpl();

    @Override
    public void save(Cliente cliente) throws DupValOnIndexException {
        try {
            clienteDao.inserir(cliente);
        } catch (DupValOnIndexException ex) {
            throw new DupValOnIndexException();
        }
    }

    @Override
    public Object getObject() {
        return this.cliente;
    }

    @Override
    public void setObjet(Cliente e) {
        this.cliente = e;
    }

    @Override
    public List<Cliente> getObjectList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente getDAOClientePorLogin(Login l) {
        return clienteDao.listarPorLogin(l);
    }

    @Override
    public Cliente getDAOClienteId(Cliente cliente) {
        return clienteDao.listarPorId(cliente);
    }
}
