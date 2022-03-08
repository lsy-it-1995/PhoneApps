package com.example.bookreader;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static Utils instance;

    private SharedPreferences sharedPreferences;
    private static final String ALL_BOOK_KEY = "allBook",
                                ALREADYREADBOOK = "alreadyRead",
                                WANTREADBOOK = "wantRead",
                                CURRENTREAD = "currentReading",
                                FAVOURITEBOOK = "fav_book";
    private Utils(Context context){
        sharedPreferences = context.getSharedPreferences("alternate_DB", Context.MODE_PRIVATE);

        if(getAllbooks() == null){
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(getAlreadyReadBooks() == null){
            editor.putString(ALREADYREADBOOK, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getWantReadBook() == null){
            editor.putString(WANTREADBOOK, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getCurrentlyReading() == null){
            editor.putString(CURRENTREAD, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getFavouriteBook() == null){
            editor.putString(FAVOURITEBOOK, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData(){
        ArrayList<Book> books = new ArrayList<>();

        //todo: add data
        books.add(new Book(1, 106,"When Breath Becomes Air", "Paul Kalanithi",
                "data:image/jpeg;base64,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",
                "short description", "long description"));
        books.add(new Book(2, 197,"The Alchemist", "Paulo Coelho",
                "https://images-na.ssl-images-amazon.com/images/I/41ybG235TcL._SX329_BO1,204,203,200_.jpg",
                "short description", "long description"));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOK_KEY, gson.toJson(books));
        editor.commit();
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllbooks();
        if(books == null){
            return null;
        }
        for(Book b: books){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public static Utils getInstance(Context context){
        if(instance != null){
            return instance;
        }
        return new Utils(context);
    }

    public ArrayList<Book> getAllbooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOK_KEY, null),type);
        return books;
    }
    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADYREADBOOK, null),type);
        return books;
    }
    public ArrayList<Book> getWantReadBook() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANTREADBOOK, null),type);
        return books;
    }
    public ArrayList<Book> getCurrentlyReading() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTREAD, null),type);
        return books;
    }
    public ArrayList<Book> getFavouriteBook() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITEBOOK, null),type);
        return books;
    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADYREADBOOK);
                editor.putString(ALREADYREADBOOK, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean AddToWantRead(Book book){
        ArrayList<Book> books = getWantReadBook();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANTREADBOOK);
                editor.putString(WANTREADBOOK, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean AddToCurrentRead(Book book){
        ArrayList<Book> books = getCurrentlyReading();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTREAD);
                editor.putString(CURRENTREAD, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToFavoriteRead(Book book){
        ArrayList<Book> books = getFavouriteBook();
        if(null != books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITEBOOK);
                editor.putString(FAVOURITEBOOK, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromALreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    books.remove(b);
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(ALREADYREADBOOK);
                    editor.putString(ALREADYREADBOOK, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantReadBook();
        if(null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    books.remove(b);
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(WANTREADBOOK);
                    editor.putString(WANTREADBOOK, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeFromFavouriteRead(Book book){
        ArrayList<Book> books = getFavouriteBook();
        if(null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    books.remove(b);
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(FAVOURITEBOOK);
                    editor.putString(FAVOURITEBOOK, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeFromCurrentRead(Book book){
        ArrayList<Book> books = getCurrentlyReading();
        if(null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    books.remove(b);
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(CURRENTREAD);
                    editor.putString(CURRENTREAD, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
}
