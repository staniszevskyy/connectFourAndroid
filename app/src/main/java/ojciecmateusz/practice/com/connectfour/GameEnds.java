package ojciecmateusz.practice.com.connectfour;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class GameEnds extends AppCompatDialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int winner = getArguments().getInt("num");
        String win;
        if (winner == 1) win = "pierwszy, posługujący się kolorem czerwonym.";
        else win = "drugi, posługujący się kolorem niebieskim.";
        builder.setTitle("Gra zakończona")
                .setMessage("Wygrywa gracz " + win + " Gratulacje")
                .setNegativeButton("Zagraj ponownie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity main = (MainActivity) getActivity();
                        Intent intent = main.getIntent();
                        main.finish();
                        main.startActivity(intent);
                    }
                })
                .setPositiveButton("Zakończ grę", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity main = (MainActivity) getActivity();
                        main.finish();
                        System.exit(0);
                    }
                })
        ;
        return builder.create();
    }
}
