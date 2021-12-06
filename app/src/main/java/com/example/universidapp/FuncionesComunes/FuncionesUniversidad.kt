package com.example.universidapp.FuncionesComunes

import android.content.Context
import android.widget.Toast
import com.example.universidapp.Entidades.Alumno
import com.example.universidapp.Entidades.Materia
import com.example.universidapp.Entidades.Profesor
import com.example.universidapp.Entidades.Universidad

class CompanionObject_Funciones {
    companion object {
        val universidad = Universidad()
        val uni = crearUniversidad()

        // Funcion para harcodear un listado de alumnos y profesores
        fun crearUniversidad() : Universidad {

            // Alumnos

            val alumno1 = Alumno("dsalto", "dai123")
            alumno1.dni = "33212222"
            alumno1.nombre = "Daiana"
            alumno1.apellido = "Salto"
            alumno1.esProfesor = false

            val alumno2 = Alumno("pgomez", "pedro123")
            alumno2.dni = "34544678"
            alumno2.nombre = "Pedro"
            alumno2.apellido = "Gomez"
            alumno2.esProfesor = false

            /*
            val alumno3 = Alumno("lamel", "luciana123")
            alumno3.dni = "44311234"
            alumno3.nombre = "Luciana"
            alumno3.apellido = "Amel"
            alumno3.esProfesor = false

            val alumno4 = Alumno("csalto", "cami123")
            alumno4.dni = "30987176"
            alumno4.nombre = "Camila"
            alumno4.apellido = "Salto"
            alumno4.esProfesor = false

            val alumno5 = Alumno("dmartinez", "da123")
            alumno5.dni = "36789222"
            alumno5.nombre = "Daniela"
            alumno5.apellido = "Martinez"
            alumno5.esProfesor = false

            val alumno6 = Alumno("jgomez", "jesus123")
            alumno6.dni = "23678543"
            alumno6.nombre = "Jesus"
            alumno6.apellido = "Gomez"
            alumno6.esProfesor = false

            val alumno7 = Alumno("mherrera", "mario123")
            alumno7.dni = "34564334"
            alumno7.nombre = "Mario"
            alumno7.apellido = "Herrera"
            alumno7.esProfesor = false

            val alumno8 = Alumno("eviera", "eze123")
            alumno8.dni = "36533231"
            alumno8.nombre = "Ezequiel"
            alumno8.apellido = "Viera"
            alumno8.esProfesor = false

            val alumno9 = Alumno("sbaroni", "simon123")
            alumno9.dni = "33986332"
            alumno9.nombre = "Simon"
            alumno9.apellido = "Baroni"
            alumno9.esProfesor = false

            val alumno10 = Alumno("aavila", "ale123")
            alumno10.dni = "94678921"
            alumno10.nombre = "Alejo"
            alumno10.apellido = "Avila"
            alumno10.esProfesor = false
            */

            universidad.Alumnos = universidad.Alumnos.plus(alumno1)
            universidad.Alumnos = universidad.Alumnos.plus(alumno2)
            /*
            universidad.Alumnos = universidad.Alumnos.plus(alumno3)
            universidad.Alumnos = universidad.Alumnos.plus(alumno4)
            universidad.Alumnos = universidad.Alumnos.plus(alumno5)
            universidad.Alumnos = universidad.Alumnos.plus(alumno6)
            universidad.Alumnos = universidad.Alumnos.plus(alumno7)
            universidad.Alumnos = universidad.Alumnos.plus(alumno8)
            universidad.Alumnos = universidad.Alumnos.plus(alumno9)
            universidad.Alumnos = universidad.Alumnos.plus(alumno10)
            */

            // Profesores

            val profesor1 = Profesor("cgonzalez", "catag111")
            profesor1.dni = "23566734"
            profesor1.nombre = "Catalina"
            profesor1.apellido = "Gonzalez"
            profesor1.esProfesor = true

            val profesor2 = Profesor("jgomez", "jaimeg111")
            profesor2.dni = "37523678"
            profesor2.nombre = "Jaime"
            profesor2.apellido = "Gomez"
            profesor2.esProfesor = true

            /*
            val profesor3 = Profesor("lvalente", "lu111")
            profesor3.dni = "24311265"
            profesor3.nombre = "Lucia"
            profesor3.apellido = "Valente"
            profesor3.esProfesor = true


            val profesor4 = Profesor("lcarrasco", "lucio111")
            profesor4.dni = "24318634"
            profesor4.nombre = "Lucio"
            profesor4.apellido = "Carrasco"
            profesor4.esProfesor = true

            val profesor5 = Profesor("rmartinez", "ruby111")
            profesor5.dni = "14311234"
            profesor5.nombre = "Ruby"
            profesor5.apellido = "Martinez"
            profesor5.esProfesor = true

            val profesor6 = Profesor("arodriguez", "amelia111")
            profesor6.dni = "94312123"
            profesor6.nombre = "Amelia"
            profesor6.apellido = "Rodriguez"
            profesor6.esProfesor = true
            */

            universidad.Profesores = universidad.Profesores.plus(profesor1)
            universidad.Profesores = universidad.Profesores.plus(profesor2)

            /*
            universidad.Profesores = universidad.Profesores.plus(profesor3)
            universidad.Profesores = universidad.Profesores.plus(profesor4)
            universidad.Profesores = universidad.Profesores.plus(profesor5)
            universidad.Profesores = universidad.Profesores.plus(profesor6)
            */

            // Llama a metodo para consumir API Materias
            val listaMateriasAPI = FuncionesAPI.obtenerDatosAPI()
            for (materia in listaMateriasAPI){
                val nuevaMateria = Materia(materia.idMateria, materia.nombreMateria, materia.datosMateria)
                universidad.Materias = universidad.Materias.plus(nuevaMateria) as ArrayList<Materia>
            }

            return universidad
        }

        //Devuelve la universidad creada en la funcion crearUniversidad
        fun obtenerUniversidad() : Universidad {
            return uni
        }


        // Funcion para dar de alta un alumno/profesor
        fun altaUsuario(datos: Pair<List<String>, Boolean>){
            if (datos.second) {
                val nuevoProfesor = Profesor(datos.first[0],datos.first[1])
                nuevoProfesor.dni = datos.first[2]
                nuevoProfesor.nombre = datos.first[3]
                nuevoProfesor.apellido = datos.first[4]
                nuevoProfesor.esProfesor = datos.second

                uni.Profesores = uni.Profesores.plus(nuevoProfesor)
            }
            else {
                val nuevoAlumno = Alumno(datos.first[0],datos.first[1])
                nuevoAlumno.dni = datos.first[2]
                nuevoAlumno.nombre = datos.first[3]
                nuevoAlumno.apellido = datos.first[4]
                nuevoAlumno.esProfesor = datos.second

                uni.Alumnos = uni.Alumnos.plus(nuevoAlumno)
            }
        }

        //Funcion para tomar materia
        fun tomarMateria(usuarioDNI: String, esProfesor: Boolean, materiaId : Int, mensaje: String) : String {
            var materiaSeleccionada: Materia

            for (materia in uni.Materias) {
                if (materia.idMateria == materiaId) {
                    materiaSeleccionada = materia
                    if (esProfesor) {
                        for (profe in uni.Profesores){
                            if (profe.dni == usuarioDNI){
                                profe.tomarMateria(materiaSeleccionada)
                            }
                        }
                    } else {
                        for (alumno in uni.Alumnos){
                            if (alumno.dni == usuarioDNI){
                                alumno.tomarMateria(materiaSeleccionada)
                            }
                        }
                    }
                }
            }
            return mensaje
        }
        

        // Funcion para llamar toasts desde cualquier activity
        fun Mensaje(mensaje : String, context: Context)
        {
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        }

    }
}