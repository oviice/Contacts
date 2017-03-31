package com.example.ovi.contactlist;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView contacts;
    MyAdapter adapter;
    ArrayList<Contact> list;
    Button loadMore;
    int state=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<>();
        contacts= (RecyclerView) findViewById(R.id.recycl);
        loadMore= (Button) findViewById(R.id.load);
        ContentResolver resolver=getContentResolver();
        Cursor cursor=resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract.Contacts.DISPLAY_NAME+" ASC");

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                    new String[]{id}, null);
            String phoneNum="";
            if (phoneCursor.moveToFirst())
                phoneNum= phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phoneCursor.close();
            list.add(new Contact(name,phoneNum));

        }

        adapter = new MyAdapter(generateSubList(state), getLayoutInflater());
        contacts.setLayoutManager(new LinearLayoutManager(this));
        contacts.setAdapter(adapter);

        loadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData(generateSubList(state));
            }
        });
    }

    private ArrayList<Contact> generateSubList(int state) {
        ArrayList<Contact> list1=new ArrayList<>();
        int i;
        for (i=state;i<list.size() && i<state+10;i++)
            list1.add(list.get(i));
        this.state=i;
        return  list1;
    }


}
