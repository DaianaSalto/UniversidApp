package com.example.universidapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.example.universidapp.Adaptadores.AdaptadorListaMaterias
import com.example.universidapp.FuncionesComunes.CompanionObject_Funciones
import com.example.universidapp.R
import com.example.universidapp.DataClass.Usuario

class ListaMateriasActivity : AppCompatActivity() {

    // Declaro la variable para guardar los datos que quiero ver en la lista
    private lateinit var listaMateriasView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_materias)

        // Guardo en variables los datos de los intent extras
        val usuario = intent.getParcelableExtra<Usuario>("Usuario")

        //Declaro variables para cada elemento de la activity
        val lblUsu = findViewById<TextView>(R.id.tvUser)

        //Seteo textos por para las variables
        lblUsu.text = "Alumno: " + usuario!!.username

        // Si es profesor, cambio el texto de la label
        if (usuario.esProfesor){
            lblUsu.text = "Profesor: " + usuario!!.username
        }

        //Llamo desde el companion object la funcion para obtener los datos de la universidad y guardo la lista de materias en una variable
        val listaMaterias = CompanionObject_Funciones.obtenerUniversidad().Materias

        //Asigno al listview el elemento listview del activity
        listaMateriasView = findViewById(R.id.list_view_lista_materias)

        // Llamo al adaptador para poder levantar una lista y le paso la activity como contexto y mi lista.
        val adaptadorSimple = AdaptadorListaMaterias(this, listaMaterias)
        listaMateriasView.adapter = adaptadorSimple

        //Al hacer click en uno de los items de la lista:
        listaMateriasView.setOnItemClickListener{ parent, view, position, id ->

            // Preparo y envio el intent con el usuario y el id de la materia a la nueva activity
            val materia : Int = listaMaterias[position].idMateria
            val intent = Intent(this, DetalleMateriaActivity:: class.java)
            intent.putExtra("Usuario", usuario )
            intent.putExtra("Materia", materia)

            startActivity(intent)
            finish()
        }
    }
}