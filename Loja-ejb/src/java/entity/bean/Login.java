/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author cbsantos
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findByIdCliente", query = "SELECT l FROM Login l WHERE l.idCliente = :idCliente"),
    @NamedQuery(name = "Login.findByNamLogin", query = "SELECT l FROM Login l WHERE l.namLogin = :namLogin and l.desPassword = :desPassword"),
    @NamedQuery(name = "Login.findByDesPassword", query = "SELECT l FROM Login l WHERE l.desPassword = :desPassword"),
    @NamedQuery(name = "Login.findByDatCreation", query = "SELECT l FROM Login l WHERE l.datCreation = :datCreation")})
public class Login implements Serializable {

    @OneToMany(mappedBy = "idCliente",fetch = FetchType.EAGER)
    private List<Carrinho> carrinhoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "customForeignGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name = "customForeignGenerator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "cliente"))
    //@GeneratedValue(generator = "id_cliente" )
    //@Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Size(max = 256)
    @Column(name = "nam_login")
    private String namLogin;
    @Size(max = 256)
    @Column(name = "des_password")
    private String desPassword;
    @Column(name = "dat_creation")
    @Temporal(TemporalType.DATE)
    private Date datCreation;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Cliente cliente;

    public Login() {
    }

    public Login(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNamLogin() {
        return namLogin;
    }

    public void setNamLogin(String namLogin) {
        this.namLogin = namLogin;
    }

    public String getDesPassword() {
        return desPassword;
    }

    public void setDesPassword(String desPassword) {
        this.desPassword = desPassword;
    }

    public Date getDatCreation() {
        return datCreation;
    }

    public void setDatCreation(Date datCreation) {
        this.datCreation = datCreation;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.bean.Login[ idCliente=" + idCliente + " ]";
    }

    @XmlTransient
    public List<Carrinho> getCarrinhoList() {
        return carrinhoList;
    }

    public void addCarrinho(Carrinho carrinho) {
        if (carrinhoList == null ){
            carrinhoList = new ArrayList<Carrinho>();
        }
        this.carrinhoList.add(carrinho);
    }

    @PostConstruct
    public void setDefaultDatCriaca() {
        this.datCreation = new Date();
    }
}
