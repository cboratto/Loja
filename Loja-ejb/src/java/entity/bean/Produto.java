/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author caioboratto
 */
@Entity
@Table(name = "produto", catalog = "loja", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIdProduto", query = "SELECT p FROM Produto p WHERE p.idProduto = :idProduto"),
    @NamedQuery(name = "Produto.findByNomProduto", query = "SELECT p FROM Produto p WHERE p.nomProduto = :nomProduto"),
    @NamedQuery(name = "Produto.findByValProduto", query = "SELECT p FROM Produto p WHERE p.valProduto = :valProduto")})

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_produto")
    private Integer idProduto;
    @Size(max = 256)
    @Column(name = "nom_produto")
    private String nomProduto;
    @Size(max = 256)
    @Column(name = "des_url_img")
    private String desUrlImg;
    @Size(max = 3000)
    @Column(name = "des_produto")
    private String desProduto;    
    @Column(name = "val_produto")
    private Double valProduto;
    @OneToMany(mappedBy = "idProduto")
    private List<CarrinhoItem> carrinhoItemList;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categoria idCategoria;

    public Produto() {
    }

    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomProduto() {
        return nomProduto;
    }

    public void setNomProduto(String nomProduto) {
        this.nomProduto = nomProduto;
    }

    public Double getValProduto() {
        return valProduto;
    }

    public void setValProduto(Double valProduto) {
        this.valProduto = valProduto;
    }

    @XmlTransient
    public List<CarrinhoItem> getCarrinhoItemList() {
        return carrinhoItemList;
    }

    public void setCarrinhoItemList(List<CarrinhoItem> carrinhoItemList) {
        this.carrinhoItemList = carrinhoItemList;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Produto[ idProduto=" + idProduto + " ]";
    }

    public String getDesUrlImg() {
        return desUrlImg;
    }

    public void setDesUrlImg(String desUrlImg) {
        this.desUrlImg = desUrlImg;
    }

    public String getDesProduto() {
        return desProduto;
    }

    public void setDesProduto(String desProduto) {
        this.desProduto = desProduto;
    }

}
