
package com.shahinjo.dev.chartsitunes.models.store_apps;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("entry")
    @Expose
    private List<Entry> entry = null;
    @SerializedName("updated")
    @Expose
    private Updated updated;
    @SerializedName("rights")
    @Expose
    private FeedRights rights;
    @SerializedName("title")
    @Expose
    private FeedTitle title;
    @SerializedName("icon")
    @Expose
    private Icon icon;
    @SerializedName("link")
    @Expose
    private List<FeedLink> link = null;
    @SerializedName("id")
    @Expose
    private FeedId id;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public Updated getUpdated() {
        return updated;
    }

    public void setUpdated(Updated updated) {
        this.updated = updated;
    }

    public FeedRights getRights() {
        return rights;
    }

    public void setRights(FeedRights rights) {
        this.rights = rights;
    }

    public FeedTitle getTitle() {
        return title;
    }

    public void setTitle(FeedTitle title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<FeedLink> getLink() {
        return link;
    }

    public void setLink(List<FeedLink> link) {
        this.link = link;
    }

    public FeedId getId() {
        return id;
    }

    public void setId(FeedId id) {
        this.id = id;
    }

}
