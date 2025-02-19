package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.model.recycler.ingredientsComplex.IngredientAdapter;
import com.example.mealplanner.model.recycler.ingredientsComplex.IngredientEventModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCart extends Fragment {

    ArrayList<IngredientEventModel> ingredientsList = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShoppingCart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCart.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCart newInstance(String param1, String param2) {
        ShoppingCart fragment = new ShoppingCart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.ingredientsRecycler);
        setIngredientEventModels();
        IngredientAdapter adapter = new IngredientAdapter(view.getContext(), ingredientsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    private void setIngredientEventModels(){
        String[] ingredientNames = getResources().getStringArray(R.array.ingredient_name_cv);
        String[] ingreedientDetails = getResources().getStringArray(R.array.ingredient_detail_cv);
        String[] ingredientPrices = getResources().getStringArray(R.array.ingredient_price_cv);

        for (int i = 0; i < ingredientNames.length; i++) {
            ingredientsList.add(new IngredientEventModel(ingredientNames[i], ingreedientDetails[i], ingredientPrices[i]));
        }
    }
}