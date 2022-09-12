package com.example.shopapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shopapp.R;
import com.example.shopapp.adapter.ShowAllAdapter;
import com.example.shopapp.models.CategoryMolder;
import com.example.shopapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;

    Toolbar toolbar;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        toolbar =findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String type = getIntent().getStringExtra("type");

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.show_all_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this, showAllModelList);
        recyclerView.setAdapter(showAllAdapter);

//        firestore.collection("ShowAll")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot doc :task.getResult().getDocuments()) {
//                                ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
//                                showAllModelList.add(showAllModel);
//                                showAllAdapter.notifyDataSetChanged();
//                            }
//                        } else {
//                        //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });

        if (type == null || type.isEmpty()){
            firestore.collection("ShowAll")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }

        if(type != null && type.equalsIgnoreCase("hp")){
            firestore.collection("ShowAll").whereEqualTo("type","hp")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("asus")){
            firestore.collection("ShowAll").whereEqualTo("type","asus")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("apple")){
            firestore.collection("ShowAll").whereEqualTo("type","apple")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("razer")){
            firestore.collection("ShowAll").whereEqualTo("type","razer")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("dell")){
            firestore.collection("ShowAll").whereEqualTo("type","dell")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
        if(type != null && type.equalsIgnoreCase("gigabyte")){
            firestore.collection("ShowAll").whereEqualTo("type","gigabyte")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc :task.getResult().getDocuments()) {
                                    ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                //    Toast.makeText(getActivity(),""+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}