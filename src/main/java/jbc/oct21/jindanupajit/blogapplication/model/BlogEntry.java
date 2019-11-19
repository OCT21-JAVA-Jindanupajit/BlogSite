package jbc.oct21.jindanupajit.blogapplication.model;

import org.pegdown.PegDownProcessor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

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

    private String jumbotron;

    public BlogEntry() {
    }

    public BlogEntry(BlogEntry other) {
        copyValueFrom(other);
    }

    public BlogEntry(Timestamp timestamp, Category category, User user, String title, String content, String jumbotron) {
        this.timestamp = timestamp;
        this.category = category;
        this.user = user;
        this.title = title;
        this.content = content;
        this.jumbotron = jumbotron;
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

    public String getDateString() {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

    public String getTimeString() {
        return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
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

    public String getHtmlContent() {
        PegDownProcessor pegDownProcessor = new PegDownProcessor();
        return pegDownProcessor.markdownToHtml(content);
    }

    public String getTextContent() {
        return getHtmlContent().replaceAll("\\<.*?\\>", "");
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJumbotron() {
        return jumbotron;
    }

    public void setJumbotron(String jumbotron) {
        this.jumbotron = jumbotron;
    }

    @Override
    protected BlogEntry clone() {
        return new BlogEntry(this.timestamp, this.category, this.user, this.title, this.content, this.jumbotron);
    }

    public BlogEntry copyValueFrom(BlogEntry other) {
        this.timestamp = other.timestamp;
        this.category = other.category;
        this.user = other.user;
        this.title = other.title;
        this.content = other.content;
        this.jumbotron = other.jumbotron;
        return this;
    }
}
