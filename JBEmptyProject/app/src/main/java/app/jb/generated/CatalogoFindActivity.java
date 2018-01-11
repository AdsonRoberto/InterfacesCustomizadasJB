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
import org.jb.model.Catalogo;
import org.jb.model.Produto;

public class CatalogoFindActivity extends AppCompatActivity {

	String id = null;
	String nome = null;
	String descricao = null;
	String produtos = null;

	CheckBox checkBoxFindid = null;
	CheckBox checkBoxFindnome = null;
	CheckBox checkBoxFinddescricao = null;
	CheckBox checkBoxFindprodutos = null;

	EditText editTextId = null;
	EditText editTextNome = null;
	EditText editTextDescricao = null;
	TextView textViewCollectionProdutos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_catalogo_find);

		checkBoxFindid = 
			(CheckBox) findViewById(R.id.catalogoFindActivityCheckboxFindEditTextId);
		checkBoxFindnome = 
			(CheckBox) findViewById(R.id.catalogoFindActivityCheckboxFindEditTextNome);
		checkBoxFinddescricao = 
			(CheckBox) findViewById(R.id.catalogoFindActivityCheckboxFindEditTextDescricao);
		checkBoxFindprodutos = 
			(CheckBox) findViewById(R.id.catalogoFindActivityCheckboxFindTextViewCollectionProdutos);

		editTextId = (EditText) findViewById(R.id.catalogoFindActivityJBAttributeEditTextId);
		editTextNome = (EditText) findViewById(R.id.catalogoFindActivityJBAttributeEditTextNome);
		editTextDescricao = (EditText) findViewById(R.id.catalogoFindActivityJBAttributeEditTextDescricao);
		textViewCollectionProdutos = (TextView) findViewById(R.id.catalogoFindActivityJBAttributeTextViewCollectionProdutos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_find_catalogo_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	if (id == R.id.menu_find_catalogo_activity_action_find) {
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
		id = editTextId.getText().toString();
		nome = editTextNome.getText().toString();
		descricao = editTextDescricao.getText().toString();

    	Intent intent = new Intent(this, CatalogoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.RESULT);

    	if(checkBoxFindid.isChecked()) {
    		intent.putExtra("id", id);
    	}
    	if(checkBoxFindnome.isChecked()) {
    		intent.putExtra("nome", nome);
    	}
    	if(checkBoxFinddescricao.isChecked()) {
    		intent.putExtra("descricao", descricao);
    	}

    	startActivity(intent);
	}

}