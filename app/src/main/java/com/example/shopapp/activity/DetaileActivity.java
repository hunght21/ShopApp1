package com.example.shopapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopapp.R;
import com.example.shopapp.models.NewProductsModel;
import com.example.shopapp.models.PopularProductsModel;
import com.example.shopapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetaileActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView rating,name,description, quantity, intPrice;
    Button addToCar, buyNow;
    ImageView addItems, removeItem;

    Toolbar toolbar;

    int totalQuantity = 1;
    int totalPrice = 0;

    NewProductsModel newProductsModel =null;

    //PopularProducts
    PopularProductsModel popularProductsModel = null;

    //Show All
    ShowAllModel showAllModel = null;


    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);

        toolbar =findViewById(R.id.detailer_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        firestore =FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if(obj instanceof NewProductsModel){
            newProductsModel = (NewProductsModel) obj;
        }else  if(obj instanceof PopularProductsModel){
            popularProductsModel = (PopularProductsModel) obj;
        }else  if(obj instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) obj;
        }
        Anhxa();

        //New Products
        if(newProductsModel != null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
//            price.setText(newProductsModel.getPrice());
            name.setText(newProductsModel.getName());
            intPrice.setText(String.valueOf(newProductsModel.getIntPrice()));

            totalPrice = newProductsModel.getIntPrice() * totalQuantity;
        }
        //PopularProducst
        if(popularProductsModel != null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            rating.setText(popularProductsModel.getRating());
            description.setText(popularProductsModel.getDescription());
 //           price.setText(popularProductsModel.getPrice());
            name.setText(popularProductsModel.getName());
            intPrice.setText(String.valueOf(popularProductsModel.getIntPrice()));

            totalPrice = popularProductsModel.getIntPrice() * totalQuantity;
        }

        if(showAllModel != null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
 //           price.setText(showAllModel.getPrice());
            name.setText(showAllModel.getName());
            intPrice.setText(String.valueOf(showAllModel.getIntPrice()));

            totalPrice = showAllModel.getIntPrice() * totalQuantity;
        }

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetaileActivity.this, AddressActivity.class);

                if(newProductsModel != null){
                    intent.putExtra("item", newProductsModel);

                }
                if (popularProductsModel != null){
                    intent.putExtra("item", popularProductsModel);
                }
                if (showAllModel != null){
                    intent.putExtra("item", showAllModel);
                }
                startActivity(intent);
            }
        });

        addToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCar();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity <10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                }
                if (newProductsModel != null){
                    totalPrice = newProductsModel.getIntPrice() * totalQuantity;
                }
                if(popularProductsModel !=null){
                    totalPrice = popularProductsModel.getIntPrice() * totalQuantity;
                }
                if(showAllModel != null){
                    totalPrice = showAllModel.getIntPrice() * totalQuantity;
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity >1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
    }

    private void addToCar() {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDay = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM,dd, yyyy");
        saveCurrentDate = currentDate.format(calForDay.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDay.getTime());

        final HashMap<String, Object> cartMap =  new HashMap<>();

        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice", intPrice.getText().toString());
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("AddToCar").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetaileActivity.this, "Added To A Cart" ,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void Anhxa(){
        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed);
        rating = findViewById(R.id.rating);
        name = findViewById(R.id.detailed_name);
        description = findViewById(R.id.detailed_desc);
        intPrice = findViewById(R.id.detailed_price);
        addToCar = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now1);
        addItems = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);


    }
}