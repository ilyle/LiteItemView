package edu.coerscnu.litelibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.xiaoqi.liteitemview.LiteItemView;

public class MainActivity extends AppCompatActivity {

    private LiteItemView mLiv;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mLiv = findViewById(R.id.liv);
        mLiv.setChecked(true);
        mLiv.setOnLiteItemViewClick(new LiteItemView.OnLiteItemViewClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(mContext, "fafa", Toast.LENGTH_SHORT).show();
            }
        });
        mLiv.setOnLiteItemCheckChangeListener(new LiteItemView.OnLiteItemCheckChangeListener() {
            @Override
            public void onCheckedChanged(boolean checked) {
                Toast.makeText(mContext, "dafa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
