package com.leo.paris.listelivre

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_liste, container, false)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(LivreService::class.java)
        var livres: List<Livre>? = null
        val context = context
        api.listeLivre().enqueue(object: Callback<List<Livre>> {
            override fun onFailure(call: Call<List<Livre>>, t: Throwable){
                Timber.e("error on download data")
            }

            override fun onResponse(call: Call<List<Livre>>, response: Response<List<Livre>>){
                livres = response.body()
                val listView = view!!.findViewById<ListView>(R.id.liste_livre)
                listView.adapter = ListeAdaptateur(livres, LayoutInflater.from(context), {livre -> onItemPressed(livre)})

            }
        })


        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onItemPressed(livre: Livre) {
        listener?.onFragmentInteraction(livre)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(livre: Livre)
    }

}
