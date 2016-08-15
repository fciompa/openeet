package com.github.openeet.openeet;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.net.URL;
import java.util.Date;

import openeet.lite.EetRegisterRequest;
import openeet.lite.EetSaleDTO;
import openeet.lite.Main;

/**
 * Created by rasekl on 8/10/16.
 */
public class RegisterSaleTask extends AsyncTask <EetSaleDTO,Integer, String> {
    private static final String LOGTAG="RegisterSaleTask";
    public static final String ACTION_SALE_REGISTERED_CHANGE="com.github.openeet.openeet.action.SaleRegisteredChange";


    private Context context;

    protected RegisterSaleTask(Context context){
        this.context=context;
    }

    public static IntentFilter getMatchAllFilter(){
        IntentFilter ret=new IntentFilter();
        ret.addAction(ACTION_SALE_REGISTERED_CHANGE);
        return ret;
    }

    @Override
    protected String doInBackground(EetSaleDTO... dtoSales) {
        SaleService.getInstance().registerSale(dtoSales[0]);
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(RegisterSaleTask.ACTION_SALE_REGISTERED_CHANGE));
        return null;
    }
}