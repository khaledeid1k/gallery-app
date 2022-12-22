package com.example.leafpicrevivedk.Ui.sharedprefrence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.leafpicrevivedk.Ui.Settinform.Public;


public class shresprefrence {
    public  static void  savepaswordandlogin(Context context, int email){
        SharedPreferences preferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        preferences.edit().putInt("email",email).commit();

        preferences.edit().putBoolean("login",true).commit();
    }
    public static boolean getlogin(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        boolean login = sharedPreferences.getBoolean("login", false);
        return login;
    }
    public  static int getpassword(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("user",context.MODE_PRIVATE);
        return  sharedPreferences.getInt("email",19);


    }
    public static void svaeregisterdata(Context context,boolean b){
        SharedPreferences sharedPreferences=context.getSharedPreferences("r",context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("l",b).commit();
    }
    public static boolean getdataregister(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("r",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("l",false);
    }
    public static void savestuateofswitch(Context context,boolean b){
        SharedPreferences sharedPreferences=context.getSharedPreferences("f",context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("p",b).commit();
    }
    public static boolean getdstatue(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("f",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("p",false);
    }

    public  static void  savevalue(Context context, int v){
        SharedPreferences sharedPreferences=context.getSharedPreferences("c",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("v",v).commit();
    }
    public  static int  getvalue(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("c",context.MODE_PRIVATE);
        return sharedPreferences.getInt("v",0);
    }

 public  static void  savevalue1(Context context, int v){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cc",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("vv",v).commit();
    }
    public  static int  getvalue1(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cc",context.MODE_PRIVATE);
        return sharedPreferences.getInt("vv",0);
    }

 public  static void  savevalue2(Context context, int v){
        SharedPreferences sharedPreferences=context.getSharedPreferences("ccc",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("vvv",v).commit();
    }
    public  static int  getvalue2(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("ccc",context.MODE_PRIVATE);
        return sharedPreferences.getInt("vvv",0);
    }

 public  static void  savevalue3(Context context, int v){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cccc",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("vvvv",v).commit();
    }
    public  static int  getvalue3(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cccc",context.MODE_PRIVATE);
        return sharedPreferences.getInt("vvvv",0);
    }
    public static void savecolor(Context context,int c){
        SharedPreferences sharedPreferences=context.getSharedPreferences("color",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("color1",c).commit();
    }

    public static int getcolor(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("color",context.MODE_PRIVATE);
        return   sharedPreferences.getInt("color1",1);
    }
    public static int getthem(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("theme",context.MODE_PRIVATE);
        return   sharedPreferences.getInt("theme1",1);
    }
    public static void savetheme(Context context,int c){
        SharedPreferences sharedPreferences=context.getSharedPreferences("theme",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("theme1",c).commit();
    }
    public static void savecolor2(Context context,int f){
        SharedPreferences sharedPreferences=context.getSharedPreferences("ee",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("d",f).commit();
    }
    public static int  getcolor2(Context c){
        SharedPreferences sharedPreferences=c.getSharedPreferences("ee",c.MODE_PRIVATE);
        return  sharedPreferences.getInt("d",1);
    }
    public static void savetheme2(Context context,int f){
        SharedPreferences sharedPreferences=context.getSharedPreferences("tt",context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("dd",f).commit();
    }
    public static int  gettheme2(Context c){
        SharedPreferences sharedPreferences=c.getSharedPreferences("tt",c.MODE_PRIVATE);
        return  sharedPreferences.getInt("dd",1);
    }
    public static void savetheme(Context context,boolean b){
        SharedPreferences sharedPreferences=context.getSharedPreferences("savetheme",context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("saveboolen1",b);
    }
    public static boolean gettheme(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("savetheme",context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("saveboolen1",false);
    }


}
