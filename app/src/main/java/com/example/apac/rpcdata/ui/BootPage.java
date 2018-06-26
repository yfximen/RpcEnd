package com.example.apac.rpcdata.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.apac.rpcdata.R;

public class BootPage extends AppCompatActivity {

    private Button bt_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_page);
        ImageView i_page = findViewById(R.id.i_page);

        bt_page = findViewById(R.id.bt_page);
        bt_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BootPage.this,LoginAcitivy.class));
                finish();
             }
        });
    }
}
