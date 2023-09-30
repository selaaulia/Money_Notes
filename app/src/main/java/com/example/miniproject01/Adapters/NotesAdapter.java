package com.example.miniproject01.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject01.Models.Notes;
import com.example.miniproject01.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Notes> items;
    private OnItemNotesListener listener;

    public interface OnItemNotesListener {
        void onTransactionClicked(int index, Notes item);
    }

    public NotesAdapter(List<Notes> items, OnItemNotesListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView nominalText;
        TextView contentText;
        TextView timeText;
        TextView dateText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            nominalText = itemView.findViewById(R.id.nominal);
            contentText = itemView.findViewById(R.id.text_content);
            timeText = itemView.findViewById(R.id.text_date);
            dateText = itemView.findViewById(R.id.text_time);
        }
        public void bind(final int index, final Notes item) {
            titleText.setText(item.getTitle());
            nominalText.setText(item.strResult(item.getNominal()));
            contentText.setText(item.getContent());
            timeText.setText(item.getWaktu());
            dateText.setText(item.getDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });
        }
    }
}
