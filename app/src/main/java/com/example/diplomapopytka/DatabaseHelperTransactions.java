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
    //private static final String COL_2 = "TRANSACTION_NAME";
    private static final String COL_3 = "CATEGORY";
    private static final String COL_4 = "SUM";
    private static final String COL_5 = "TYPE"; // New column for transaction type

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

    public boolean addTransaction(String category, double sum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, category);
        contentValues.put(COL_4, sum);
        //contentValues.put(COL_5, type);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Cursor cursor = getAllData();

        while (cursor.moveToNext()) {
            int sumIndex = cursor.getColumnIndex(COL_4);
            int categoryIndex = cursor.getColumnIndex(COL_3);
            int typeIndex = cursor.getColumnIndex(COL_5);

            if (sumIndex != -1 && categoryIndex != -1 && typeIndex != -1) {
                Transaction transaction = new Transaction(
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
}
