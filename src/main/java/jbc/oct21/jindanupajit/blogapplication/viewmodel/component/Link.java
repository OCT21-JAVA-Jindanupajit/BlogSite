package jbc.oct21.jindanupajit.blogapplication.viewmodel.component;

public class Link {
    private static long autoId = 0;
    private String id;
    private String label;
    private String url;

    public Link() {
        this.id =  String.format("%s-%06d",this.getClass().getSimpleName(),++autoId);
        this.label = id;
        this.url = "#";
    }
    public Link(String label, String url) {
        this.id =  String.format("%s-%06d",this.getClass().getSimpleName(),++autoId);
        this.label = label;
        this.url = url;
    }
    public Link(String id, String label, String url) {
        ++autoId;
        this.id =  id;
        this.label = label;
        this.url = url;
    }

    public static long getAutoId() {
        return ++autoId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
