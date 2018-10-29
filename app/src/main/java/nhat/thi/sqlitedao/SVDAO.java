package nhat.thi.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nhat.thi.database.DatabaseHelper;
import nhat.thi.model.Sinhvien;

public class SVDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "Book";
    public static final String SQL_BOOK = "CREATE TABLE Book (idbook text primary key, name text, price1 double, price2 double );";
    public static final String TAG = "BookDAO";

    public SVDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    //insert
    public int insertBook(Sinhvien book) {
        ContentValues values = new ContentValues();
        values.put("idbook", book.getMaSV());
        values.put("name", book.getMaLop());
        values.put("price1", book.getDiemtoan());
        values.put("price2", book.getDiemtoan());

        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public List<Sinhvien> getAllBook() {
        List<Sinhvien> bookList = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Sinhvien ee = new Sinhvien();
            ee.setMaSV(c.getString(0));
            ee.setMaLop(c.getString(1));
            ee.setDiemtoan(c.getDouble(2));
            ee.setDiemVan(c.getDouble(3));
            bookList.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return bookList;
    }

    public Sinhvien getBook(String idbook) {
        List<Sinhvien> bookList = new ArrayList<>();
        Sinhvien ee = new Sinhvien();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {

            ee.setMaSV(c.getString(0));
            ee.setMaLop(c.getString(1));
            ee.setDiemtoan(c.getDouble(2));
            ee.setDiemVan(c.getDouble(3));

            bookList.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return ee;
    }

    public int updateBook(String idBook, String Name, String Price) {
        ContentValues values = new ContentValues();
        values.put("idbook", idBook);
        values.put("name", Name);
        values.put("price", Price);

        int result = db.update(TABLE_NAME, values, "idbook=?", new
                String[]{idBook});
        if (result == 0) {
            return -1;
        }
        return 1;

    }


    //delete
    public int deleteTypeBookByID(String idbook) {
        int result = db.delete(TABLE_NAME, "idbook=?", new String[]{idbook});
        if (result == 0)
            return -1;
        return 1;
    }


}
