package com.example.leafpicrevivedk.Ui.Settinform;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Constant;
import com.example.leafpicrevivedk.Ui.Forthfristscreen.MainActivity;
import com.example.leafpicrevivedk.Ui.Methods;
import com.example.leafpicrevivedk.Ui.NavigationView.Setting;
import com.example.leafpicrevivedk.Ui.dialogfordarckmode;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;
import com.example.leafpicrevivedk.Ui.wheniopenfolder.PhotosActivity;
import com.example.leafpicrevivedk.databinding.FragmentPublicBinding;
import com.example.leafpicrevivedk.databinding.FragmentThemeBinding;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;


public class Theme extends Fragment {

    public Theme() {
        // Required empty public constructor
    }
    Methods methods;
    FragmentThemeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_theme,
                container,false);
        View view=binding.getRoot();

        // Inflate the layout for this fragment
            //Restore the fragment's state here
        //    defultcolor= ContextCompat.getColor(getContext(),R.color.colorPrimary);

        binding.Primarycolorc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Opendialog();
                }
            });
        binding.AccentColor.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Opendialog2();
               }
           });
        binding.basetheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog3();
            }
        });
            methods=new Methods();
        return view;
    }




    private void Opendialog() {
        AmbilWarnaDialog colorpicker=new AmbilWarnaDialog(getContext(),
                Constant.color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Constant.color =color;
//                ColorDrawable colorDrawable
//                        = new ColorDrawable(color);
                shresprefrence.savecolor(getContext(),color);
                shresprefrence.savetheme(getContext(),Constant.theme);
                methods.setColorTheme();
                Setting.toolbar.setBackgroundColor(color);
            }
        });
        colorpicker.show();
    }

    private void Opendialog2() {

        AmbilWarnaDialog accentColor=new AmbilWarnaDialog(getContext(),
                Constant.color2, new AmbilWarnaDialog.OnAmbilWarnaListener() {
    @Override
    public void onCancel(AmbilWarnaDialog dialog) {

    }

    @Override
    public void onOk(AmbilWarnaDialog dialog, int color) {
        Constant.color2=color;
        shresprefrence.savecolor2(getContext(),color);
        shresprefrence.savetheme2(getContext(),Constant.theme2);
        methods.setColorTheme2();
        binding.themstext.setTextColor(color);

//            Intent i = new Intent(getContext(), Setting.class);
//            getActivity().finish();
//            getActivity().overridePendingTransition(0, 0);
//            startActivity(i);
//            getActivity().overridePendingTransition(0, 0);


    }
});
accentColor.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.themstext.setTextColor(Constant.color2);

    }


    private void opendialog3() {
        dialogfordarckmode dialogfordarckmode=new dialogfordarckmode();
        dialogfordarckmode.setTargetFragment(Theme.this,1);
        dialogfordarckmode.show(getFragmentManager(),"dialogfordarckmode");
    }
}