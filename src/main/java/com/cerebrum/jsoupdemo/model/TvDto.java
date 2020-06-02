package com.cerebrum.jsoupdemo.model;

public class TvDto {

    private String model;
    private String price;
    private ImageDto imageDto;

    public TvDto(TV tv) {
        this.model = tv.getModel();
        this.price = tv.getPrice();
        this.imageDto = new ImageDto(tv.getImage());
    }

    public TvDto() {}
}
