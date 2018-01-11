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

import org.jb.model.Cliente;
import org.jb.model.Sexo;
import org.jb.model.Pedido;

public class ClienteInsertActivity extends AppCompatActivity {

	Cliente obj;
	EditText editTextNome = null;
	Spinner spinnerSexo = null;
	EditText editTextDataNascimento = null;
	TextView textViewCollectionPedidos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_cliente_insert);

		obj = new Cliente();

		editTextNome = (EditText) findViewById(R.id.clienteInsertActivityJBAttributeEditTextNome);
		spinnerSexo = (Spinner) findViewById(R.id.clienteInsertActivityJBAttributeSpinnerSexo);
		editTextDataNascimento = (EditText) findViewById(R.id.clienteInsertActivityJBAttributeEditTextDataNascimento);
		textViewCollectionPedidos = (TextView) findViewById(R.id.clienteInsertActivityJBAttributeTextViewCollectionPedidos);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
				(this, android.R.layout.simple_spinner_item, EnumUtil.descriptionValues(Sexo.values()));
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSexo.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_insert_cliente_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Handle action bar item clicks here. The action bar will
    	// automatically handle clicks on the Home/Up button, so long
    	// as you specify a parent activity in AndroidManifest.xml.
    	int id = item.getItemId();
    	if (id == R.id.menu_insert_cliente_activity_action_save) {
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

		new SendInsertRequestTask().execute();
	}

	//AsyncTasks for Remote Persistence

	private class SendInsertRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			try {
				WebDAO dao = new WebDAO();
				return dao.insert(obj);
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
}