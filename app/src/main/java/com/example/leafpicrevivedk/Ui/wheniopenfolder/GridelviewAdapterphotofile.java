package com.example.leafpicrevivedk.Ui.wheniopenfolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Model.Model_images;
import com.example.leafpicrevivedk.Ui.sharedprefrence.shresprefrence;

import java.util.ArrayList;

public class GridelviewAdapterphotofile extends RecyclerView.Adapter<GridelviewAdapterphotofile.holder> {
    //ال arraylist دي جواها كام واحد من نوع Model_images
    private ArrayList<Model_images> al_menu;
    private boolean multiSelect = false;
    private ArrayList<String> selectedItems = new ArrayList<>();
    Context context;
    int int_position;
    click click;
    ImageView rightwe;
    ActionMode actionMode = null;
    private int selCount = -1;


    public interface click {
        void Onclicklistener(int postion);
    }

    public void onclick(click click) {
        this.click = click;
    }

    public GridelviewAdapterphotofile(Context context, ArrayList<Model_images> al_menu, int int_position) {
        this.al_menu = al_menu;
        this.context = context;
        this.int_position = int_position;


    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterphotowhenfolderopen, parent, false), click);
    }

    @Override
    //الدتا بقا هتتغير هنا كل اما يعملBindViewHolder
    public void onBindViewHolder(@NonNull final holder holder, final int position) {
        //كل اما اعمل binding خليلي textview تاخد name بناء علي position الي احنا ادنهوله


        holder.tv_foldern.setVisibility(View.GONE);
        holder.tv_foldersize.setVisibility(View.GONE);
        holder.update(al_menu.get(int_position).getAl_imagepath().get(position));

        Glide.with(context).load(al_menu.get(int_position).getAl_imagepath().get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.iv_image);
    }



    @Override
    public int getItemCount() {
        return al_menu.get(int_position).getAl_imagepath() != null ?
                al_menu.get(int_position).getAl_imagepath().size() : 0;
    }

    //كده حطينا دتا جوه Arraylist
    public void setArrayList(ArrayList<Model_images> al_menu) {
        this.al_menu = al_menu;
        notifyDataSetChanged();
    }

    //الي هيشيل views
    public class holder extends RecyclerView.ViewHolder {
        TextView tv_foldern, tv_foldersize;
        ImageView iv_image, right;

        public holder(@NonNull View itemView, final click mlistener) {
            super(itemView);
            //كدا عرفناه textview الي جوه layout الي الدتا فيه هتتغير

            tv_foldern = itemView.findViewById(R.id.tv_folder1);
            tv_foldersize = itemView.findViewById(R.id.tv_folder21);
            iv_image = itemView.findViewById(R.id.iv_image1);
            right = itemView.findViewById(R.id.rightw1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.Onclicklistener(position);
                        }
                    } }});

        }

        private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

            // Called when the action mode is created; startActionMode() was called
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                multiSelect = true;
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.select, menu);
                ((PhotosActivity) context).getSupportActionBar().hide();
                return true;
            }

            // Called each time the action mode is shown. Always called after onCreateActionMode, but
            // may be called multiple times if the mode is invalidated.
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false; // Return false if nothing is done
            }

            // Called when the user selects a contextual menu item
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share:
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, 0);
                        shareIntent.setType("image/jpeg");
                        ((Activity) context).startActivity(Intent.createChooser(shareIntent, ((Activity) context).getResources().getText(R.string.send_to)));

                    //mode.finish(); // Action picked, so close the CAB
                        return true;
                    case R.id.delete:
                        if(shresprefrence.getdstatue(context)) {
                            Dialogforpasswordbeforeremove();


                        }
                        else{
                            Dialogforremove();

                        }
//                        for (String intItem : selectedItems) {
//                            al_menu.get(int_position).getAl_imagepath().remove(intItem);
//                            notifyItemRemoved(int_position);
//                            notifyDataSetChanged();
//                            notifyItemRangeChanged(getAdapterPosition(), al_menu.get(int_position).getAl_imagepath().size());
//                        }
//                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            // Called when the user exits the action mode
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                ((PhotosActivity) context).getSupportActionBar().show();
                selCount = -1;
                multiSelect = false;
                selectedItems.clear();
                notifyDataSetChanged();
               // PhotosActivity.deletesignmarkedphotoes();

            }

        };

        void selectItem(String item) {
            if (multiSelect) {
                if (selectedItems.contains(item)) {
                    selectedItems.remove(item);
                    iv_image.setBackgroundColor(Color.WHITE);
                } else {
                    selectedItems.add(item);
                    iv_image.setBackgroundColor(Color.LTGRAY);


                }
            }
        }

        public void removeAt(int position) {
            al_menu.get(int_position).getAl_imagepath().remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
            notifyItemRangeChanged(position, al_menu.get(int_position).getAl_imagepath().size());
        }

        void update(final String value)    {
            if (selectedItems.contains(value)) {
               iv_image.setBackgroundColor(Color.LTGRAY);
            } else {
                iv_image.setBackgroundColor(Color.WHITE);
            }
            iv_image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // triggers when you long press
                    rightwe = itemView.findViewById(R.id.rightw1);
                    if (selCount == -1) {
                        rightwe.setVisibility(View.VISIBLE);
                        selCount += 2;
                        multiSelect=true;
                        selectItem(value);
                         v.setSelected(true);
                        // Start the CAB using the ActionMode.Callback defined above
                        actionMode = ((Activity) context).startActionMode(actionModeCallback);
                        actionMode.setTag(getAdapterPosition());
                        actionMode.setTitle(selCount + "selected");
                    }
                    return true;
                }
            });
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //triggers when click
                    rightwe = itemView.findViewById(R.id.rightw1);
                    if (selCount != -1 && !v.isSelected()) {
                        rightwe.setVisibility(View.VISIBLE);
                        v.setSelected(true);
                        selectItem(value);
                        ++selCount;
                        actionMode.setTitle(selCount + " selected");
                        actionMode.setTag(getAdapterPosition());
                    } else if (v.isSelected() && selCount >= 1) {
                        selCount--;
                        selectItem(value);
                        v.setSelected(false);
                        rightwe.setVisibility(View.INVISIBLE);
                        actionMode.setTitle(selCount + " selected");
                    }
                    // Start the CAB using the ActionMode.Callback defined above
                }
            });


        }
        void  Dialogforremove (){
            AlertDialog.Builder dialog=new AlertDialog.Builder(context);
            dialog.setMessage("Are you sure for remove items ?")
                  .setTitle("Warning")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (String intItem : selectedItems) {
                                al_menu.get(int_position).getAl_imagepath().remove(intItem);
                                notifyItemRemoved(getAdapterPosition());
                                notifyDataSetChanged();
                                notifyItemRangeChanged(getAdapterPosition(), al_menu.get(int_position).getAl_imagepath().size());
                            }
                          actionMode.finish();
                            notifyItemRemoved(getAdapterPosition());
                            notifyDataSetChanged();
                            notifyItemRangeChanged(getAdapterPosition(), al_menu.get(int_position).getAl_imagepath().size());



                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }
        void Dialogforpasswordbeforeremove(){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("PASSWORD");
            alertDialog.setMessage("Enter Password");
            LinearLayout linearLayout=new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            final EditText input = new EditText(context);
            input.setHint("Password");
            linearLayout.addView(input);
            input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);

            alertDialog.setView(linearLayout);
            alertDialog.setIcon(R.drawable.about);
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (shresprefrence.getlogin(context)) {
                                if (!input.getText().toString().isEmpty()) {
                                    if ((shresprefrence.getpassword(context)
                                            == Integer.parseInt(input.getText().toString()))) {
                                        Dialogforremove();
                                    } else {
                                        Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });

            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            alertDialog.show();


        }



    }


}