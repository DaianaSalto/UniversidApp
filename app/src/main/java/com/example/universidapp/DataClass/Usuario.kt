package com.example.universidapp.DataClass

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

//Data class con datos de Usuario que implementa la interfaz Parcelable
//Permite pasar objetos entre los distintos componentes de mi app
data class Usuario(var dni : String, var username: String, var password: String, var esProfesor: Boolean) : Parcelable {
    companion object {
        //Le indica al compilador de Kotlin que no le genere getter ni setter a creator
        //El creator genera instancias de la clase parcelable

        @JvmField val CREATOR: Parcelable.Creator<Usuario> = object : Parcelable.Creator<Usuario>{
            override fun newArray(size: Int): Array<Usuario?> = arrayOfNulls(size)
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun createFromParcel(source: Parcel): Usuario = Usuario(source)
        }
    }


    //Constructor adicional - toma el objeto Parcel e invoca al constructor creado inicialmente
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readBoolean()
    )



    // Guardo datos dentro del objeto Parcel
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeString(dni)
            dest.writeString(username)
            dest.writeString(password)
            dest.writeBoolean(esProfesor)
          }
    }

    override fun describeContents(): Int = 0
}
