package com.frank.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.frank.popuplist.PopupList;
import com.frank.popuplist.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_long_click;
    private ListView lv_main;
    private List<String> mDataList = new ArrayList<>();
    private ArrayAdapter<String> mDataAdapter;
    private List<String> popupMenuItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_long_click = (Button) findViewById(R.id.btn_long_click);
        lv_main = (ListView) findViewById(R.id.lv_main);
        mDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, mDataList);
        lv_main.setAdapter(mDataAdapter);

        popupMenuItemList.add(getString(R.string.copy));
        popupMenuItemList.add(getString(R.string.delete));
        popupMenuItemList.add(getString(R.string.share));
        popupMenuItemList.add(getString(R.string.more));
        PopupList popupList = new PopupList();
        popupList.init(this, lv_main, popupMenuItemList, new PopupList.PopupListListener() {
            @Override
            public boolean showPopupList(View adapterView, View contextView, int contextPosition) {
                return true;
            }

            @Override
            public void onPopupListClick(View contextView, int contextPosition, int position) {
                Toast.makeText(MainActivity.this, contextPosition + "," + position, Toast.LENGTH_SHORT).show();
            }
        });
        popupList.setIndicatorView(popupList.getDefaultIndicatorView());

        PopupList normalViewPopupList = new PopupList();
        normalViewPopupList.init(this, btn_long_click, popupMenuItemList, new PopupList.PopupListListener() {
            @Override
            public boolean showPopupList(View adapterView, View contextView, int contextPosition) {
                return true;
            }

            @Override
            public void onPopupListClick(View contextView, int contextPosition, int position) {
                Toast.makeText(MainActivity.this, contextPosition + "," + position, Toast.LENGTH_SHORT).show();
            }
        });
        normalViewPopupList.setIndicatorView(normalViewPopupList.getDefaultIndicatorView());

        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "onItemClicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        getData();
    }

    private void getData() {
        for (int i = 0; i < 40; i++) {
            mDataList.add("No." + i);
        }
        mDataAdapter.notifyDataSetChanged();
    }

}
