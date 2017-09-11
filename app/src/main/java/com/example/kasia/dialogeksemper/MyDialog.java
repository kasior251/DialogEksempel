package com.example.kasia.dialogeksemper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by kasia on 11.09.2017.
 */

public class MyDialog extends DialogFragment {

    private DialogClickListener callback;

    public interface DialogClickListener {
        public void onYesClick();
        public void onNoClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callback = (DialogClickListener)getActivity();
        }
        catch (ClassCastException e) {
            throw new ClassCastException("Kallende klasse må implementere interfacet!");
        }
    }

    //konstruktør
    public static MyDialog newInstance (int title) {
        MyDialog frag = new MyDialog();
        Bundle args = new Bundle();
        args.putInt("Tittel", title);
        frag.setArguments(args);
        return frag;
    }

    //kode som lager selve dialogboksen
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Avslutt").setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                callback.onYesClick();
                            }
                        })
                .setNegativeButton(R.string.ikkeok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                callback.onNoClick();
                            }
                        })
                .create();
    }
}
