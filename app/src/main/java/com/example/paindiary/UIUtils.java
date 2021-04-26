package com.example.paindiary;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;
public class UIUtils {

    public static AlertDialog createDialog(String title, String message, Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog dlg = builder.create();
        dlg.show();
        return dlg;
    }

    public static AlertDialog createDialog(String message, Context context){
        return createDialog(context.getString(R.string.wait),message,context);
    }

    public static void makeToast(String text,Context context){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

}