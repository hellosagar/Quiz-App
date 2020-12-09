package com.sagarkhurana.quizforfun.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sagarkhurana.quizforfun.R;
import com.sagarkhurana.quizforfun.data.Attempt;

import java.util.List;

import static com.sagarkhurana.quizforfun.other.Utils.formatDate;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.AttemptViewHolder> {

    private final List<Attempt> attempts;

    public HistoryAdapter(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    @NonNull
    @Override
    public AttemptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.AttemptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttemptViewHolder holder, int position) {

        Attempt item = attempts.get(position);

        holder.tvSubject.setText(String.valueOf(item.getSubject()));
        holder.tvEarned.setText(String.valueOf(item.getEarned()));
        holder.tvDate.setText(formatDate(item.getCreatedTime()));

        holder.cvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO NOTHING
            }
        });

    }

    @Override
    public int getItemCount() {
        return attempts.size();
    }

    public static class AttemptViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSubject,tvEarned,tvDate;
        public CardView cvParent;

        public AttemptViewHolder(View v) {
            super(v);
            tvSubject = v.findViewById(R.id.textView23);
            tvEarned = v.findViewById(R.id.textView24);
            tvDate = v.findViewById(R.id.textView25);
            cvParent = v.findViewById(R.id.cvItemHistory);

        }
    }

}
