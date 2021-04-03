package com.example.game.Fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;

import com.example.game.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class inRowFragment extends Fragment {

    private static final String TAG = "inRowFragment";

    RadioButton mRadioButton5;
    RadioButton mRadioButton6;
    GridLayout mGridLayout;
    Button[][] buttons;
    int turn = 0;
    View view;

    public inRowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_in_row, container, false);

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

                ButtonGenerator(number);

                for (int i = 0; i < number; i++) {
                    for (int j = 0; j < number; j++)
                        mGridLayout.addView(buttons[i][j], 130, 130);
                }
                setOnclick(number);
            }
        });
    }

    private void setOnclick(final int number) {
        for (int i = 0; i < number; i++)
            for (final Button button : buttons[i]) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (turn % 2 == 0) {
                            button.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.DARKEN);
                            button.setText("Blue");
                            button.setTextColor(Color.TRANSPARENT);

                        } else {
                            button.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
                            button.setText("Red");
                            button.setTextColor(Color.TRANSPARENT);
                        }
                        turn++;
                        button.setEnabled(false);
                        for (int i = 0; i < number; i++)
                            for (int j = 0; j < number; j++) {
                                if (buttons[i][j].equals(button)) {
                                    subArray(number, i, j);
                                    if (i >= 1)
                                        buttons[i - 1][j].setEnabled(true);
                                }
                            }
                    }
                });
            }
    }

    private void ButtonGenerator(int length) {
        buttons = new Button[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                buttons[i][j] = new Button(getActivity());
                if (i < length - 1)
                    buttons[i][j].setEnabled(false);
            }
        }
    }

    private void subArray(int number, int row, int column) {
        Winner(number, buttons[row]);//Horizontal

        Button[] subVertical = new Button[number];
        for (int k = 0; k < number; k++) {
            subVertical[k] = buttons[k][column];
        }
        Winner(number, subVertical);//Vertical

        // top-left to bottom-right - green diagonals
        int blue, red;
        for (int rowStart = 0; rowStart < buttons.length - 4; rowStart++) {
            blue = 0;
            red = 0;
            for (int i = rowStart, col = 0; i < buttons.length && col < buttons.length; i++, col++) {
                if (buttons[i][col].getText().equals("Blue")) {
                    blue++;
                    if (blue >= 4) {
                        for (int j = 0; j < number; j++)
                            for (Button button : buttons[j])
                                button.setEnabled(false);
                        Snackbar.make(view, "Blue is winner", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.container_layout, new inRowFragment())
                                                .commit();
                                    }
                                }).show();
                    }
                } else if (buttons[i][col].getText().equals("Red")) {
                    red++;
                    if (red >= 4) {
                        for (int j = 0; j < number; j++)
                            for (Button button : buttons[j])
                                button.setEnabled(false);
                        Snackbar.make(view, "Red is winner", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.container_layout, new inRowFragment())
                                                .commit();
                                    }
                                }).show();
                    }
                }
            }
        }

// top-left to bottom-right - red diagonals
        int rowStart = 0;
        for (int colStart = 1; colStart < buttons.length - 4; colStart++) {
            blue = 0;
            red = 0;
            for (int i = 0, col = colStart; i < buttons.length && col < buttons.length; i++, col++) {
                if (buttons[i][col].getText().equals("Blue")) {
                    blue++;
                    if (blue >= 4) {
                        for (int j = 0; j < number; j++)
                            for (Button button : buttons[j])
                                button.setEnabled(false);
                        Snackbar.make(view, "Blue is winner", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.container_layout, new inRowFragment())
                                                .commit();
                                    }
                                }).show();
                    }
                } else if (buttons[i][col].getText().equals("Red")) {
                    red++;
                    if (red >= 4) {
                        for (int j = 0; j < number; j++)
                            for (Button button : buttons[j])
                                button.setEnabled(false);
                        Snackbar.make(view, "Red is winner", BaseTransientBottomBar.LENGTH_INDEFINITE)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.container_layout, new inRowFragment())
                                                .commit();
                                    }
                                }).show();
                    }
                }
            }
        }
    }


    private void Winner(int number, Button[] subButton) {
        for (int i = 0; i < number / 2; i++) {
            int counter = 0;
            try {
                String temp = subButton[i].getText().toString();
                if (temp.equals(""))
                    continue;
                for (int j = i; j < i + 4; j++) {
                    if (temp.equals(subButton[j].getText().toString()))
                        counter++;
                }
                if (counter == 4) {
                    for (int j = 0; j < number; j++)
                        for (Button button : buttons[j])
                            button.setEnabled(false);
                    Snackbar.make(view, temp + " is winner", BaseTransientBottomBar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.container_layout, new inRowFragment())
                                            .commit();
                                }
                            }).show();
                    break;
                }
            } catch (NullPointerException e) {
                subButton[i] = new Button(getActivity());
                subButton[i].setText("");
                i--;
            }

        }


    }

}
//Winner(number, Arrays.copyOfRange(buttons, k, k + number));
