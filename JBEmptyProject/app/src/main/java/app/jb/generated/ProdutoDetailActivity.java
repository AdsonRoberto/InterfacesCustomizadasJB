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
import org.jb.model.Produto;
import org.jb.model.Catalogo;

public class ProdutoDetailActivity extends AppCompatActivity {

	Produto obj;
	EditText editTextId = null;
	EditText editTextDescricao = null;
	EditText editTextValor = null;
	TextView textViewCollectionCatalogos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_produto_detail);

		editTextId = (EditText) findViewById(R.id.produtoDetailActivityJBAttributeEditTextId);
		editTextDescricao = (EditText) findViewById(R.id.produtoDetailActivityJBAttributeEditTextDescricao);
		editTextValor = (EditText) findViewById(R.id.produtoDetailActivityJBAttributeEditTextValor);
		textViewCollectionCatalogos = (TextView) findViewById(R.id.produtoDetailActivityJBAttributeTextViewCollectionCatalogos);

		editTextId.setEnabled(false);
		editTextDescricao.setEnabled(false);
		editTextValor.setEnabled(false);
		textViewCollectionCatalogos.setEnabled(false);

    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_detail_produto_activity, menu);
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
	public void openDetailObjectCatalogos(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ProdutoJoinTableCatalogoListActivity.class);
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
				obj = (Produto) dao.findById(Produto.class, index);
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
			editTextDescricao.setText(new String(obj.getDescricao()));
			editTextValor.setText(Double.toString(obj.getValor()));
		}
	}

}