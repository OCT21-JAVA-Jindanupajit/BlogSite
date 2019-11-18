package jbc.oct21.jindanupajit.blogapplication.viewmodel.component;

public class Brand {
    private static long autoId = 0;
    private String id;
    private String label;
    private String logoUrl;
    private String linkUrl;

    public Brand() {
        this.id = String.format("%s-%06d",this.getClass().getSimpleName(),++autoId);
        this.label = this.id;
        this.logoUrl = null;
        this.linkUrl = "#";
    }

    public Brand(String id, String label, String logoUrl, String linkUrl) {
        ++autoId;
        this.id = id;
        this.label = label;
        this.logoUrl = logoUrl;
        this.linkUrl = linkUrl;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
