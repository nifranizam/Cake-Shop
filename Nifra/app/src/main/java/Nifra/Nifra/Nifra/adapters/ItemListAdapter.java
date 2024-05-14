package Nifra.Nifra.Nifra.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import uom.aysha.tha_204013d.R;
import Nifra.Nifra.Nifra.CakeFormActivity;
import Nifra.Nifra.Nifra.models.Item;


import java.util.Objects;
import java.util.Random;

public class ItemListAdapter extends ListAdapter<Item, ItemListAdapter.ViewHolder> {

    private final Activity activity;

    public ItemListAdapter(Activity activity){
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    private static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    (oldItem.getDescription().equals(newItem.getDescription())) &&
                    (oldItem.getPrice().equals(newItem.getPrice()));
        }
    };




    public Item getItemAt(int position){
        return getItem(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.cake_item_card,parent,false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item = getItemAt(position);

        TextView tv_product_title = holder.tv_product_title;
        tv_product_title.setText(item.getName());

        TextView tv_product_desc = holder.tv_product_desc;
        tv_product_desc.setText(item.getDescription());

        TextView tv_product_price = holder.tv_product_price;
        tv_product_price.setText("Rs. "+ item.getPrice());

        ImageView iv_cakeImg = holder.iv_cakeImg;
        Random rand = new Random(); //instance of random class
        int upperbound = 4;
        int int_random = rand.nextInt(upperbound);
        iv_cakeImg.setImageResource(int_random==1?R.drawable.cake1:int_random==2?R.drawable.cake2:R.drawable.cake3);

        holder.btn_edit.setOnClickListener(x -> {
            Intent intent = new Intent(this.activity, CakeFormActivity.class);
            intent.putExtra("ProductTitle", item.getName());
            intent.putExtra("ProductDesc", item.getDescription());
            intent.putExtra("ProductPrice", item.getPrice());
            intent.putExtra("Position", position);
            intent.putExtra("ID", item.getId());
            activity.startActivityForResult(intent,2);
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_cakeImg;
        private final TextView tv_product_title;
        private final TextView tv_product_desc;
        private final TextView tv_product_price;
        private final Button btn_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cakeImg = itemView.findViewById(R.id.iv_cakeImg);
            tv_product_title = itemView.findViewById(R.id.tv_product_title);
            tv_product_desc = itemView.findViewById(R.id.tv_product_desc);
            tv_product_price = itemView.findViewById(R.id.tv_product_price);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }


}
