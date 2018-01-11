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
import org.jb.model.Item;
import org.jb.model.Pedido;
import org.jb.model.Produto;

public class ItemEditActivity extends AppCompatActivity {

	Item obj;
	TextView textViewObjectPedido = null;
	TextView textViewObjectProduto = null;
	EditText editTextQuantidade = null;
	EditText editTextValor = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_item_edit);

		textViewObjectPedido = (TextView) findViewById(R.id.itemEditActivityJBAttributeTextViewObjectPedido);
		textViewObjectProduto = (TextView) findViewById(R.id.itemEditActivityJBAttributeTextViewObjectProduto);
		editTextQuantidade = (EditText) findViewById(R.id.itemEditActivityJBAttributeEditTextQuantidade);
		editTextValor = (EditText) findViewById(R.id.itemEditActivityJBAttributeEditTextValor);

    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_edit_item_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	if (id == R.id.menu_edit_item_activity_action_save) {
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
		if(requestCode == 1) {
			int indexPedido = data.getIntExtra("indexPedido", 0);
			if(indexPedido > 0) {
				getIntent().putExtra("indexPedido", indexPedido);
				new SendActivityResultPedidoRequestTask().execute();
			}
		}

		if(requestCode == 2) {
			int indexProduto = data.getIntExtra("indexProduto", 0);
			if(indexProduto > 0) {
				getIntent().putExtra("indexProduto", indexProduto);
				new SendActivityResultProdutoRequestTask().execute();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	//Object Interactions
	public void openSelectionObjectPedido(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, PedidoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.SELECTION);
    	startActivityForResult(intent, 1);
	}

	public void openSelectionObjectProduto(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ProdutoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.SELECTION);
    	startActivityForResult(intent, 2);
	}

	//CRUD Methods

    public void save() {
		obj.setQuantidade(Integer.parseInt(editTextQuantidade.getText().toString()));
		obj.setValor(Double.parseDouble(editTextValor.getText().toString()));

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
			textViewObjectPedido.setText(DescriptionUtil.extractDescription(obj.getPedido(), DescriptionType.PRIMARY));
			textViewObjectProduto.setText(DescriptionUtil.extractDescription(obj.getProduto(), DescriptionType.PRIMARY));
			editTextQuantidade.setText(Integer.toString(obj.getQuantidade()));
			editTextValor.setText(Double.toString(obj.getValor()));
		}
	}


	//AsyncTasks for Activity Result

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