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


public class Hosp_7_Fragment extends Fragment {
    private TextView tvHosp7tel;
    private ImageButton ibCallHosp7;
    private ImageButton ibPlusHosp7;
    private Button btnDoctorHosp7;
    private EditText etUserHosp7;
    private EditText etCommentHosp7;
    private ListView lvHosp7;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private UserMSListAdapter adapter;
    private List<UserMS> userListHosp7 = new ArrayList<>();
    private CallDocFragment callDocFragment;
    private FragmentTransaction ft;



    public Hosp_7_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_hosp_7_,null);

        tvHosp7tel = (TextView)v.findViewById(R.id.tvHosp7tel);
        ibCallHosp7 = (ImageButton)v.findViewById(R.id.ibCallHosp7);
        ibPlusHosp7 = (ImageButton)v.findViewById(R.id.ibPlusHosp7);
        btnDoctorHosp7 = (Button)v.findViewById(R.id.btnDoctorHosp7);
        etUserHosp7 = (EditText)v.findViewById(R.id.etUserHosp7);
        etCommentHosp7 = (EditText)v.findViewById(R.id.etCommentHosp7);
        lvHosp7 = (ListView)v.findViewById(R.id.lvHosp7);

        initFirebase();
        addEventFirebaseListner();

        ibCallHosp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDial = tvHosp7tel.getText().toString();
                Uri number = Uri.parse("tel:"+toDial);
                Intent callIntent = new Intent (Intent.ACTION_DIAL,number);
                startActivity(callIntent);
            }
        });

        btnDoctorHosp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDocFragment = new CallDocFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main,callDocFragment);
                ft.addToBackStack(" ").commit();
            }
        });

        ibPlusHosp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserMS user = new UserMS(UUID.randomUUID().toString(),etUserHosp7.getText().toString(),etCommentHosp7.getText().toString());
                databaseReference.child("hosp7").child(user.getId()).setValue(user);
                etUserHosp7.setText(" ");
                etCommentHosp7.setText(" ");
            }
        });

        return v;
    }

    private void addEventFirebaseListner() {
        databaseReference.child("hosp7").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(userListHosp7.size()>0)
                    userListHosp7.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    UserMS user = postSnapshot.getValue(UserMS.class);
                    userListHosp7.add(user);
                }
                adapter = new UserMSListAdapter(Hosp_7_Fragment.this.getActivity(),userListHosp7);
                lvHosp7.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(Hosp_7_Fragment.this.getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



    }


}
