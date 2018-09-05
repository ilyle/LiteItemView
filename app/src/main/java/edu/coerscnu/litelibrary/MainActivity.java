package edu.coerscnu.litelibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        mLiv.setOnLiteItemViewClick(new LiteItemView.OnLiteItemViewClick() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.liv:
                        Toast.makeText(mContext, mLiv.getLeftText(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
