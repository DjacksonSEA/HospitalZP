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


public class MotorSichFragment extends Fragment {

    private ImageButton ibCall;
    private ImageButton ibPlusMS;
    private Button btnDoctor;
    private TextView tvMStel;
    private EditText etUserMS;
    private EditText etCommentMS;
    private ListView lvMS;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private UserMSListAdapter adapter;
    private List<UserMS> userListMS = new ArrayList<>();
    private CallDocFragment callDocFragment;
    private FragmentTransaction ft;

    public MotorSichFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_motor_sich,null);

        tvMStel = (TextView) v.findViewById(R.id.tvHosp7tel);
        ibCall = (ImageButton)v.findViewById(R.id.ibCallHosp7);
        ibPlusMS = (ImageButton)v.findViewById(R.id.ibPlusMS);
        btnDoctor = (Button)v.findViewById(R.id.btnDoctorHosp7);
        etUserMS = (EditText)v.findViewById(R.id.etUserHosp7) ;
        etCommentMS = (EditText)v.findViewById(R.id.etCommentHosp7);
        lvMS = (ListView)v.findViewById(R.id.lvMS) ;


        initFirebase();
        addEventFirebaseListner();

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDocFragment = new CallDocFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.activity_main,callDocFragment);
                ft.addToBackStack(" ").commit();
            }
        });

        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toDial = tvMStel.getText().toString();
                Uri number = Uri.parse("tel:"+toDial);
                Intent callIntent = new Intent (Intent.ACTION_DIAL,number);
                startActivity(callIntent);

            }
        });


        ibPlusMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserMS user = new UserMS(UUID.randomUUID().toString(),etUserMS.getText().toString(),etCommentMS.getText().toString());
                databaseReference.child("users").child(user.getId()).setValue(user);
                etUserMS.setText(" ");
                etCommentMS.setText(" ");
            }
        });



        return v;
    }

    private void addEventFirebaseListner() {
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(userListMS.size()>0)
                    userListMS.clear();
                    for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                        UserMS user = postSnapshot.getValue(UserMS.class);
                        userListMS.add(user);
                    }
                    adapter = new UserMSListAdapter(MotorSichFragment.this.getActivity(),userListMS);
                    lvMS.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(MotorSichFragment.this.getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



    }


}
