package com.iampeanut.cat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by satjawatpanakarn on 5/2/2017 AD.
 */

public class ProblemListAdapter extends RecyclerView.Adapter<ProblemListAdapter.ListHolder> {
    private List<Problem> problemList;
    private Context context;

    public ProblemListAdapter(Context context) {
        this.context = context;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
        notifyDataSetChanged();
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_problem, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListHolder holder, final int position) {
        holder.tvName.setText(problemList.get(position).getName());
        holder.cbProblem.setOnCheckedChangeListener(null);
        holder.cbProblem.setChecked(problemList.get(position).isProblem());
        holder.cbProblem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Fix check in same group
                    for (int i = 0; i < problemList.size(); i++) {
                        if (problemList.get(i).getKey().equals(problemList.get(position).getKey()) && problemList.get(i).isProblem()) {
                            problemList.get(i).setProblem(false);
                            Toast.makeText(context, "ตรวจสอบอาการให้ดีน้าเป็นตรงไหนกันแน่~", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            break;
                        }
                    }
                }
                problemList.get(position).setProblem(isChecked);
            }
        });
        if (hasIcon(position)) {
            holder.ivIcon.setImageResource(problemList.get(position).getIcon());
        }
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = !holder.cbProblem.isChecked();
                holder.cbProblem.setChecked(check);
            }
        });
    }

    private boolean hasIcon(int position) {
        return problemList.get(position).getIcon() != 0;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }

    @Override
    public int getItemCount() {
        return problemList != null ? problemList.size() : 0;
    }

    static class ListHolder extends RecyclerView.ViewHolder {
        public LinearLayout llItem;
        public TextView tvName;
        public CheckBox cbProblem;
        public ImageView ivIcon;

        public ListHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            cbProblem = (CheckBox) itemView.findViewById(R.id.cb_problem);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_card);
            ivIcon = (ImageView) itemView.findViewById(R.id.ci_icon);
        }
    }
}
