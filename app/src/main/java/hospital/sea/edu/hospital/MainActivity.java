package hospital.sea.edu.hospital;

import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    private MenuFragment menuFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuFragment = new MenuFragment();
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main,menuFragment);
        ft.commit();


    }



}
