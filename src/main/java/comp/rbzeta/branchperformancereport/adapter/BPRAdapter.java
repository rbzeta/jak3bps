package comp.rbzeta.branchperformancereport.adapter;

/**
 * Created by Robyn on 17/09/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comp.rbzeta.branchperformancereport.R;
import comp.rbzeta.branchperformancereport.model.BranchPerformanceModel;

public class BPRAdapter extends RecyclerView.Adapter<BPRAdapter.MyViewHolder> {

    private List<BranchPerformanceModel> bprList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, branch, jabatan;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            branch = (TextView) view.findViewById(R.id.branch);
            jabatan = (TextView) view.findViewById(R.id.jabatan);
        }
    }


    public BPRAdapter(List<BranchPerformanceModel> bprList) {
        this.bprList = bprList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bpr_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BranchPerformanceModel bpr = bprList.get(position);
        holder.name.setText(bpr.getEmpName());
        holder.branch.setText(bpr.getBranchName());
        holder.jabatan.setText(bpr.getEmpJob());
    }

    @Override
    public int getItemCount() {
        return bprList.size();
    }
}