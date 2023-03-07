package com.example.messenger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private static final String EXTRA_CURRENT_USER_ID = "current_id";
    private static final String EXTRA_OTHER_USER_ID = "other_id";
    private TextView textViewTitle;
    private View viewOnlineStatus;
    private RecyclerView recyclerViewChat;
    private EditText editTextMessage;
    private ImageView imageViewSendMessage;
    private MessagesAdapter messagesAdapter;
    private String currentUserId;
    private String otherUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();
        currentUserId = getIntent().getStringExtra(EXTRA_CURRENT_USER_ID);
        otherUserId = getIntent().getStringExtra(EXTRA_OTHER_USER_ID);
        messagesAdapter = new MessagesAdapter(currentUserId);
        recyclerViewChat.setAdapter(messagesAdapter);
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Message message = new Message(
                    "text " + i,
                    currentUserId,
                    otherUserId
            );
            messages.add(message);
        }
        for (int i = 0; i < 5; i++) {
            Message message = new Message(
                    "text " + i,
                    otherUserId,
                    currentUserId
            );
            messages.add(message);
        }
        messagesAdapter.setMessages(messages);
    }

    private void initViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        viewOnlineStatus = findViewById(R.id.viewOnlineStatus);
        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        imageViewSendMessage = findViewById(R.id.imageViewSendMessage);
    }

    public static Intent newIntent(Context context, String currentUserId, String otherUserId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(EXTRA_CURRENT_USER_ID, currentUserId);
        intent.putExtra(EXTRA_OTHER_USER_ID, otherUserId);
        return intent;
    }
}