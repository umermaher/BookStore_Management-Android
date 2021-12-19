package com.example.db;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText name,title,price,id;
    boolean testId=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        id=findViewById(R.id.id);
      //  BookDb db=new BookDb(MainActivity.this);


    }
    //save and insert into database
    public void save(View view) {
        BookDb db=new BookDb(MainActivity.this);

        if(checkInput()){
            String n= name.getText().toString();
            String t=title.getText().toString();
            String p=price.getText().toString();

            Book b=new Book(n,t,p);
            db.insert(b);
        }
        else if(testId){
            Toast.makeText(this, "Enter required info", Toast.LENGTH_SHORT).show();
        }
    }

//    update the existing row
    public void updateData(View view) {
        BookDb db=new BookDb(MainActivity.this);
        if(checkInput()){
            String n= name.getText().toString();
            String t=title.getText().toString();
            String p=price.getText().toString();
            String idNum=id.getText().toString();

            if(id.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter id", Toast.LENGTH_SHORT).show();
            }
            else{
                try{
                    int i=Integer.parseInt(idNum);
                    Book b=new Book(n,t,p);
                    b.setId(i);
                    db.update(b);
                }catch (Exception e){
                    Toast.makeText(this, "Wrong id", Toast.LENGTH_SHORT).show();
                }
            }
        }else
            Toast.makeText(this, "Enter id", Toast.LENGTH_SHORT).show();

    }

    public void deleteData(View v){
        if(id.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter id", Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                String idNum=id.getText().toString();
                int i=Integer.parseInt(idNum);
                BookDb db=new BookDb(MainActivity.this);
                db.delete(idNum);
            }catch (Exception e){
                Toast.makeText(this, "Wrong id", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void booksList(View view) {
        Intent i=new Intent(MainActivity.this,BooksList.class);
        startActivity(i);
    }

//   checking inputs, whether required info is inserted
    public boolean checkInput(){
        boolean test=true;
        if(name.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            test=false;
        }
        if(title.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter title", Toast.LENGTH_SHORT).show();
            test=false;
        }
        if(price.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter price", Toast.LENGTH_SHORT).show();
            test=false;
        }
        try{
            if(!price.getText().toString().isEmpty()) {
                //
                int d = Integer.parseInt(price.getText().toString());
            }
        }catch(Exception e){
            price.setError("Price in numbers");
            test=false;
            testId=false;
        }
        return test;
    }
}
