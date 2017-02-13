package com.example.bucki.androidlabs;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    ArrayList<String> chat = new ArrayList<String>();
    private static final String ACTIVITY_NAME = "ChatWindowActivity";
    private ChatDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);


        final ListView listView = (ListView)findViewById(R.id.listView);
        final Button send = (Button)findViewById(R.id.button4);
        final EditText message = (EditText)findViewById(R.id.editText);

        //create database using helper
        helper = new ChatDatabaseHelper(this);
        Cursor cursor = helper.getData();

        while(cursor.moveToNext()){
            chat.add( cursor.getString( cursor.getColumnIndex(helper.KEY_MSG) ) );
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + cursor.getString( cursor.getColumnIndex(helper.KEY_MSG) ) );
        }

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = message.getText().toString();
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                message.setText(""); // empty text field when send is pressed
                chat.add(newMessage);
                helper.insertData(newMessage);
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return chat.size();
        }

        public String getItem(int position){
            return chat.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position % 2 == 0) result = inflater.inflate(R.layout.chat_row_incoming, null);
            else result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }

    public void onDestroy(){
        super.onDestroy();
        helper.close();
    }
}
