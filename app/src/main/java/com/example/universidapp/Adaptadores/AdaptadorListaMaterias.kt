package com.example.universidapp.Adaptadores
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.universidapp.Entidades.Materia
import com.example.universidapp.R

// Clase para poder mostrar datos en una lista

class AdaptadorListaMaterias(var contexto: Context, listado: List<Materia>) : BaseAdapter() {

    // Creo una lista vacia
    var listado: List<Materia>? = null

    init {
        this.listado = listado
    }

    // Pide cada uno de los elementos a mostrar en la lista
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var vista: View? = convertView

        // si la lista esta vacia (para mostrar el primer item), levanta (inflate) una vista (layout) con el item materia (contiene nombre y datos de 1 materia)
        if (vista == null) {
            vista = LayoutInflater.from(contexto).inflate(R.layout.item_lista_materias, null)
            holder = ViewHolder(vista)
            vista.tag = holder

        // En las siguientes llamadas:
        } else {

            holder = vista.tag as? ViewHolder
        }

        // Genero una variable para guardar cada item de la lista como una instancia de Materia
        val materiaObjeto = getItem(position) as Materia


        //Seteo en cada item el nombre de la materia como label
        holder?.nombreMat?.text = materiaObjeto.nombreMateria

        return vista!!
    }

    //Devuelve el objeto Materia que se muestra en una determinada posición
    override fun getItem(position: Int): Any {
        return listado?.get(position)!!
    }


    //Devuelve el identificador de fila de una determinada posición de la lista
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //Devuelve el número de elementos de la lista
    override fun getCount(): Int {
        return listado?.count()!! //
    }

    // Crea una vista con un text view y lo asigna a una variable por id
    class ViewHolder(vista: View) {
        var nombreMat: TextView? = null

        init {
            nombreMat = vista.findViewById(R.id.tvMateria)
        }
    }
}