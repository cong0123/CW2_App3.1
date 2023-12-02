package com.example.cw2_app31;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private int imageIndex = 0; // Index của hình ảnh được chọn
    private int[] imageIds = {R.drawable.business, R.drawable.composer, R.drawable.doctor,
            R.drawable.engineer, R.drawable.farmer, R.drawable.operator, R.drawable.patrol,
            R.drawable.student, R.drawable.teacher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(getApplicationContext());

        ImageView imageView = findViewById(R.id.imageView);
        updateImageView(imageView);

        Button previousButton = findViewById(R.id.buttonPrevious);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageIndex = (imageIndex - 1 + imageIds.length) % imageIds.length;
                updateImageView(imageView);
            }
        });

        Button nextButton = findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageIndex = (imageIndex + 1) % imageIds.length;
                updateImageView(imageView);
            }
        });

        Button saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

        Button goToDetailButton = findViewById(R.id.buttonGoToDetail);
        goToDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetailPage();
            }
        });
    }

    private void updateImageView(ImageView imageView) {
        imageView.setImageResource(imageIds[imageIndex]);
    }

    private void goToDetailPage() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    private void saveDetails() {
        EditText nameTxt = findViewById(R.id.editTextName);
        EditText dobTxt = findViewById(R.id.editTextBirthday);
        EditText emailTxt = findViewById(R.id.editTextEmail);

        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();
        Log.d("SaveDetails", "Name: " + name + ", DOB: " + dob + ", Email: " + email);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(dob) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            long personId = dbHelper.insertDetail(name, dob, email, imageIds[imageIndex]);
            Toast.makeText(this, "Người đã được tạo với ID: " + personId, Toast.LENGTH_LONG).show();
        }
    }
}


