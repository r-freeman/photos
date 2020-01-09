package com.example.photos.model;

public class PhotoEntity {
    private int id;
    private String title;
    private String thumbnail;
    private String fileName;

    public PhotoEntity() {
    }

    public PhotoEntity(int id, String title, String thumbnail, String fileName) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.fileName = fileName;
    }

    public PhotoEntity(String title, String thumbnail, String fileName) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "PhotoEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
