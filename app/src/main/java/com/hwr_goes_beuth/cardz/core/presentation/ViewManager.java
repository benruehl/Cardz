package com.hwr_goes_beuth.cardz.core.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.hwr_goes_beuth.cardz.R;

import java.util.concurrent.Callable;

/**
 * Created by Project0rion on 04.12.2016.
 */
public class ViewManager {

    public void switchView(Context sourceView, Class targetViewClass) {
        Intent myIntent = new Intent(sourceView, targetViewClass);
        sourceView.startActivity(myIntent);
    }

    public ConfirmationBuilder confirm(Context contextView) {
        return new ConfirmationBuilder(contextView);
    }


    public static class ConfirmationBuilder {

        private Context context;
        private String title;
        private String content;
        private String positiveButtonText;
        private String negativeButtonText;
        private boolean isCancelable;
        private Callable onConfirm;
        private Callable onRefuse;

        public ConfirmationBuilder(Context context) {
            this.context = context;
            title = "";
            content = "";
            positiveButtonText = context.getResources().getString(android.R.string.yes);
            negativeButtonText = context.getResources().getString(android.R.string.no);
            isCancelable = true;
            onConfirm = new Callable() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            };
            onRefuse = new Callable() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            };
        }

        public ConfirmationBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ConfirmationBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ConfirmationBuilder positiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public ConfirmationBuilder negativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public ConfirmationBuilder isCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        public ConfirmationBuilder onConfirm(Callable onConfirm) {
            this.onConfirm = onConfirm;
            return this;
        }

        public ConfirmationBuilder onRefuse(Callable onRefuse) {
            this.onRefuse = onRefuse;
            return this;
        }

        public AlertDialog build() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setCancelable(isCancelable);
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder.setMessage(content);
            alertDialogBuilder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        onConfirm.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            alertDialogBuilder.setNeutralButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        onRefuse.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return alertDialogBuilder.create();
        }
    }
}
