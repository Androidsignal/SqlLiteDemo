package com.tops.sqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText edtFName, edtLName;
    Button btnUpdate;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtFName = (EditText) findViewById(R.id.edtFName);
        edtLName = (EditText) findViewById(R.id.edtLName);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        edtFName.setText(getIntent().getStringExtra("fname"));
        edtLName.setText(getIntent().getStringExtra("lname"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudBean studBean = new StudBean();
                studBean.setId(getIntent().getStringExtra("id"));
                studBean.setfName(edtFName.getText().toString());
                studBean.setlName(edtLName.getText().toString());

                SqlHelper sqlHelper = new SqlHelper(UpdateActivity.this);
                sqlHelper.updateData(studBean);
                Toast.makeText(UpdateActivity.this, "Data Update Success...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateActivity.this, DisplayActivity.class));
                finish();
            }
        });
    }
}
