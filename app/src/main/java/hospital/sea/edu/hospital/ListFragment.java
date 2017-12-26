package hospital.sea.edu.hospital;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

    private ImageButton ivMap;
    private FragmentTransaction ft;
    private ListView lvFragList;
    private FNameListAdapter adapter;
    private List<ItemFNameList> mList;
    private MotorSichFragment motorSichFragment;
    private Hosp_9_Fragment hosp_9_fragment;
    private Hosp_3_Fragment hosp_3_fragment;
    private Hosp_7_Fragment hosp_7_fragment;
    private Hosp_1_Fragment hosp_1_fragment;


    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_list,null);

        lvFragList = (ListView)v.findViewById(R.id.lvFragList);
        ivMap = (ImageButton)v.findViewById(R.id.ivMap);
        mList = new ArrayList<>();
        mList.add(new ItemFNameList(1,"Поликлиника Мотор Сич","г. Запорожье, ул. Брюллова, 6"));
        mList.add(new ItemFNameList(2,"Центральная поликлиника №1","г. Запорожье, ул.Запорожского казачества, д. 25"));
        mList.add(new ItemFNameList(3,"Городская поликлиника №9","г. Запорожье, ул.Дудыкина 6"));
        mList.add(new ItemFNameList(4,"Городская поликлиника №3","г. Запорожье, проспект Металлургов, д. 9"));
        mList.add(new ItemFNameList(5,"Детская поликлиника №7","г. Запорожье, ул.Горького, д. 32А"));

        adapter = new FNameListAdapter(getContext(),mList);
        lvFragList.setAdapter(adapter);

        lvFragList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(view.getTag().equals(1)){
                    motorSichFragment = new MotorSichFragment();
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.activity_main,motorSichFragment);
                    ft.addToBackStack(" ").commit();
                }
                else if(view.getTag().equals(2)){
                    hosp_1_fragment = new Hosp_1_Fragment();
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.activity_main,hosp_1_fragment);
                    ft.addToBackStack(" ").commit();
                }
                else if(view.getTag().equals(3)){
                    hosp_9_fragment = new Hosp_9_Fragment();
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.activity_main,hosp_9_fragment);
                    ft.addToBackStack(" ").commit();
                }
                else if(view.getTag().equals(4)){
                    hosp_3_fragment = new Hosp_3_Fragment();
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.activity_main,hosp_3_fragment);
                    ft.addToBackStack(" ").commit();
                }
                else if(view.getTag().equals(5)){
                    hosp_7_fragment = new Hosp_7_Fragment();
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.activity_main,hosp_7_fragment);
                    ft.addToBackStack(" ").commit();
                }

            }
        });




        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent map = new Intent(ListFragment.this.getActivity(),MapActivity.class);
                map.setData(Uri.parse("geo:47.826277, 35.165883"));
                startActivity(map);

            }
        });

        return v;
    }

}
