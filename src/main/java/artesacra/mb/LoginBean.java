package artesacra.mb;

import artesacra.dao.LoginDAO;
import artesacra.dao.ProfissionalDAO;
import artesacra.dbutil.SessionUtils;
import artesacra.modelo.Profissional;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    Profissional profissional;
    private String password;
    private String mensagem;
    private String usuario;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    
    public String entrar(){
     return "pages/menu_gestao_configuracoes";
    
    }
    //validar login
    public String loginAdmistracao() {

        boolean valid = LoginDAO.validate(usuario, password);

        profissional = profissionalDAO.findByUsernamePassword(usuario, password);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("usuario", profissional);
            return "pages/menu_gestao_configuracoes";

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }

    }

    public String loginVendedores() {
        profissional = profissionalDAO.findByUsernamePassword(usuario, password);
        boolean valid = LoginDAO.validate(usuario, password);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("usuario", profissional);
            return "pages/menu_vendas";

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }

    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/index";
    }
}
