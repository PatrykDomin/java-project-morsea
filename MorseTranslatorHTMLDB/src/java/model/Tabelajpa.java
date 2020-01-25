/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patryk Domin
 * @version 1.0
 */
@Entity
@Table(name = "TABELAJPA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabelajpa.findAll", query = "SELECT t FROM Tabelajpa t")
    , @NamedQuery(name = "Tabelajpa.findById", query = "SELECT t FROM Tabelajpa t WHERE t.id = :id")
    , @NamedQuery(name = "Tabelajpa.findByUserinput", query = "SELECT t FROM Tabelajpa t WHERE t.userinput = :userinput")
    , @NamedQuery(name = "Tabelajpa.findByUseroutput", query = "SELECT t FROM Tabelajpa t WHERE t.useroutput = :useroutput")})
public class Tabelajpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(max = 255)
    @Column(name = "USERINPUT")
    private String userinput;
    @Size(max = 255)
    @Column(name = "USEROUTPUT")
    private String useroutput;

    /**
     * constructor
     */
    public Tabelajpa() {
    }
    /**
     * constuctor with id setter
     * @param id - given id
     */
    public Tabelajpa(Long id) {
        this.id = id;
    }
    /**
     * get id 
     * @return id 
     */
    public Long getId() {
        return id;
    }
    /**
     * set id 
     * @param id - given id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * get user input
     * @return userinput
     */
    public String getUserinput() {
        return userinput;
    }
    /**
     * set user input
     * @param userinput - text to translate
     */
    public void setUserinput(String userinput) {
        this.userinput = userinput;
    }
    /**
     * get user input 
     * @return user output
     */
    public String getUseroutput() {
        return useroutput;
    }
    /**
     * set user output
     * @param useroutput - translated text
     */
    public void setUseroutput(String useroutput) {
        this.useroutput = useroutput;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabelajpa)) {
            return false;
        }
        Tabelajpa other = (Tabelajpa) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Tabelajpa[ id=" + id + " ]";
    }
    
}
