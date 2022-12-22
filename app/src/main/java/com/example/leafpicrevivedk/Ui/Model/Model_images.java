package com.example.leafpicrevivedk.Ui.Model;

import android.widget.ImageView;

import java.util.ArrayList;

public class Model_images {
    String str_folder;
        ArrayList<String> al_imagepath=new ArrayList<>();
    int view;





    public Model_images() {

    }


    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }


    public String getStr_folder() {
        return str_folder;
    }

    public void setStr_folder(String str_folder) {
        this.str_folder = str_folder;
    }




    public ArrayList<String> getAl_imagepath() {
        return al_imagepath;
    }

    public void setAl_imagepath(ArrayList<String> al_imagepath) {
        this.al_imagepath = al_imagepath; }
}
