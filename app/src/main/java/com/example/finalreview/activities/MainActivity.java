package com.example.finalreview.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DogDatabase ddb;

    Toast toast;
    int checkIdx = -1;



    @RequiresApi(api = Build.VERSION_CODES.O)
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
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                ImageView imageLarge = findViewById(R.id.imageViewLarge);
                //Logic here doesnt work
                if(checkIdx != -1){
                    imageLarge.setImageResource(AllDBDogs.get(checkIdx).getDogTypeTgt());
                }
                checkIdx = sharedPreferences.getInt("IMGING",-1);


                runOnUiThread(new Runnable() {



                    @Override
                    public void run() {
                        DogAdapter dogAdapter = new DogAdapter(AllDBDogs, new DogAdapter.onClickInterface() {

                            @Override
                            public void onClickEvent(int posistion) {
                                checkIdx = posistion;

                                if(toast != null)
                                    toast.cancel();
                                toast = Toast.makeText(MainActivity.this, ""+AllDBDogs.get(posistion).getDogName(), Toast.LENGTH_SHORT);
                                toast.show();

                                if(checkIdx!=-1){
                                    imageLarge.setImageResource(AllDBDogs.get(posistion).getDogTypeTgt());
                                }else{
                                    imageLarge.setImageResource(0);
                                }


                            }
                        });
                        binding.recyclerViewDog.setAdapter(dogAdapter);
                    }
                });
            }
        });
    }




    //read csv data

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Dog> ReadDogCSV(){
        List<Dog> DogList = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try{
            String dogLine;
            while((dogLine = reader.readLine()) != null) {
                String[] eachDogField = dogLine.split(",");
                //Change the name to int for drawable use
                int dogDrawable = getResources().getIdentifier(eachDogField[1],"drawable",getPackageName());
                //Change the date format
                //Change data format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

                LocalDate dob = LocalDate.parse(eachDogField[4],formatter);
                String formattedDate = dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Dog eachDog = new Dog(eachDogField[0],dogDrawable,eachDogField[2],eachDogField[3],formattedDate);
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

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putInt("IMGING",checkIdx);
        editor.commit();
    }

}