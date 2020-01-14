package com.example.photos.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// define the table name for Room
@Entity(tableName = "photos")
public class PhotoEntity {

    // define the primary key for Room
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String thumbnail;
    private String fileName;
    private int favourite;

    // tell Room to ignore this constructor
    @Ignore
    public PhotoEntity() {
    }

    // Room will use this constructor
    public PhotoEntity(int id, String title, String thumbnail, String fileName, int favourite) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.fileName = fileName;
        this.favourite = favourite;
    }

    // tell Room to ignore this constructor
    @Ignore
    public PhotoEntity(String title, String thumbnail, String fileName, int favourite) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.fileName = fileName;
        this.favourite = favourite;
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

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "PhotoEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", fileName='" + fileName + '\'' +
                ", favourite=" + favourite +
                '}';
    }
}
