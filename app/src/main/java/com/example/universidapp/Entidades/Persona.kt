package com.example.universidapp.Entidades

abstract class Persona () {
    lateinit var dni: String
    lateinit var apellido: String
    lateinit var nombre: String
    var esProfesor : Boolean = false

    open fun tomarMateria(materia: Materia){
    }
}