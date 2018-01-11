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
import org.jb.model.Item;
import org.jb.model.Pedido;
import org.jb.model.Produto;

public class ItemDetailActivity extends AppCompatActivity {

	Item obj;
	EditText editTextId = null;
	TextView textViewObjectPedido = null;
	TextView textViewObjectProduto = null;
	EditText editTextQuantidade = null;
	EditText editTextValor = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_item_detail);

		editTextId = (EditText) findViewById(R.id.itemDetailActivityJBAttributeEditTextId);
		textViewObjectPedido = (TextView) findViewById(R.id.itemDetailActivityJBAttributeTextViewObjectPedido);
		textViewObjectProduto = (TextView) findViewById(R.id.itemDetailActivityJBAttributeTextViewObjectProduto);
		editTextQuantidade = (EditText) findViewById(R.id.itemDetailActivityJBAttributeEditTextQuantidade);
		editTextValor = (EditText) findViewById(R.id.itemDetailActivityJBAttributeEditTextValor);

		editTextId.setEnabled(false);
		textViewObjectPedido.setEnabled(false);
		textViewObjectProduto.setEnabled(false);
		editTextQuantidade.setEnabled(false);
		editTextValor.setEnabled(false);

    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_detail_item_activity, menu);
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
	public void openDetailObjectPedido(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, PedidoDetailActivity.class);
		intent.putExtra("index", obj.getId());
		startActivity(intent);
	}
	public void openDetailObjectProduto(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ProdutoDetailActivity.class);
		intent.putExtra("index", obj.getId());
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
				obj = (Item) dao.findById(Item.class, index);
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
			textViewObjectPedido.setText(DescriptionUtil.extractDescription(obj.getPedido(), DescriptionType.PRIMARY));
			textViewObjectProduto.setText(DescriptionUtil.extractDescription(obj.getProduto(), DescriptionType.PRIMARY));
			editTextQuantidade.setText(Integer.toString(obj.getQuantidade()));
			editTextValor.setText(Double.toString(obj.getValor()));
		}
	}

	private class SendActivityResultPedidoRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int indexPedido = getIntent().getIntExtra("indexPedido", 0);
			try {
				WebDAO dao = new WebDAO();
				Pedido object = (Pedido) dao.findById(Pedido.class, indexPedido);
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

	private class SendActivityResultProdutoRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int indexProduto = getIntent().getIntExtra("indexProduto", 0);
			try {
				WebDAO dao = new WebDAO();
				Produto object = (Produto) dao.findById(Produto.class, indexProduto);
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