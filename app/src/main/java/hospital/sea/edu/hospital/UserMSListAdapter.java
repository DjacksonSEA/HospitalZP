package hospital.sea.edu.hospital;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by User on 04.11.2017.
 */

public class UserMSListAdapter extends BaseAdapter{

    private Activity activity;
    private List<UserMS> msList;
    private LayoutInflater inflater;


    public UserMSListAdapter(Activity activity, List<UserMS> msList) {
        this.activity = activity;
        this.msList = msList;
    }

    @Override
    public int getCount() {
        return msList.size();
    }

    @Override
    public Object getItem(int i) {
        return msList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_motorsich_list,null);

        TextView tvNameMS = (TextView)itemView.findViewById(R.id.tvNameMS);
        TextView tvCommentMS = (TextView)itemView.findViewById(R.id.tvCommentMS);

        tvNameMS.setText(msList.get(i).getName());
        tvCommentMS.setText(msList.get(i).getComment());

        return itemView;
    }
}
