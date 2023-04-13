package com.example.finalreview.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dogs")
public class Dog {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="dogid")
    private String Dogid;

    public Dog() {
    }

    @ColumnInfo(name="dogtypetgt")
    private String DogTypeTgt;

    @ColumnInfo(name="dogtype")
    private String DogType;

    @ColumnInfo(name="dogname")
    private String DogName;

    @ColumnInfo(name="dogdob")
    private String DogDob;

    public Dog(@NonNull String dogid, String dogTypeTgt, String dogType, String dogName, String dogDob) {
        Dogid = dogid;
        DogTypeTgt = dogTypeTgt;
        DogType = dogType;
        DogName = dogName;
        DogDob = dogDob;
    }

    @NonNull
    public String getDogid() {
        return Dogid;
    }

    public void setDogid(@NonNull String dogid) {
        Dogid = dogid;
    }

    public String getDogTypeTgt() {
        return DogTypeTgt;
    }

    public void setDogTypeTgt(String dogTypeTgt) {
        DogTypeTgt = dogTypeTgt;
    }

    public String getDogType() {
        return DogType;
    }

    public void setDogType(String dogType) {
        DogType = dogType;
    }

    public String getDogName() {
        return DogName;
    }

    public void setDogName(String dogName) {
        DogName = dogName;
    }

    public String getDogDob() {
        return DogDob;
    }

    public void setDogDob(String dogDob) {
        DogDob = dogDob;
    }
}
