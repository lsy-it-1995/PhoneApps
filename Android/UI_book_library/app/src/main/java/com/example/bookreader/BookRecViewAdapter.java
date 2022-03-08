package com.example.bookreader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.bookreader.BookActivity.BOOK_ID_KEY;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList();
    private Context mContext;
    private String parentActivity;

    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called");
        holder.txtbook.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgbook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        holder.authortxt.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDescription());

        if(books.get(position).isExpand()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
            if(parentActivity.equals("allBook")){
                holder.btnDelete.setVisibility(View.GONE);
            }else if(parentActivity.equals("alreadyRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure" + books.get(position).getName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromALreadyRead(books.get(position))){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("wantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure" + books.get(position).getName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromWantToRead(books.get(position))){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("CurrentlyReading")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure" + books.get(position).getName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromCurrentRead(books.get(position))){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("favouriteBook")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure" + books.get(position).getName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeFromFavouriteRead(books.get(position))){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
        }else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgbook;
        private TextView txtbook;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandLayout;
        private TextView authortxt, txtDescription;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgbook = itemView.findViewById(R.id.imgBook);
            txtbook = itemView.findViewById(R.id.nameBook);
            downArrow = itemView.findViewById(R.id.downArrow);
            upArrow = itemView.findViewById(R.id.upArrow);
            expandLayout = itemView.findViewById(R.id.expandRelativeLayout);
            authortxt = itemView.findViewById(R.id.AuthorName);
            txtDescription = itemView.findViewById(R.id.shortDescriptionTxt);
            btnDelete = itemView.findViewById(R.id.deleteBtn);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpand(!book.isExpand());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpand(!book.isExpand());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    };
}
