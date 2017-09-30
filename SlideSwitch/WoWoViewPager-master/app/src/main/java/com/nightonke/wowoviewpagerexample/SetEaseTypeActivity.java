package com.nightonke.wowoviewpagerexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

public class SetEaseTypeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ease_type);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new SetEaseTypeAdapter(this));
        listView.setOnItemClickListener(this);

        checkBox = (CheckBox)findViewById(R.id.checkbox);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (getIntent().getStringExtra("AnimationType")) {
            case "WoWoPositionAnimation":
                intent = new Intent(this, WoWoPositionAnimationActivity.class);
                break;
            case "WoWoTranslationAnimation":
                intent = new Intent(this, WoWoTranslationAnimationActivity.class);
                break;
            case "WoWoScaleAnimation":
                intent = new Intent(this, WoWoScaleAnimationActivity.class);
                break;
            case "WoWoAlphaAnimation":
                intent = new Intent(this, WoWoAlphaAnimationActivity.class);
                break;
            case "WoWoRotationAnimation":
                intent = new Intent(this, WoWoRotationAnimationActivity.class);
                break;
            case "WoWoElevationAnimation":
                intent = new Intent(this, WoWoElevationAnimationActivity.class);
                break;
            case "WoWoTextViewTextSizeAnimation":
                intent = new Intent(this, WoWoTextViewTextSizeAnimationActivity.class);
                break;
            case "WoWoTextViewColorAnimation":
                intent = new Intent(this, WoWoTextViewColorAnimationActivity.class);
                break;
            case "WoWoTextViewTextAnimation":
                intent = new Intent(this, WoWoTextViewTextAnimationActivity.class);
                break;
            case "WoWoBackgroundColorAnimation":
                intent = new Intent(this, WoWoBackgroundColorAnimationActivity.class);
                break;
            case "WoWoShapeColorAnimation":
                intent = new Intent(this, WoWoShapeColorAnimationActivity.class);
                break;
            case "WoWoLayerListColorAnimation":
                intent = new Intent(this, WoWoLayerListColorAnimationActivity.class);
                break;
            case "WoWoStateListColorAnimation":
                intent = new Intent(this, WoWoStateListColorAnimationActivity.class);
                break;
            case "WoWoPathAnimation":
                intent = new Intent(this, WoWoPathAnimationActivity.class);
                break;
            case "CustomAnimation":
                intent = new Intent(this, CustomAnimationActivity.class);
                break;
            default: return;
        }
        switch (parent.getId()) {
            case R.id.listview:
                intent.putExtra("easeType", position);
                intent.putExtra("useSameEaseTypeBack", checkBox.isChecked());
                startActivity(intent);
                break;
        }
    }
}
