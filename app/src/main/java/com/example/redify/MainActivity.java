package com.example.redify;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BookAdapter.OnBookClickListener{

    private TextView detailTitle;
    private TextView detailAuthor;
    private TextView detailDate;
    private TextView detailDescription;
    private RecyclerView rvList;
    private RecyclerView rvListHorizontal;
    private RecyclerView rvFavorite;
    private Button button;
    private ToggleButton tbFavorites;
    private ToggleButton tbrecomed;
    private CardView cardView;
    private CardView favoriteCard;

    private ArrayList<Book> bookList;
    private ArrayList<Book> favoriteBooksList;
    private ArrayList<Book> newnessBooksList;
    private BookAdapter bookAdapter;
    private BookAdapter bookAdapterFavorite;
    private BookAdapter bookAdapterNewness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            bookList = new ArrayList<>(Arrays.asList(new Book[] {
                    new Book("Cien años de soledad", "Gabriel García Márquez", "1967", R.drawable.cien_anos_de_soledad, "La obra más conocida de García Márquez, que narra la historia de la familia Buendía en el mítico pueblo de Macondo.", true, false, 4),
                    new Book("1984", "George Orwell", "1949", R.drawable.t1984, "Un futuro distópico donde un régimen totalitario controla todos los aspectos de la vida de los ciudadanos.", true, false, 4),
                    new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", R.drawable.the_great_gatsby, "La historia de Jay Gatsby, un hombre que busca recuperar el amor perdido en la sociedad estadounidense de los años 20.", false, false, 4),
                    new Book("Moby-Dick", "Herman Melville", "1851", R.drawable.moby_dick, "La travesía de Ishmael y el obsesivo Capitán Ahab en busca de la ballena blanca.", false, false, 4),
                    new Book("Pride and Prejudice", "Jane Austen", "1813", R.drawable.pride_and_prejudice, "Un relato sobre la clase social, el amor y los prejuicios en la Inglaterra del siglo XIX.", false, false, 4),
                    new Book("The Catcher in the Rye", "J.D. Salinger", "1951", R.drawable.the_catcher_in_the_ye, "La historia de Holden Caulfield, un joven rebelde que lucha por encontrar su lugar en la sociedad.", false, true, 4),
                    new Book("To Kill a Mockingbird", "Harper Lee", "1960", R.drawable.to_ill_a_mockingbird, "Una conmovedora historia sobre la injusticia racial en el sur de Estados Unidos.", false, false, 4),
                    new Book("Brave New World", "Aldous Huxley", "1932", R.drawable.brave_new_world, "En un mundo controlado por el consumo y la tecnología, la humanidad busca la felicidad a cualquier precio.", false, false, 4),
                    new Book("The Hobbit", "J.R.R. Tolkien", "1937", R.drawable.the_hobbit, "La aventura de Bilbo Bolsón en la Tierra Media, donde se enfrenta a dragones y encuentra tesoros.", false, false, 4),
                    new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "1997", R.drawable.harry, "El joven Harry Potter descubre que es un mago y empieza su educación en la escuela Hogwarts.", true, true, 4),
                    new Book("The Alchemist", "Paulo Coelho", "1988", R.drawable.the_alchemist, "La historia de un joven pastor que persigue su sueño de encontrar un tesoro en Egipto.", false, false, 4),
                    new Book("The Road", "Cormac McCarthy", "2006", R.drawable.the_road, "Una novela post-apocalíptica que sigue a un padre y su hijo mientras luchan por sobrevivir.", false, false, 4),
                    new Book("Frankenstein", "Mary Shelley", "1818", R.drawable.frankenstein, "La historia del científico Víctor Frankenstein y su monstruosa creación.", false, false, 4),
                    new Book("Wuthering Heights", "Emily Brontë", "1847", R.drawable.wuthering_heights, "Una historia de pasión, venganza y tormento en la agreste campiña inglesa.", false, false, 4),
                    new Book("The Picture of Dorian Gray", "Oscar Wilde", "1890", R.drawable.the_picture_of_dorian_gray, "Dorian Gray, un joven que mantiene su juventud mientras su retrato envejece y refleja sus pecados.", true, true, 4),
                    new Book("The Odyssey", "Homer", "700 BC", R.drawable.the_odyssey, "La épica aventura de Odiseo mientras regresa a su hogar después de la guerra de Troya.", false, false, 4),
                    new Book("Les Misérables", "Victor Hugo", "1862", R.drawable.les_miserables, "La lucha de Jean Valjean por redimir su vida en la Francia del siglo XIX.", false, true, 4),
                    new Book("Dracula", "Bram Stoker", "1897", R.drawable.dracula, "La famosa historia del conde Drácula y su intento de conquistar Londres.", false, true, 4),
                    new Book("The Shining", "Stephen King", "1977", R.drawable.the_shining, "Jack Torrance se convierte en el cuidador de un hotel aislado donde la locura y lo sobrenatural acechan.", false, true, 4),
                    new Book("The Martian", "Andy Weir", "2011", R.drawable.the_martian, "La lucha de Mark Watney por sobrevivir en Marte después de ser abandonado por su equipo.", false, true, 4)
            }));
        } catch (Exception e) {
            Log.e("MainActivity", "Error al inicializar la lista de libros", e);
        }

        try {
            favoriteBooksList = new ArrayList<>();
            for (Book book : bookList) {
                if (book.isFavorite()) {
                    favoriteBooksList.add(book);
                }
            }

            newnessBooksList = new ArrayList<>();
            for (Book book : bookList) {
                if (book.isNewness()) {
                    newnessBooksList.add(book);
                }
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error al filtrar libros favoritos o nuevos", e);
        }

        try {
            rvList = findViewById(R.id.rv);
            rvListHorizontal = findViewById(R.id.rvHorizontal);
            rvFavorite = findViewById(R.id.rvFavorite);

            cardView = findViewById(R.id.detailCardView);
            favoriteCard = findViewById(R.id.favoriteCard);
            detailTitle = findViewById(R.id.detail_title);
            detailAuthor = findViewById(R.id.detail_author);
            detailDate = findViewById(R.id.detail_date);
            detailDescription = findViewById(R.id.detail_description);

            rvList.setLayoutManager(new LinearLayoutManager(this));
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rvListHorizontal.setLayoutManager(layoutManager);
            rvFavorite.setLayoutManager(new LinearLayoutManager(this));
        } catch (Exception e) {
            Log.e("MainActivity", "Error al inicializar los RecyclerViews o CardViews", e);
        }

        try {
            bookAdapterFavorite = new BookAdapter(favoriteBooksList, this);
            bookAdapterNewness = new BookAdapter(newnessBooksList, this);
            bookAdapter = new BookAdapter(bookList, this);
            rvFavorite.setAdapter(bookAdapterFavorite);
            rvList.setAdapter(bookAdapter);
            rvListHorizontal.setAdapter(bookAdapterNewness);
        } catch (Exception e) {
            Log.e("MainActivity", "Error al configurar los adaptadores de los RecyclerViews", e);
        }

        closeCardView();
        openFavoriteBook();
        openNewnessBook();
    }

    @Override
    public void onBookClick(Book book) {
        try {
            detailTitle.setText(book.getTitle());
            detailAuthor.setText(book.getAuthor());
            detailDate.setText(book.getDate());
            detailDescription.setText(book.getDescription());

            if (book.isFavorite()) {
                if (!favoriteBooksList.contains(book)) {
                    favoriteBooksList.add(book);
                }
            } else {
                favoriteBooksList.remove(book);
            }

            bookAdapterFavorite.notifyDataSetChanged();
            bookAdapter.notifyDataSetChanged();

            cardView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Log.e("MainActivity", "Error al manejar el clic en el libro", e);
        }
    }

    public void closeCardView() {
        try {
            button = findViewById(R.id.detail_button);
            button.setOnClickListener(view -> cardView.setVisibility(View.INVISIBLE));
        } catch (Exception e) {
            Log.e("MainActivity", "Error al configurar el botón de cerrar cardView", e);
        }
    }

    public void openFavoriteBook() {
        try {
            tbFavorites = findViewById(R.id.toggleButton2);
            tbFavorites.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    favoriteCard.setVisibility(View.VISIBLE);
                } else {
                    favoriteCard.setVisibility(View.INVISIBLE);
                }
            });
        } catch (Exception e) {
            Log.e("MainActivity", "Error al configurar el ToggleButton de favoritos", e);
        }
    }

    public void openNewnessBook() {
        try {
            tbrecomed = findViewById(R.id.tb_recomend);
            tbrecomed.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (!isChecked) {
                    Toast.makeText(this, "Función de proxima actualización", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("MainActivity", "Error al configurar el ToggleButton de nuevos libros", e);
        }
    }


}