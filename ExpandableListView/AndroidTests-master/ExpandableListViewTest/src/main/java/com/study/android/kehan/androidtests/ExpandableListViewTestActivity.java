package com.study.android.kehan.androidtests;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kehan on 16-7-19.
 */
public class ExpandableListViewTestActivity extends AppCompatActivity {

    private final String tag = "ExpandableListViewTest";

    private ExpandableListView listview;
    private MyExpandableListViewAdapter adapter;
    private Button updateParent;
    private Button updateChild;

    private Map<String, List<String>> dataset = new HashMap<>();
    private String[] parentList = new String[]{"first", "second", "third"};
    private List<String> childrenList1 = new ArrayList<>();
    private List<String> childrenList2 = new ArrayList<>();
    private List<String> childrenList3 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_layout);
        listview = (ExpandableListView) findViewById(R.id.expandablelistview);
        updateChild = (Button) findViewById(R.id.updateChildData);
        updateParent = (Button) findViewById(R.id.updateParentData);
        initialData();
        adapter = new MyExpandableListViewAdapter();
        listview.setAdapter(adapter);
        listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int parentPos, int childPos, long l) {
                Toast.makeText(ExpandableListViewTestActivity.this,
                        dataset.get(parentList[parentPos]).get(childPos), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String content = "";
                if ((int) view.getTag(R.layout.child_item) == -1) {
                    content = "父类第" + view.getTag(R.layout.parent_item) + "项" + "被长按了";
                } else {
                    content = "父类第" + view.getTag(R.layout.parent_item) + "项" + "中的"
                            + "子类第" + view.getTag(R.layout.child_item) + "项" + "被长按了";
                }
                Toast.makeText(ExpandableListViewTestActivity.this, content, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        updateChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateChildData();
                Toast.makeText(ExpandableListViewTestActivity.this, "子项数据已更新", Toast.LENGTH_SHORT).show();
            }
        });

        updateParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateParentData();
                Toast.makeText(ExpandableListViewTestActivity.this, "父项数据已更新", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initialData() {
        childrenList1.add(parentList[0] + "-" + "first");
        childrenList1.add(parentList[0] + "-" + "second");
        childrenList1.add(parentList[0] + "-" + "third");
        childrenList2.add(parentList[1] + "-" + "first");
        childrenList2.add(parentList[1] + "-" + "second");
        childrenList2.add(parentList[1] + "-" + "third");
        childrenList3.add(parentList[2] + "-" + "first");
        childrenList3.add(parentList[2] + "-" + "second");
        childrenList3.add(parentList[2] + "-" + "third");
        dataset.put(parentList[0], childrenList1);
        dataset.put(parentList[1], childrenList2);
        dataset.put(parentList[2], childrenList3);
    }

    /**
     * 更新子项数据
     */
    private void updateChildData() {
        childrenList1.clear();
        childrenList1.add(parentList[0] + "-new-" + "first");
        childrenList1.add(parentList[0] + "-new-" + "second");
        childrenList1.add(parentList[0] + "-new-" + "third");
        childrenList2.clear();
        childrenList2.add(parentList[1] + "-new-" + "first");
        childrenList2.add(parentList[1] + "-new-" + "second");
        childrenList2.add(parentList[1] + "-new-" + "third");
        childrenList3.clear();
        childrenList3.add(parentList[2] + "-new-" + "first");
        childrenList3.add(parentList[2] + "-new-" + "second");
        childrenList3.add(parentList[2] + "-new-" + "third");
        adapter.notifyDataSetChanged();
    }

    /**
     * 更新父项数据
     */
    private void updateParentData() {
        dataset.clear();
        parentList[0] = "new-first";
        parentList[1] = "new-second";
        parentList[2] = "new-third";
        dataset.put(parentList[0], childrenList1);
        dataset.put(parentList[1], childrenList2);
        dataset.put(parentList[2], childrenList3);
        adapter.notifyDataSetChanged();
    }

    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        //  获得某个父项的某个子项
        @Override
        public Object getChild(int parentPos, int childPos) {
            return dataset.get(parentList[parentPos]).get(childPos);
        }

        //  获得父项的数量
        @Override
        public int getGroupCount() {
            if (dataset == null) {
                Toast.makeText(ExpandableListViewTestActivity.this, "dataset为空", Toast.LENGTH_SHORT).show();
                return 0;
            }
            return dataset.size();
        }

        //  获得某个父项的子项数目
        @Override
        public int getChildrenCount(int parentPos) {
            if (dataset.get(parentList[parentPos]) == null) {
                Toast.makeText(ExpandableListViewTestActivity.this, "\" + parentList[parentPos] + \" + 数据为空", Toast.LENGTH_SHORT).show();
                return 0;
            }
            return dataset.get(parentList[parentPos]).size();
        }

        //  获得某个父项
        @Override
        public Object getGroup(int parentPos) {
            return dataset.get(parentList[parentPos]);
        }

        //  获得某个父项的id
        @Override
        public long getGroupId(int parentPos) {
            return parentPos;
        }

        //  获得某个父项的某个子项的id
        @Override
        public long getChildId(int parentPos, int childPos) {
            return childPos;
        }

        //  按函数的名字来理解应该是是否具有稳定的id，这个函数目前一直都是返回false，没有去改动过
        @Override
        public boolean hasStableIds() {
            return false;
        }

        //  获得父项显示的view
        @Override
        public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) ExpandableListViewTestActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.parent_item, null);
            }
            view.setTag(R.layout.parent_item, parentPos);
            view.setTag(R.layout.child_item, -1);
            TextView text = (TextView) view.findViewById(R.id.parent_title);
            text.setText(parentList[parentPos]);
            return view;
        }

        //  获得子项显示的view
        @Override
        public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) ExpandableListViewTestActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.child_item, null);
            }
            view.setTag(R.layout.parent_item, parentPos);
            view.setTag(R.layout.child_item, childPos);
            TextView text = (TextView) view.findViewById(R.id.child_title);
            text.setText(dataset.get(parentList[parentPos]).get(childPos));
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ExpandableListViewTestActivity.this, "点到了内置的textview",
                            Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
