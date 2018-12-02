package com.leo.paris.listelivre

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LivreFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LivreFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LivreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var livre: Livre? = null
    private var listener: OnFragmentInteractionListener2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            livre = it.getParcelable<Livre>("livre")

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_livre, container, false)
        val titre = view!!.findViewById<TextView>(R.id.titre_view_2)
        titre.text = livre!!.title
        val resume = view!!.findViewById<TextView>(R.id.resume_view)
        var texte = ""
        for(partie in livre!!.synopsis){
            texte += partie + "\n"
        }
        resume.text = texte


        val couverture = view!!.findViewById<ImageView>(R.id.couverture_view_2)
        Picasso.get().load(livre!!.cover).into(couverture);

        val bouton = view!!.findViewById<ImageButton>(R.id.retour_btn)
        bouton.setOnClickListener{
            onButtonPressed()
        }

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed() {
        listener?.onFragmentInteraction2()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener2) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener2 {
        // TODO: Update argument type and name
        fun onFragmentInteraction2()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LivreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(livre: Livre) =
                LivreFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("livre", livre)
                    }
                }
    }
}
