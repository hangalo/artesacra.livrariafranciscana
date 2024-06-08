/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package artesacra.mb;

import artesacra.dao.ClienteDAO;
import artesacra.dao.MunicipioDAO;
import artesacra.dao.ProvinciaDAO;
import artesacra.modelo.Cliente;
import artesacra.modelo.Municipio;
import artesacra.modelo.Provincia;
import artesacra.modelo.Sexo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author informatica
 */
@Named(value = "clienteBean")
@RequestScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = -1;
    private MunicipioDAO municipioDAO;
    private List<Municipio> municipios;
    ProvinciaDAO provinciaDAO = new ProvinciaDAO();
    private Provincia provincia;

    List<Cliente> listaClientes;

    private List<Provincia> provincias;

    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();

    @PostConstruct
    public void inicializar() {
        municipioDAO = new MunicipioDAO();
        provincia = new Provincia();
        provincias = new ArrayList<>();
        provincias = provinciaDAO.findAll();

        listaClientes = new ArrayList<>();
        listaClientes = clienteDAO.findAll();
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /*
    
   

    public String eliminar() {
        funcionarioDao.delete(funcionario);
        funcionario = new Profissional();
        return null;
    }

    public String prepararEditar() {
        return "funcionario-editar";
    }

    public String editar() {
        funcionarioDao.update(funcionario);
        funcionario = new Profissional();
        return "funcionario-lista?faces-redirect=true";
    }
    
     public List<SelectItem> getOpSexos() {
        List<SelectItem> list = new ArrayList<>();
        for (Sexo sexo : Sexo.values()) {
            list.add(new SelectItem(sexo, sexo.getExtensao()));
        }
        return list;
    }
     */
    // carrega municipios em função da provincia
    public void carregaMunicipiosDaProvincia() {
        System.out.println("Provncia >>>>>" + provincia);
        municipios = municipioDAO.findByIdProvincia(provincia);
    }

    public List<SelectItem> getOpSexos() {
        List<SelectItem> list = new ArrayList<>();
        for (Sexo sexo : Sexo.values()) {
            list.add(new SelectItem(sexo, sexo.getAbreviatura()));
        }
        return list;
    }

    public void guardar(ActionEvent event) {
        if (clienteDAO.save(cliente)) {
            cliente = new Cliente();
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso ao guardar os dados", "Sucesso ao guardar os dados");
           
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro ao guardar os dados", "Erro ao guardar os dados");
            
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
