package jbc.oct21.jindanupajit.blogapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Image {
    @Id
    @SequenceGenerator(name = "Image", sequenceName = "ImageId", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(generator = "Image")
    private long id;
    private String url;
    private String secure_url;

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecure_url() {
        return secure_url;
    }

    public void setSecure_url(String secure_url) {
        this.secure_url = secure_url;
    }
}
