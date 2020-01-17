package com.example.photos.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * The PhotoEntity class is using Room annotations here to mirror
 */

// define the table name that the PhotoEntity class represents
@Entity(tableName = "photos")
public class PhotoEntity {

    // Using the @PrimaryKey annotation to define the primary key variable for Room
    // autoGenerate = true means that the id will auto increment.
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String thumbnail;
    private String fileName;
    private int favourite;

    // @Ignore annotations means Room will ignore this constructor
    @Ignore
    public PhotoEntity() {
    }

    // Room will use this constructor that doesn't have @Ignore annotation
    public PhotoEntity(int id, String title, String thumbnail, String fileName, int favourite) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.fileName = fileName;
        this.favourite = favourite;
    }

    // @Ignore annotations means Room will ignore this constructor
    @Ignore
    public PhotoEntity(String title, String thumbnail, String fileName, int favourite) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.fileName = fileName;
        this.favourite = favourite;
    }

    /********************** getters and setters *************************/

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
