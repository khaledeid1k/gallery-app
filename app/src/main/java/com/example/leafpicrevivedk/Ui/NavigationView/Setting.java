package com.example.leafpicrevivedk.Ui.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Constant;
import com.example.leafpicrevivedk.Ui.Forthfristscreen.MainActivity;
import com.example.leafpicrevivedk.Ui.Settinform.Public;
import com.example.leafpicrevivedk.Ui.Settinform.Theme;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;
import com.example.leafpicrevivedk.Ui.wheniopenfolder.PhotosActivity;
import com.example.leafpicrevivedk.databinding.ActivitySettingBinding;

import java.util.ArrayList;

public class Setting extends AppCompatActivity {

    int appTheme;
    int themeColor;
    int appColor;
    Constant constant;
    static public Toolbar toolbar;
    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appColor = shresprefrence.getcolor(Setting.this);
        appTheme = shresprefrence.getthem(Setting.this);
        themeColor = appColor;
        constant.color = appColor;

        if (themeColor == 0) {
            setTheme(constant.theme);
        } else if (appTheme == 0) {
            setTheme(constant.theme);
        } else {
            setTheme(appTheme);
        }
         binding= DataBindingUtil.setContentView(this,R.layout.activity_setting);
        binding.setLifecycleOwner(this);

        getSupportFragmentManager().
                beginTransaction().replace(R.id.contanier,new Public()).commit();
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.container2,new Theme()).commit();

        toolbar=findViewById(R.id.toolbarsetteing);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Constant.color);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Setting");




//        if(shresprefrence.getcolor(Setting.this)!=0){
//            int u=shresprefrence.getcolor(Setting.this);
//            ColorDrawable colorDrawable
//                    = new ColorDrawable(u);
//            toolbar.setBackground(colorDrawable);
//        }

//
//        Intent intent=getIntent();
//        if(intent!=null&&intent.hasExtra("v")){
//
//        }


    }


}