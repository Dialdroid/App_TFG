package com.example.medicinapp.models;

public class SliderItem {

    String imageURL;
    Long timestamp;

    public SliderItem(){

    }

    public SliderItem(String imageURL, Long timestamp) {
        this.imageURL = imageURL;
        this.timestamp = timestamp;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
