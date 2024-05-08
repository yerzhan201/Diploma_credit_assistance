package com.example.diplomapopytka;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
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
        holder.white_block.setBackgroundColor(Color.parseColor(accountCard.getColor()));
        holder.white_block.setRadius(30);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start UpdateAccountCard activity
                Intent intent = new Intent(v.getContext(), update_account_card.class);

                // Pass the account_card data to the UpdateAccountCard activity
                intent.putExtra("id", accountCard.getId()); // Pass the account card's id
                intent.putExtra("bankName", accountCard.getBankName());
                intent.putExtra("balance", accountCard.getBalance());
                intent.putExtra("color", accountCard.getColor());

                // Start the UpdateAccountCard activity
                v.getContext().startActivity(intent);
            }
        });
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
        private CardView white_block;

        public AccountCardViewHolder(View itemView) {
            super(itemView);
            bankName = itemView.findViewById(R.id.bank_name);
            balance = itemView.findViewById(R.id.balance);
            white_block = itemView.findViewById(R.id.white_block);
        }


    }
}
