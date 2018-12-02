package com.leo.paris.listelivre

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), ListeFragment.OnFragmentInteractionListener, LivreFragment.OnFragmentInteractionListener2 {
    override fun onFragmentInteraction2() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_liste, ListeFragment(), ListeFragment::class.java.name).addToBackStack("liste_fragment")
                .commit()
    }

    override fun onFragmentInteraction(livre: Livre) {
        val fragment = LivreFragment.newInstance(livre)
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_livre, fragment, LivreFragment::class.java.name)
                .addToBackStack("livre_fragment").commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_liste, ListeFragment(), ListeFragment::class.java.name).addToBackStack("liste_fragment")
                .commit()
    }
}
