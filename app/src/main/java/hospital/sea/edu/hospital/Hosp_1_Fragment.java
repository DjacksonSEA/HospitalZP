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


public class Hosp_1_Fragment extends Fragment {
    private TextView tvHosp1tel;
    private ImageButton ibCallHosp1;
    private ImageButton ibPlusHosp1;
    private Button btnDoctorHosp1;
    private EditText etUserHosp1;
    private EditText etCommentHosp1;
    private ListView lvHosp1;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private UserMSListAdapter adapter;
    private List<UserMS> userListHosp1 = new ArrayList<>();
    private CallDocFragment callDocFragment;
    private FragmentTransaction ft;



    public Hosp_1_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_hosp_1_,null);

        tvHosp1tel = (TextView)v.findViewById(R.id.tvHosp7tel);
        ibCallHosp1 = (ImageButton)v.findViewById(R.id.ibCallHosp7);
        ibPlusHosp1 = (ImageButton)v.findViewById(R.id.ibPlusHosp1);
        btnDoctorHosp1 = (Button)v.findViewById(R.id.btnDoctorHosp7);
        etUserHosp1 = (EditText)v.findViewById(R.id.etUserHosp7);
        etCommentHosp1 = (EditText)v.findViewById(R.id.etCommentHosp7);
        lvHosp1 = (ListView)v.findViewById(R.id.lvHosp1);

        initFirebase();
        addEventFirebaseListner();

        ibCallHosp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDial = tvHosp1tel.getText().toString();
                Uri number = Uri.parse("tel:"+toDial);
                Intent callIntent = new Intent (Intent.ACTION_DIAL,number);
                startActivity(callIntent);
            }
        });

        btnDoctorHosp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDocFragment = new CallDocFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main,callDocFragment);
                ft.addToBackStack(" ").commit();
            }
        });

        ibPlusHosp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserMS user = new UserMS(UUID.randomUUID().toString(),etUserHosp1.getText().toString(),etCommentHosp1.getText().toString());
                databaseReference.child("hosp1").child(user.getId()).setValue(user);
                etUserHosp1.setText(" ");
                etCommentHosp1.setText(" ");
            }
        });

        return v;
    }

    private void addEventFirebaseListner() {
        databaseReference.child("hosp1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(userListHosp1.size()>0)
                    userListHosp1.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    UserMS user = postSnapshot.getValue(UserMS.class);
                    userListHosp1.add(user);
                }
                adapter = new UserMSListAdapter(Hosp_1_Fragment.this.getActivity(),userListHosp1);
                lvHosp1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(Hosp_1_Fragment.this.getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



    }


}
