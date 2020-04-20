package com.nguyenminhngan.hocoptionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    ListView lvTinhThanh;
    ArrayAdapter<String> adapterTinhThanh;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        btn1 = (Button) findViewById(R.id.btnNhanGiuLau);
        registerForContextMenu(btn1);
        lvTinhThanh = (ListView) findViewById(R.id.lvTinhThanh);
        adapterTinhThanh = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );
        adapterTinhThanh.addAll(getResources().getStringArray(R.array.arrTinhThanh));
        lvTinhThanh.setAdapter(adapterTinhThanh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        inflater.inflate(R.menu.action_menu,menu);
        MenuItem mnuSearch =menu.findItem(R.id.mnuSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mnuSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTinhThanh.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuGioiThieu:
                moManHinhGioiThieu();
                break;
            case R.id.mnuHuongDan:
                moManHinhHuongDan();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void moManHinhGioiThieu() {
        Intent intent = new Intent(MainActivity.this, GioiThieuActivity.class);
        startActivity(intent);
    }

    private void moManHinhHuongDan() {
        Intent intent = new Intent(this, HuongDanActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuMauDo:
                btn1.setTextColor(Color.RED);
                break;
            case R.id.mnuMauVang:
                btn1.setTextColor(Color.YELLOW);
                break;
            case R.id.mnuMauXanh:
                btn1.setTextColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
