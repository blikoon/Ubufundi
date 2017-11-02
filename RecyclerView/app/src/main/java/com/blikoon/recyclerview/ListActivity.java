package com.blikoon.recyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.blikoon.recyclerview.model.ChatMessage;
import com.blikoon.recyclerview.model.ChatMessageModel;

public class ListActivity extends ActionBarActivity implements
        ChatMessagesAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ChatMessagesAdapter mAdapter;
    private LinearLayoutManager mVerticalManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView = new RecyclerView(this);

        mVerticalManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);


        //Parameters to pass to adapter when starting chat activity
        /*
            jid of the contact [user@server.com]

         */

        mAdapter = new ChatMessagesAdapter(this,"gakwaya@salama.im");
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        //Apply margins decoration to all collections
        mRecyclerView.addItemDecoration(new InsetDecoration(this));

        //Default to vertical layout
        mRecyclerView.setLayoutManager(mVerticalManager);

        setContentView(mRecyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add_item)
        {
            ChatMessageModel.get(getApplicationContext()).addMessage(new ChatMessage("Sent Message.A really big message sent by the user",133345454, ChatMessage.Type.SENT));
        }
        if(item.getItemId() == R.id.action_remove_item)
        {
            ChatMessageModel.get(getApplicationContext()).addMessage(new ChatMessage("Received Message. My huge message sent by my xmpp  client",133345454, ChatMessage.Type.RECEIVED));

        }
        return true;
    }

    /** OnItemClickListener Methods */
    @Override
    public void onItemClick(ChatMessagesAdapter.ChatMessageViewHolder item, int position) {
        Toast.makeText(this, item.getSummary(), Toast.LENGTH_SHORT).show();
    }


}
