package com.samhita.samhitafirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mSendMessage;
    private Button mSendButton;
    private ListView mMessagesList;
    private MessageAdapter mMessageAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendButton = (Button) findViewById(R.id.send_button);
        mSendMessage = (EditText) findViewById(R.id.message_box);
        mMessagesList = (ListView) findViewById(R.id.messages_list);

        ArrayList<UserMessage> messages = new ArrayList<>();

        UserMessage userMessage1 = new UserMessage("hi", "Alice");
        UserMessage userMessage2 = new UserMessage("hello", "Bob");
        UserMessage userMessage3 = new UserMessage("hi", "Alice");
        UserMessage userMessage4 = new UserMessage("bye", "Bob");

        messages.add(userMessage1);
        messages.add(userMessage2);
        messages.add(userMessage3);
        messages.add(userMessage4);

        mMessageAdapter = new MessageAdapter(MainActivity.this, messages);
        mMessagesList.setAdapter(mMessageAdapter);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = mSendMessage.getText().toString();

                UserMessage userMessage = new UserMessage(message, "Anonymous");
                mDatabaseReference.push().setValue(userMessage);

                mSendMessage.setText("");
            }
        });

        initFirebase();
    }

    private void initFirebase() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("messages");
    }

    class MessageAdapter extends ArrayAdapter<UserMessage> {

       public MessageAdapter(Context context, ArrayList<UserMessage> userMessages) {
          super(context, 0, userMessages);
       }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {

                convertView = LayoutInflater
                                .from(MainActivity.this)
                                .inflate(R.layout.message_item_layout, null, false);

            }

            UserMessage message = getItem(position);

            TextView messageView = (TextView) convertView.findViewById(R.id.message);
            TextView userView = (TextView) convertView.findViewById(R.id.user);

            messageView.setText(message.getMessage());
            userView.setText(message.getUser());

            return convertView;
        }
    }
}
