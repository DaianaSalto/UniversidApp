package com.example.universidapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.universidapp.R
import com.example.universidapp.Seguridad.Validaciones
import com.example.universidapp.DataClass.Usuario

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Creo servicio para validar
        val validar = Validaciones()

        //Declaro variables para cada elemento de la activity
        val txtDni = findViewById<EditText>(R.id.etDNI)
        val txtNombre = findViewById<EditText>(R.id.etNombre)
        val txtApellido = findViewById<EditText>(R.id.etApellido)
        val txtUsuario = findViewById<EditText>(R.id.etUsuario)
        val txtPassword = findViewById<EditText>(R.id.etPassword)
        val boolEsProfesor = findViewById<CheckBox>(R.id.cbEsProfesor)
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)


        // Al hacer click en el boton:
        btnRegistro.setOnClickListener {

            //Guardo los valores de cada input en diferentes variables
            val username = txtUsuario.text.toString()
            val password = txtPassword.text.toString()
            val dni = txtDni.text.toString()
            val nombre = txtNombre.text.toString()
            val apellido = txtApellido.text.toString()
            var esProfesor = false
            if (boolEsProfesor.isChecked) {
                esProfesor = true
            }

            //Creo una lista con los datos para registrar al nuevo alumno/profesor
            val listaDatos = listOf<String>(username,password,dni,nombre,apellido)

            //Llamo a la funcion del servicio validar para realizar las validaciones del registro
            val checkedData = validar.checkUserRegistro(Triple(listaDatos, esProfesor, this))

            if (checkedData)
            {
                //Si los datos estàn ok, se crea un nuevo usuario, se prepara y se envia a la siguiente actividad
                val nuevoUsuario = Usuario(dni, username, password, esProfesor)
                val intent = Intent(this, LoginActivity:: class.java)
                intent.putExtra("Usuario", nuevoUsuario)
                startActivity(intent)

                txtUsuario.text.clear()
                txtPassword.text.clear()
                txtNombre.text.clear()
                txtApellido.text.clear()
                txtDni.text.clear()

                finish()
            }

        }
    }
}