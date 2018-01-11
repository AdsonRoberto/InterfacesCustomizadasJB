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
import org.jb.model.Pedido;
import org.jb.model.SituacaoPedido;
import org.jb.model.Cliente;
import org.jb.model.Item;

public class PedidoFindActivity extends AppCompatActivity {

	String cliente = null;
	String data = null;
	String situacao = null;
	String itens = null;

	CheckBox checkBoxFindcliente = null;
	CheckBox checkBoxFinddata = null;
	CheckBox checkBoxFindsituacao = null;
	CheckBox checkBoxFinditens = null;

	TextView textViewObjectCliente = null;
	EditText editTextData = null;
	Spinner spinnerSituacao = null;
	TextView textViewCollectionItens = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_pedido_find);

		checkBoxFindcliente = 
			(CheckBox) findViewById(R.id.pedidoFindActivityCheckboxFindTextViewObjectCliente);
		checkBoxFinddata = 
			(CheckBox) findViewById(R.id.pedidoFindActivityCheckboxFindEditTextData);
		checkBoxFindsituacao = 
			(CheckBox) findViewById(R.id.pedidoFindActivityCheckboxFindSpinnerSituacao);
		checkBoxFinditens = 
			(CheckBox) findViewById(R.id.pedidoFindActivityCheckboxFindTextViewCollectionItens);

		textViewObjectCliente = (TextView) findViewById(R.id.pedidoFindActivityJBAttributeTextViewObjectCliente);
		editTextData = (EditText) findViewById(R.id.pedidoFindActivityJBAttributeEditTextData);
		spinnerSituacao = (Spinner) findViewById(R.id.pedidoFindActivityJBAttributeSpinnerSituacao);
		textViewCollectionItens = (TextView) findViewById(R.id.pedidoFindActivityJBAttributeTextViewCollectionItens);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
				(this, android.R.layout.simple_spinner_item, EnumUtil.descriptionValues(SituacaoPedido.values()));
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSituacao.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_find_pedido_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	if (id == R.id.menu_find_pedido_activity_action_find) {
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

		if(requestCode == 1) {
			int indexCliente = data.getIntExtra("indexCliente", 0);
			if(indexCliente > 0) {
				getIntent().putExtra("indexCliente", indexCliente);
				new SendActivityResultClienteRequestTask().execute();
			}
		}

	super.onActivityResult(requestCode, resultCode, data);
}

	//Object Interactions
	public void openSelectionObjectcliente(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ClienteListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.SELECTION);
    	startActivityForResult(intent, 1);
	}

	//CRUD Methods

    public void find() {
		data = DateUtil.fromDate(DateUtil.toDate(editTextData.getText().toString(), ""), "");
		situacao = "" + SituacaoPedido.valueOf(spinnerSituacao.getSelectedItem().toString()).ordinal();

    	Intent intent = new Intent(this, PedidoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.RESULT);

    	if(checkBoxFindcliente.isChecked()) {
    		intent.putExtra("cliente", cliente);
    	}
    	if(checkBoxFinddata.isChecked()) {
    		intent.putExtra("data", data);
    	}
    	if(checkBoxFindsituacao.isChecked()) {
    		intent.putExtra("situacao", situacao);
    	}

    	startActivity(intent);
	}


	//AsyncTasks for Activity Result

	private class SendActivityResultClienteRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int indexCliente = getIntent().getIntExtra("indexCliente", 0);
			try {
				WebDAO dao = new WebDAO();
				Cliente object = (Cliente) dao.findById(Cliente.class, indexCliente);
				return DescriptionUtil.extractDescription(object, DescriptionType.PRIMARY);
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
			((TextView) dialogWidget).setText(result);
		}
	}

}