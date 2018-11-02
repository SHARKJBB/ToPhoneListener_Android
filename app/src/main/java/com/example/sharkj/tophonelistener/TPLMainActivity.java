package com.example.sharkj.tophonelistener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TPLMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        加载界面
        setContentView(R.layout.activity_tplmain);
        //实现按钮的点击效果
        //1.获得界面上的按钮组件
        final Button btn = this.findViewById(R.id.BT_TPL);
        //2.对按钮组件添加点击事件
        btn.setOnClickListener(new View.OnClickListener() {
            //当按钮被点击的时候，会自动调用执行OnClick();方法
            @Override
            public void onClick(View view) {
                Toast.makeText(TPLMainActivity.this, "正在开启去电监控功能...", Toast.LENGTH_LONG).show();
                // 获得界面上的监控者手机号码并且保存备用
                EditText et = findViewById(R.id.ET_ID);
                TPLInformations.phoneNumber = et.getText().toString();
                //程序流程跳转
                Intent it = new Intent();
                it.setClass(TPLMainActivity.this, TPLService.class);
                startService(it);
            }
        });

    }
}
