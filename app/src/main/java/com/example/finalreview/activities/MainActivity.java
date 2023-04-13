package com.example.finalreview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.finalreview.R;
import com.example.finalreview.adapter.DogAdapter;
import com.example.finalreview.database.DogDatabase;
import com.example.finalreview.databinding.ActivityMainBinding;
import com.example.finalreview.interfaces.DogDao;
import com.example.finalreview.model.Dog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DogDatabase ddb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Dog> DogFromFile = ReadDogCSV();
        //DogAdapter dogAdater = new DogAdapter(DogFromFile);
        ddb = Room.databaseBuilder(getApplicationContext(),DogDatabase.class,"dog.db").build();
        DogDao dogDao = ddb.doogDao();


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        binding.recyclerViewDog.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        executorService.execute(new Runnable() {

            @Override
            public void run() {
                dogDao.insertDogs(DogFromFile);
                List<Dog> AllDBDogs= dogDao.GetAllDogs();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DogAdapter dogAdapter = new DogAdapter(AllDBDogs);
                        binding.recyclerViewDog.setAdapter(dogAdapter);
                    }
                });
            }
        });
    }


    //read csv data
    private List<Dog> ReadDogCSV(){
        List<Dog> DogList = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try{
            String dogLine;
            while((dogLine = reader.readLine()) != null) {
                String[] eachDogField = dogLine.split(",");
                Dog eachDog = new Dog(eachDogField[0],eachDogField[1],eachDogField[2],eachDogField[3],eachDogField[4]);
                DogList.add(eachDog);
            }
        }catch (IOException ex){
            throw new RuntimeException("Error reading file");
        }finally{
            try{
                inputStream.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }



        return DogList;
    }

}