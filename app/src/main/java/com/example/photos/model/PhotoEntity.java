package com.example.photos.model;

public class PhotoEntity {
    private int id;
    private String title;
    private String fileName;

    public PhotoEntity() {
    }

    public PhotoEntity(int id, String title, String fileName) {
        this.id = id;
        this.title = title;
        this.fileName = fileName;
    }

    public PhotoEntity(String title, String fileName) {
        this.title = title;
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
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
