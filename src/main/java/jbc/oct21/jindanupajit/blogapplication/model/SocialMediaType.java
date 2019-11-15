package jbc.oct21.jindanupajit.blogapplication.model;

import javax.persistence.*;

@Entity
public class SocialMediaType {

    @Id
    @SequenceGenerator(name = "SocialMediaType", sequenceName = "SocialMediaTypeId", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(generator = "SocialMediaType")
    private long id;

    private String name;

    private String logoUrl;

    public SocialMediaType() {
    }

    public SocialMediaType(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
