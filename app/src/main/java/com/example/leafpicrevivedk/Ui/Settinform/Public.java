package com.example.leafpicrevivedk.Ui.Settinform;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Constant;
import com.example.leafpicrevivedk.Ui.Forthfristscreen.MainActivity;
import com.example.leafpicrevivedk.Ui.NavigationView.Setting;
import com.example.leafpicrevivedk.Ui.Settinform.inpublic.Insecurity;
import com.example.leafpicrevivedk.Ui.Settinform.inpublic.dialogforcoiums;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;
import com.example.leafpicrevivedk.databinding.FragmentPublicBinding;

import java.util.ArrayList;

public class Public extends Fragment implements dialogforcoiums.Numberofcolumns{

    FragmentPublicBinding binding;

    int appTheme;
    int themeColor;
    int appColor;
    Constant constant;

    public Public() {
        // Required empty public constructor
    }
    ArrayList<Switch>myAlTv = new ArrayList<>();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
 //View view= inflater.inflate(R.layout.fragment_public, container, false);
      //  changeColor();
        appColor = shresprefrence.getcolor2(getContext());
        appTheme = shresprefrence.gettheme2(getContext());
        themeColor = appColor;
        constant.color2 = appColor;

        if (themeColor == 0) {
            getContext().setTheme(constant.theme2);
        } else if (appTheme == 0) {
            getContext(). setTheme(constant.theme2);
        } else {
            getContext(). setTheme(appTheme);
        }


        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            getContext().setTheme(R.style.AppThemedarck);
        }else{
            getContext().setTheme(R.style.AppTheme);
        }
//        appTheme = shresprefrence.getthem(getContext());
//        themeColor = appColor;
//        constant.color = appColor;
//
//        if (themeColor == 0) {
//            visetTheme(constant.theme);
//        } else if (appTheme == 0) {
//            setTheme(constant.theme);
//        } else {
//            setTheme(appTheme);
//        }
         binding=DataBindingUtil.inflate(inflater,R.layout.fragment_public,
                container,false);
        View view=binding.getRoot();
        binding.securitycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogifexsitpassword();
            }

    });
        binding.multicolums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Changenumberofcolumes();
            }
        });
//
//        myAlTv.add(binding.switch1);
//        myAlTv.add(binding.switch2);
//        //...
        binding.publicc.setTextColor(Constant.color2);


      //  binding.switch1.setHighlightColor(Constant.color2);
        //binding.switch2.setBackgroundColor(Constant.color2);


    //     binding.setLifecycleOwner(this);+
    return view;
    }



    void ShowDialogifexsitpassword(){
        if (shresprefrence.getdataregister(getContext())) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle("PASSWORD");
            alertDialog.setMessage("Enter Password");
            LinearLayout linearLayout=new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            final EditText input = new EditText(getContext());
            input.setHint("Password");
            linearLayout.addView(input);
            input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);

            alertDialog.setView(linearLayout);
            alertDialog.setIcon(R.drawable.about);
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (shresprefrence.getlogin(getContext())) {
                                if (shresprefrence.getpassword(getContext()) == Integer.parseInt(input.getText().toString())&&!input.getText().toString().isEmpty()) {
                                    Intent intent = new Intent(getContext(), Insecurity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(getContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //                                password = input.getText().toString();
//                                if (password.compareTo("") == 0) {
//                                    if (pass.equals(password)) {
//                                        Toast.makeText(getContext(),
//                                                "Password Matched", Toast.LENGTH_SHORT).show();
//                                        Intent myIntent1 = new Intent(getContext(),
//                                                Show.class);
//                                        startActivityForResult(myIntent1, 0);
//                                    } else {
//                                        Toast.makeText(getApplicationContext(),
//                                                "Wrong Password!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
                        }
                    });

            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();


        }else{
            Intent intent = new Intent(getContext(), Insecurity.class);
            startActivity(intent);
        }
    }
    private void Changenumberofcolumes() {
        dialogforcoiums dialogforcoiums=new dialogforcoiums();
        dialogforcoiums.setTargetFragment(Public.this,1);
        dialogforcoiums.show(getFragmentManager(),"dialogforcoiums");
    }

    @Override
    public void getvalue(int value) {
       binding.publicc.setText(value);

    }

    public void changeColor() {
        for (int i = 0; i < myAlTv.size(); i++)
        {
            myAlTv.get(i).setTextColor(Constant.color2);
        }
    }
//    private void restartActivity() {
//        Intent intent = getIntent();
//        finish();
//        startActivity(intent);
//    }
    @Override
    public void onResume() {
        super.onResume();
        binding.publicc.setTextColor(Constant.color2);
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.switch1 .getThumbDrawable()
                            .setColorFilter(Constant.color2, PorterDuff.Mode.MULTIPLY);
                }else{
                    binding.switch1 .getThumbDrawable()
                            .setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.MULTIPLY);

                }
            }
        });
        binding.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.switch2 .getThumbDrawable()
                            .setColorFilter(Constant.color2, PorterDuff.Mode.MULTIPLY);
                }else{
                    binding.switch2 .getThumbDrawable()
                            .setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.MULTIPLY);
                }
            }
        });



    }

private void restart(){
        Intent i=new Intent(getContext(), Setting.class);
        startActivity(i);
 getActivity().finish();
}


}




