package app.jb.generated;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.app.AlertDialog;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

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

public class PedidoDetailActivity extends AppCompatActivity {

	Pedido obj;
	EditText editTextId = null;
	TextView textViewObjectCliente = null;
	EditText editTextData = null;
	Spinner spinnerSituacao = null;
	TextView textViewCollectionItens = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_pedido_detail);

		editTextId = (EditText) findViewById(R.id.pedidoDetailActivityJBAttributeEditTextId);
		textViewObjectCliente = (TextView) findViewById(R.id.pedidoDetailActivityJBAttributeTextViewObjectCliente);
		editTextData = (EditText) findViewById(R.id.pedidoDetailActivityJBAttributeEditTextData);
		spinnerSituacao = (Spinner) findViewById(R.id.pedidoDetailActivityJBAttributeSpinnerSituacao);
		textViewCollectionItens = (TextView) findViewById(R.id.pedidoDetailActivityJBAttributeTextViewCollectionItens);

		editTextId.setEnabled(false);
		textViewObjectCliente.setEnabled(false);
		editTextData.setEnabled(false);
		spinnerSituacao.setEnabled(false);
		textViewCollectionItens.setEnabled(false);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
				(this, android.R.layout.simple_spinner_item, EnumUtil.descriptionValues(SituacaoPedido.values()));
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSituacao.setAdapter(dataAdapter);
    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_detail_pedido_activity, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();

    	return super.onOptionsItemSelected(item);
    }

	public void selectDate(View view) {
		Dialog dialog = TimestampDialogBuilder.build(this, view);
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
	}

	//Object Interactions
	public void openDetailObjectCliente(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ClienteDetailActivity.class);
		intent.putExtra("index", obj.getId());
		startActivity(intent);
	}
	public void openDetailObjectItens(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ItemListActivity.class);
		intent.putExtra("ORIGIN_INDEX", obj.getId());
		startActivity(intent);
	}

	//Method Calls

	//Load Method
    public void load() {
		new SendLoadRequestTask().execute();
    }

	//AsyncTasks for Remote Persistence

	private class SendLoadRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int index = getIntent().getIntExtra("index", 0);

			try {
				WebDAO dao = new WebDAO();
				obj = (Pedido) dao.findById(Pedido.class, index);
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
			editTextId.setText(Integer.toString(obj.getId()));
			textViewObjectCliente.setText(DescriptionUtil.extractDescription(obj.getCliente(), DescriptionType.PRIMARY));
			editTextData.setText(DateUtil.fromDate(obj.getData(), ""));
			spinnerSituacao.setSelection(obj.getSituacao().ordinal());;
		}
	}

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