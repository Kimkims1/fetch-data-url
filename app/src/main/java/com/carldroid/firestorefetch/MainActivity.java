package com.carldroid.firestorefetch;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterTopCompanies adapterTopCompanies;

    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.topCompaniesRv);
        firestoreDB = FirebaseFirestore.getInstance();

        loadCompaniesList();

        firestoreListener = firestoreDB.collection("TopCompanies")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<ModelTopCompanies> companiesList = new ArrayList<>();

                        for (DocumentSnapshot doc : documentSnapshots) {
                            ModelTopCompanies companies = doc.toObject(ModelTopCompanies.class);
                            companies.setId(doc.getId());
                            companiesList.add(companies);
                        }

                        adapterTopCompanies = new AdapterTopCompanies(companiesList, getApplicationContext(), firestoreDB);
                        recyclerView.setAdapter(adapterTopCompanies);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        firestoreListener.remove();
    }

    private void loadCompaniesList() {

        firestoreDB.collection("TopCompanies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ModelTopCompanies> companiesList = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                ModelTopCompanies companies = doc.toObject(ModelTopCompanies.class);
                                companies.setId(doc.getId());
                                companiesList.add(companies);
                            }

                            adapterTopCompanies = new AdapterTopCompanies(companiesList, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapterTopCompanies);
                        } else {
                            Toast.makeText(MainActivity.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

