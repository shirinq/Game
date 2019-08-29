package com.example.game.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.game.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class inRowFragment extends Fragment {

    private static final String TAG = "inRowFragment";

    RadioButton mRadioButton5;
    RadioButton mRadioButton6;
    GridLayout mGridLayout;
    Button[] buttons;
    int turn = 0;

    public inRowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in_row, container, false);

        mRadioButton5 = view.findViewById(R.id.radioButton5);
        mRadioButton6 = view.findViewById(R.id.radioButton6);
        mGridLayout = view.findViewById(R.id.GridLayout);

        if (savedInstanceState != null) {
            mRadioButton5.setVisibility(View.INVISIBLE);
            mRadioButton6.setVisibility(View.INVISIBLE);
        }
        setOnclick(mRadioButton5, 5);
        setOnclick(mRadioButton6, 6);


        return view;
    }

    private void setOnclick(Button button, final int number) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRadioButton5.setVisibility(View.INVISIBLE);
                mRadioButton6.setVisibility(View.INVISIBLE);
                mGridLayout.setRowCount(number);
                mGridLayout.setColumnCount(number);
                ButtonGenerator(number * number);
                for (int i = 0; i < number * number; i++) {
                    mGridLayout.addView(buttons[i], 130, 130);
                }
                setOnclick(number);
            }
        });
    }

    private void setOnclick(final int number) {
        for (final Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View view) {
                    ColorDrawable colorBlue = new ColorDrawable();
                    ColorDrawable colorRed = new ColorDrawable();
                    colorBlue.setColor(Color.BLUE);
                    colorRed.setColor(Color.RED);
                    if (turn % 2 == 0)
                        button.setBackground(colorBlue);
                    else
                        button.setBackground(colorRed);
                    turn++;
                    button.setShadowLayer(360, 120, 120, Color.BLACK);
                    button.setEnabled(false);
                    for (int j = 0; j < buttons.length; j++) {
                        if (buttons[j].equals(button) && j > 4)
                            buttons[j - number].setEnabled(true);
                        //if(buttons.length-number<j&&j<buttons.length)
                    }
                }
            });
        }
    }

    private void ButtonGenerator(int length) {
        buttons = new Button[length];
        for (int i = 0; i < length; i++) {
            buttons[i] = new Button(getActivity());
            if (i < length - Math.sqrt(length))
                buttons[i].setEnabled(false);
        }
    }

    private void Winner(int number, Button subButton) {

    }

}
