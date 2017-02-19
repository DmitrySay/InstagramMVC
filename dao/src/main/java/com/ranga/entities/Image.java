package com.ranga.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_IMAGE_ID")
    private int id;

    @Column(columnDefinition = "text")
    private String comment;

    @Column
    private String filename;

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
