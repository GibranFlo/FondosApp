package com.gibranflores.fondos.FragmentosAdministrador;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gibranflores.fondos.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;


public class RegistroAdmin extends Fragment {


    TextView FechaRegistro;
    EditText Correo, Password, Nombres, Apellidos, Edad;
    Button Registrar;

    FirebaseAuth auth;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_admin, container, false);

        FechaRegistro = view.findViewById(R.id.FechaRegistro);
        Correo = view.findViewById(R.id.Correo);
        Password = view.findViewById(R.id.Password);
        Nombres = view.findViewById(R.id.Nombre);
        Apellidos = view.findViewById(R.id.Apellido);
        Edad = view.findViewById(R.id.Edad);


        Registrar = view.findViewById(R.id.Registrar);

        auth = FirebaseAuth.getInstance();

        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'deL' yyyy");
        String SFecha = fecha.format(date);
        FechaRegistro.setText(SFecha);

        //Registrar
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = Correo.getText().toString();
                String pass = Password.getText().toString();
                String nombre = Nombres.getText().toString();
                String apellidos =  Apellidos.getText().toString();
                String edad = Edad.getText().toString();

                /*Todos los campos son obligatorios*/
                if (correo.equals("") || pass.equals("") || nombre.equals("") || apellidos.equals("") || edad.equals("")) {
                    Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {

                    //Validacion de correo
                    if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                        Correo.setError("Correo inválido");
                        Correo.setFocusable(true);
                    } else if (pass.length() < 6) {
                        Password.setError("La contraseña debe tener al menos 6 caracteres");
                    } else {
                        ReistroAdminstradores(correo, pass);
                    }

                }

            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Registrando espere un momento");
        progressDialog.setCancelable(false);


        return view;
    }

    private void ReistroAdminstradores(String correo, String pass) {
        progressDialog.show();

        auth.createUserWithEmailAndPassword(correo, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    //El usuario se ha creado correctamente
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            FirebaseUser user = auth.getCurrentUser();
                            assert user != null;


                            String UID = user.getUid();
                            String correo = Correo.getText().toString();
                            String pass = Password.getText().toString();
                            String nombre = Nombres.getText().toString();
                            String apellido = Apellidos.getText().toString();
                            String edad = Edad.getText().toString();
                            int EdadInt = Integer.parseInt(edad);

                            HashMap<String, Object> Administradores = new HashMap<>();

                            Administradores.put("UID", UID);
                            Administradores.put("correo", correo);
                            Administradores.put("pass", pass);
                            Administradores.put("nombre", nombre);
                            Administradores.put("apellido", apellido);
                            Administradores.put("edad", edad);
                            Administradores.put("IMAGEN", "");


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("BASE DE DATOS ADMIN");
                            reference.child(UID).setValue(Administradores);
                            getActivity().finish();
                            } else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error al registrar", Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}