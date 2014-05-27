package com.example.android.navigationdrawerexample;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: DarthDesktop
 * Date: 5/26/14
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class DeckFragment extends Fragment {
    View rootView = null;
    public static final String ARG_FRAG_NUMBER = "frag_number";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.deck, container, false);
        int i = getArguments().getInt(ARG_FRAG_NUMBER);
        String planet = getResources().getStringArray(R.array.fragments)[i];

        // int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
        //                "drawable", getActivity().getPackageName());
        // ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(planet);
        return rootView;
    }
}