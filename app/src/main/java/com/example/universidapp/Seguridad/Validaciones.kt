package com.example.universidapp.Seguridad

import android.content.Context
import com.example.universidapp.FuncionesComunes.CompanionObject_Funciones
import com.example.universidapp.DataClass.Usuario

open class Validaciones {

    // Llamo a la funcion que devuelve un objeto univerdidad con sus alumnos, profesores y materias

    val universidad = CompanionObject_Funciones.obtenerUniversidad()


    //////////////////////// LOGIN

    open fun checkUserLogin(usuario: Triple<String, String, Context>): Pair<Boolean, Usuario> {

        var validData = false
        var error = ""
        var usuario_existente = Usuario("", "", "", false)

        //Checkeo que se hayan completado datos y si no fue hecho guardo error en variable para notificar
        if (usuario.first == "" || usuario.second == "") {
            error = "Todos los campos deben completarse."
        }

        else {
            for (alumno in universidad.Alumnos) {
                if (alumno.username != usuario.first) {
                    error = "Verificá tus credenciales."
                }
                //Si el usuario coincide, checkeo que la contraseña coincida con la del usuario
                // sino guardo error en variable para notificar
                else if (alumno.password != usuario.second) {
                    error = "Verificá tus credenciales."
                } else {
                    validData = true
                    usuario_existente = Usuario(alumno.dni, alumno.username, alumno.password, alumno.esProfesor)
                }

            }
            if (!validData) {
                for (profesor in universidad.Profesores) {
                    if (profesor.username != usuario.first) {
                        error = "Verificá tus credenciales."
                    }
                    //Si el usuario coincide, checkeo que la contraseña coincida con la del usuario
                    // sino guardo error en variable para notificar
                    else if (profesor.password != usuario.second) {
                        error = "Verificá tus credenciales."
                    } else {
                        validData = true
                        usuario_existente =
                            Usuario(profesor.dni, profesor.username, profesor.password, profesor.esProfesor)
                    }
                }
            }
        }

        //Si validData es false, notifica el error que corresponda
        if (!validData) {
            CompanionObject_Funciones.Mensaje(error, usuario.third)
        }

        return Pair(validData, usuario_existente)
    }


    //////////////////////// REGISTRO

    open fun checkUserRegistro(usuario: Triple<List<String>, Boolean, Context>): Boolean {

        var validData = false
        var registrado = false
        var error = ""
        var datoVacio = false

        //Checkeo que se hayan completado datos y si no fue hecho guardo error en variable para notificar
        for (dato in usuario.first) {
            if (dato == "") {
                datoVacio = true
            }
        }

        if (datoVacio) {
            error = "Todos los campos deben completarse."
        }

        //Si los datos estan completos y el usuario "esProfesor" es true, valido que el dni no exista en la lista de profesores
        else if (usuario.second) {
            for (profesor in universidad.Profesores) {
                if (profesor.dni == usuario.first[2]) {
                    error = "Persona ya registrada."
                    registrado = true
                }
            }
        }

        //Si los datos estan completos y el usuario "esProfesor" es false, valido que el dni no exista en la lista de alumnos
        else if (!usuario.second) {
            for (alumno in universidad.Alumnos) {
                if (alumno.dni == usuario.first[2]) {
                    error = "Persona ya registrada."
                    registrado = true
                }
            }
        }

        // Si todas las validaciones anteriores estan ok:
        if (!registrado && !validData) {
            validData = true

            //Doy de alta el usuario en la universidad
            CompanionObject_Funciones.altaUsuario(Pair(usuario.first, usuario.second))
            error = "¡Registro exitoso!"
            CompanionObject_Funciones.Mensaje(error, usuario.third)
        }

        //Si validData es false, notifica el error que corresponda
        if (!validData) {
            CompanionObject_Funciones.Mensaje(error, usuario.third)
        }

        return validData
    }

    //Funcion para validar materias ya tomadas
    open fun checkMaterias(materiaId: Int, esProfesor: Boolean, usuarioDNI: String): Pair<String, Boolean>{
        var resultado = ""
        var continuarRegistro = false

        //Recorro la lista de materias comparando por Id de materia en la lista de cada alumno o profesor
        for (materia in CompanionObject_Funciones.obtenerUniversidad().Materias) {
            if (materia.idMateria == materiaId) {
                if (esProfesor) {
                    for (profe in CompanionObject_Funciones.uni.Profesores) {
                        if (profe.dni == usuarioDNI) {
                            if (profe.MateriasProfesor.isEmpty()) {
                                continuarRegistro = true
                                resultado = "Registro de materia existoso"
                            } else {
                                for (mat in profe.MateriasProfesor) {
                                    if (mat.idMateria == materiaId) {
                                        resultado = "Materia ya registrada"
                                    } else {
                                        continuarRegistro = true
                                        resultado = "Registro de materia existoso"
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (alumno in CompanionObject_Funciones.uni.Alumnos) {
                        if (alumno.dni == usuarioDNI) {
                            if (alumno.MateriasAlumno.isEmpty()) {
                                continuarRegistro = true
                                resultado = "Inscripción a materia existosa"
                            }
                            else {
                                for (mat in alumno.MateriasAlumno) {
                                    if (mat.idMateria == materiaId) {
                                        resultado = "Ya estás inscripto en esta materia"
                                    } else {
                                        continuarRegistro = true
                                        resultado = "Inscripción a materia exitosa"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //Devuelvo el mensaje y el boolean para saber si esta ok o no
        return Pair(resultado, continuarRegistro)
    }

}