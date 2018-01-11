package app.jb.generated;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

@SuppressLint("NewApi")
public class TimestampDialogBuilder {
	public static Dialog build(Context context, final View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		final DatePicker datePicker = new DatePicker(context);
		final TimePicker timePicker = new TimePicker(context);
		final EditText editText = ((EditText)view);

		datePicker.setVisibility(View.INVISIBLE);
		timePicker.setVisibility(View.INVISIBLE);

		if(editText.getInputType() == InputType.TYPE_CLASS_DATETIME || 
				editText.getInputType() == (InputType.TYPE_CLASS_DATETIME + 
											InputType.TYPE_DATETIME_VARIATION_DATE)) {
			datePicker.setVisibility(View.VISIBLE);
			datePicker.setCalendarViewShown(false);
		}
		if(editText.getInputType() == InputType.TYPE_CLASS_DATETIME || 
				editText.getInputType() == (InputType.TYPE_CLASS_DATETIME + 
											InputType.TYPE_DATETIME_VARIATION_TIME)) { 
			timePicker.setVisibility(View.VISIBLE);
		}

		LinearLayout container = new LinearLayout(context);
		container.setOrientation(LinearLayout.VERTICAL);
		builder.setView(container);
		
		if(datePicker != null)
			container.addView(datePicker);
		if(timePicker != null)
			container.addView(timePicker);

        builder.setMessage("Informe uma Data");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   String timestamp = "";
                	   if(datePicker.getVisibility() == View.VISIBLE) {
                		   timestamp += String.format("%02d", datePicker.getDayOfMonth());
                		   timestamp += "/";
                		   timestamp += String.format("%02d", datePicker.getMonth());
                		   timestamp += "/";
                		   timestamp += datePicker.getYear();
                		   timestamp += " ";
                	   }
                	   if(timePicker.getVisibility() == View.VISIBLE) {
                		   timestamp += String.format("%02d", timePicker.getCurrentHour());
                		   timestamp += ":";
                		   timestamp += String.format("%02d", timePicker.getCurrentMinute());
                	   }
                	   editText.setText(timestamp);
                   }
               });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
	}
}
