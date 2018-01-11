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
import app.jb.generated.ProdutoListFragment;
import org.jb.model.Produto;
import org.jb.model.Catalogo;

public class ProdutoJoinTableCatalogoListActivity extends AppCompatActivity implements CatalogoListFragmentDelegate {

    CatalogoListFragment listCatalogo = null;
	View dialogWidget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

    	FragmentManager fm = getFragmentManager();
		setContentView(R.layout.activity_produto_jointable_catalogo_list);
    	if (fm.findFragmentById(R.id.produtoJoinTableCatalogoListActivityFragmentList) == null) { 
    		listCatalogo = new CatalogoListFragment(); 
    		listCatalogo.onAttach(this); 
    		fm.beginTransaction().add(R.id.produtoJoinTableCatalogoListActivityFragmentList, listCatalogo).commit(); 
    	}
    	else { 
    		listCatalogo = (CatalogoListFragment) fm.findFragmentById(R.id.produtoJoinTableCatalogoListActivityFragmentList);
    		listCatalogo.onAttach(this); 
    		fm.beginTransaction().commit(); 
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.menu_list_produto_jointable_catalogo_activity, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();

    	if (id == R.id.context_menu_list_produto_jointable_catalogo_activity_action_add) {
    		objectDefaultActionAddCatalogo();
    		return true;
    	}

    	return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	// Inflate the context menu.
    	super.onCreateContextMenu(menu, v, menuInfo);
    	getMenuInflater().inflate(R.menu.context_menu_list_produto_jointable_catalogo_activity, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// Handle action context menu item clicks here.l
    	int id = item.getItemId();
    	int index = getIntent().getIntExtra("index", 0);

    	if (id == R.id.context_menu_list_produto_jointable_catalogo_activity_action_detail) {
    		openDetailSelectedItem(index);
    		return true;
    	}
    	if (id == R.id.context_menu_list_produto_jointable_catalogo_activity_action_delete) {
    		openDeleteSelectedItem(index);
    		return true;
    	}

    	return super.onContextItemSelected(item);
    }

    public void openDetailSelectedItem(int index) {
		Intent intent = new Intent(this, CatalogoDetailActivity.class);
		intent.putExtra("index", index);
		startActivity(intent);
    }

    public void openDeleteSelectedItem(int index) {
		int originIndex = getIntent().getIntExtra("ORIGIN_INDEX", 0);
		getIntent().putExtra("TARGET_INDEX", index);

    	AlertDialog.Builder alert = new AlertDialog.Builder(ProdutoJoinTableCatalogoListActivity.this);
    	alert.setTitle(R.string.dialog_activity_produto_jointable_catalogo_list_remove_title);

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

    public void objectDefaultActionAddCatalogo() {
    	LayoutInflater layoutInflater = LayoutInflater.from(ProdutoJoinTableCatalogoListActivity.this);
    	View dialogView = layoutInflater.inflate(R.layout.dialog_activity_produto_jointable_catalogo_list_add, null);

    	AlertDialog.Builder alert = new AlertDialog.Builder(ProdutoJoinTableCatalogoListActivity.this);
    	alert.setTitle(R.string.dialog_activity_produto_jointable_catalogo_list_add_title);
    	alert.setView(dialogView);

    	final TextView textViewObjectCatalogo =
    			(TextView) dialogView.findViewById(R.id.produtoJoinTableCatalogoInsertDialogTextViewCatalogo);
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

    public void openSelectionObjectCatalogo(View view) {
		dialogWidget = view;
		Intent intent = new Intent(this, CatalogoListActivity.class);
		intent.putExtra("TYPE_LIST_VIEW", TypeListView.SELECTION);
    	startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == 0) {
			int indexCatalogo = data.getIntExtra("indexCatalogo", 0);
			if(indexCatalogo > 0) {
				getIntent().putExtra("TARGET_INDEX", indexCatalogo);
				new SendActivityResultCatalogoRequestTask().execute();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public List<Catalogo> getListCatalogo() {
		Integer originIndex = getIntent().getIntExtra("ORIGIN_INDEX", 0);
		
		WebDAO dao = new WebDAO();
		List<Catalogo> catalogos = new ArrayList<Catalogo>();
		try {
			String[] params = {"produto"};
			Object[] values = {originIndex};
			catalogos = (List<Catalogo>) dao.listCollection(Produto.class, "catalogos", params, values);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return catalogos;
	}

	//AsyncTasks for Remote Persistence

	private class SendAddRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int idProduto = getIntent().getIntExtra("ORIGIN_INDEX", 0);
			int idCatalogo = getIntent().getIntExtra("TARGET_INDEX", 0);

			String[] params = {"produto", "catalogo"};
			Object[] values = {idProduto, idCatalogo};

			try {
				WebDAO dao = new WebDAO();
				return dao.addIntoCollection(Produto.class, "catalogos", params, values);
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
			if(listCatalogo != null)
				listCatalogo.load();
		}
	}

	private class SendRemoveRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int idProduto = getIntent().getIntExtra("ORIGIN_INDEX", 0);
			int idCatalogo = getIntent().getIntExtra("TARGET_INDEX", 0);

			String[] params = {"produto", "catalogo"};
			Object[] values = {idProduto, idCatalogo};

			try {
				WebDAO dao = new WebDAO();
				return dao.removeFromCollection(Produto.class, "catalogos", params, values);
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
			if(listCatalogo != null)
				listCatalogo.load();
		}
	}

	private class SendActivityResultCatalogoRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int indexCatalogo = getIntent().getIntExtra("TARGET_INDEX", 0);
			try {
				WebDAO dao = new WebDAO();
				Catalogo object = (Catalogo) dao.findById(Catalogo.class, indexCatalogo);
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