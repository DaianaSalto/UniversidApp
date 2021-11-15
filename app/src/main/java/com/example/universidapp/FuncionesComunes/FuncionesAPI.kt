package com.example.universidapp.FuncionesComunes

import android.util.Log
import com.example.universidapp.ConsultasAPI.ConsutarDatosAPI
import com.example.universidapp.Entidades.Materia
import org.json.JSONArray

class FuncionesAPI {
    companion object {
        fun obtenerDatosAPI() : ArrayList<Materia>{
            //Guardo en una variable los datos que me trae la consulta al endpoint
            val datos =
                ConsutarDatosAPI.consultarDatos("https://universidapp.free.beeceptor.com/lists/materias")

            //Declaro una lista de objetos Materia
            val arrayMaterias:  ArrayList<Materia> = ArrayList()

            // Declaro una variable de tipo JSONArray y le asigno los datos que me devolviò la api
            val datosObjectJson= JSONArray(datos)

            // Recorro la lista y guardo cada item (cada objeto json) en la variable materia
            // Y agrego al array de materias, una instancia de Materia con los datos de cada item de la API
            for ( i in 0..6)
            {
                val materia = datosObjectJson.getJSONObject(i)

                arrayMaterias.add(
                    Materia(
                        materia.getInt("idMateria"),
                        materia.getString("nombreMateria"),
                        materia.getString("datosMateria")

                    )
                )
            }
        return arrayMaterias
    }

    }
}