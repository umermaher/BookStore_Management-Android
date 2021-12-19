package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class BooksList extends AppCompatActivity {
    ListView booksList;
    ImageView back;
    EditText searchBar;
    ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        booksList=findViewById(R.id.booksList);
        searchBar=findViewById(R.id.search_bar);
        search=findViewById(R.id.search);
        
        ListAdapter adp=new ListAdapter(BooksList.this,null);
        booksList.setAdapter(adp);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookDb d=new BookDb(BooksList.this);
                List<Book> books= d.getAllData();
                int i=books.size();
                
                if(booksList.getCount()!=i){
                    ListAdapter adp=new ListAdapter(BooksList.this, null);
                    booksList.setAdapter(adp);
                }
                else
                onBackPressed();
            }
        });
        
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(searchBar.getText().toString().isEmpty()){
                    //Toast.makeText(BooksList.this, "", Toast.LENGTH_SHORT).show();
                }
                else{
                    String s=searchBar.getText().toString();
                    ListAdapter adp=new ListAdapter(BooksList.this, s);
                    booksList.setAdapter(adp);
                }
            }
        });
    }
}