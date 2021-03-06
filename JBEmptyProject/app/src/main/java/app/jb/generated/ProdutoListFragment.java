package app.jb.generated;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import org.jb.ui.annotation.visual.enums.DescriptionType;
import org.jb.ui.annotation.visual.util.DescriptionUtil;

import org.jb.model.Produto;

import app.jb.generated.*;

public class ProdutoListFragment extends ListFragment {
	ProdutoListFragmentDelegate delegate;

    List<Produto> listProduto = null;
    ArrayAdapter<String> adapter = null;
    String[] textValues = null;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	registerForContextMenu(this.getListView());
    }

    @Override
    public void onResume() {
    	super.onResume();
    	load();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Produto obj = listProduto.get(position);
    	getActivity().getIntent().putExtra("index", obj.getId());
    	getActivity().openContextMenu(l);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	if(container != null) {
    		ListView listView = (ListView) container.findViewById(R.id.ProdutoListFragmentListView);
    		return listView;
    	}
    	return super.onCreateView(inflater, container, savedInstanceState);
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			delegate = (ProdutoListFragmentDelegate) activity;
    	} catch (ClassCastException castException) {
        	/** The activity does not implement the listener. */
    	}
	}

    public void load() {
		new ProdutoListFragment.SendLoadRequestTask().execute();
    }

	private class SendLoadRequestTask extends AsyncTask<String, Integer, String> {
		protected String doInBackground(String... urls) {
    		if(delegate != null) {
    			listProduto = delegate.getListProduto();
    		}
    		else {
    			listProduto = new ArrayList<Produto>();
    		}
			return "";
		}

		protected void onProgressUpdate(Integer... progress) {
			//TODO
		}

		protected void onPostExecute(String result) {
    		textValues = new String[listProduto.size()];
			for(int i = 0; i < listProduto.size(); i++) {
				textValues[i] = DescriptionUtil.extractDescription(listProduto.get(i), DescriptionType.PRIMARY);
				textValues[i] += "___";
				textValues[i] += DescriptionUtil.extractDescription(listProduto.get(i), DescriptionType.SECONDARY);
			}

    		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, textValues) {
				@SuppressWarnings("deprecation")
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {

			    	TwoLineListItem twoLineListItem;

			    	if (convertView == null) {
			        	LayoutInflater inflater = (LayoutInflater) this.getContext()
			        	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			        	twoLineListItem = (TwoLineListItem) inflater.inflate(
			         	       android.R.layout.simple_list_item_2, null);
			    	} else {
			        	twoLineListItem = (TwoLineListItem) convertView;
			    	}

			    	TextView text1 = twoLineListItem.getText1();
			    	TextView text2 = twoLineListItem.getText2();

			    	String []values = textValues[position].split("___");
			    	text1.setText(values[0]);
			    	text2.setText(values[1]);

			    	return twoLineListItem;
				}
			};
    		setListAdapter(adapter);
		}
    }
}
