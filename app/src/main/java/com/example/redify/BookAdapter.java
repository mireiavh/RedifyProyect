package com.example.redify;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> bookList;
    private OnBookClickListener listener;

    public BookAdapter(ArrayList<Book> bookList, OnBookClickListener listener) {
        this.bookList = bookList;
        this.listener = listener;
    }

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);
            return new BookViewHolder(view);
        } catch (Exception e) {
            Log.e("BookAdapter", "Error al inflar el layout del BookViewHolder", e);
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        try {
            Book book = bookList.get(position);

            holder.tvTitle.setText(book.getTitle());
            holder.tvAuthor.setText(book.getAuthor());
            holder.tvDate.setText(book.getDate());
            holder.tvDescription.setText(book.getDescription());

            holder.ivImage.setImageResource(book.getImage());

            if (book.isFavorite()) {
                holder.imbtn_favorite.setImageResource(R.drawable.like);
            } else {
                holder.imbtn_favorite.setImageResource(R.drawable.like_blank);
            }

            holder.btn_readmore.setOnClickListener(v -> {
                try {
                    Toast.makeText(v.getContext(), "Función de proxima actualización", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("BookAdapter", "Error al manejar el clic en el botón de favoritos", e);
                }
            });

            holder.imbtn_favorite.setOnClickListener(v -> {
                try {
                    book.setFavorite(!book.isFavorite());
                    notifyItemChanged(position);

                    if (book.isFavorite()) {
                        Toast.makeText(v.getContext(), book.getTitle() + " ha sido agregado a favoritos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), book.getTitle() + " ha sido eliminado de favoritos", Toast.LENGTH_SHORT).show();
                    }

                    listener.onBookClick(book);
                } catch (Exception e) {
                    Log.e("BookAdapter", "Error al manejar el clic en el botón de favoritos", e);
                }
            });

            holder.itemView.setOnClickListener(v -> {
                try {
                    if (listener != null) {
                        listener.onBookClick(book);
                    }
                } catch (Exception e) {
                    Log.e("BookAdapter", "Error al manejar el clic en el item", e);
                }
            });
        } catch (Exception e) {
            Log.e("BookAdapter", "Error al enlazar los datos del libro al ViewHolder", e);
        }
    }

    @Override
    public int getItemCount() {
        try {
            return bookList.size();
        } catch (Exception e) {
            Log.e("BookAdapter", "Error al obtener el tamaño de la lista de libros", e);
            return 0;
        }
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvDate;
        TextView tvDescription;
        ImageView ivImage;
        ImageButton imbtn_favorite;
        Button btn_readmore;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            try {
                tvTitle = itemView.findViewById(R.id.tv_title);
                tvAuthor = itemView.findViewById(R.id.tv_author);
                tvDate = itemView.findViewById(R.id.tv_date);
                tvDescription = itemView.findViewById(R.id.tv_description);
                ivImage = itemView.findViewById(R.id.im_bc_vertical);
                imbtn_favorite = itemView.findViewById(R.id.imbtn_favorite);
                btn_readmore = itemView.findViewById(R.id.btn_readmore);
            } catch (Exception e) {
                Log.e("BookAdapter", "Error al inicializar las vistas en BookViewHolder", e);
            }
        }
    }
}
