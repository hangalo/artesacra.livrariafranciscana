/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.mb;

import artesacra.dao.EspecialidadeDAO;
import artesacra.modelo.Especialidade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "EspecialidadeMB")
@SessionScoped
public class EspecialidadeMB implements Serializable {
/*
    Especialidade Especialidade = new Especialidade();
    EspecialidadeDAO EspecialidadeDao = new EspecialidadeDAO();
    List<Especialidade> listaEspecialidades = new ArrayList<>();
    private Especialidade especialidade;

    @PostConstruct
    public void inicializar() {

    }

    public Especialidade getEspecialidade() {
        return Especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public List<Especialidade> getListaEspecialidades() {
        return listaEspecialidades;
    }

    public void setListaEspecialidades(List<Especialidade> listaEspecialidades) {
        List<Especialidade> listaespecialidades = null;
        this.listaEspecialidades = listaespecialidades;
    }

    public String insert() {
        EspecialidadeDao.insert(Especialidade);
        Especialidade = new Especialidade();
        return "Especialidade-lista?faces-redirect=true";
    }

    public String eliminar() {
        EspecialidadeDao.delete(Especialidade);
        Especialidade = new Especialidade();
        return null;
    }

    public String prepararEditar() {
        return "especialidade-editar";
    }

    public String editar() {
        EspecialidadeDao.update(Especialidade);
        Especialidade = new Especialidade();
        return "especialidade-lista?faces-redirect=true";
    }
*/
}
