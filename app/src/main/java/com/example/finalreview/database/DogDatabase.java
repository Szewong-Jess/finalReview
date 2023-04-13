package com.example.finalreview.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.finalreview.interfaces.DogDao;
import com.example.finalreview.model.Dog;

@Database(entities = {Dog.class},version = 2,exportSchema = false)
public abstract class DogDatabase extends RoomDatabase {
    public abstract DogDao doogDao();
}
