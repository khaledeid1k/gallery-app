package com.example.leafpicrevivedk.Ui.Settinform.inpublic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Constant;
import com.example.leafpicrevivedk.Ui.Forthfristscreen.MainActivity;
import com.example.leafpicrevivedk.Ui.Settinform.Public;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;
import com.example.leafpicrevivedk.databinding.ActivityInsecurityBinding;

public class Insecurity extends AppCompatActivity {
  ActivityInsecurityBinding binding;
    int appTheme;
    int themeColor;
    int appColor;
    Constant constant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appColor = shresprefrence.getcolor(Insecurity.this);
        appTheme = shresprefrence.getthem(Insecurity.this);
        themeColor = appColor;
        constant.color = appColor;

        if (themeColor == 0) {
            setTheme(constant.theme);
        } else if (appTheme == 0) {
            setTheme(constant.theme);
        } else {
            setTheme(appTheme);
        }
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppThemedarck);
        }else{
         setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_insecurity);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_insecurity);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.toolbarinsecurity);
        binding.toolbarinsecurity.setBackgroundColor(Constant.color);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Security");
        binding.makepassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked&&!shresprefrence.getdataregister(Insecurity.this)){
                    showDiaalogtogetpassword();
                }
               else if(!isChecked) {
                    shresprefrence.svaeregisterdata(Insecurity.this, false);
                    binding.makepassword.setChecked(false);
                    binding.makepasswordfordelet.setChecked(false);
                }

            }
        });
        // for make switch on when use password to open security
            if (shresprefrence.getdataregister(Insecurity.this)) {
                binding.makepassword.setChecked(true);
            }else {
                binding.makepassword.setChecked(false);
            }


     //////////////////////////



            binding.makepasswordfordelet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!shresprefrence.getdataregister(Insecurity.this)) {
                        binding.makepasswordfordelet.setChecked(false);
                        shresprefrence.savestuateofswitch(Insecurity.this,isChecked);
                    }
                    shresprefrence.savestuateofswitch(Insecurity.this,isChecked);
                }

            });
        binding.makepasswordfordelet.setChecked(shresprefrence.getdstatue(Insecurity.this));


    }
    private void showDiaalogtogetpassword() {
       // final EditText editText1=new EditText(Insecurity.this);
      //  final EditText editText2=new EditText(Insecurity.this);
//        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        );

       // editText1.setLayoutParams(lp);
       // editText2.setLayoutParams(lp);

        LinearLayout layout = new LinearLayout(Insecurity.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText editText1 = new EditText(Insecurity.this);
        editText1.setHint("Your Password");
        layout.addView(editText1);

        final EditText  editText2 = new EditText(Insecurity.this);
        editText2.setHint("Repeat Your password");
        layout.addView(editText2);

        editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        AlertDialog.Builder dialog=new AlertDialog.Builder(Insecurity.this);
        dialog.setTitle("Make Password")
        .setMessage("Enter Password")
        .setView(layout)
         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                if(s1.equals(s2)){
                    if(s1.length()<=5){
                            shresprefrence.svaeregisterdata(Insecurity.this, true);
                            shresprefrence.savepaswordandlogin(Insecurity.this,
                                    Integer.parseInt(s1));
                            binding.makepassword.setChecked(true);

                    }else{
                        binding.makepassword.setChecked(false);
                        Toast.makeText(Insecurity.this, "Bigger than 5 numbers ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    binding.makepassword.setChecked(false);
                    Toast.makeText(Insecurity.this, "Not Equal", Toast.LENGTH_SHORT).show();
                }

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shresprefrence.svaeregisterdata(Insecurity.this,false);
                binding.makepassword.setChecked(false);

                if(!shresprefrence.getdataregister(Insecurity.this)
                        ||!binding.makepassword.isChecked()) {
                    binding.makepasswordfordelet.setChecked(false);
                }
            }
        }).show();

    }



}