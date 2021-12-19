package com.example.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class ListAdapter extends BaseAdapter {
    List<Book> bookList;
    Context context;
    public ListAdapter(Context context, String search){
        this.context=context;
        BookDb db=new BookDb(context);

        if(search==null)
            bookList=db.getAllData();
        else{
            bookList=db.searchData(search);
            if(bookList==null) {
                bookList = db.getAllData();
                Toast.makeText(context, "Your search did not match any book", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public int getCount() {
        return bookList.size();
    }
    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View v=inflater.inflate(R.layout.row,null);

         Book book=bookList.get(i);
         TextView id=v.findViewById(R.id.id);
         TextView name=v.findViewById(R.id.name);
         TextView title=v.findViewById(R.id.title);
         TextView price=v.findViewById(R.id.price);

         id.setText(String.valueOf(book.getId()));
         name.setText(String.valueOf(book.getName()));
         title.setText(String.valueOf(book.getTitle()));
         price.setText(String.valueOf(book.getPrice()));

        return v;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
