package com.example.mckinley;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<List_data>list_data;
    private Context context;
    private Button button;

    OnPoemActivityListener PoemActivityListener;

    public interface OnPoemActivityListener
    {

        public void performaddpoem();
    }


    public MyAdapter(List<List_data> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data,parent,false);
        // button = view.findViewById(R.id.bn);
        //  button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //  public void onClick(View view) {
        //  PoemActivityListener.performaddpoem();
        //}
        // });

        return new ViewHolder(view);
    }
    public void onAttach(Context context){
        Activity activity=(Activity) context;
        PoemActivityListener =(OnPoemActivityListener) activity;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final List_data listData = list_data.get(position);


        holder.UserName.setText(listData.getName());
        holder.UserYear.setText(listData.getyear());
        holder.Color.setText(String.valueOf(listData.getColor()));
        holder.Pantone_value.setText(String.valueOf(listData.getPantone_value()));


    }


    @Override
    public int getItemCount() {
        return list_data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView UserName, UserPassword,UserYear,Color,Pantone_value;

        public ViewHolder(View itemView) {
            super(itemView);

            UserName = itemView.findViewById(R.id.user_name);
            UserPassword = itemView.findViewById(R.id.user_pass);

        }
    }

}
