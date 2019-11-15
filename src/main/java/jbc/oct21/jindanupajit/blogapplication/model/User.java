package jbc.oct21.jindanupajit.blogapplication.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @SequenceGenerator(name = "User", sequenceName = "UserId", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(generator = "User")
    private long id;

    private String name;

    @Lob
    private String biography;

    private String photo;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<SocialMedia> socialMedia;

    public User() {
    }

    public User(String name, String biography, String photo, List<SocialMedia> socialMedia) {
        this.name = name;
        this.biography = biography;
        this.photo = photo;
        this.socialMedia = socialMedia;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedia> socialMedia) {
        this.socialMedia = socialMedia;
    }
}