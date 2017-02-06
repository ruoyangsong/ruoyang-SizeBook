/*
 * Name: Ruoyang Song
 * CCID: ruoyang
 * Date: 2017.2.5
 * Copyright2017, Ruoyang Song, All rights reserved.
 */
package com.example.ruoyang.sizebook2;

import java.util.Date;

/**
 * Created by ruoyang on 2/5/17.
 */
public class Record {
    private String name;
    private String date;
    private double neck;
    private double bust;
    private double chest;
    private double waist;
    private double hip;
    private double inseam;
    private String comment;

    //the constructor of record
    public Record(String name) {
        this.name = name;
    }

    //the getter and setter of record
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getBust() {
        return bust;
    }

    public void setBust(double bust) {
        this.bust = bust;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getInseam() {
        return inseam;
    }

    public void setInseam(double inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    //the toString of record
    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", neck=" + neck +
                ", bust=" + bust +
                ", chest=" + chest +
                ", waist=" + waist +
                ", hip=" + hip +
                ", inseam=" + inseam +
                ", comment='" + comment + '\'' +
                '}';
    }
}
