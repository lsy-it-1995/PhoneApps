package com.example.bookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";
    private ImageView bookImage;
    private Button currentlyRead, wantRead, alreadyRead, addFavourite;
    private TextView bookName, authorName, pageNumber, longDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();

        Intent intent = getIntent();
        if(intent != null){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(incomingBook != null){
                    setData(incomingBook);
                    headleAlreadyRead(incomingBook);
                    handleWantToReadBook(incomingBook);
                    handleCurrentReadBook(incomingBook);
                    handleFavoriteBook(incomingBook);
                }
            }
        }

    }
    private void handleWantToReadBook(final Book book){
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantReadBook();
        boolean existRead = false;
        for(Book b: wantToReadBooks){
            if(b.getId() == book.getId()){
                existRead = true;
            }
        }
        if(existRead){
            wantRead.setEnabled(false);
        }else{
            wantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).AddToWantRead(book)){
                        Toast.makeText(BookActivity.this, "add to library", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, waitlistActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "sth wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void handleCurrentReadBook(Book book){
        ArrayList<Book> currentReadBook = Utils.getInstance(this).getCurrentlyReading();
        boolean existRead = false;
        for(Book b: currentReadBook){
            if(b.getId() == book.getId()){
                existRead = true;
            }
        }
        if(existRead){
            currentlyRead.setEnabled(false);
        }else{
            currentlyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).AddToCurrentRead(book)){
                        Toast.makeText(BookActivity.this, "Work", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, currentReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Not Work", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void handleFavoriteBook(Book book){
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavouriteBook();
        boolean existRead = false;
        for(Book b: favoriteBooks){
            if(b.getId() == book.getId()){
                existRead = true;
            }
        }
        if(existRead){
            addFavourite.setEnabled(false);
        }else{
            addFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFavoriteRead(book)){
                        Toast.makeText(BookActivity.this, "Work", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, favouriteActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Not Work", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void headleAlreadyRead(Book book){
        ArrayList<Book> alreadyReadBookList = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existRead = false;
        for(Book b: alreadyReadBookList){
            if(b.getId() == book.getId()){
                existRead = true;
            }
        }
        if(existRead){
            alreadyRead.setEnabled(false);
        }else{
            alreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Works", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, finishedBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Not Works", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void initView(){
        bookImage = findViewById(R.id.book_image);
        currentlyRead = findViewById(R.id.btn_CurrentlyReading);
        wantRead = findViewById(R.id.btnWantRead);
        alreadyRead = findViewById(R.id.btnAlreadyRead);
        addFavourite = findViewById(R.id.btnAddFavorite);

        bookName = findViewById(R.id.txtBookName);
        authorName = findViewById(R.id.txtAuthorName);
        pageNumber = findViewById(R.id.txtPage);
        longDescription = findViewById(R.id.txtLongDescription);
    }
    private void setData(Book book){
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        pageNumber.setText(String.valueOf(book.getPage()));
        longDescription.setText(book.getLongDescription());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl()).into(bookImage);
    }
}