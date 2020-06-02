package com.cerebrum.jsoupdemo.model;

public class ImageDto {

    private String alt;
    private String title;
    private String source;

    public ImageDto(Image image) {
        this.alt = image.getAlt();
        this.title = image.getTitle();
        this.source = image.getSource();
    }

    public ImageDto(){}
}
