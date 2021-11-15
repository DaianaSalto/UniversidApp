package com.example.universidapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.universidapp.R
import com.example.universidapp.DataClass.Usuario

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        //Declaro variables para cada elemento de la activity
        val btnVerMaterias = findViewById<Button>(R.id.btnVerMaterias)
        val lblNombreUsuario = findViewById<TextView>(R.id.tvUsuario)

        // Guardo en una variable los datos del data class Usuario
        val usuario = intent.getParcelableExtra<Usuario>("Usuario")

        //Seteo textos por para las variables
        lblNombreUsuario.text = usuario!!.username.uppercase()

        // Al hacer click en el boton, preparo y envio el intent con el usuario a la nueva activity
        btnVerMaterias.setOnClickListener {
            val intent = Intent(this, ListaMateriasActivity:: class.java)
            intent.putExtra("Usuario", usuario )
            startActivity(intent)
        }
    }

}