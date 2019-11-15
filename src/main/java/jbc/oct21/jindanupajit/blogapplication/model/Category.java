package jbc.oct21.jindanupajit.blogapplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Category {

    @Id
    @SequenceGenerator(name = "Category", sequenceName = "CategoryId", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(generator = "Category")
    private long id;

    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<BlogEntry> blogEntry;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

