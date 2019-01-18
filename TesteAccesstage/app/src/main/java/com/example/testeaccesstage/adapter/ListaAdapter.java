package com.example.testeaccesstage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testeaccesstage.R;
import com.example.testeaccesstage.model.Evento;


import java.util.ArrayList;
import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder>{

    private List<Evento> eventoList = new ArrayList<>() ;

    public ListaAdapter(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public ListaAdapter() {
    }

    public void adicioarEvento(List<Evento> eventoList){

        if (this.eventoList.isEmpty()){
            this.eventoList = eventoList;
        }else {
            this.eventoList.addAll(eventoList);
        }
            notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View evento = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_view,viewGroup,false
        );
        return new ViewHolder(evento) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Evento evento = eventoList.get(i);
        viewHolder.bind(evento);
    }

    @Override
    public int getItemCount() {
        return eventoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView id;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_view);
            id = itemView.findViewById(R.id.txt_id);
            title = itemView.findViewById(R.id.txt_title);

        }
        public  void bind (final Evento evento){

            id.setText(evento.getId());
            title.setText(evento.getTitle());


        }
    }




}
