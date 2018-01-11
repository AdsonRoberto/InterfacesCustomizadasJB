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
import org.jb.model.Produto;
import org.jb.model.Catalogo;

public class ProdutoFindActivity extends AppCompatActivity {

	String descricao = null;
	String valor = null;
	String catalogos = null;

	CheckBox checkBoxFinddescricao = null;
	CheckBox checkBoxFindvalor = null;
	CheckBox checkBoxFindcatalogos = null;

	EditText editTextDescricao = null;
	EditText editTextValor = null;
	TextView textViewCollectionCatalogos = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_produto_find);

		checkBoxFinddescricao = 
			(CheckBox) findViewById(R.id.produtoFindActivityCheckboxFindEditTextDescricao);
		checkBoxFindvalor = 
			(CheckBox) findViewById(R.id.produtoFindActivityCheckboxFindEditTextValor);
		checkBoxFindcatalogos = 
			(CheckBox) findViewById(R.id.produtoFindActivityCheckboxFindTextViewCollectionCatalogos);

		editTextDescricao = (EditText) findViewById(R.id.produtoFindActivityJBAttributeEditTextDescricao);
		editTextValor = (EditText) findViewById(R.id.produtoFindActivityJBAttributeEditTextValor);
		textViewCollectionCatalogos = (TextView) findViewById(R.id.produtoFindActivityJBAttributeTextViewCollectionCatalogos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_find_produto_activity, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	if (id == R.id.menu_find_produto_activity_action_find) {
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
		descricao = editTextDescricao.getText().toString();
		valor = editTextValor.getText().toString();

    	Intent intent = new Intent(this, ProdutoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.RESULT);

    	if(checkBoxFinddescricao.isChecked()) {
    		intent.putExtra("descricao", descricao);
    	}
    	if(checkBoxFindvalor.isChecked()) {
    		intent.putExtra("valor", valor);
    	}

    	startActivity(intent);
	}

}