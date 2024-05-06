package com.example.diplomapopytka;// LoanAdapter.java
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {

    private List<Loan> loanList;
    private Context context;



    public LoanAdapter(Context context, List<Loan> loanList) {

        this.context = context;

        this.loanList = loanList;
    }

    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_loan, parent, false);
        return new LoanViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
        Loan loan = loanList.get(position);

        holder.loanName.setText(loan.getLoanName());
        // Assuming you have similar TextViews for the other fields
        holder.bankName.setText(loan.getBankName());
        holder.date.setText(loan.getloanDate());
        holder.monthlyPayment.setText(String.valueOf(loan.getmonthlyPayment()));
        holder.loanAmount.setText(loan.getloanAmount());
        holder.termLoan.setText(String.valueOf(loan.getloanTerm()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update_loan.class);
                intent.putExtra("loan_id", loan.getId());
                intent.putExtra("loan_name", loan.getLoanName());
                intent.putExtra("bank_name", loan.getBankName());
                intent.putExtra("loan_date", loan.getloanDate());
                intent.putExtra("monthly_payment", loan.getmonthlyPayment());
                intent.putExtra("loan_amount", loan.getloanAmount());
                intent.putExtra("loan_term", loan.getloanTerm());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        int size = loanList.size();
        Log.d("LoanAdapter", "Number of items: " + size);
        return size;
    }
    public void updateLoanList(List<Loan> newLoanList) {
        this.loanList = newLoanList;
        notifyDataSetChanged();
    }



    public class LoanViewHolder extends RecyclerView.ViewHolder {
        public TextView loanName;
        // Assuming you have similar TextViews for the other fields
        public TextView bankName;
        public TextView date;
        public TextView monthlyPayment;
        public TextView loanAmount;
        public TextView termLoan;
        LinearLayout mainLayout;

        public LoanViewHolder(View view) {
            super(view);

            loanName = view.findViewById(R.id.loan_name);
            // Initialize the other TextViews
            bankName = view.findViewById(R.id.bank_name);
            date = view.findViewById(R.id.loan_date);
            monthlyPayment = view.findViewById(R.id.monthly_payment);
            loanAmount = view.findViewById(R.id.loan_amount);
            termLoan = view.findViewById(R.id.loan_term);
            mainLayout = view.findViewById(R.id.mainLayout);
        }
    }
}
