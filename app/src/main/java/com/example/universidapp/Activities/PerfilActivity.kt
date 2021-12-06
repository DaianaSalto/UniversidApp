package com.example.universidapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.universidapp.Adaptadores.AdaptadorListaMaterias
import com.example.universidapp.DataClass.Usuario
import com.example.universidapp.R
import com.example.universidapp.Seguridad.Validaciones

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        val validar = Validaciones()

        val lblNombre = findViewById<TextView>(R.id.nombreTv)
        val lblApellido = findViewById<TextView>(R.id.apellidoTv)
        val lblDni = findViewById<TextView>(R.id.dniTv)
        val lblTipo = findViewById<TextView>(R.id.tipoTv)
        val lblUser = findViewById<TextView>(R.id.usuarioTv)
        val lblLista = findViewById<TextView>(R.id.listaTv)
        val imgPerfil = findViewById<ImageView>(R.id.imgPerfil)
        val lblTxt = findViewById<TextView>(R.id.tvTxto)
        imgPerfil.setImageResource(R.drawable.alum_2)

        // Guardo en una variable los datos del data class Usuario
        val usuario = intent.getParcelableExtra<Usuario>("Usuario")


        //Obtengo los datos de Persona que me faltan
        val datos = validar.obtenerDatosUsuario(usuario)

        if (usuario != null) {
            lblNombre.text = "Nombre: " + datos.first
            lblApellido.text = "Apellido: " + datos.second
            lblTipo.text = "Tipo: Alumno"
            lblDni.text = "DNI: " + usuario.dni
            lblUser.text = "Usuario: " + usuario.username

            if (datos.third.isEmpty()){
                lblTxt.text = "Todavia no tomaste ninguna materia."
            }

            var materiasString = ""
            for (materia in datos.third){
                materiasString = "◉ " + materia.nombreMateria + "\n" + materiasString
            }
            lblLista.text = materiasString

            // Si es profesor, cambio el texto de la label
            if (usuario.esProfesor){
                lblTipo.text = "Tipo: Profesor"
                if (datos.third.isEmpty()){
                    lblTxt.text = "Todavia no tomaste ninguna materia."
                }
                else {
                    lblTxt.text = "Tenés estas materias registradas:"
                }
                imgPerfil.setImageResource(R.drawable.prof_1)

            }
        }
    }
}