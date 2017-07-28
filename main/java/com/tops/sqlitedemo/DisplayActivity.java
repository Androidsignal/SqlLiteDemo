package com.tops.sqlitedemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<StudBean> arrayList;
    SqlHelper sqlHelper;
    Adpt adpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listView = (ListView) findViewById(R.id.mylist);
        sqlHelper = new SqlHelper(DisplayActivity.this);
        arrayList = sqlHelper.displayData();
        adpt = new Adpt(DisplayActivity.this, arrayList);
        listView.setAdapter(adpt);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Insert");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().equals("Insert")) {
            startActivity(new Intent(DisplayActivity.this, InsertActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Update");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = adapterContextMenuInfo.position;
        final StudBean studBean = arrayList.get(position);
        if (item.getTitle().equals("Update")) {

            Intent intent = new Intent(DisplayActivity.this, UpdateActivity.class);
            intent.putExtra("id", studBean.getId());
            intent.putExtra("fname", studBean.getfName());
            intent.putExtra("lname", studBean.getlName());
            startActivity(intent);
            finish();
        } else if (item.getTitle().equals("Delete")) {

            AlertDialog.Builder alert = new AlertDialog.Builder(DisplayActivity.this);
            alert.setTitle("Delete?");
            alert.setMessage("Are u sure want to delete?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    sqlHelper.deleteData(studBean.getId());
                    arrayList.remove(position);
                    adpt.notifyDataSetChanged();
                    Toast.makeText(DisplayActivity.this, "Data Delete Success...", Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("No", null);
            alert.show();
        }
        return super.onContextItemSelected(item);
    }
}
