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
import org.jb.model.Catalogo;
import org.jb.model.Produto;

public class CatalogoDetailActivity extends AppCompatActivity {

	Catalogo obj;
	EditText editTextId = null;
	EditText editTextNome = null;
	EditText editTextDescricao = null;
	TextView textViewCollectionProdutos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_catalogo_detail);

		editTextId = (EditText) findViewById(R.id.catalogoDetailActivityJBAttributeEditTextId);
		editTextNome = (EditText) findViewById(R.id.catalogoDetailActivityJBAttributeEditTextNome);
		editTextDescricao = (EditText) findViewById(R.id.catalogoDetailActivityJBAttributeEditTextDescricao);
		textViewCollectionProdutos = (TextView) findViewById(R.id.catalogoDetailActivityJBAttributeTextViewCollectionProdutos);

		editTextId.setEnabled(false);
		editTextNome.setEnabled(false);
		editTextDescricao.setEnabled(false);
		textViewCollectionProdutos.setEnabled(false);

    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_detail_catalogo_activity, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();

    	if (id == R.id.action_class_catalogo_method_adicionarproduto) {
    		objectActionAdicionarProduto();
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

	public void selectDate(View view) {
		Dialog dialog = TimestampDialogBuilder.build(this, view);
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == 1) {
			int indexProduto = data.getIntExtra("indexProduto", 0);
			if(indexProduto > 0) {
				getIntent().putExtra("indexProduto", indexProduto);
				new SendActivityResultProdutoRequestTask().execute();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	//Object Interactions
	public void openSelectionObjectProduto(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ProdutoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.SELECTION);
    	startActivityForResult(intent, 1);
	}

	public void openDetailObjectProdutos(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, CatalogoJoinTableProdutoListActivity.class);
		intent.putExtra("ORIGIN_INDEX", obj.getId());
		startActivity(intent);
	}

	//Method Calls
    public void objectActionAdicionarProduto() {
		LayoutInflater layoutInflater = LayoutInflater.from(CatalogoDetailActivity.this);
		View dialogView = layoutInflater.inflate(R.layout.dialog_class_catalogo_method_adicionarproduto, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(CatalogoDetailActivity.this);
		alert.setTitle(R.string.action_label_class_catalogo_method_adicionarproduto);
		alert.setView(dialogView);

		final TextView textViewObjectProduto = 
	        (TextView) 	dialogView.findViewById(R.id.catalogoDetailActivityMethodAdicionarProdutoTextViewObjectProduto);
		alert.setPositiveButton(R.string.dialog_action_ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
	  			// Do something with value!
    			new SendActionAdicionarProdutoRequestTask().execute();
    			load();
	  		}
		});

		alert.setNegativeButton(R.string.dialog_action_cancel, new DialogInterface.OnClickListener() {
	  		public void onClick(DialogInterface dialog, int whichButton) {
	    		// Canceled.
	  		}
		});

		alert.show();
    }


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
				obj = (Catalogo) dao.findById(Catalogo.class, index);
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
			editTextNome.setText(new String(obj.getNome()));
			editTextDescricao.setText(new String(obj.getDescricao()));
		}
	}

	private class SendActionAdicionarProdutoRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int indexCatalogo = getIntent().getIntExtra("indexCatalogo", 0);

			//TODO

			return "The Body of the Method (adicionarProduto) is Empty!";
		}

		protected void onProgressUpdate(Integer... progress) {
			//TODO
		}

		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
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