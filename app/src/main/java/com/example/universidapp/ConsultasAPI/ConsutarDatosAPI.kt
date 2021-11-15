package com.example.universidapp.ConsultasAPI

import android.os.StrictMode
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ConsutarDatosAPI {
    companion object
    {
        @Throws(IOException::class)
        //Funcion que recibe una url (endpoint), levanta una conexion con el metodo Get,
        // guarda los datos obtenidos en una variable y los devuelve
        fun consultarDatos(url:String):String{

            //Desactiva validaciones en la conexion
            val policy= StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var datosDescargados: InputStream?=null
            try{
                val direccionWEB= URL(url)
                val conexion=direccionWEB.openConnection() as HttpURLConnection
                conexion.requestMethod="GET"
                conexion.connect()
                datosDescargados=conexion.inputStream
                return datosDescargados.bufferedReader().use{
                    it.readText()
                }
            }catch (e: IOException)
            {
                //Log.d("MENSAJE", e.message.toString() )
            }
            finally {
                if(datosDescargados!=null)
                {
                    datosDescargados.close()
                }
            }
            return "null"
        }
    }
}