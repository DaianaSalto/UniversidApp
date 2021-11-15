package com.example.universidapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.universidapp.FuncionesComunes.CompanionObject_Funciones
import com.example.universidapp.R
import com.example.universidapp.Seguridad.Validaciones
import com.example.universidapp.DataClass.Usuario

class DetalleMateriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_materia)

        //Llamo desde el companion object la funcion para obtener los datos de la universidad y guardo la lista de materias en una variable
        val listaMaterias = CompanionObject_Funciones.obtenerUniversidad().Materias

        // Guardo en variables los datos de los intent extras
        val usuario = intent.getParcelableExtra<Usuario>("Usuario")
        val materia = intent.getIntExtra("Materia", 404)

        //Declaro variables para cada elemento de la activity
        val lblNombreMateria = findViewById<TextView>(R.id.tvNombreMateria)
        val lblDatoMateria= findViewById<TextView>(R.id.tvDatoMateria)
        val lblTipoUser= findViewById<TextView>(R.id.tvTipoUser)
        val lblNombre= findViewById<TextView>(R.id.tvNombre)
        val btnAgregarMateria= findViewById<Button>(R.id.btnAgregarMateria)

        //Seteo textos por para las variables
        lblTipoUser.text = "Alumno: "
        lblNombre.text = usuario!!.username

        // Si es profesor, cambio el texto de la label y del boton
        if (usuario.esProfesor){
            lblTipoUser.text = "Profesor: "
            btnAgregarMateria.text = "TOMAR MATERIA"
        }


        //Recorro la lista de materias y seteo los textos de las labels con los datos del item
        for (item in listaMaterias) {
            if (item.idMateria == materia){
                lblNombreMateria.text = item.nombreMateria
                lblDatoMateria.text = item.datosMateria
            }
        }

        // Valido si ya tiene la materia
        fun validarMateria(): Pair<String, Boolean> {
            val validar = Validaciones()
            return validar.checkMaterias(materia, usuario.esProfesor, usuario.dni)
        }


        btnAgregarMateria.setOnClickListener{
            val validado = validarMateria()

            // Al hacer click en el boton, si las validaciones estan OK, se llama a la funcion tomar materia
            if (validado.second){
                val inscripcion = CompanionObject_Funciones.tomarMateria(usuario.dni, usuario.esProfesor, materia, validado.first)
                CompanionObject_Funciones.Mensaje(inscripcion, this)
            }

            //Sino solo se muestra el mensaje de error
            else {
            CompanionObject_Funciones.Mensaje(validado.first, this)
            }

            // Preparo y envio el intent con el usuario a la nueva activity
            val intent = Intent(this, ListaMateriasActivity:: class.java)
            intent.putExtra("Usuario", usuario )
            startActivity(intent)
            finish()

        }
    }
}