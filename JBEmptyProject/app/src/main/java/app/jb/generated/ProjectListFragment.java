package app.jb.generated;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.Context;
import app.jb.generated.*;

public class ProjectListFragment extends ListFragment {
	ProjectListFragmentDelegate delegate;

    String[] entityLabelArray = new String[] { "Clientes", "Produtos", "Pedidos", "Catalogos", "Itens"};
    String[] entityIconArray = new String[] { "", "", "", "", ""};
    Class<?>[] entityActivityArray = new Class<?>[] { ClienteListActivity.class, ProdutoListActivity.class, PedidoListActivity.class, CatalogoListActivity.class, ItemListActivity.class};

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	if(this.delegate != null)
    		this.delegate.openActivity(entityActivityArray[position]);
    	else
    		Toast.makeText(getActivity(), entityLabelArray[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	ArrayElement []elements = new ArrayElement[entityLabelArray.length];
    	for(int i = 0; i < elements.length; i++) {
    		int resourceId = inflater.getContext().getResources()
    			.getIdentifier(entityIconArray[i], "drawable", inflater.getContext().getPackageName());
    		if(resourceId == 0) 
    			resourceId = R.drawable.ic_launcher;
    		elements[i] = new ArrayElement(resourceId, entityLabelArray[i]);
    	}

    	StableArrayAdapter adapter = new StableArrayAdapter(inflater.getContext(), R.layout.fragment_project_list_row_layout, elements);
    	setListAdapter(adapter);
    	return super.onCreateView(inflater, container, savedInstanceState);
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			delegate = (ProjectListFragmentDelegate) activity;
    	} catch (ClassCastException castException) {
    	    /** The activity does not implement the listener. */
    	}
	}

	private class StableArrayAdapter extends ArrayAdapter<ArrayElement> {
		private final Context context;
		private final int resourceId;
		private final ArrayElement[] values;

		public StableArrayAdapter(Context context, int resourceId, ArrayElement[] values) {
			super(context, resourceId, values);
			this.context = context;
			this.resourceId = resourceId;
			this.values = values;
		}

		@Override
	  	public View getView(int position, View convertView, ViewGroup parent) {
	    	View rowView = convertView;
	    	if(rowView == null) {
	    		LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    		rowView = inflater.inflate(resourceId, parent, false);
	    	}

	    	TextView textView = (TextView) rowView.findViewById(R.id.ProjectListFragmentRowLayoutTextViewTitle);
	    	ImageView imageView = (ImageView) rowView.findViewById(R.id.ProjectListFragmentRowLayoutImageViewIcon);

			ArrayElement aElement = values[position];

	    	textView.setText(aElement.getTitle());
	    	imageView.setImageResource(aElement.getIconResource());

	    	return rowView;
		}

		@Override
		public long getItemId(int position) {
			ArrayElement item = getItem(position);
			return 0;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

	private class ArrayElement {
		int iconResource;
		String title;

		public ArrayElement(int iconResource, String title) {
			this.iconResource = iconResource;
			this.title = title;
		}

		public int getIconResource() {
			return iconResource;
		}

		public void setIconResource(int iconResource) {
			this.iconResource = iconResource;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}
}
