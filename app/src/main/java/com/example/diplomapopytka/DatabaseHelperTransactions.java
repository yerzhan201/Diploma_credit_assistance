package com.example.diplomapopytka;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperTransactions extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "transactions.db";
    private static final String TABLE_NAME = "transactions";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TRANSACTION_NAME";
    private static final String COL_3 = "CATEGORY";
    private static final String COL_4 = "SUM";
    private static final String COL_5 = "TYPE";
    private static final String COL_6 = "DATE";// New column for transaction type

    public DatabaseHelperTransactions(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TRANSACTION_NAME TEXT, CATEGORY TEXT, SUM REAL, TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean addTransaction(String transactionName,String category, double sum,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, transactionName);
        contentValues.put(COL_3, category);
        contentValues.put(COL_4, sum);
        contentValues.put(COL_5, type);


        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public boolean updateTransaction(int id, String transactionName,  double sum, String category, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, transactionName);
        contentValues.put(COL_3, category);
        contentValues.put(COL_4, sum);
        contentValues.put(COL_5, type);
        int result = db.update(TABLE_NAME, contentValues, COL_1 + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // Method to delete a transaction
    public boolean deleteTransaction(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COL_1 + " = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Cursor cursor = getAllData();

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(COL_1);
            int nameIndex = cursor.getColumnIndex(COL_2);
            int sumIndex = cursor.getColumnIndex(COL_4);
            int categoryIndex = cursor.getColumnIndex(COL_3);
            int typeIndex = cursor.getColumnIndex(COL_5);
            int dateIndex = cursor.getColumnIndex(COL_6);

            if (idIndex != -1 && nameIndex != -1 && sumIndex != -1 && categoryIndex != -1 && typeIndex != -1) {
                Transaction transaction = new Transaction(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),

                        String.valueOf(cursor.getDouble(sumIndex)),
                        cursor.getString(categoryIndex),
                        cursor.getString(typeIndex)
                );
                transactions.add(transaction);
            }
        }

        cursor.close();
        return transactions;
    }
    public Transaction getTransaction(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_1 + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Transaction transaction = new Transaction(
                cursor.getInt(0), // Assuming the ID is at index 0
                cursor.getString(1), // Assuming the TRANSACTION_NAME is at index 1
                cursor.getString(2), // Assuming the CATEGORY is at index 2
                cursor.getString(3), // Assuming the SUM is at index 3
                cursor.getString(4) // Assuming the TYPE is at index 4
        );

        cursor.close();
        return transaction;
    }
}
