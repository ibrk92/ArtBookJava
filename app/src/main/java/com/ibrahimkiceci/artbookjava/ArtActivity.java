package com.ibrahimkiceci.artbookjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;

import com.ibrahimkiceci.artbookjava.databinding.ActivityArtBinding;

public class ArtActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;

    private ActivityArtBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }

    public void save(View view){

        String title = binding.editTextTitle.getText().toString();
        String desc = binding.editTextDesc.getText().toString();

        try {

            sqLiteDatabase = this.openOrCreateDatabase("Notes", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes (id INTEGER PRIMARY KEY, notetitle VARCHAR, notedesc VARCHAR)");
            String sqlString = "INSERT INTO notes (notetitle, notedesc) VALUES(?, ?, ?, ?)";
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);
            sqLiteStatement.bindString(1, title);
            sqLiteStatement.bindString(2, desc);
            sqLiteStatement.execute();


        }catch (Exception e){
            e.printStackTrace();
        }

        Intent intent = new Intent(ArtActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}