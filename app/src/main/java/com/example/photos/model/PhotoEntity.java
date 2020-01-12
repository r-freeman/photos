package com.example.photos.model;

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

    // tell Room to ignore this constructor
    @Ignore
    public PhotoEntity() {
    }

    // Room will use this constructor
    public PhotoEntity(int id, String title, String thumbnail, String fileName) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.fileName = fileName;
    }

    // tell Room to ignore this constructor
    @Ignore
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
