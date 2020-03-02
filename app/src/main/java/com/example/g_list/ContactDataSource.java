package com.example.g_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ContactDataSource {
    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    public ContactDataSource (Context context){
        dbHelper = new ContactDBHelper(context);
    }

    int listCount = 0;

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    //SQL METHODS
    public boolean addFood(String groceryStore, String productName, Bitmap photo){
        boolean success;
        try{
            ContentValues initialValues = new ContentValues();
            initialValues.put("product_name", productName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] picture = baos.toByteArray();
            initialValues.put("product_photo", picture);

            success = database.insert(groceryStore, null, initialValues) > 0;
        }
        catch(Exception e){
            success = false;
        }

        return success;
    }

    public ArrayList<Product> getProducts(String groceryStore){
        ArrayList<Product> allProducts = new ArrayList<>();
        try{
            if(!groceryStore.equals("kroger") && !groceryStore.equals("publix")){
                Log.w(TAG, "getProducts: grocery store was not found");
                throw new Exception();
            }
            String query = "SELECT * FROM " + groceryStore; //Sorting Can Go Here when that part is done
            Cursor cursor = database.rawQuery(query, null);
            Product product;
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                product = new Product();
                product.setProductID(cursor.getInt(0));
                product.setProductName(cursor.getString(1));
                byte[] photo = cursor.getBlob(2);
                if(photo != null){
                    ByteArrayInputStream is = new ByteArrayInputStream(photo);
                    Bitmap picture = BitmapFactory.decodeStream(is);
                    product.setProductPhoto(picture);
                }
            }
            cursor.close();
        }
        catch(Exception e){
            allProducts = new ArrayList<>(); //If array is empty
        }
        return allProducts;
    }


    public Product getSpecificProduct(int product_Id, String groceryStore) {
        Product product = new Product();
        String query = "SELECT * FROM " + groceryStore + " WHERE _id =" + product_Id;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            product.setProductName(cursor.getString(1));
            byte[] photo = cursor.getBlob(2);
            if(photo != null){
                ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
                Bitmap thePicture = BitmapFactory.decodeStream(imageStream);
                product.setProductPhoto(thePicture);
            }
            cursor.close();
        }
        return product;
    }

    public Boolean createNewList() {
        boolean isSuccessful;
        try {
            String query = "CREATE TABLE list" + listCount + " (list_id integer primary key autoincrement, product_name text not null, product_photo blob, quantity integer default 0);";
            listCount++;
            database.execSQL(query);
            isSuccessful = true;
        } catch (Exception e) {
            isSuccessful = false;
        }
        return isSuccessful;
    }


    public boolean addItemToList(int item){
        boolean isSuccesful = false;


        return isSuccesful;
    }

}
