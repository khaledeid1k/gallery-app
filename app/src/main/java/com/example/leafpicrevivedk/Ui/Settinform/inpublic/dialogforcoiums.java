package com.example.leafpicrevivedk.Ui.Settinform.inpublic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Forthfristscreen.MainActivity;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;

public class dialogforcoiums extends AppCompatDialogFragment {
    SeekBar seekBar1;
    SeekBar seekBar2;
    SeekBar seekBar3;
    SeekBar seekBar4;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    int value=2;
    int value1=3;
    int value2=2;
    int value3=3;
public Numberofcolumns numberofcolumns;
    public interface Numberofcolumns {
        void getvalue (int value);
    }
//public  void  v (Numberofcolumns numberofcolumns){
//    this.numberofcolumns=numberofcolumns;
//}



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialogforcolums,null);
        seekBar1=view.findViewById(R.id.seekBar1);
        seekBar2=view.findViewById(R.id.seekBar2);
        seekBar3=view.findViewById(R.id.seekBar3);
        seekBar4=view.findViewById(R.id.seekBar4);
        textView1=view.findViewById(R.id.FoldersCoulmnspt);
        textView2=view.findViewById(R.id.MediaCoulmnspt);
        textView3=view.findViewById(R.id.FoldersCoulmnslt);
        textView4=view.findViewById(R.id.MediaCoulmnslt);
//        shresprefrence.savevalue(getContext(),value);
//        shresprefrence.savevalue1(getContext(),value1);
//        shresprefrence.savevalue2(getContext(),value2);
//        shresprefrence.savevalue3(getContext(),value3);


        if (shresprefrence.getvalue(getContext()) != value) {
            value = shresprefrence.getvalue(getContext());
        }
        textView1.setText("Folders Columns" + " " + value);
        seekBar1.setProgress(value);

        if (shresprefrence.getvalue1(getContext()) != value1) {
            value1 = shresprefrence.getvalue1(getContext());
        }
        textView2.setText("Folders Columns" + " " + value1);
        seekBar2.setProgress(value1);

        if (shresprefrence.getvalue2(getContext()) != value2) {
            value2 = shresprefrence.getvalue2(getContext());
        }
        textView3.setText("Folders Columns" + " " + value2);
        seekBar3.setProgress(value2);


        if (shresprefrence.getvalue3(getContext()) != value3) {
            value3 = shresprefrence.getvalue3(getContext());
        }
        textView4.setText("Folders Columns" + " " + value3);
        seekBar4.setProgress(value3);


        builder.setView(view)
                .setTitle("Columns")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),value);
                        MainActivity.recyclerView.setLayoutManager(layoutManager);



                          shresprefrence.savevalue(getContext(),value);
                          shresprefrence.savevalue1(getContext(),value1);
                          shresprefrence.savevalue2(getContext(),value2);
                          shresprefrence.savevalue3(getContext(),value3);
                        forphotesinfoldercolumns(shresprefrence.getvalue1(getContext()));



                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

       seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress >= 1){
                   value = progress;
               }else{
                   value=1;
               }
               textView1.setText("Folders Columns"+" "+value);
               //shresprefrence.savevalue(getContext(),value);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
       seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress >= 1){
                   value1 = progress;
               }else{
                   value1=1;
               }
               textView2.setText("Folders Columns"+" "+value1);
              // shresprefrence.savevalue1(getContext(),value1);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
       seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress >= 1){
                   value2= progress;
               }else{
                   value2=1;
               }
               textView3.setText("Folders Columns"+" "+value2);
             //  shresprefrence.savevalue2(getContext(),value2);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });
       seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               if(progress >= 1){
                   value3 = progress;
               }else{
                   value3=1;
               }
               textView4.setText("Folders Columns"+" "+value3);
           //  shresprefrence.savevalue3(getContext(),value3);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });

        return builder.create();
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//         super.onCreateView(inflater, container, savedInstanceState);
//        View view=inflater.inflate(R.layout.dialogforcolums,container,false);
//       // AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
//        seekBar1=view.findViewById(R.id.seekBar1);
//        seekBar2=view.findViewById(R.id.seekBar2);
//        seekBar3=view.findViewById(R.id.seekBar3);
//        seekBar4=view.findViewById(R.id.seekBar4);
//        textView1=view.findViewById(R.id.FoldersCoulmnspt);
//        textView2=view.findViewById(R.id.MediaCoulmnspt);
//        textView2=view.findViewById(R.id.FoldersCoulmnslt);
//        textView4=view.findViewById(R.id.MediaCoulmnslt);
//        Button button=view.findViewById(R.id.yes);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numberofcolumns.getvalue(2);
//
//            }
//        });
////        builder.setView(view)
////                .setTitle("Columns")
////                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        //Public pc= (Public) getActivity().getSupportFragmentManager().findFragmentByTag("Public");
////
////                        numberofcolumns.getvalue(2);
////
////                    }
////                })
////                .setNegativeButton("No", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
////
////                    }
////                }).show();
//
//
//        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(progress<value){
//                    textView1.setText("Folders Columns"+" " +value);
//                }else{
//                    value=progress;
//                    textView1.setText("Folders Columns"+" "+progress);
//                }
//
//
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(progress<1){
//                    textView2.setText("Media Columns"+" " +1);
//                }else{
//                    textView2.setText("Media Columns"+" "+progress);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(progress<1){
//                    textView3.setText("Folders Columns"+" " +1);
//                }else{
//                    textView3.setText("Folders Columns"+" "+progress);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(progress<1){
//                    textView4.setText("Media Columns"+" " +1);
//                }else{
//                    textView4.setText("Media Columns"+" "+progress);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//
//       // builder.create();
//
//        return  view;
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            numberofcolumns= (Numberofcolumns) getTargetFragment();
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+
                    "must implement Example DialogListener");
        }

    }
    public int forphotesinfoldercolumns(int value){
        value1=value;
        return  value1;

    }


    /////////////////////////////////////////////////
    //    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        Activity a = null;
//
//        try {
//            if (context instanceof Activity){
//                a=(Activity) context;
//            }
//            numberofcolumns= (Numberofcolumns) a;
//        } catch (ClassCastException e) {
//            throw  new ClassCastException(context.toString()+
//                    "must implement Example DialogListener");
//        }
//
//    }





}
