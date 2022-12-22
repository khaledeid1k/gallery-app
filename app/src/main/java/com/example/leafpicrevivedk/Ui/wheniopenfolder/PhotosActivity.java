package com.example.leafpicrevivedk.Ui.wheniopenfolder;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Constant;
import com.example.leafpicrevivedk.Ui.Forthfristscreen.MainActivity;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;
import com.example.leafpicrevivedk.Ui.Settinform.inpublic.dialogforcoiums;


public class PhotosActivity extends AppCompatActivity {
    //implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
    int int_position;
    public static RecyclerView recyclerView;
    public static Toolbar toolbar;
     GridelviewAdapterphotofile adapter;
    ImageView iv_imagee;
    private boolean uriToImage;
    GridLayoutManager gridLayoutManager;
    dialogforcoiums dialogforcoiums;
    int appTheme;
    int themeColor;
    int appColor;
    Constant constant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appColor = shresprefrence.getcolor(PhotosActivity.this);
        appTheme = shresprefrence.getthem(PhotosActivity.this);
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
        setContentView(R.layout.activity_photos);
        recyclerView = findViewById(R.id.gv_folderphotes);
        iv_imagee = findViewById(R.id.iv_image1);
        dialogforcoiums=new dialogforcoiums();
        int_position = getIntent().getIntExtra("value", 0);
        int v = dialogforcoiums.forphotesinfoldercolumns(shresprefrence.getvalue1(PhotosActivity.this));
        if(v<=0) {
            gridLayoutManager= new GridLayoutManager(this, 3);
        }else{
            gridLayoutManager= new GridLayoutManager(this, v);

        }
        recyclerView.setLayoutManager(gridLayoutManager);
       // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new GridelviewAdapterphotofile(this, MainActivity.al_images, int_position);
        recyclerView.setAdapter(adapter);
        toolbar = findViewById(R.id.tooledite);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainActivity.al_images.get(int_position).getStr_folder() + "");
        // عشان اعرض العلامه دي -> اما ادوس عليها هيرجعني ورا ولازم اضيف parent لل activity ف manifests
        toolbar.setBackgroundColor(Constant.color);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //        if(shresprefrence.getcolor(PhotosActivity.this)!=0){
//            int u=shresprefrence.getcolor(PhotosActivity.this);
//            ColorDrawable colorDrawable
//                    = new ColorDrawable(u);
//            toolbar.setBackground(colorDrawable);
//        }

    }


    //public static  void deletesignmarkedphotoes(){
//      //  recyclerView.setAdapter(adapter);
//    }
    @Override
    protected void onResume() {
        toolbar.setBackgroundColor(Constant.color);
        super.onResume();
    }


}


