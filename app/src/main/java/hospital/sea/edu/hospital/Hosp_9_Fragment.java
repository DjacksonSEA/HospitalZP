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


public class Hosp_9_Fragment extends Fragment {
    private TextView tvHosp9tel;
    private ImageButton ibCallHosp9;
    private ImageButton ibPlusHosp9;
    private Button btnDoctorHosp9;
    private EditText etUserHosp9;
    private EditText etCommentHosp9;
    private ListView lvHosp9;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private UserMSListAdapter adapter;
    private List<UserMS> userListHosp9 = new ArrayList<>();
    private CallDocFragment callDocFragment;
    private FragmentTransaction ft;



    public Hosp_9_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_hosp_9_,null);

        tvHosp9tel = (TextView)v.findViewById(R.id.tvHosp7tel);
        ibCallHosp9 = (ImageButton)v.findViewById(R.id.ibCallHosp7);
        ibPlusHosp9 = (ImageButton)v.findViewById(R.id.ibPlusHosp9);
        btnDoctorHosp9 = (Button)v.findViewById(R.id.btnDoctorHosp7);
        etUserHosp9 = (EditText)v.findViewById(R.id.etUserHosp7);
        etCommentHosp9 = (EditText)v.findViewById(R.id.etCommentHosp7);
        lvHosp9 = (ListView)v.findViewById(R.id.lvHosp9);

        initFirebase();
        addEventFirebaseListner();

        ibCallHosp9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDial = tvHosp9tel.getText().toString();
                Uri number = Uri.parse("tel:"+toDial);
                Intent callIntent = new Intent (Intent.ACTION_DIAL,number);
                startActivity(callIntent);
            }
        });

        btnDoctorHosp9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDocFragment = new CallDocFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main,callDocFragment);
                ft.addToBackStack(" ").commit();
            }
        });

        ibPlusHosp9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserMS user = new UserMS(UUID.randomUUID().toString(),etUserHosp9.getText().toString(),etCommentHosp9.getText().toString());
                databaseReference.child("hosp9").child(user.getId()).setValue(user);
                etUserHosp9.setText(" ");
                etCommentHosp9.setText(" ");
            }
        });

        return v;
    }

    private void addEventFirebaseListner() {
        databaseReference.child("hosp9").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(userListHosp9.size()>0)
                    userListHosp9.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    UserMS user = postSnapshot.getValue(UserMS.class);
                    userListHosp9.add(user);
                }
                adapter = new UserMSListAdapter(Hosp_9_Fragment.this.getActivity(),userListHosp9);
                lvHosp9.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(Hosp_9_Fragment.this.getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



    }


}
