package net.vxinwen.db.dao;

import java.util.ArrayList;
import java.util.List;

import net.vxinwen.bean.Category;
import net.vxinwen.db.DBOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class CategoryDao {
	private final static String TABLE = "category";

	public List<Category> getAll(Context context) {
		SQLiteDatabase db = new DBOpenHelper(context).getReadableDatabase();
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		List<Category> list = new ArrayList<Category>();
		if (cursor.moveToFirst()) {
			do {
				long id = cursor.getLong(0);
				String name = cursor.getString(1);
				Category cate = new Category();
				cate.setId(id);
				cate.setName(name);

			} while (cursor.moveToNext());
		}
		return list;
	}

	public List<String> getAllNames(Context context) {
		SQLiteDatabase db = new DBOpenHelper(context).getReadableDatabase();
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		List<String> list = new ArrayList<String>();
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(1);
				Category cate = new Category();
				cate.setName(name);

			} while (cursor.moveToNext());
		}
		return list;
	}

	public boolean insert(Context context, Category category) {
		if (category == null || category.getName() == null)
			return false;
		SQLiteDatabase db = new DBOpenHelper(context).getWritableDatabase();
		db.beginTransaction();
		ContentValues values = new ContentValues();
		values.put("name", category.getName());
		long id = db.insert(TABLE, null, values);
		db.endTransaction();
		db.close();
		return id >= 0;
	}
}
