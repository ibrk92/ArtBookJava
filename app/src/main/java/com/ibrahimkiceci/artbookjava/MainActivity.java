package com.ibrahimkiceci.artbookjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ibrahimkiceci.artbookjava.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Note>noteArrayList;
    NoteAdapter noteAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        noteArrayList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(noteArrayList);
        binding.recyclerView.setAdapter(noteAdapter);

        getData();
    }

    private void getData(){

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Notes", MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM notes", null);
            int titleIx = cursor.getColumnIndex("notetitle");
            int idIx = cursor.getColumnIndex("id");

            while(cursor.moveToNext()){
                String title = cursor.getString(titleIx);
                int id = cursor.getInt(idIx);

                Note note = new Note(title, id);
                noteArrayList.add(note);

            }

            noteAdapter.notifyDataSetChanged();

            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.art_menu, menu);



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_art) {

            Intent intent = new Intent(this, ArtActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }
}