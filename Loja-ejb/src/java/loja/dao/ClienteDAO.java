/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.dao;

import entity.bean.Cliente;
import javax.ejb.Remote;

/**
 *
 * @author cbsantos
 */
@Remote
public interface ClienteDAO extends DAOInterface<Cliente> {

}
