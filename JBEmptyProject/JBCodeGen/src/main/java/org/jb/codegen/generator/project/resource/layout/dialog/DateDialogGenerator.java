package org.jb.codegen.generator.project.resource.layout.dialog;

import org.jb.codegen.generator.project.Generator;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

public class DateDialogGenerator extends Generator {

	public DateDialogGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DateDialogGenerator(Messager messager, Filer filer, Element element) {
		super(messager, filer, element);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void source() {
		str.append("package app.jb.generated;\n");
		str.append("\n");
		str.append("import java.util.Calendar;\n");
		str.append("\n");
		str.append("import android.annotation.SuppressLint;\n");
		str.append("import android.app.AlertDialog;\n");
		str.append("import android.app.Dialog;\n");
		str.append("import android.content.Context;\n");
		str.append("import android.content.DialogInterface;\n");
		str.append("import android.text.InputType;\n");
		str.append("import android.view.View;\n");
		str.append("import android.widget.DatePicker;\n");
		str.append("import android.widget.EditText;\n");
		str.append("import android.widget.LinearLayout;\n");
		str.append("import android.widget.TimePicker;\n");
		str.append("\n");
		str.append("@SuppressLint(\"NewApi\")\n");
		str.append("public class TimestampDialogBuilder {\n");
		str.append("	public static Dialog build(Context context, final View view) {\n");
		str.append("		AlertDialog.Builder builder = new AlertDialog.Builder(context);\n");
		str.append("		\n");
		str.append("		final DatePicker datePicker = new DatePicker(context);\n");
		str.append("		final TimePicker timePicker = new TimePicker(context);\n");
		str.append("		final EditText editText = ((EditText)view);\n");
		str.append("\n");
		str.append("		datePicker.setVisibility(View.INVISIBLE);\n");
		str.append("		timePicker.setVisibility(View.INVISIBLE);\n");
		str.append("\n");
		str.append("		if(editText.getInputType() == InputType.TYPE_CLASS_DATETIME || \n");
		str.append("				editText.getInputType() == (InputType.TYPE_CLASS_DATETIME + \n");
		str.append("											InputType.TYPE_DATETIME_VARIATION_DATE)) {\n");
		str.append("			datePicker.setVisibility(View.VISIBLE);\n");
		str.append("			datePicker.setCalendarViewShown(false);\n");
		str.append("		}\n");
		str.append("		if(editText.getInputType() == InputType.TYPE_CLASS_DATETIME || \n");
		str.append("				editText.getInputType() == (InputType.TYPE_CLASS_DATETIME + \n");
		str.append("											InputType.TYPE_DATETIME_VARIATION_TIME)) { \n");
		str.append("			timePicker.setVisibility(View.VISIBLE);\n");
		str.append("		}\n");
		str.append("\n");
		str.append("		LinearLayout container = new LinearLayout(context);\n");
		str.append("		container.setOrientation(LinearLayout.VERTICAL);\n");
		str.append("		builder.setView(container);\n");
		str.append("		\n");
		str.append("		if(datePicker != null)\n");
		str.append("			container.addView(datePicker);\n");
		str.append("		if(timePicker != null)\n");
		str.append("			container.addView(timePicker);\n");
		str.append("\n");
		str.append("        builder.setMessage(\"Informe uma Data\");\n");
		str.append("        builder.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n");
		str.append("                   public void onClick(DialogInterface dialog, int id) {\n");
		str.append("                	   String timestamp = \"\";\n");
		str.append("                	   if(datePicker.getVisibility() == View.VISIBLE) {\n");
		str.append("                		   timestamp += String.format(\"%02d\", datePicker.getDayOfMonth());\n");
		str.append("                		   timestamp += \"/\";\n");
		str.append("                		   timestamp += String.format(\"%02d\", datePicker.getMonth());\n");
		str.append("                		   timestamp += \"/\";\n");
		str.append("                		   timestamp += datePicker.getYear();\n");
		str.append("                		   timestamp += \" \";\n");
		str.append("                	   }\n");
		str.append("                	   if(timePicker.getVisibility() == View.VISIBLE) {\n");
		str.append("                		   timestamp += String.format(\"%02d\", timePicker.getCurrentHour());\n");
		str.append("                		   timestamp += \":\";\n");
		str.append("                		   timestamp += String.format(\"%02d\", timePicker.getCurrentMinute());\n");
		str.append("                	   }\n");
		str.append("                	   editText.setText(timestamp);\n");
		str.append("                   }\n");
		str.append("               });\n");
		str.append("        builder.setNegativeButton(\"Cancelar\", new DialogInterface.OnClickListener() {\n");
		str.append("                   public void onClick(DialogInterface dialog, int id) {\n");
		str.append("                       // User cancelled the dialog\n");
		str.append("                   }\n");
		str.append("               });\n");
		str.append("        // Create the AlertDialog object and return it\n");
		str.append("        return builder.create();\n");
		str.append("	}\n");
		str.append("}\n");
	}
	
	@Override
	public void generate() {
		JavaFileObject file = null;
		try {
			file = filer.createSourceFile("java." + "app.jb.generated." + "TimestampDialogBuilder", element);

			BufferedWriter bw = new BufferedWriter(file.openWriter());
			bw.append(str.toString());
			bw.close();
			//file.openWriter().append(str.toString()).close();
		} catch (FilerException e2) {
			//e2.printStackTrace();
		}  catch(IOException e) {
			e.printStackTrace();
		}
	}

}
