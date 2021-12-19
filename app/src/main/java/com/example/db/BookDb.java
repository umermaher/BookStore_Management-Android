package com.example.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookDb extends SQLiteOpenHelper {
    public static final String DBName="Book.db";
    public static final String TABLE="books";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String TITLE="title";
    public static final String PRICE="price";
    Context context;

    //To create a database
    public BookDb(@Nullable Context context) {
        super(context, DBName, null, 1);
        this.context=context;
    }

    //To create table(s) in your database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table Books (id integer primary key, name text, title text, price text);";
        sqLiteDatabase.execSQL(query);

    }
    //To upgrade or make changes in your database
    //there is version of database of type int
    //on every change there is also a change in version --> oldversion(i) | new version(i1)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    Insert data into database
    public void insert(Book b) {
        SQLiteDatabase sql=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME,b.getName());
        cv.put(TITLE,b.getTitle());
        cv.put(PRICE,b.getPrice());
        long test = sql.insert(TABLE,null,cv);
        if(test==-1){
            Toast.makeText(context, "Data not inserted!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data is inserted succesfully!", Toast.LENGTH_SHORT).show();
        sql.close();
    }
    //for update
    public void update(Book b){
        SQLiteDatabase sql=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME,b.getName());
        cv.put(TITLE,b.getTitle());
        cv.put(PRICE,b.getPrice());
        sql.update(TABLE,cv,"id = ?",new String[]{String.valueOf(b.getId())});
        Toast.makeText(context, "Book data updated", Toast.LENGTH_SHORT).show();
//        in 4th argument string array is passed to check multiple columns e.g,
//        sql.update(TABLE,cv,"id = ? AND price < ?",new String[]{"5","1000"});
        sql.close();
    }
    //delete row
    public void delete(String id){
        SQLiteDatabase sql=this.getWritableDatabase();
        sql.delete(TABLE,"id = ?",new String[]{id});
        Toast.makeText(context, "Book data deleted", Toast.LENGTH_SHORT).show();
    }

    public List<Book> searchData(String search){
        List<Book> booksData=new ArrayList<>();
        SQLiteDatabase sql=this.getReadableDatabase();
        Cursor cursor=sql.query(TABLE,null,"id = ? OR name = ? OR title = ?", new String[]{search,search,search},null,null,null);
        cursor.moveToFirst();
        for(int i=0;i<cursor.getCount();i++){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String title=cursor.getString(2);
            String price=cursor.getString(3);

            Book temp=new Book(name,title,price);
            temp.setId(id);
            booksData.add(temp);
            cursor.moveToNext();
        }
        return booksData;
    }

    public List<Book> getAllData(){
        List<Book> booksData=new ArrayList<>();
        SQLiteDatabase sql=this.getReadableDatabase();
        Cursor cursor = sql.query(TABLE, null, null, null, null, null, null);
//        if only table is passed then the whole data can get
        //This interface provides random read-write access to the result set returned by a database query.
        //In simple words cursor points a particular row to access data from it.
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String title = cursor.getString(2);
            String price = cursor.getString(3);

            Book temp = new Book(name, title, price);
            temp.setId(id);
            booksData.add(temp);

            cursor.moveToNext();
        }

        sql.close();
        return booksData;
    }
}
