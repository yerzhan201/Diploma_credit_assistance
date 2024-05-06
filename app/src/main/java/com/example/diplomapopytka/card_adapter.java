package com.example.diplomapopytka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.diplomapopytka.R;
import com.example.diplomapopytka.account_card;

import java.util.List;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class card_adapter extends RecyclerView.Adapter<card_adapter.AccountCardViewHolder> {
    private List<account_card> accountCards;

    public card_adapter(List<account_card> accountCards) {
        this.accountCards = accountCards;
    }

    @Override
    public AccountCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_card, parent, false);
        return new AccountCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountCardViewHolder holder, int position) {
        account_card accountCard = accountCards.get(position);
        holder.bankName.setText(accountCard.getBankName());
        holder.balance.setText(String.valueOf(accountCard.getBalance()));
        holder.card_account.setBackgroundColor(Color.parseColor(accountCard.getColor()));
    }
    public void setAccounts(List<account_card> accountCards) {
        this.accountCards = accountCards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return accountCards.size();
    }

    class AccountCardViewHolder extends RecyclerView.ViewHolder {
        private TextView bankName;
        private TextView balance;
        private LinearLayout card_account;

        public AccountCardViewHolder(View itemView) {
            super(itemView);
            bankName = itemView.findViewById(R.id.bank_name);
            balance = itemView.findViewById(R.id.balance);
            card_account = itemView.findViewById(R.id.card_account);
        }


    }
}
