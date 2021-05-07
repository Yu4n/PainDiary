package com.example.paindiary.ui.DailyRecord;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.paindiary.R;
import com.example.paindiary.db.AppDatabase;
import com.example.paindiary.db.User;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
//    AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
//            AppDatabase.class,"user").allowMainThreadQueries().build();
    private List<User> users;
    private Context context;
    public ItemsAdapter (Context context,List<User> users){
        this.users = users;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        TextView tv =  (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_user, parent, false);
//        tv.findViewById(R.id.item_user_adapter);
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_user, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TextView tv = holder.textView;
        //TextView textView = holder.textView;
        holder.textView.setText(users.get(position).email + users.get(position).mood);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View tv) {
            super(tv);
            textView=(TextView)tv.findViewById(R.id.item_user_adapter);
        }
    }

}
