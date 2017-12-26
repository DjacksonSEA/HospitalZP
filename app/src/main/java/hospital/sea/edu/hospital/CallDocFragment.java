package hospital.sea.edu.hospital;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CallDocFragment extends Fragment {

    private EditText etName;
    private EditText etBorn;
    private EditText etPhone;
    private EditText etSpecDoc;
    private Button btnDocGo;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<DocGo> docList = new ArrayList<>();


    public CallDocFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_call_doc,null);

        etName = (EditText)v.findViewById(R.id.etName);
        etBorn = (EditText)v.findViewById(R.id.etBorn);
        etPhone = (EditText)v.findViewById(R.id.etPhone);
        etSpecDoc = (EditText)v.findViewById(R.id.etSpecDoc);
        btnDocGo = (Button)v.findViewById(R.id.btnDocGo);

        initFirebase();



        btnDocGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocGo user = new DocGo(UUID.randomUUID().toString(),etName.getText().toString(),etBorn.getText().toString(),etPhone.getText().toString(),etSpecDoc.getText().toString());
                databaseReference.child("DocGo").child(user.getId()).setValue(user);
                etName.setText(" ");
                etBorn.setText(" ");
                etPhone.setText(" ");
                etSpecDoc.setText(" ");

                Toast.makeText(getContext(), "Ваша заявка отправлена", Toast.LENGTH_SHORT).show();

            }
        });
        return v;
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(CallDocFragment.this.getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



    }


}