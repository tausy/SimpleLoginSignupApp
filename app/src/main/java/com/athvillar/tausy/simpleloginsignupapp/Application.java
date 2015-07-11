package com.athvillar.tausy.simpleloginsignupapp;



import com.parse.Parse;



/**
 * Created by TAUSY on 7/12/2015.
 */
public class Application extends android.app.Application {

        public Application() {
        }

        @Override
        public void onCreate() {
            super.onCreate();

            //ParseObject.registerSubclass(LoginActivity.class);
            //initialize parse
            Parse.initialize(this, "uYs1RLpvxwowOJJrCvrRWTGG3QqCtVcsbDkMGH20",
                    "H4BV8cDqtMeEKK32AtbTOeQsog0xZ7v5HH1CyCuu");
        }

}
