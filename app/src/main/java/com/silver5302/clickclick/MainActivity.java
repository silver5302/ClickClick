package com.silver5302.clickclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView startbtn;
    ImageView[] btns = new ImageView[12];
    Random rnd = new Random();
    int[] arr = new int[12];
    int cnt = 1;
    int stage=1;
    boolean start=true;
    LinearLayout main;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbtn = (ImageView) findViewById(R.id.startbtn);
        shuffle();
        for (int i = 0; i < 12; i++) {
            btns[i] = (ImageView) findViewById(R.id.btn01 + i);
         }




    }
    public void clickStart(View v){

        if(start) drawImg(R.drawable.num01);
        start=false;
        startbtn.setImageResource(R.drawable.ing);

    }

    public void clickNum(View v) {
        int tag = (Integer) v.getTag();
        if (tag == cnt) {
            ((ImageView) v).setVisibility(View.INVISIBLE);
            cnt++;

        }
        if  (cnt == 13&&stage==1)  stage(R.drawable.alpa01);

        if   (cnt==13&&stage==2)   stage(R.drawable.cha01);

        //ending 화면 띄우기
        if(stage==3&&cnt==13){
            main=(LinearLayout)findViewById(R.id.main);
            main.setBackgroundResource(R.drawable.bg4);
            TextView text=(TextView)findViewById(R.id.text1);
            text.setText("");
            text=(TextView)findViewById(R.id.text3);
            text.setText("");
            startbtn.setVisibility(View.INVISIBLE);
            interstitialAd=new InterstitialAd(this);
            interstitialAd.setAdUnitId("ca-app-pub-1090083221527642/9441424619");
            AdRequest adRequest=new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    Toast.makeText(MainActivity.this,"광고로딩실패",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    interstitialAd.show();
                    Toast.makeText(MainActivity.this,"광고성공",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void drawImg(int shape) {
        for (int i = 0; i < 12; i++) {

            btns[i].setImageResource(shape + arr[i] - 1);
            btns[i].setTag(arr[i]);

    }
    }

    public void shuffle() {
        for (int i = 0; i < 12; i++) {
            arr[i] = rnd.nextInt(12) + 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    i--;
                    break;
                }
            }
        }
    }
    public void stage(int a){
        cnt = 1;
        shuffle();
        for(int i=0;i<12;i++){
            btns[i].setVisibility(View.VISIBLE);
        }

        drawImg(a);
        stage++;

    }
}
