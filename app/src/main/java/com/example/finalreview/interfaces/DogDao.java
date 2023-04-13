package com.example.finalreview.interfaces;

import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.finalreview.model.Dog;

import java.util.List;

@Dao
public interface DogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDogs(List<Dog> dogs);

    @Query("SELECT * FROM dogs")
    List<Dog> GetAllDogs();
}
