package com.example.game.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.game.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicTacToeFragment extends Fragment {

    public static final String TEXT_STATE = "Text_State";
    public static final String ENABLE_STATE = "Enable_State";
    Button btn00;
    Button btn01;
    Button btn02;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn20;
    Button btn21;
    Button btn22;
    View view;

    static String winner;
    static String[][] buttonText = new String[3][3];
    static int xo = 0;

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        if (savedInstanceState == null)
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    buttonText[i][j] = "e";
        Bind();
        if (savedInstanceState != null) {
            String[] btnText = savedInstanceState.getStringArray(TEXT_STATE);
            boolean[] btnState = savedInstanceState.getBooleanArray(ENABLE_STATE);
            btn00.setText(btnText[0]);
            btn01.setText(btnText[1]);
            btn02.setText(btnText[2]);
            btn10.setText(btnText[3]);
            btn11.setText(btnText[4]);
            btn12.setText(btnText[5]);
            btn20.setText(btnText[6]);
            btn21.setText(btnText[7]);
            btn22.setText(btnText[8]);

            btn00.setEnabled(btnState[0]);
            btn01.setEnabled(btnState[1]);
            btn02.setEnabled(btnState[2]);
            btn10.setEnabled(btnState[3]);
            btn11.setEnabled(btnState[4]);
            btn12.setEnabled(btnState[5]);
            btn20.setEnabled(btnState[6]);
            btn21.setEnabled(btnState[7]);
            btn22.setEnabled(btnState[8]);

        }

        return view;
    }

    private void Bind() {
        btn00 = view.findViewById(R.id.btn0_0);
        btn01 = view.findViewById(R.id.btn0_1);
        btn02 = view.findViewById(R.id.btn0_2);
        btn10 = view.findViewById(R.id.btn1_0);
        btn11 = view.findViewById(R.id.btn1_1);
        btn12 = view.findViewById(R.id.btn1_2);
        btn20 = view.findViewById(R.id.btn2_0);
        btn21 = view.findViewById(R.id.btn2_1);
        btn22 = view.findViewById(R.id.btn2_2);

        ButtonOnClick(btn00, 0, 0);
        ButtonOnClick(btn01, 0, 1);
        ButtonOnClick(btn02, 0, 2);
        ButtonOnClick(btn10, 1, 0);
        ButtonOnClick(btn11, 1, 1);
        ButtonOnClick(btn12, 1, 2);
        ButtonOnClick(btn20, 2, 0);
        ButtonOnClick(btn21, 2, 1);
        ButtonOnClick(btn22, 2, 2);

    }

    private void ButtonOnClick(final Button button, final int row, final int column) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (xo % 2 == 0) {
                    button.setText(R.string.X);
                    TicTacToeFragment.buttonText[row][column] = "X";
                } else {
                    button.setText(R.string.O);
                    TicTacToeFragment.buttonText[row][column] = "O";
                }
                xo++;
                button.setEnabled(false);

                if (whoIsWinner(TicTacToeFragment.buttonText))
                    Result(winner + " is Winner!");

                if (xo == 9)
                    Result("No one is Winner!");
            }
        });
    }

    private Boolean whoIsWinner(String[][] buttonText) {
        for (int i = 0; i < 3; i++) {
            if (buttonText[0][i].equals(buttonText[1][i]) && buttonText[0][i].equals(buttonText[2][i])
                    && !buttonText[0][i].equals("e")) {
                winner = buttonText[0][i];
                return true;
            }
            if (buttonText[i][0].equals(buttonText[i][1]) && buttonText[i][0].equals(buttonText[i][2])
                    && !buttonText[i][0].equals("e")) {
                winner = buttonText[i][0];
                return true;
            }
        }
        if (buttonText[0][0].equals(buttonText[1][1]) && buttonText[0][0].equals(buttonText[2][2])
                || buttonText[0][2].equals(buttonText[1][1]) && buttonText[0][2].equals(buttonText[2][0])) {
            if (buttonText[1][1].equals("e"))
                return false;
            winner = buttonText[1][1];
            return true;
        }
        return false;
    }

    private void ButtonVisibility() {
        btn00.setEnabled(false);
        btn01.setEnabled(false);
        btn02.setEnabled(false);
        btn10.setEnabled(false);
        btn11.setEnabled(false);
        btn12.setEnabled(false);
        btn20.setEnabled(false);
        btn21.setEnabled(false);
        btn22.setEnabled(false);
    }

    private void Result(final String showMassage) {
        ButtonVisibility();
        Snackbar.make(getActivity().findViewById(R.id.fragment_frame), showMassage, Snackbar.LENGTH_LONG).show();
        xo = 0;
        buttonText = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttonText[i][j] = "e";
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] btnText = new String[9];
        boolean[] btnState = new boolean[9];
        int k = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (!buttonText[i][j].equals("e"))
                    btnText[k] = buttonText[i][j];
                else
                    btnState[k] = true;
                k++;
            }

        outState.putBooleanArray(ENABLE_STATE, btnState);
        outState.putStringArray(TEXT_STATE, btnText);
    }
}
