package com.gibranflores.fondos.CatergoriasAdmin.EspacioA;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gibranflores.fondos.R;

public class ViewHolderEspacio extends RecyclerView.ViewHolder {

    View mView;

    private ViewHolderEspacio.ClickListener mClickListener;

    public interface ClickListener{
        void OnItemClick(View view, int position);
        void OnItemLongClick(View view, int position);
    }

    public void setMyClickListener(ViewHolderEspacio.ClickListener ClickListener){
        mClickListener = ClickListener;
    }

    public ViewHolderEspacio(@NonNull View itemView) {
        super(itemView);
        mView =  itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.OnItemClick(view,getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.OnItemLongClick(view,getAdapterPosition());
                return true;
            }
        });

    }

    public void SetEspacio(Context context, String nombre, int vista, String imagen){
        ImageView ImagenEspacio;
        TextView NombreImagenEspacio;
        TextView VistasEspacios;

        ImagenEspacio = mView.findViewById(R.id.ImagenEspacio);
        NombreImagenEspacio = mView.findViewById(R.id.NombreImagenEspacio);
        VistasEspacios = mView.findViewById(R.id.VistasEspacios);

        NombreImagenEspacio.setText(nombre);

        String VistaString = String.valueOf(vista);

        VistasEspacios.setText(VistaString);

        try {

        }
        catch (Exception e){
            
        }
    }

}
