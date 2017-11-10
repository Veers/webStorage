package com.veers.storage.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity

public class Userfile implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="SSO_ID", unique=true, nullable=false)
    private String ssoId;

    @NotEmpty
    @Column(name="NAME", nullable=false)
    private String name;

    @NotEmpty
    @Column(name="PATH", nullable=false)
    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        Userfile other = (Userfile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "file to string";
    }


}
