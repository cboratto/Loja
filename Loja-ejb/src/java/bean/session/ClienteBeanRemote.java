/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.session;

import entity.bean.Cliente;
import entity.bean.Login;
import javax.ejb.Remote;

/**
 *
 * @author caioboratto
 */
@Remote()
public interface ClienteBeanRemote extends InterfaceBeanRemote<Cliente> {

    public Cliente getDAOClientePorLogin(Login l);

    public Cliente getDAOClienteId(Cliente cliente);
}
