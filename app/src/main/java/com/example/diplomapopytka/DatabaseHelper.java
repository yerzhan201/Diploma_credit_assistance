package com.example.diplomapopytka;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;


// DatabaseHelper.java
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "loansManager.db";

    // Loans table name
    private static final String TABLE_LOANS = "loans";

    // Loans table columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LOAN_NAME = "loan_name";
    private static final String KEY_BANK_NAME = "bank_name";
    private static final String KEY_DATE = "date";
    private static final String KEY_MONTHLY_PAYMENT = "monthly_payment";
    private static final String KEY_AMOUNT_LOAN = "amount_loan";
    private static final String KEY_TERM_LOAN = "term_loan";
    private static final String TABLE_NAME_ACCOUNT = "account_card";
    private static final String COLUMN_BANK_NAME = "bank_name";
    private static final String COLUMN_BALANCE = "balance";
    private static final String COLUMN_COLOR = "color";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOANS_TABLE = "CREATE TABLE " + TABLE_LOANS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_LOAN_NAME + " TEXT,"
                + KEY_BANK_NAME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_MONTHLY_PAYMENT + " TEXT,"
                + KEY_AMOUNT_LOAN + " TEXT,"
                + KEY_TERM_LOAN + " TEXT" + ")";
        db.execSQL(CREATE_LOANS_TABLE);

        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_NAME_ACCOUNT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_BANK_NAME + " TEXT,"
                + COLUMN_BALANCE + " REAL,"
                + COLUMN_COLOR + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);

        // Create tables again
        onCreate(db);
    }

    // Method to add a loan
    public void addLoan(Loan loan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOAN_NAME, loan.getLoanName());
        values.put(KEY_BANK_NAME, loan.getBankName());
        values.put(KEY_DATE, loan.getloanDate());
        values.put(KEY_MONTHLY_PAYMENT, loan.getmonthlyPayment());
        values.put(KEY_AMOUNT_LOAN, loan.getloanAmount());
        values.put(KEY_TERM_LOAN, loan.getloanTerm());

        // Inserting Row
        db.insert(TABLE_LOANS, null, values);
        db.close(); // Closing database connection
    }
    public void addAccountCard(account_card accountCard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BANK_NAME, accountCard.getBankName());
        values.put(COLUMN_BALANCE, accountCard.getBalance());
        values.put(COLUMN_COLOR, accountCard.getColor());
        db.insert(TABLE_NAME_ACCOUNT, null, values);

        db.close();
    }
    public int updateAccountCard(account_card accountCard) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BANK_NAME, accountCard.getBankName());
        values.put(COLUMN_BALANCE, accountCard.getBalance());
        values.put(COLUMN_COLOR, accountCard.getColor());

        // Updating row
        return db.update(TABLE_NAME_ACCOUNT, values, KEY_ID + " = ?",
                new String[]{String.valueOf(accountCard.getId())});
    }
    public void deleteaccountcard(int accountid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_ACCOUNT, KEY_ID + " = ?", new String[]{String.valueOf(accountid)});
        db.close(); // Close the database connection
    }

    public List<account_card> getAllAccounts() {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_ACCOUNT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<account_card> accounts = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int bankNameIndex = cursor.getColumnIndex(COLUMN_BANK_NAME);
                int balanceIndex = cursor.getColumnIndex(COLUMN_BALANCE);
                int colorIndex = cursor.getColumnIndex(COLUMN_COLOR);

                // Check if the column indices are not -1 before trying to get the values from the cursor
                if (bankNameIndex != -1 && balanceIndex != -1 && colorIndex != -1) {
                    int id = cursor.getInt(idIndex);

                    String bankName = cursor.getString(bankNameIndex);
                    String balance = cursor.getString(balanceIndex);
                    String color = cursor.getString(colorIndex);
                    account_card accountCard = new account_card(id,bankName, balance, color);
                    accountCard.setId(id);

                    // Add the account_card object to the accounts list
                    accounts.add(accountCard);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return accounts;
    }

    public void updateLoan(Loan loan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOAN_NAME, loan.getLoanName());
        values.put(KEY_BANK_NAME, loan.getBankName());
        values.put(KEY_DATE, loan.getloanDate());
        values.put(KEY_MONTHLY_PAYMENT, loan.getmonthlyPayment());
        values.put(KEY_AMOUNT_LOAN, loan.getloanAmount());
        values.put(KEY_TERM_LOAN, loan.getloanTerm());

        // updating row
        db.update(TABLE_LOANS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(loan.getId())});
        db.close();
    }
    public void deleteLoan(int loanId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOANS, KEY_ID + " = ?", new String[]{String.valueOf(loanId)});
        db.close(); // Close the database connection
    }


    // Method to get all loans
    public List<Loan> getLoans() {
        List<Loan> loanList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LOANS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Loan loan = new Loan(
                        cursor.getInt(0),
                        cursor.getString(1), // loan name
                        cursor.getString(2), // bank name
                        cursor.getString(3), // date
                        cursor.getString(4), // monthly payment
                        cursor.getString(5), // amount loan
                        cursor.getString(6)     // term loan
                );
                loanList.add(loan);
            } while (cursor.moveToNext());
        }

        // Return loan list
        return loanList;
    }
}

