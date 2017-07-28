package com.tops.sqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText edtFName, edtLName;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtFName = (EditText) findViewById(R.id.edtFName);
        edtLName = (EditText) findViewById(R.id.edtLName);
        btnInsert = (Button) findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudBean studBean = new StudBean();
                studBean.setfName(edtFName.getText().toString());
                studBean.setlName(edtLName.getText().toString());

                SqlHelper sqlHelper = new SqlHelper(InsertActivity.this);
                sqlHelper.insertData(studBean);
                Toast.makeText(InsertActivity.this, "Data Insert Success...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InsertActivity.this, DisplayActivity.class));
                finish();
            }
        });
    }
}
