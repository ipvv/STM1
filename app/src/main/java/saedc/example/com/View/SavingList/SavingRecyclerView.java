package saedc.example.com.View.SavingList;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import saedc.example.com.Model.Entity.Saving;
import saedc.example.com.Model.Pojo.saving_pojo;
import saedc.example.com.R;

/**
 * Created by saedc on 10/02/18.
 */

public class SavingRecyclerView extends RecyclerView.Adapter<SavingRecyclerView.MyViewHolder> {


    private List<saving_pojo> savingslist;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //you can add dd/MM/yyyy for date
    private LayoutInflater layoutInflater;
    Recyclerviewclick recyclerVewItemClickListener;
    private int lastPosition = -1;
    Context context;

    public SavingRecyclerView(Context context, ArrayList<saving_pojo> spendings, Recyclerviewclick listener) {

        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.savingslist = spendings;
        this.recyclerVewItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_saving_list_row, viewGroup, false);

        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        saving_pojo s = savingslist.get(position);
        Double quantity1 = s.getRawSaving().getItem_price();
        Double quantity2 = s.getRawSaving().getItem_saveing();
        Date date = s.getRawSaving().getEnd_date();

    }

    @Override
    public int getItemCount() {
        return savingslist.size();
    }

    public void updateItems(List<saving_pojo> spendings){
        final SavingDiffCallback diffCallback = new SavingDiffCallback(this.savingslist, spendings);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.savingslist.clear();
        this.savingslist.addAll(spendings);
        diffResult.dispatchUpdatesTo(this);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation slide_up = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            viewToAnimate.startAnimation(slide_up);
            lastPosition = position;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{
        @BindView(R.id.item_price)
        TextView price;

        @BindView(R.id.item_price_part)
        TextView price_part;

        @BindView(R.id.endDate)
        TextView EndDate;


        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }
        public TextView getTxtPrice() {
            return price;
        }

        public TextView getTxtprice_part() {
            return price_part;
        }

        public TextView getTxtEndDate() {
            return EndDate;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != -1){
                recyclerVewItemClickListener.onItemClick(savingslist.get(position).getRawSaving());
            }
        }
        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            if(position != -1){
                recyclerVewItemClickListener.onItemLongClick(savingslist.get(position).getRawSaving().getId());
            }
            return false;
        }

        }


    }

