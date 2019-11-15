package jbc.oct21.jindanupajit.blogapplication.model;

import javax.persistence.*;

@Entity
public class SocialMedia {

    @Id
    @SequenceGenerator(name = "SocialMedia", sequenceName = "SocialMediaId", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(generator = "SocialMedia")
    private long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    private SocialMediaType socialMediaType;

    private String url;

    public SocialMedia() {
    }

    public SocialMedia(SocialMediaType socialMediaType, String url) {
        this.socialMediaType = socialMediaType;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SocialMediaType getSocialMediaType() {
        return socialMediaType;
    }

    public void setSocialMediaType(SocialMediaType socialMediaType) {
        this.socialMediaType = socialMediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
