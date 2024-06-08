/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artesacra.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class Profissional implements Serializable{
    private Integer idProfissional;
    private String nomeProfissional;
    private String sobrenomeProfissional;
    private Date dataNascimento;
    private Sexo sexoProfissional;
    private String emailProfissional;
    private String telefoneProfissional;
    private String ruaProfissional;
    private String casaProfissional;
    private String bairroProfissional;
    private String distrititoProfissional;
    private Municipio municipio;
    private CategoriaProfissional categoriaProfissional;

    public Profissional() {
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getSobrenomeProfissional() {
        return sobrenomeProfissional;
    }

    public void setSobrenomeProfissional(String sobrenomeProfissional) {
        this.sobrenomeProfissional = sobrenomeProfissional;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexoProfissional() {
        return sexoProfissional;
    }

    public void setSexoProfissional(Sexo sexoProfissional) {
        this.sexoProfissional = sexoProfissional;
    }

    public String getEmailProfissional() {
        return emailProfissional;
    }

    public void setEmailProfissional(String emailProfissional) {
        this.emailProfissional = emailProfissional;
    }

    public String getTelefoneProfissional() {
        return telefoneProfissional;
    }

    public void setTelefoneProfissional(String telefoneProfissional) {
        this.telefoneProfissional = telefoneProfissional;
    }

    public String getRuaProfissional() {
        return ruaProfissional;
    }

    public void setRuaProfissional(String ruaProfissional) {
        this.ruaProfissional = ruaProfissional;
    }

    public String getCasaProfissional() {
        return casaProfissional;
    }

    public void setCasaProfissional(String casaProfissional) {
        this.casaProfissional = casaProfissional;
    }

    public String getBairroProfissional() {
        return bairroProfissional;
    }

    public void setBairroProfissional(String bairroProfissional) {
        this.bairroProfissional = bairroProfissional;
    }

    public String getDistrititoProfissional() {
        return distrititoProfissional;
    }

    public void setDistrititoProfissional(String distrititoProfissional) {
        this.distrititoProfissional = distrititoProfissional;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public CategoriaProfissional getCategoriaProfissional() {
        return categoriaProfissional;
    }

    public void setCategoriaProfissional(CategoriaProfissional categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idProfissional);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profissional other = (Profissional) obj;
        return Objects.equals(this.idProfissional, other.idProfissional);
    }

    @Override
    public String toString() {
        return "Profissional{" + "nomeProfissional=" + nomeProfissional + ", sobrenomeProfissional=" + sobrenomeProfissional + '}';
    }

  
    
    
}
