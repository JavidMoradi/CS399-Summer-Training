package com.summertraining.iysproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pages {
    private @Id @GeneratedValue Long id;
    private String name;
    private String packages;

    @ManyToMany
    @JoinTable(
            name = "page_methods",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "method_id")
    )
    private Set<Methods> pageMethods = new HashSet<>();

    public Pages() {}

    public Pages(Long id, String name, String packages) {
        this.id = id;
        this.name = name;
        this.packages = packages;
    }

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

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    @Override
    public String toString() {
        return "Pages{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packages='" + packages + '\'' +
                '}';
    }

    public Set<Methods> getPageMethods() {
        return pageMethods;
    }

    public void setPageMethods(Set<Methods> pageMethods) {
        this.pageMethods = pageMethods;
    }

    public void addMethodToPage(Methods methods) {
        this.pageMethods.add(methods);
    }
}
