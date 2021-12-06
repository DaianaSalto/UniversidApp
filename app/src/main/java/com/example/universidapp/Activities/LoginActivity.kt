package com.example.universidapp.Activities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.universidapp.R
import com.example.universidapp.Seguridad.Validaciones
import com.example.universidapp.DataClass.Usuario

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Guardo en variables los datos de los intent extras
        val nuevoUsuario = intent.getParcelableExtra<Usuario>("Usuario")

        //Creo servicio para validar
        val validar = Validaciones()

        //Declaro variables para cada elemento de la activity
        val dato_usuario = findViewById<EditText>(R.id.txtUsuario)
        val dato_password = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnIngresar)
        val linkRegistro = findViewById<TextView>(R.id.linkRegistro)

        if (nuevoUsuario?.username != null){
            dato_usuario.setText(nuevoUsuario.username)
        }

        btnLogin.setOnClickListener {
            //Se crean variables con los datos ingresados
            val dato_usuarioC = dato_usuario.text.toString()
            val dato_passwordC = dato_password.text.toString()

            //Llamo a la funcion para validar datos contra la lista de personas de la universidad
            val checkedData = validar.checkUserLogin(Triple(dato_usuarioC, dato_passwordC, this))

            //Si la data vuelve OK, se envia el data class usuario a la siguiente activity
            if (checkedData.first)
            {
                val intent = Intent(this, PrincipalActivity:: class.java)
                intent.putExtra("Usuario", checkedData.second)

                startActivity(intent)

                dato_usuario.text.clear()
                dato_password.text.clear()
            }

        }

        //Si hace click en el link de registro, se redirige a la activity correspondiente
        linkRegistro.setOnClickListener {
                val intent = Intent(this, RegistroActivity:: class.java)
                startActivity(intent)
                dato_usuario.text.clear()
                dato_password.text.clear()
            }

        }
    }

