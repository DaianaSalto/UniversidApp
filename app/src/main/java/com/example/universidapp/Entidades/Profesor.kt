package com.example.universidapp.Entidades

class Profesor (var username: String, var password: String): Persona() {

    var MateriasProfesor : List<Materia> = listOf()

    override fun tomarMateria(materia: Materia){
        MateriasProfesor = MateriasProfesor.plus(materia)
    }

}