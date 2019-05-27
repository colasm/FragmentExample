package com.example.fragment;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.fragment.fragments.PizzaDetailFragment;
import com.example.fragment.fragments.PizzaMenuFragment;

public class MainActivity extends AppCompatActivity implements PizzaMenuFragment.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DEBUG", getResources().getConfiguration().orientation + "");

        if (savedInstanceState == null) {
            // Instanciation du fragment 1
            PizzaMenuFragment firstFragment = new PizzaMenuFragment();

            // Insertion du fragment 1 dans le container
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer, firstFragment);
            ft.commit();
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // instanciation du second fragment
            PizzaDetailFragment secondFragment = new PizzaDetailFragment();

            // construction d'un bundle pour informer de l'information à afficher (0)
            Bundle args = new Bundle();
            args.putInt("position", 0);
            // les informations Activité -> fragment sont transmises via setArguments et getArguments
            secondFragment.setArguments(args);

            // insertion dans le second container lorsque l'écran est en paysage
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.flContainer2, secondFragment)
                    .commit();
        }
    }

    @Override
    public void onPizzaItemSelected(int position) {
        Toast.makeText(this, "Called By Fragment A: position - "+ position, Toast.LENGTH_SHORT).show();

        // Charge le fragment associé au détail
        PizzaDetailFragment secondFragment = new PizzaDetailFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        // communication de l'argument au fragment
        secondFragment.setArguments(args);

        // si horizontal : remplissage du container 2
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer2, secondFragment)
                    .commit();
        }else{
            // sinon remplissage du container 1
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, secondFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
