package com.example.sqlite_a.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite_a.MainActivity;
import com.example.sqlite_a.R;
import com.example.sqlite_a.database.DBController;
import com.example.sqlite_a.database.Teman;
import com.example.sqlite_a.edit_teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder>{

    private ArrayList<Teman> listData;
    private Context control;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    //
    public TemanAdapter.
            TemanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        //
        control = parent.getContext();
        //
        return new TemanViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {

        String nm,tlp,id;

        id = listData.get(position).getId();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelpon();
        DBController db = new DBController(control);

        holder.namaTxt.setText(nm);
        holder.namaTxt.setTextSize(30);
        holder.namaTxt.setTextColor(Color.BLUE);
        holder.telponTxt.setText(tlp);

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popmenu = new PopupMenu(control, holder.cardku);
                popmenu.inflate(R.menu.popupmenu);
                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit:
                                Intent i = new Intent(control, edit_teman.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nm);
                                i.putExtra("telpon",tlp);
                                control.startActivity(i);
                                break;
                            case R.id.hapus:
                                HashMap<String,String> values = new HashMap<>();
                                values.put("id", id);
                                db.deleteData(values);
                                Intent j = new Intent(control, MainActivity.class);
                                control.startActivity(j);
                                break;
                        }
                        return true;
                    }
                });
                popmenu.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)? listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt, telponTxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView)view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);

            cardku.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    return true;
                }
            });
        }
    }
}
