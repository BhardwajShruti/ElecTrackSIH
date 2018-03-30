package com.example.android.electracksih;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NITANT SOOD on 30-03-2018.
 */

public class ChatBotListAdapter extends RecyclerView.Adapter<ChatBotListAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View incomingMessageView = inflater.inflate(R.layout.chat_bot_incoming, parent, false);
        View outgoingMesssageView = inflater.inflate(R.layout.chat_bot_outgoing, parent, false);
        return new ChatBotListAdapter.ViewHolder(incomingMessageView,outgoingMesssageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ChatBotFragment.messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView message;
        public ViewHolder(View incomingView,View outgoingView) {
            super(incomingView);
            int currentPosition=getAdapterPosition();
            View CurrentView;
            if(currentPosition%2==0){
                CurrentView=outgoingView;
            }
            else{
                CurrentView=incomingView;
            }

            message=itemView.findViewById(R.id.BotTextView);
        }
    }
}
