package com.example.leafpicrevivedk.Ui.Forthfristscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.leafpicrevivedk.R;
import com.example.leafpicrevivedk.Ui.Model.Model_images;

import java.util.ArrayList;
import java.util.List;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.holder> {
    //ال arraylist دي جواها كام واحد من نوع Model_images
    private ArrayList<Model_images>  al_menu = new ArrayList<>();
    Context context;
    // make this for search
    List<Model_images> fullitems;
   click click;


    public  interface  click{ void Onclicklistener(int postion);}

    public  void onclick(click click){
        this.click=click;
    }
    public FoldersAdapter(Context context, ArrayList<Model_images> al_menu){
        this.al_menu = al_menu;
        fullitems=new ArrayList<>(al_menu);
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_photosfolder, parent, false),click);
    }

    @Override
    //الدتا بقا هتتغير هنا كل اما يعملBindViewHolder
    public void onBindViewHolder(@NonNull holder holder, int position) {
        //كل اما اعمل binding خليلي textview تاخد name بناء علي position الي احنا ادنهوله

        holder.tv_foldersize.setText(al_menu.get(position).getAl_imagepath().size()+"");

        Glide.with(context).load(al_menu.get(position)
                .getAl_imagepath().get(0))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.iv_image);

    }

    @Override
    public int getItemCount() {
        return al_menu!=null ?al_menu.size():0;
    }

    //كده حطينا دتا جوه Arraylist
    public void setArrayList(ArrayList<Model_images>  al_menu) {
        this. al_menu =  al_menu;
        notifyDataSetChanged();
    }

    //الي هيشيل views
    public class holder extends RecyclerView.ViewHolder {
        TextView tv_foldern, tv_foldersize;
        ImageView iv_image;
        public holder(@NonNull View itemView, final click click) {
            super(itemView);
            //كدا عرفناه textview الي جوه layout الي الدتا فيه هتتغير
            tv_foldern =itemView.findViewById(R.id.tv_folder0);
            tv_foldersize = itemView.findViewById(R.id.tv_folder20);
            iv_image =itemView.findViewById(R.id.iv_image0);
       iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click!=null){
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        click.Onclicklistener(position);
                    }

                }
            }
        });
        }

    }


    public Filter getFilter() {
        return examplefilter;
    }

    private  Filter examplefilter=new Filter() {
        // work on background
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Model_images> filteredlist=new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                //لو مفيش كلام مكتوب للبحس عنه اعرض list كلها
                filteredlist.addAll(fullitems);
                // لو كتب حاجه ف مربع السيرش
            }else{
                String filterpattern=constraint.toString().toLowerCase().trim(); //trim عشات اشيب المسافه الي ف اول الكلام
                for(Model_images v:fullitems){
                    if(v.getStr_folder().toLowerCase().contains(filterpattern)
                    ){
                        // لو هو العنصر الي ف list بيحتو علي الحرف الي هو كتبه ف السيرش حط item في list دي
                        filteredlist.add(v);
                    }
                }
            }
            FilterResults results=new FilterResults();
            // publishResult ده هيرجع list الي ظهرت من البحث ل
            results.values=filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            al_menu.clear();
            al_menu.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
