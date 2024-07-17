package com.gibranflores.fondos.FragmentosAdministrador;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gibranflores.fondos.CatergoriasAdmin.AestheticA.AestheticA;
import com.gibranflores.fondos.CatergoriasAdmin.CybercoreA.CybercoreA;
import com.gibranflores.fondos.CatergoriasAdmin.EspacioA.EspacioA;
import com.gibranflores.fondos.CatergoriasAdmin.FrutigerA.FrutigerA;
import com.gibranflores.fondos.R;


public class InicioAdmin extends Fragment {

Button Frutiger, Espacio, Cybercore, Aesthetic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio_admin, container, false);

        Frutiger = view.findViewById(R.id.Frutiger);
        Espacio = view.findViewById(R.id.Espacio);
        Cybercore = view.findViewById(R.id.Cybercore);
        Aesthetic = view.findViewById(R.id.Aesthetic);

        Frutiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FrutigerA.class));
            }
        });

        Espacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EspacioA.class));
            }
        });

        Cybercore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CybercoreA.class));
            }
        });

        Aesthetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AestheticA.class));
            }
        });

        return view;
    }
}