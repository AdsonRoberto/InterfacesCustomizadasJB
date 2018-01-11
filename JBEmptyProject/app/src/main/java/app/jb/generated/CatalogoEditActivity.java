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
import org.jb.model.Catalogo;
import org.jb.model.Produto;

public class CatalogoEditActivity extends AppCompatActivity {

	Catalogo obj;
	EditText editTextId = null;
	EditText editTextNome = null;
	EditText editTextDescricao = null;
	TextView textViewCollectionProdutos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_catalogo_edit);

		editTextId = (EditText) findViewById(R.id.catalogoEditActivityJBAttributeEditTextId);
		editTextNome = (EditText) findViewById(R.id.catalogoEditActivityJBAttributeEditTextNome);
		editTextDescricao = (EditText) findViewById(R.id.catalogoEditActivityJBAttributeEditTextDescricao);
		textViewCollectionProdutos = (TextView) findViewById(R.id.catalogoEditActivityJBAttributeTextViewCollectionProdutos);

    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_edit_catalogo_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	if (id == R.id.menu_edit_catalogo_activity_action_save) {
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
		obj.setId(Integer.parseInt(editTextId.getText().toString()));
		obj.setNome(editTextNome.getText().toString());
		obj.setDescricao(editTextDescricao.getText().toString());

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

}