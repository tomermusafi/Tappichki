package com.example.game2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter_ScoreModel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Score> scores;
    private OnItemClickListener mItemClickListener;

    public Adapter_ScoreModel(Context context, ArrayList<Score> notes) {
        this.context = context;
        this.scores = notes;
    }

    public void updateList(ArrayList<Score> scores) {
        this.scores = scores;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_note, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final Score score = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.note_LBL_title.setText(""+ score.getScore());
            genericViewHolder.note_LBL_place.setText(""+ (position + 1) + ".");
            if(position == 0){
                genericViewHolder.note_IMG_cup.setImageResource(R.drawable.img_first_place);
            }
            if(position == 1){
                genericViewHolder.note_IMG_cup.setImageResource(R.drawable.second_place);
            }
            if(position == 2){
                genericViewHolder.note_IMG_cup.setImageResource(R.drawable.third_place);
            }
        }
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    private Score getItem(int position) {
        return scores.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView note_LBL_title, note_LBL_place;
        private ImageView note_IMG_cup;


        public ViewHolder(final View itemView) {
            super(itemView);
            this.note_LBL_title = itemView.findViewById(R.id.note_LBL_title);
            this.note_LBL_place = itemView.findViewById(R.id.note_LBL_place);
            this.note_IMG_cup = itemView.findViewById(R.id.note_IMG_cup);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), getItem(getAdapterPosition()));
                }
            });
        }
    }

    public void removeAt(int position) {
        scores.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, scores.size());
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Score note);
    }
}
