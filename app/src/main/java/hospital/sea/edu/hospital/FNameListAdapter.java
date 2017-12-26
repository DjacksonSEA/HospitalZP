package hospital.sea.edu.hospital;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 02.11.2017.
 */

public class FNameListAdapter extends BaseAdapter {

    private Context context;
    private List<ItemFNameList> mList;

    public FNameListAdapter(Context context, List<ItemFNameList> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         View v = View.inflate(context,R.layout.item_fragment_list,null);
         TextView tvNameItem = (TextView)v.findViewById(R.id.tvNameItem);
         TextView tvNameStreet = (TextView)v.findViewById(R.id.tvNameStreet);
         tvNameItem.setText(mList.get(i).getName());
         tvNameStreet.setText(mList.get(i).getStreet());
         v.setTag(mList.get(i).getId());

        return v;

    }
}
