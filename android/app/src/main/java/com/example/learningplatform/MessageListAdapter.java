package com.example.learningplatform;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context mContext;
    public List<Message> mMessageList;
    String name;
    SharedPreferences sharedPreferences;
    public MessageListAdapter(Context context, List<Message> messageList, String name) {
        sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        mContext = context;
        mMessageList = messageList;
        this.name = name;
    }
    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Message message = mMessageList.get(position);
        final int id = sharedPreferences.getInt("Id",0);
        if (message.senderId == id) {
// If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
// If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }
    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }
    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = mMessageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }
    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;
        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }
        void bind(Message message) {
            String s = message.time.substring(0,10) +" "+ message.time.substring(11,19);
            messageText.setText(message.messageContent);
// Format the stored timestamp into a readable String using method.
            timeText.setText(s);
        }
    }
    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameView;
        ImageView profileImage;
        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
            nameView = (TextView) itemView.findViewById(R.id.text_message_name);
        }
        void bind(Message message) {
            messageText.setText(message.messageContent);
// Format the stored timestamp into a readable String using method.
            String s = message.time.substring(0,10) +" "+ message.time.substring(11,19);
            timeText.setText(s);
            nameView.setText(name);

// Insert the profile image from the URL into the ImageView.
            //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }
}

class Message{
    int senderId;
    int receiverId;
    String messageContent;
    String time;
    public Message(int senderId,int receiverId,String messageContent,String time){
        this.senderId= senderId;
        this.receiverId = receiverId;
        this.messageContent= messageContent;
        this.time= time;
    }
}
