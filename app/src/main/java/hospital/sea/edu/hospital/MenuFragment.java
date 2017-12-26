package hospital.sea.edu.hospital;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MenuFragment extends Fragment {

    private Button btnMenu;
    private ListFragment listFragment;
    private FragmentTransaction ft;


    public MenuFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_menu,null);



        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listFragment = new ListFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main,listFragment);
                ft.addToBackStack(" ").commit();

            }
        });

        return v;
    }




}
