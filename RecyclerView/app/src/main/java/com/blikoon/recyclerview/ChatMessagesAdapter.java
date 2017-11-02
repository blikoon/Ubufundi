package com.blikoon.recyclerview;

/**
 * Created by gakwaya on 2017/11/1.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blikoon.recyclerview.model.ChatMessage;
import com.blikoon.recyclerview.model.ChatMessageModel;

import java.util.List;

public class ChatMessagesAdapter extends RecyclerView.Adapter<ChatMessagesAdapter.ChatMessageViewHolder>
implements ChatMessageModel.OnMessageAddListener{


    /* Interface implementation from ChatMessageModel when to inform the adapter when a new message is added */
    @Override
    public void onMessageAdd() {
        mChatMessageList = ChatMessageModel.get(mContext).getMessages();
        notifyDataSetChanged();
    }

    /*
        * Click handler interface. RecyclerView does not have
        * its own built in like AdapterViews do.
        */
    public interface OnItemClickListener {
        public void onItemClick(ChatMessageViewHolder item, int position);
    }


    private static final int SENT = 1;
    private static final int RECEIVED = 2;



    private List<ChatMessage> mChatMessageList;
    private OnItemClickListener mOnItemClickListener;
    private LayoutInflater mLayoutInflater;
    private String contactJid;
    private Context mContext;

    public ChatMessagesAdapter(Context context, String contactJid) {
        mLayoutInflater = LayoutInflater.from(context);

        mContext = context;

        mChatMessageList = ChatMessageModel.get(mContext).getMessages();
        ChatMessageModel.get(mContext).setMessageAddListener(this);

        this.contactJid = contactJid;
    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        switch (viewType) {
            case SENT:
                itemView = mLayoutInflater
                        .inflate(R.layout.chat_message_view_sent, parent, false);
                return new ChatMessageViewHolder(itemView,this);
            case RECEIVED:
                itemView = mLayoutInflater
                        .inflate(R.layout.chat_message_view_received, parent, false);
                return new ChatMessageViewHolder(itemView,this);
            default:
                itemView = mLayoutInflater
                        .inflate(R.layout.chat_message_view_received, parent, false);
                return new ChatMessageViewHolder(itemView,this);
        }
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        holder.setTitle(mChatMessageList.get(position).getMessage());
        holder.setSummary(Long.toString(mChatMessageList.get(position).getTimestamp()) );
    }

    @Override
    public int getItemCount() {
        return mChatMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage.Type messageType = mChatMessageList.get(position).getType();

        if(messageType == ChatMessage.Type.SENT)
        {
            return SENT;
        }
        if(messageType == ChatMessage.Type.RECEIVED)
        {
            return RECEIVED;
        }
        return SENT;

    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /* Methods to manage modifying the data set */
    public void insertItemAtIndex(String item, int position) {
//        mItems.add(position, item);
//        //Notify the view to trigger a change animation
//        notifyItemInserted(position);
    }

    public void removeItemAtIndex(int position) {
//        if (position >= mItems.size()) return;
//        mItems.remove(position);
//        //Notify the view to trigger a change animation
//        notifyItemRemoved(position);
    }

    /* Required implementation of ViewHolder to wrap item view */
    public static class ChatMessageViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private ChatMessagesAdapter mParent;
        private TextView mTitleView, mSummaryView;

        public ChatMessageViewHolder(View itemView, ChatMessagesAdapter parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            mParent = parent;
            mTitleView = (TextView) itemView.findViewById(R.id.text_title);
            mSummaryView = (TextView) itemView.findViewById(R.id.text_summary);
        }

        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
        }

        public void setSummary(CharSequence summary) {
            mSummaryView.setText(summary);
        }

        public CharSequence getSummary() {
            return mSummaryView.getText();
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = mParent.getOnItemClickListener();


            if (listener != null) {
                listener.onItemClick(this, getPosition());
            }
        }



    }
}
