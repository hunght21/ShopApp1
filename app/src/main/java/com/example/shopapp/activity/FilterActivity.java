package com.example.shopapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.shopapp.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    private Chip chipAsus, chipHp, chiLenovo, chipApple, chipGigabyte;
    private Chip chipIntel, chipRyzen;
    private Chip chip8g, chip16g;

    private Button button;
    private ArrayList<String> selectecdchip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        selectecdchip = new ArrayList<>();

        Anhxa();
        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    selectecdchip.add(compoundButton.getText().toString());
                }
                else {
                    selectecdchip.remove(compoundButton.getText().toString());
                }
            }
        };

        chipApple.setOnCheckedChangeListener(checkedChangeListener);
        chipAsus.setOnCheckedChangeListener(checkedChangeListener);
        chipGigabyte.setOnCheckedChangeListener(checkedChangeListener);
        chiLenovo.setOnCheckedChangeListener(checkedChangeListener);
        chipHp.setOnCheckedChangeListener(checkedChangeListener);

        chipIntel.setOnCheckedChangeListener(checkedChangeListener);
        chipRyzen.setOnCheckedChangeListener(checkedChangeListener);

        chip8g.setOnCheckedChangeListener(checkedChangeListener);
        chip16g.setOnCheckedChangeListener(checkedChangeListener);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
//                resultIntent.putExtra("data", selectecdchip.toString());
//                setResult(101,resultIntent);
                finish();
            }
        });

    }

    private void Anhxa() {

        chipApple = findViewById(R.id.chipapple);
        chiLenovo = findViewById(R.id.chiplenovo);
        chipHp = findViewById(R.id.chiphp);
        chipGigabyte = findViewById(R.id.chipgigabyte);
        chipAsus = findViewById(R.id.chipasus);

        chipIntel = findViewById(R.id.chipIntel);
        chipRyzen = findViewById(R.id.chipRyzen);

        chip8g = findViewById(R.id.chip8g);
        chip16g = findViewById(R.id.chip16g);

        button = findViewById(R.id.button2);
    }
}