package com.example.rapideats.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.rapideats.Helper.ManagementCart;
import com.example.rapideats.Domain.Foods;
import com.example.rapideats.R;
import com.example.rapideats.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
ActivityDetailBinding binding;
private Foods object;
private int num = 1;
private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        getIntentExtra();
        setVariable();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void setVariable(){
        managementCart= new ManagementCart(this);
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.pic);
        binding.priceTxt.setText("$"+object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar()+ " Rating");
        binding.ratingBar.setRating(( (float) object.getStar()));
        binding.totalTxt.setText(num*object.getPrice()+"$");

        binding.plusBtn.setOnClickListener(v -> {
                num=num+1;
                binding.numTxt.setText(num + " ");
                binding.totalTxt.setText(("$" + num*object.getPrice()));
        });

        binding.minusBtn.setOnClickListener(v ->  {
                if(num>1)
                {
                    num = num-1;
                    binding.numTxt.setText(num+"");
                    binding.totalTxt.setText("$" + (num*object.getPrice()));
                }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(num);
                managementCart.insertFood(object);
            }
        });
    }
    private void getIntentExtra(){
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}