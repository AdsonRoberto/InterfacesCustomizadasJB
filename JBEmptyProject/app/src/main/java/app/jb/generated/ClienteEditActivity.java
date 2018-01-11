package app.jb.generated;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
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

public class ClienteEditActivity extends AppCompatActivity {

	Cliente obj;
	EditText editTextNome = null;
	Spinner spinnerSexo = null;
	EditText editTextDataNascimento = null;
	TextView textViewCollectionPedidos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_cliente_edit);

		editTextNome = (EditText) findViewById(R.id.clienteEditActivityJBAttributeEditTextNome);
		spinnerSexo = (Spinner) findViewById(R.id.clienteEditActivityJBAttributeSpinnerSexo);
		editTextDataNascimento = (EditText) findViewById(R.id.clienteEditActivityJBAttributeEditTextDataNascimento);
		textViewCollectionPedidos = (TextView) findViewById(R.id.clienteEditActivityJBAttributeTextViewCollectionPedidos);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
				(this, android.R.layout.simple_spinner_item, EnumUtil.descriptionValues(Sexo.values()));
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSexo.setAdapter(dataAdapter);
    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_edit_cliente_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	if (id == R.id.menu_edit_cliente_activity_action_save) {
    		save();
    		this.onBackPressed();
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

    public void save() {
		obj.setNome(editTextNome.getText().toString());
		obj.setSexo(Sexo.valueOf(spinnerSexo.getSelectedItem().toString()));
		obj.setDataNascimento(DateUtil.toDate(editTextDataNascimento.getText().toString()));

		new SendUpdateRequestTask().execute();
	}

	//Load Method
    public void load() {
		new SendLoadRequestTask().execute();
    }

	//AsyncTasks for Remote Persistence

	private class SendUpdateRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			try {
				WebDAO dao = new WebDAO();
				return dao.update(obj);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "Não foi possível executar a operação!";
		}

		protected void onProgressUpdate(Integer... progress) {
			//TODO
		}

		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
		}
	}

	private class SendLoadRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int index = getIntent().getIntExtra("index", 0);

			try {
				WebDAO dao = new WebDAO();
				obj = (Cliente) dao.findById(Cliente.class, index);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "Não foi possível executar a operação!";
		}

		protected void onProgressUpdate(Integer... progress) {
			//TODO
		}

		protected void onPostExecute(String result) {
			editTextNome.setText(new String(obj.getNome()));
			spinnerSexo.setSelection(obj.getSexo().ordinal());;
			editTextDataNascimento.setText(DateUtil.fromDate(obj.getDataNascimento(), ""));
		}
	}

}