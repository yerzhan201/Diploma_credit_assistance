package com.example.diplomapopytka;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.category.setText(transaction.getCategory());
        holder.transactionSum.setText(transaction.getSum());


        if ("income".equals(transaction.getType())) {
            holder.transactionSum.setTextColor(Color.GREEN);
        } else if ("outcome".equals(transaction.getType())) {
            holder.transactionSum.setTextColor(Color.RED);
        }
    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        TextView transactionSum;


        public ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            transactionSum = itemView.findViewById(R.id.transaction_sum);
        }

    }
    public void updateTransactions(List<Transaction> newTransactions) {
        this.transactions = newTransactions;
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }
}