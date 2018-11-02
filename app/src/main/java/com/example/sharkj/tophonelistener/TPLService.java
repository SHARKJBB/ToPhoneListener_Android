package com.example.sharkj.tophonelistener;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by sharkj on 2017/12/1.
 */

public class TPLService extends Service {
    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Toast.makeText(TPLService.this, "程序流程已经进入到Service类中...", Toast.LENGTH_LONG).show();
        Toast.makeText(TPLService.this, "监控者手机号码 = " + TPLInformations.phoneNumber, Toast.LENGTH_LONG).show();
//        去电监控的核心代码-->开启和监控

        ToPhoneListenerReceiver tplr = new ToPhoneListenerReceiver();
        IntentFilter tplif = new IntentFilter();
        tplif.addAction("android.intent.action.NEW_OUTGOING_CALL");
        this.registerReceiver(tplr, tplif);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
