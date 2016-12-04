package com.hwr_goes_beuth.cardz.core.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.concurrent.Callable;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class ViewManager {

    public void switchView(Context sourceView, Class targetViewClass) {
        Intent myIntent = new Intent(sourceView, targetViewClass);
        sourceView.startActivity(myIntent);
    }

    public void showDialog(Object sourceView, int errorCode, boolean isCancelable, final Callable onConfirm, final Callable onRefuse) {
        Activity sourceActivity = sourceView instanceof Activity ? (Activity)sourceView : null;

        /*ErrorCodeDialogInfo dialogInfo = ErrorCodeUtils.getErrorCodeDialogInfo(errorCode, sourceActivity);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(sourceActivity);
        alertDialogBuilder.setCancelable(isCancelable);
        alertDialogBuilder.setTitle(dialogInfo.getTitle());
        alertDialogBuilder.setMessage(dialogInfo.getMessage());
        alertDialogBuilder.setPositiveButton(dialogInfo.getPositiveChoice(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    onConfirm.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        alertDialogBuilder.setNeutralButton(dialogInfo.getNegativeChoice(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    onRefuse.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        alertDialogBuilder.create().show();*/
    }
}
