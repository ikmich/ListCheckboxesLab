package org.ikmich.listcheckboxeslab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> data;
    ListView lv;
    Button btn;
    CheckBox chkSelectAll;

    public final int NUM_DATA = 500;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = getData();

        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyAdapter(this, R.layout.list_item, data, true);
        lv.setAdapter(adapter);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log selected items.
                Log.d(">> foo", "Selected items:");
                List<String> selectedItems = adapter.getSelected();
                for (String s : selectedItems) {
                    Log.d(">> foo", s);
                }
            }
        });

        chkSelectAll = (CheckBox) findViewById(R.id.chk_select_all);
        chkSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    adapter.selectAll();
                } else {
                    adapter.unselectAll();
                }
            }
        });
    }

    public List<String> getData() {
        data = new LinkedList<>();

        for (int i = 0; i < NUM_DATA; i++) {
            String s = "Data " + i;
            data.add(s);
        }

        return data;
    }
}
