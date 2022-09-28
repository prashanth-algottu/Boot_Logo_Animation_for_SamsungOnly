package com.tectoro.examples.bootanimation;

import android.app.Application;
import android.content.Context;

public class GlobalApplication extends Application {

    protected static Context appContext;

    //    private static final String KPE_LICENSE_KEY = "KLM09-7BQYD-FO03B-MQCG2-51MKC-K1UZG"; //kpe premium
    protected static final String KPE_LICENSE_KEY = "KLM06-7V8XQ-I1SGK-A95KQ-X7G1J-4J0PW"; //kpe development

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

}
