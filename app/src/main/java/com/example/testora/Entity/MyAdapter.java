package com.example.testora.Entity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testora.MainActivity;
import com.example.testora.R;
import com.example.testora.services.ClickListener;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
public static myitems my ;
public static   int val=0;

    private Context mContext;
    private List<myitems> UserList;
    public MyAdapter( Context mContext, List<myitems> UserList) {
        this.mContext = mContext;
        this.UserList = UserList;
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mitemView= LayoutInflater.from(mContext).inflate(R.layout.activity_listview,parent,false);

        return new ViewHolder(mitemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final myitems singleItem = UserList.get(position);
        holder.txt.setText(singleItem.getTitle());
        holder.txt1.setText(singleItem.getEtat());
        holder.up.setText(singleItem.getDate());
        final int i=position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), UserList.get(i).toString() + String.valueOf(i), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(view.getContext());
                my=UserList.get(i);
data.ac=true;



            }
        });



    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt1;
        private TextView txt;
        private TextView up;
        private Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.title);
            txt1=itemView.findViewById(R.id.etat);
            btn=itemView.findViewById(R.id.button);
            up=itemView.findViewById(R.id.update);



        }
    }
}
