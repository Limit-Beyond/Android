package com.example.dome2;

import android.app.usage.NetworkStats;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetBroadcastReceiver extends BroadcastReceiver {
    private NetStatusMonitor netStatusMonitor;

    public void setNetStatusMonitor(NetStatusMonitor netStatusMonitor) {
        this.netStatusMonitor = netStatusMonitor;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            boolean netStatus = NetUtil.getNetstatus(context);
            if(netStatusMonitor!=null)
                netStatusMonitor.onNetChange(netStatus);
        }
    }
}
