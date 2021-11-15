package com.example.universidapp.Entidades

class Alumno (var username: String, var password: String): Persona() {

    var MateriasAlumno : List<Materia> = listOf()

    override fun tomarMateria(materia: Materia){
        MateriasAlumno = MateriasAlumno.plus(materia)
    }
}