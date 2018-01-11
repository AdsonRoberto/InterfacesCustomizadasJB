package app.jb.generated;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.DialogInterface;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.*;
import android.widget.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jb.codegen.generator.auxiliar.TypeListView;
import org.jb.persistence.web.dao.WebDAO;

import java.util.List;
import java.util.ArrayList;

import app.jb.generated.*;
import app.jb.generated.ClienteListFragment;
import org.jb.model.Cliente;

public class ClienteListActivity extends AppCompatActivity implements ClienteListFragmentDelegate {

    ClienteListFragment list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	TypeListView type = (TypeListView) getIntent().getSerializableExtra("TYPE_LIST_VIEW");
    	int list_activity_resource = 0;
    	int list_activity_list_fragment_resource = 0;

		if(type == TypeListView.RESULT) {
    		list_activity_resource = R.layout.activity_cliente_find_result_list;
    		list_activity_list_fragment_resource = R.id.clienteFindResultListActivityFragmentList;
		}
		else if(type == TypeListView.SELECTION) {
    		list_activity_resource = R.layout.activity_cliente_select_list;
    		list_activity_list_fragment_resource = R.id.clienteSelectListActivityFragmentList;
		}
		else  {
    		list_activity_resource = R.layout.activity_cliente_list;
    		list_activity_list_fragment_resource = R.id.clienteListActivityFragmentList;
		}

    	setContentView(list_activity_resource);
    	FragmentManager fm = getFragmentManager();

    	if (fm.findFragmentById(list_activity_list_fragment_resource) == null) { 
    		list = new ClienteListFragment(); 
    		list.onAttach(this); 
    		fm.beginTransaction().add(list_activity_list_fragment_resource, list).commit(); 
    	}
    	else { 
    		list = (ClienteListFragment) fm.findFragmentById(list_activity_list_fragment_resource);
    		list.onAttach(this); 
    		fm.beginTransaction().commit(); 
    	}
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	// Inflate the context menu.
    	super.onCreateContextMenu(menu, v, menuInfo);

    	TypeListView type = (TypeListView) getIntent().getSerializableExtra("TYPE_LIST_VIEW");
		if(type == TypeListView.RESULT) {
    		getMenuInflater().inflate(R.menu.context_menu_find_result_list_cliente_activity, menu);
		}
		else if(type == TypeListView.SELECTION) {
    		getMenuInflater().inflate(R.menu.context_menu_select_list_cliente_activity, menu);
		}
		else {
    		getMenuInflater().inflate(R.menu.context_menu_list_cliente_activity, menu);
		}
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// Handle action context menu item clicks here.l
    	int id = item.getItemId();
    	int index = getIntent().getIntExtra("index", 0);

    	if (id == R.id.context_menu_list_cliente_activity_action_detail) {
    		openDetailSelectedItem(index);
    		return true;
    	}
    	if (id == R.id.context_menu_list_cliente_activity_action_edit) {
    		openEditSelectedItem(index);
    		return true;
    	}
    	if (id == R.id.context_menu_list_cliente_activity_action_delete) {
    		openDeleteSelectedItem(index);
    		return true;
    	}
    	if (id == R.id.context_menu_select_list_cliente_activity_action_select) {
    		openSelectionItem(index);
    		return true;
    	}
    	return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Inflate the menu; this adds items to the action bar if it is present.

    	TypeListView type = (TypeListView) getIntent().getSerializableExtra("TYPE_LIST_VIEW");
		if(type == TypeListView.RESULT) {
    		//Nothing);
		}
		else if(type == TypeListView.SELECTION) {
    		//Nothing);
		}
		else {
    		getMenuInflater().inflate(R.menu.menu_list_cliente_activity, menu);
		}
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	int index = getIntent().getIntExtra("index", 0);

    	if (id == R.id.menu_list_cliente_activity_action_new) {
    		Intent intent = new Intent(this, ClienteInsertActivity.class);
    		startActivity(intent);
    		return true;
    	}
    	if (id == R.id.menu_list_cliente_activity_action_find) {
    		Intent intent = new Intent(this, ClienteFindActivity.class);
    		startActivity(intent);
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

	//The detail view call is delegated to the concrete subclasses.
    public void openDetailSelectedItem(int index) {
    	Intent intent = new Intent(this, ClienteDetailActivity.class);
    	intent.putExtra("index", index);
    	startActivity(intent);
    }

	//The edit view call is delegated to the concrete subclasses.
    public void openEditSelectedItem(int index) {
    	Intent intent = new Intent(this, ClienteEditActivity.class);
    	intent.putExtra("index", index);
    	startActivity(intent);
    }

    public void openDeleteSelectedItem(int index) {
		getIntent().putExtra("index", index);

    	AlertDialog.Builder alert = new AlertDialog.Builder(ClienteListActivity.this);
    	alert.setTitle(R.string.activity_title_cliente_list);

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

    public void openSelectionItem(int index) {
    	Intent intent = new Intent();
    	intent.putExtra("indexCliente", index);
    	setResult(2, intent);
    	this.onBackPressed();
    }

	@Override
	public List<Cliente> getListCliente() {
		TypeListView type = (TypeListView) getIntent().getSerializableExtra("TYPE_LIST_VIEW");

		WebDAO dao = new WebDAO();
		List<Cliente> listCliente = new ArrayList<Cliente>();
		try {
			if(type == TypeListView.RESULT) {
				listCliente = new ArrayList<Cliente>();
			}
			else {
				listCliente = (List<Cliente>) dao.findAll(Cliente.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listCliente;
	}

	private class SendRemoveRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
			int indexCliente = getIntent().getIntExtra("indexCliente", 0);
			try {
				WebDAO dao = new WebDAO();
				return dao.delete(Cliente.class, indexCliente);
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

}