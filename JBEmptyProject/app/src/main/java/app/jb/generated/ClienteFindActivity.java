package app.jb.generated;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jb.ui.annotation.visual.enums.DescriptionType;
import org.jb.ui.annotation.visual.util.DescriptionUtil;

import org.jb.codegen.generator.auxiliar.TypeListView;
import org.jb.codegen.util.*;

import org.jb.persistence.web.dao.WebDAO;

import app.jb.generated.*;
import org.jb.model.Cliente;
import org.jb.model.Sexo;
import org.jb.model.Pedido;

public class ClienteFindActivity extends AppCompatActivity {

	String nome = null;
	String sexo = null;
	String dataNascimento = null;
	String pedidos = null;

	CheckBox checkBoxFindnome = null;
	CheckBox checkBoxFindsexo = null;
	CheckBox checkBoxFinddataNascimento = null;
	CheckBox checkBoxFindpedidos = null;

	EditText editTextNome = null;
	Spinner spinnerSexo = null;
	EditText editTextDataNascimento = null;
	TextView textViewCollectionPedidos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_cliente_find);

		checkBoxFindnome = 
			(CheckBox) findViewById(R.id.clienteFindActivityCheckboxFindEditTextNome);
		checkBoxFindsexo = 
			(CheckBox) findViewById(R.id.clienteFindActivityCheckboxFindSpinnerSexo);
		checkBoxFinddataNascimento = 
			(CheckBox) findViewById(R.id.clienteFindActivityCheckboxFindEditTextDataNascimento);
		checkBoxFindpedidos = 
			(CheckBox) findViewById(R.id.clienteFindActivityCheckboxFindTextViewCollectionPedidos);

		editTextNome = (EditText) findViewById(R.id.clienteFindActivityJBAttributeEditTextNome);
		spinnerSexo = (Spinner) findViewById(R.id.clienteFindActivityJBAttributeSpinnerSexo);
		editTextDataNascimento = (EditText) findViewById(R.id.clienteFindActivityJBAttributeEditTextDataNascimento);
		textViewCollectionPedidos = (TextView) findViewById(R.id.clienteFindActivityJBAttributeTextViewCollectionPedidos);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
				(this, android.R.layout.simple_spinner_item, EnumUtil.descriptionValues(Sexo.values()));
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSexo.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_find_cliente_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	if (id == R.id.menu_find_cliente_activity_action_find) {
    		find();
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

	//Dialog Methods

	public void selectDate(View view) {
		Dialog dialog = TimestampDialogBuilder.build(this, view);
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	super.onActivityResult(requestCode, resultCode, data);
}

	//Object Interactions
	//CRUD Methods

    public void find() {
		nome = editTextNome.getText().toString();
		sexo = "" + Sexo.valueOf(spinnerSexo.getSelectedItem().toString()).ordinal();
		dataNascimento = DateUtil.fromDate(DateUtil.toDate(editTextDataNascimento.getText().toString(), ""), "");

    	Intent intent = new Intent(this, ClienteListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.RESULT);

    	if(checkBoxFindnome.isChecked()) {
    		intent.putExtra("nome", nome);
    	}
    	if(checkBoxFindsexo.isChecked()) {
    		intent.putExtra("sexo", sexo);
    	}
    	if(checkBoxFinddataNascimento.isChecked()) {
    		intent.putExtra("dataNascimento", dataNascimento);
    	}

    	startActivity(intent);
	}

}