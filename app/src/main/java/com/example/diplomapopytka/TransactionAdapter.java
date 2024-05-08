package com.example.diplomapopytka;

import android.content.Intent;
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
        holder.transactionName.setText(transaction.getName());


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
        TextView transactionName;
        TextView transactionSum;


        public ViewHolder(View itemView) {
            super(itemView);
            transactionName = itemView.findViewById(R.id.transaction_name);
            category = itemView.findViewById(R.id.category);
            transactionSum = itemView.findViewById(R.id.transaction_sum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Transaction transaction = transactions.get(position);
                    int id = transaction.getId(); // Assuming your Transaction class has a getId method

                    Intent intent = new Intent(v.getContext(), update_transaction_income.class); // Replace with your edit activity class name
                    intent.putExtra("id", id);
                    intent.putExtra("transactionName", transaction.getName());
                    intent.putExtra("sum", transaction.getSum());
                    intent.putExtra("category", transaction.getCategory());
                    intent.putExtra("type", transaction.getType());
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
    public void updateTransactions(List<Transaction> newTransactions) {
        this.transactions = newTransactions;
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }
}