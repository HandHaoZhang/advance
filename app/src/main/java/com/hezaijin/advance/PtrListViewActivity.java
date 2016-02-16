package com.hezaijin.advance;

import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hezaijin.advance.utils.ViewHolder;
import com.hezaijin.advance.widgets.view.ptr.load.PtrListView;

import java.util.ArrayList;
import java.util.List;

public class PtrListViewActivity extends AppCompatActivity {
    PtrListView listView;
    private static final String TAG = "PtrListView";

    private List<Integer> list = new ArrayList<Integer>();
    private MyAdapter adapter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptr_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listView = (PtrListView) findViewById(R.id.ptrlistview);

        listView.setOnLoadMoreListener(new PtrListView.OnLoadMoreListener() {
            @Override
            public void onLoadMoreStart() {
                Log.d(TAG, "onLoadMoreStart() called with: " + " is in mainThread ? ="  + (Looper.myLooper() == Looper.getMainLooper()));
            }

            @Override
            public void onLoadMoreRunning() {
                Log.d(TAG, "onLoadMoreRunning() called with: " + " is in mainThread ? ="  + (Looper.myLooper() == Looper.getMainLooper()));
                try {
                    Thread.sleep(5000);
                    int size = i + 10;
                    for (; i < size; i++) {
                        list.add(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadMoreCompleted() {
                Log.d(TAG, "onLoadMoreCompleted() called with: " + " is in mainThread ? =" + (Looper.myLooper() == Looper.getMainLooper()));
                adapter.notifyDataSetChanged();
            }
        }).setOnPtrItemClickListener(new PtrListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick() called with: " + "position = " + position);

            }
        }).setOnFootClickListener(new PtrListView.OnFootClickListener() {
            @Override
            public void onFootClickListener(View foot) {
                Log.d(TAG, "onFootClickListener() called with: " + "");

            }
        });

        for (; i < 10; i++) {
            list.add(i);
        }

        adapter = new MyAdapter();

        listView.setAdapter(adapter);

    }

    private class MyAdapter extends BaseAdapter {

        public MyAdapter() {
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(PtrListViewActivity.this, R.layout.item_ptrlistview_demo, null);
            }
            TextView tv = ViewHolder.get(convertView, R.id.test);
            tv.setText(String.valueOf(list.get(position)));
            return convertView;
        }
    }

}
