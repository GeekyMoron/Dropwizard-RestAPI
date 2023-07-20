package model;

import java.util.Objects;

public class Bookmark {
private long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return id == bookmark.id && Objects.equals(name, bookmark.name) && Objects.equals(url, bookmark.url) && Objects.equals(description, bookmark.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, description);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String name;
    private String url;
    private String description;

    public Bookmark() {
    }
}
