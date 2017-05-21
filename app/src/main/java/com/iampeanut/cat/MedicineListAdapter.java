package com.iampeanut.cat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by satjawatpanakarn on 5/3/2017 AD.
 */

public class MedicineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> medicines;

    public void setMedicines(List<String> medicines) {
        this.medicines = new ArrayList(new HashSet(medicines));
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MedicineViewHolder) {
            ((MedicineViewHolder) holder).tvName.setText(medicines.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return medicines != null ? medicines.size() : 0;
    }

    class MedicineViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPrice;

        public MedicineViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
