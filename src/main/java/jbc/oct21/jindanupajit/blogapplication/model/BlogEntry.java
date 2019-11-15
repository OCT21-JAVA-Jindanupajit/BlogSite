package jbc.oct21.jindanupajit.blogapplication.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class BlogEntry {

    @Id
    @SequenceGenerator(name = "BlogEntry", sequenceName = "BlogEntryId", initialValue = 1001, allocationSize = 1)
    @GeneratedValue(generator = "BlogEntry")
    private long id;

    private Timestamp timestamp;

    @OneToOne (
            fetch = FetchType.EAGER
    )
    private Category category;

    @OneToOne (
            fetch = FetchType.EAGER
    )
    private User user;

    private String title;

    @Lob
    private String content;

    public BlogEntry() {
    }

    public BlogEntry(BlogEntry other) {
        copyValueFrom(other);
    }

    public BlogEntry(Timestamp timestamp, Category category, User user, String title, String content) {
        this.timestamp = timestamp;
        this.category = category;
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected BlogEntry clone() {
        return new BlogEntry(this.timestamp, this.category, this.user, this.title, this.content);
    }

    public BlogEntry copyValueFrom(BlogEntry other) {
        this.timestamp = other.timestamp;
        this.category = other.category;
        this.user = other.user;
        this.title = other.title;
        this.content = other.content;
        return this;
    }
}
