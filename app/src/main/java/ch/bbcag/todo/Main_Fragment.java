package ch.bbcag.todo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by zascho on 17.06.2015.
 */
public class Main_Fragment extends Fragment {
    private View myView;
    private ArrayList<String> test = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.main_layout, container, false);
        test.add("test1");
        test.add("test2");
        test.add("test3");
        test.add("test4");

        int e = 0;
        RelativeLayout x = (RelativeLayout) getActivity().findViewById(R.id.layout);
        for (String i: test){
            Button myButton = new Button(getActivity().getApplicationContext());
            myButton.setId(e);
            myButton.setText(i);
            x.addView(myButton);
            e++;

        }
        return myView;
    }
}
