package com.example.dishycloud.adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishycloud.R;
import com.example.dishycloud.models.StepMake;

import java.util.List;

public class StepMakeAdapter extends RecyclerView.Adapter<StepMakeAdapter.ViewHolder> {
    public interface OnStepMakeClickListener {
        void onClick(StepMake stepMake, int position);
    }

    private Context mContext;
    private List<StepMake> mStepMakes;
    private OnStepMakeClickListener mOnStepMakeClickListener;

    public void setmOnStepMakeClickListener(OnStepMakeClickListener mOnStepMakeClickListener) {
        this.mOnStepMakeClickListener = mOnStepMakeClickListener;
    }

    public StepMakeAdapter(Context mContext, List<StepMake> mStepMakes) {
        this.mContext = mContext;
        this.mStepMakes = mStepMakes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_step_make, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mEdtDesc.setText(mStepMakes.get(position).getDescrip());
        holder.mCbPrepair.setChecked(mStepMakes.get(position).isPrepairStage());
        holder.mImgOne.setImageBitmap(mStepMakes.get(position).getImageOne());
        holder.mImgTwo.setImageBitmap(mStepMakes.get(position).getImageTwo());
        holder.mtxtOrder.setText(String.valueOf(position+2));
        holder.mImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnStepMakeClickListener.onClick(mStepMakes.get(position),position);
            }
        });
        if (mStepMakes.get(position).getImageOne()==null){
            holder.mImgOne.setImageResource(R.drawable.ic_add_picture);
        }if (mStepMakes.get(position).getImageTwo()==null){
            holder.mImgTwo.setImageResource(R.drawable.ic_add_picture);
        }
        holder.mImgOne.setEnabled(false);
        holder.mImgTwo.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return mStepMakes != null ? mStepMakes.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private EditText mEdtDesc;
        private CheckBox mCbPrepair;
        private TextView mtxtOrder;
        private ImageView mImgOne, mImgTwo,mImgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mEdtDesc = itemView.findViewById(R.id.edt_descrip_make_step_rcv);
            mCbPrepair = itemView.findViewById(R.id.cb_prepair_stage_rcv);
            mImgOne = itemView.findViewById(R.id.img_step_make_1_rcv);
            mImgTwo = itemView.findViewById(R.id.img_step_make_2_rcv);
            mImgEdit = itemView.findViewById(R.id.img_edit_make_step_rcv);
            mtxtOrder = itemView.findViewById(R.id.txt_order_make_step_rcv);
        }
    }
}
