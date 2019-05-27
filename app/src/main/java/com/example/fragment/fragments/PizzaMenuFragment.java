package com.example.fragment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fragment.Data.Pizza;
import com.example.fragment.R;

public class PizzaMenuFragment extends Fragment {

    ArrayAdapter<String> itemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Pizza.pizzaMenu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        // Inflate le fichier xml du fragment
        return inflater.inflate(R.layout.fragment_pizza_menu, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // appel de l'action itemselected au niveau du listener (l'activité)
                listener.onPizzaItemSelected(position);
            }
        });
    }

    // listener du click de choix pizza
    private OnItemSelectedListener listener;



    // Appelé lorsque le fragment est associé à l'activité. L'activité n'est généralement pas prête à
    // cet instant donc pas d'action graphiques ici
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // place l'activité en écoute
        if(context instanceof OnItemSelectedListener){        // le contexte est il de type de l'interface ?
            this.listener = (OnItemSelectedListener) context; // ok, enregistre le listener
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement PizzaMenuFragment.OnItemSelectedListener");
        }
    }


    // interface de gestion de l'item
    public interface OnItemSelectedListener {
        // action sur selection de l'élément position
        void onPizzaItemSelected(int position);
    }

}
