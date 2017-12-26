package hospital.sea.edu.hospital;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Hosp_3_Fragment extends Fragment {
    private TextView tvHosp3tel;
    private ImageButton ibCallHosp3;
    private ImageButton ibPlusHosp3;
    private Button btnDoctorHosp3;
    private EditText etUserHosp3;
    private EditText etCommentHosp3;
    private ListView lvHosp3;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private UserMSListAdapter adapter;
    private List<UserMS> userListHosp3= new ArrayList<>();
    private CallDocFragment callDocFragment;
    private FragmentTransaction ft;



    public Hosp_3_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_hosp_3_,null);

        tvHosp3tel = (TextView)v.findViewById(R.id.tvHosp7tel);
        ibCallHosp3 = (ImageButton)v.findViewById(R.id.ibCallHosp7);
        ibPlusHosp3 = (ImageButton)v.findViewById(R.id.ibPlusHosp3);
        btnDoctorHosp3 = (Button)v.findViewById(R.id.btnDoctorHosp7);
        etUserHosp3 = (EditText)v.findViewById(R.id.etUserHosp7);
        etCommentHosp3 = (EditText)v.findViewById(R.id.etCommentHosp7);
        lvHosp3 = (ListView)v.findViewById(R.id.lvHosp3);

        initFirebase();
        addEventFirebaseListner();

        ibCallHosp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDial = tvHosp3tel.getText().toString();
                Uri number = Uri.parse("tel:"+toDial);
                Intent callIntent = new Intent (Intent.ACTION_DIAL,number);
                startActivity(callIntent);
            }
        });

        btnDoctorHosp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDocFragment = new CallDocFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main,callDocFragment);
                ft.addToBackStack(" ").commit();
            }
        });

        ibPlusHosp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserMS user = new UserMS(UUID.randomUUID().toString(),etUserHosp3.getText().toString(),etCommentHosp3.getText().toString());
                databaseReference.child("hosp3").child(user.getId()).setValue(user);
                etUserHosp3.setText(" ");
                etCommentHosp3.setText(" ");
            }
        });

        return v;
    }

    private void addEventFirebaseListner() {
        databaseReference.child("hosp3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(userListHosp3.size()>0)
                    userListHosp3.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    UserMS user = postSnapshot.getValue(UserMS.class);
                    userListHosp3.add(user);
                }
                adapter = new UserMSListAdapter(Hosp_3_Fragment.this.getActivity(),userListHosp3);
                lvHosp3.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(Hosp_3_Fragment.this.getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



    }


}
