package com.cerebrum.jsoupdemo.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class Image implements Serializable {
    private String alt;
    private String title;
    private String source;

    public Image() {
    }

    public Image(String alt, String title, String source) {
        this.alt = alt;
        this.title = title;
        this.source = source;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(alt, image.alt) &&
                Objects.equals(title, image.title) &&
                Objects.equals(source, image.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alt, title, source);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Image.class.getSimpleName() + "[", "]")
                .add("alt='" + alt + "'")
                .add("title='" + title + "'")
                .add("source='" + source + "'")
                .toString();
    }
}
