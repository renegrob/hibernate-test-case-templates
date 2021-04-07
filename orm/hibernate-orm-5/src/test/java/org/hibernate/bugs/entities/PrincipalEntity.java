package org.hibernate.bugs.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PRINCIPALS")
public class PrincipalEntity implements Serializable {

    @Id
    @Column(name = "PRINCIPAL_ID", nullable = false, length = 256)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 256)
    private String name;

    @ElementCollection
    @JoinTable(name = "PRINCIPAL_ALIAS", joinColumns = @JoinColumn(name = "PRINCIPAL_ID", referencedColumnName = "PRINCIPAL_ID"))
    @MapKeyColumn(name = "QUALIFIER")
    @Column(name = "ALIAS")
    private Map<String, String> aliases = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAliases() {
        return aliases;
    }

    public void setAliases(Map<String, String> aliases) {
        this.aliases = aliases;
    }
}