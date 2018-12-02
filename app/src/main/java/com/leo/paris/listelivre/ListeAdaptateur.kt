package com.leo.paris.listelivre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso

class ListeAdaptateur(val livres: List<Livre>?, private val inflater: LayoutInflater, val onItemPressed: (livre: Livre) -> Unit) : BaseAdapter() {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.row_liste_livre, parent, false)
        val imgView = view.findViewById<ImageView>(R.id.couverture_view)
        Picasso.get().load(livres!![position].cover).into(imgView);
        view.findViewById<TextView>(R.id.titre_view).text = livres!![position].title
        view.setOnClickListener {
            onItemPressed(livres!![position])
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return livres!![position]
    }

    override fun getItemId(position: Int): Long {
        return livres!![position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return livres!!.size
    }

}