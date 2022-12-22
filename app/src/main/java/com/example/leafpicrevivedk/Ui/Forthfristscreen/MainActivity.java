package com.example.leafpicrevivedk.Ui.Forthfristscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.leafpicrevivedk.Ui.Constant;
import com.example.leafpicrevivedk.Ui.Model.Model_images;
import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.NavigationView.Setting;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;
import com.example.leafpicrevivedk.Ui.wheniopenfolder.PhotosActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Model_images> al_images = new ArrayList<>();
    boolean boolean_folder;
    FoldersAdapter foldersAdapter;
    private static final int REQUEST_PERMISSIONS = 100;
    private DrawerLayout drawerLayout;
     static ArrayList<String> al_path;
    public static RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    public  static Toolbar toolbar;
    int appTheme;
    int themeColor;
    int appColor;
    Constant constant;
    View header;
    NavigationView navView;
    NavigationView c;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appColor = shresprefrence.getcolor(MainActivity.this);
        appTheme = shresprefrence.getthem(MainActivity.this);
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
        setContentView(R.layout.activity_main);
        //gv_folder = findViewById(R.id.gv_folder);
        recyclerView=findViewById(R.id.recycler);
        int f= shresprefrence.getvalue(MainActivity.this);
        if(f<=0) {
            layoutManager = new GridLayoutManager(this, 2);
        }else{
            layoutManager = new GridLayoutManager(this, f);

        }
        recyclerView.setLayoutManager(layoutManager);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return position == 0 ? 2 : 1;
//            }
//        });
        foldersAdapter = new FoldersAdapter(MainActivity.this,al_images);
        recyclerView.setAdapter(foldersAdapter);

         navView = findViewById(R.id.nav_view);
        header = navView.getHeaderView(0);
        header.setBackgroundColor(Constant.color);


        //toolbar
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Constant.color);

        //drawerLayout
        drawerLayout=findViewById(R.id.drawer);

        // for open items in navigating
        NavigationView navigationView=findViewById(R.id.nav_view);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.setting:
                        Intent intent = new Intent(getApplicationContext(), Setting.class);
                        intent.putExtra("v",0);
                        startActivity(intent);

                        break;
                }
                // to close our draw
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.a,R.string.b);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // to open folders
     foldersAdapter.onclick(new FoldersAdapter.click() {
         @Override
         public void Onclicklistener(int postion) {
             Intent intent = new Intent(MainActivity.this, PhotosActivity.class);
             intent.putExtra("value",postion);
             startActivity(intent);
         }
     });
//        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), PhotosActivity.class);
//                intent.putExtra("value",i);
//                startActivity(intent);
//            }
//        });
//        if(savedInstanceState==null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.contanier,new Setting()).commit();
//            navigationView.setCheckedItem(R.id.setting);
//        }



//        if(shresprefrence.getcolor(MainActivity.this)!=0) {
//            int color = ContextCompat.getColor(MainActivity.this, u);
//            ColorDrawable colorDrawable
//                    = new ColorDrawable(color);
//            toolbar.setBackground(colorDrawable);
//
//        }

Checkpermetion();
    }



    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

    }

    //checkPermission for access fo gallery ’s photos
    private void Checkpermetion() {

//اولا نحتاج الى ان تحقق من ان المستخدم لم يقم سابقا باعطائنا الاذن تجدر الاشارة الى ان المستخدم يستطيع التراجع عن الاذن
// من خلال الاعدادات لهذا يجب التحقق في كل مرة تحتاج فيها الى هذا الاذن

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                &&
                (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
// لم  نحصل على الاذن اول مره لذالك نعرضه معه الشرح ثاني مره
// هل تحتاج الى شرح سبب الطلب
// اظهر الشرح للمستخدم
// بعد رؤية المستخدم للشرح
            // دي بتظهر تاني مره بعد ما رفضت اول مره عشان اقوله سبب الظهور
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    &&
                    (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {
                Toast.makeText(MainActivity.this,"انا مسكين و احتاج الى ارقام الهاتف لبيعها",Toast.LENGTH_LONG).show();

            } else {

                // لا حاجة للشرح هذه اول مرة
//حسنا ان حصلنا على الاذن فهذا جيد
//لكن ان لم نحصل على الاذن فهنا نحتاج الى طلبه من المستخدم من خلال نافدة منبتقة و لاضهار هذه النافدة نحتاج الى الكود التالي:
//طلب الاذن
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
                Toast.makeText(MainActivity.this,"lolllllllllllllll",Toast.LENGTH_LONG).show();
            }
        }else {
            Log.e("Else","Else");
            fn_imagespath();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        fn_imagespath();
                    } else {
                        Toast.makeText(MainActivity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    //show photos of gallery in grid
    public ArrayList<Model_images> fn_imagespath() {
        al_images.clear();
        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage;
        //External storage can be read by every app
        //ده العنوان الي هيروحله
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

// which image properties are we querying
        String[] projection = {MediaStore.MediaColumns.DATA,
                               MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
           // مرتبه حسب اخد الداتا من gallery
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = MainActivity.this
                .getContentResolver()
                .query(uri, projection, null, null, orderBy + " DESC");


        column_index_data = cursor.
     //Common media metadata columns.
                getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//is a string like "Camera" which is the directory name of where an image or video is in.
// يعني بحدد اسم الفايل الي هجيب منه الصور
        column_index_folder_name = cursor.
                getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()) {
            //مسار الصوره
            absolutePathOfImage = cursor.getString(column_index_data);
//            Log.e("Column", absolutePathOfImage);
//            Log.e("Folder", cursor.getString(column_index_folder_name));

            for (int i = 0; i < al_images.size(); i++) {
                if (al_images.get(i).getStr_folder().
                        equals(cursor.getString(column_index_folder_name))) {
                    boolean_folder = true;
                    int_position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }


            if (boolean_folder) {
                // ده لو بنخزن كذا صوره في نفس الفولدر
                al_path = new ArrayList<>();
               // path = new ArrayList<>();

               al_path.addAll(al_images.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
               al_images.get(int_position).setAl_imagepath(al_path);



            } else {
                //هنا هنعمل فولدر جديد
               al_path = new ArrayList<>();
                //حط المسار بتاع الصوره
                al_path.add(absolutePathOfImage);

                Model_images obj_model = new Model_images();

                //حط اسم الفولدر
                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
                //حط اسم الصوره الاولنيه بس
                obj_model.setAl_imagepath(al_path);
               //item.setAl_imagepath(path);

                al_images.add(obj_model);



            }


        }


//        for (int i = 0; i < al_images.size(); i++) {
//            Log.e("FOLDER", al_images.get(i).getStr_folder());
//            for (int j = 0; j < al_images.get(i).getAl_imagepath().size(); j++) {
//                Log.e("FILE", al_images.get(i).getAl_imagepath().get(j));
//            }
//        }
        //obj_adapter = new Adapter_PhotosFolder(MainActivity.this,al_images);
        foldersAdapter.setArrayList(al_images);

        return al_images;
    }

   // for Actionbar
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
 // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem=menu.findItem(R.id.searhicon);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               foldersAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        toolbar.setBackgroundColor(Constant.color);
        header = navView.getHeaderView(0);
        header.setBackgroundColor(Constant.color);
        super.onResume();
    }


}