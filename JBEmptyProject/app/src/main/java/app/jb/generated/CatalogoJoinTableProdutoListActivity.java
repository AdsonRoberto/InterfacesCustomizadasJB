package app.jb.generated;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.content.DialogInterface;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jb.ui.annotation.visual.enums.DescriptionType;
import org.jb.ui.annotation.visual.util.DescriptionUtil;

import org.jb.codegen.generator.auxiliar.TypeListView;
import org.jb.persistence.web.dao.WebDAO;

import java.util.List;
import java.util.ArrayList;

import app.jb.generated.*;
import app.jb.generated.CatalogoListFragment;
import org.jb.model.Catalogo;
import org.jb.model.Produto;

public class CatalogoJoinTableProdutoListActivity extends AppCompatActivity implements ProdutoListFragmentDelegate {

    ProdutoListFragment listProduto = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    	FragmentManager fm = getFragmentManager();
		setContentView(R.layout.activity_catalogo_jointable_produto_list);
    	if (fm.findFragmentById(R.id.catalogoJoinTableProdutoListActivityFragmentList) == null) { 
    		listProduto = new ProdutoListFragment(); 
    		listProduto.onAttach(this); 
    		fm.beginTransaction().add(R.id.catalogoJoinTableProdutoListActivityFragmentList, listProduto).commit(); 
    	}
    	else { 
    		listProduto = (ProdutoListFragment) fm.findFragmentById(R.id.catalogoJoinTableProdutoListActivityFragmentList);
    		listProduto.onAttach(this); 
    		fm.beginTransaction().commit(); 
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_list_catalogo_jointable_produto_activity, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();

    	if (id == R.id.context_menu_list_catalogo_jointable_produto_activity_action_add) {
    		objectDefaultActionAddProduto();
    		return true;
    	}

    	return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	// Inflate the context menu.
    	super.onCreateContextMenu(menu, v, menuInfo);
    	getMenuInflater().inflate(R.menu.context_menu_list_catalogo_jointable_produto_activity, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// Handle action context menu item clicks here.l
    	int id = item.getItemId();
    	int index = getIntent().getIntExtra("index", 0);

    	if (id == R.id.context_menu_list_catalogo_jointable_produto_activity_action_detail) {
    		openDetailSelectedItem(index);
    		return true;
    	}
    	if (id == R.id.context_menu_list_catalogo_jointable_produto_activity_action_delete) {
    		openDeleteSelectedItem(index);
    		return true;
    	}

    	return super.onContextItemSelected(item);
    }

    public void openDetailSelectedItem(int index) {
		Intent intent = new Intent(this, ProdutoDetailActivity.class);
		intent.putExtra("index", index);
		startActivity(intent);
    }

    public void openDeleteSelectedItem(int index) {
		int originIndex = getIntent().getIntExtra("ORIGIN_INDEX", 0);
		getIntent().putExtra("TARGET_INDEX", index);

    	AlertDialog.Builder alert = new AlertDialog.Builder(CatalogoJoinTableProdutoListActivity.this);
    	alert.setTitle(R.string.dialog_activity_catalogo_jointable_produto_list_remove_title);

    	alert.setPositiveButton(R.string.dialog_action_yes, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			// Do something with value!
    			new SendRemoveRequestTask().execute();
    		}
    	});

    	alert.setNegativeButton(R.string.dialog_action_no, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			// Canceled.
    		}
    	});
    	alert.show();
    }

    public void objectDefaultActionAddProduto() {
    	LayoutInflater layoutInflater = LayoutInflater.from(CatalogoJoinTableProdutoListActivity.this);
    	View dialogView = layoutInflater.inflate(R.layout.dialog_activity_catalogo_jointable_produto_list_add, null);

    	AlertDialog.Builder alert = new AlertDialog.Builder(CatalogoJoinTableProdutoListActivity.this);
    	alert.setTitle(R.string.dialog_activity_catalogo_jointable_produto_list_add_title);
    	alert.setView(dialogView);

    	final TextView textViewObjectProduto =
    			(TextView) dialogView.findViewById(R.id.catalogoJoinTableProdutoInsertDialogTextViewProduto);
    	alert.setPositiveButton(R.string.dialog_action_ok, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			// Do something with value!
    			new SendAddRequestTask().execute();
    		}
    	});

    	alert.setNegativeButton(R.string.dialog_action_cancel, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    			// Canceled.
    		}
    	});
    	alert.show();
    }

    public void openSelectionObjectProduto(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, ProdutoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.SELECTION);
    	startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == 0) {
			int indexProduto = data.getIntExtra("indexProduto", 0);
			if(indexProduto > 0) {
				getIntent().putExtra("TARGET_INDEX", indexProduto);
				new SendActivityResultProdutoRequestTask().execute();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public List<Produto> getListProduto() {
		Integer originIndex = getIntent().getIntExtra("ORIGIN_INDEX", 0);
		
		WebDAO dao = new WebDAO();
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			String[] params = {"catalogo"};
			Object[] values = {originIndex};
			produtos = (List<Produto>) dao.listCollection(Catalogo.class, "produtos", params, values);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	//AsyncTasks for Remote Persistence

	private class SendAddRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int idCatalogo = getIntent().getIntExtra("ORIGIN_INDEX", 0);
			int idProduto = getIntent().getIntExtra("TARGET_INDEX", 0);

			String[] params = {"catalogo", "produto"};
			Object[] values = {idCatalogo, idProduto};

			try {
				WebDAO dao = new WebDAO();
				return dao.addIntoCollection(Catalogo.class, "produtos", params, values);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}

			return "Não foi possível executar a operação!";
		}

		protected void onProgressUpdate(Integer... progress) {
			//TODO
		}

		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
			if(listProduto != null)
				listProduto.load();
		}
	}

	private class SendRemoveRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int idCatalogo = getIntent().getIntExtra("ORIGIN_INDEX", 0);
			int idProduto = getIntent().getIntExtra("TARGET_INDEX", 0);

			String[] params = {"catalogo", "produto"};
			Object[] values = {idCatalogo, idProduto};

			try {
				WebDAO dao = new WebDAO();
				return dao.removeFromCollection(Catalogo.class, "produtos", params, values);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}

			return "Não foi possível executar a operação!";
		}

		protected void onProgressUpdate(Integer... progress) {
			//TODO
		}

		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
			if(listProduto != null)
				listProduto.load();
		}
	}

	private class SendActivityResultProdutoRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int indexProduto = getIntent().getIntExtra("TARGET_INDEX", 0);
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