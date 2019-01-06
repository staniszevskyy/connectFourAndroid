package ojciecmateusz.practice.com.connectfour;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class RestartBO3 extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Resetuj grę")
                .setPositiveButton("Resetuj pojedynczą partię", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int winner = getArguments().getInt("num");
                        MainActivity main = (MainActivity) getActivity();
                        Intent intent = main.getIntent();
                        intent.putExtra("decrement", winner);
                        main.finish();
                        main.startActivity(intent);
                    }
                })
                .setNegativeButton("Resetuj całą rozgrywkę", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity main = (MainActivity) getActivity();
                        Intent intent = main.getIntent();
                        intent.putExtra("fullReset", true);
                        main.finish();
                        main.startActivity(intent);
                    }
                })
                .setNeutralButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
