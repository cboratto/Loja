/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caioboratto
 */
@Entity
@Table(name = "carrinho_item", catalog = "loja", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarrinhoItem.findAll", query = "SELECT c FROM CarrinhoItem c"),
    @NamedQuery(name = "CarrinhoItem.findByIdCarrinhoItem", query = "SELECT c FROM CarrinhoItem c WHERE c.idCarrinhoItem = :idCarrinhoItem"),
    @NamedQuery(name = "CarrinhoItem.findByQtdItem", query = "SELECT c FROM CarrinhoItem c WHERE c.qtdItem = :qtdItem"),
    @NamedQuery(name = "CarrinhoItem.findByValTotal", query = "SELECT c FROM CarrinhoItem c WHERE c.valTotal = :valTotal"),
    @NamedQuery(name = "CarrinhoItem.findByDatCriacao", query = "SELECT c FROM CarrinhoItem c WHERE c.datCriacao = :datCriacao"),
    @NamedQuery(name = "CarrinhoItem.findByDatUpdate", query = "SELECT c FROM CarrinhoItem c WHERE c.datUpdate = :datUpdate")})
public class CarrinhoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_carrinho_item")
    private Integer idCarrinhoItem;
    @Column(name = "qtd_item")
    private Integer qtdItem=0;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "val_total")
    private Double valTotal;
    @Column(name = "dat_criacao")
    @Temporal(TemporalType.DATE)
    private Date datCriacao;
    @Column(name = "dat_update")
    @Temporal(TemporalType.DATE)
    private Date datUpdate;
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    @ManyToOne
    private Produto idProduto;
    @JoinColumn(name = "id_carrinho", referencedColumnName = "id_carrinho")
    @ManyToOne
    private Carrinho idCarrinho;

    public CarrinhoItem() {
    }

    public CarrinhoItem(Integer idCarrinhoItem) {
        this.idCarrinhoItem = idCarrinhoItem;
    }

    public Integer getIdCarrinhoItem() {
        return idCarrinhoItem;
    }

    public void setIdCarrinhoItem(Integer idCarrinhoItem) {
        this.idCarrinhoItem = idCarrinhoItem;
    }

    public Integer getQtdItem() {
        return qtdItem;
    }

    public void addQtdItem() {
        this.qtdItem=qtdItem+1;
    }

    public Double getValTotal() {
        return valTotal;
    }

    public void setValTotal(Double valTotal) {
        this.valTotal = valTotal;
    }

    public Date getDatCriacao() {
        return datCriacao;
    }

    public void setDatCriacao(Date datCriacao) {
        this.datCriacao = datCriacao;
    }

    public Date getDatUpdate() {
        return datUpdate;
    }

    public void setDatUpdate(Date datUpdate) {
        this.datUpdate = datUpdate;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Carrinho getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Carrinho idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrinhoItem != null ? idCarrinhoItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarrinhoItem)) {
            return false;
        }
        CarrinhoItem other = (CarrinhoItem) object;
        if ((this.idCarrinhoItem == null && other.idCarrinhoItem != null) || (this.idCarrinhoItem != null && !this.idCarrinhoItem.equals(other.idCarrinhoItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.CarrinhoItem[ idCarrinhoItem=" + idCarrinhoItem + " ]";
    }
    
}
