package com.example.ricardo.retrifitfilme.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricardo.retrifitfilme.R;
import com.example.ricardo.retrifitfilme.model.FilmeModelo;
import com.example.ricardo.retrifitfilme.model.ItemResposta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterFilme extends RecyclerView.Adapter<AdapterFilme.ViewHolder> {

    private List<FilmeModelo> filmeModeloList = new ArrayList<>();

    public AdapterFilme() {
    }

    public AdapterFilme(List<FilmeModelo> filmeModeloList) {
        this.filmeModeloList = filmeModeloList;
    }

    public void adicioarFilme(List<FilmeModelo> filmeModeloList) {
        if(this.filmeModeloList.isEmpty()){
            this.filmeModeloList = filmeModeloList;
        }else {
            this.filmeModeloList.addAll(filmeModeloList);
        }
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_filme,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        FilmeModelo rating = filmeModeloList.get(i);
        viewHolder.title.setText(rating.getTitle());
        viewHolder.year.setText(rating.getYear());
        viewHolder.genre.setText(rating.getGenre());

        Picasso.get().load(rating.getPoster()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return filmeModeloList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

            ImageView imageView;
            TextView title;
            TextView year;
            TextView genre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_);
            title = itemView.findViewById(R.id.txt_title);
            year = itemView.findViewById(R.id.txt_year);
            genre = itemView.findViewById(R.id.txt_genre);
        }
    }
}
