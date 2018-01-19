package com.m2i.firebaserealtimedb;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m2i.firebaserealtimedb.model.Author;
import com.m2i.firebaserealtimedb.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference bookReference;
    private ListView bookListView;
    private List<Book> bookList = new ArrayList<>();
    private bookArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        bookReference = firebaseDatabase.getReference().child("Books");
       // addBooks();
     //   bookListView = findViewById(R.id.bookListView);
        adapter = new bookArrayAdapter(this, R.layout.book_list_item, bookList);
        bookListView.setAdapter(adapter);
        // Récupération des données avec abonnement aux modifications ultérieures
        bookReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Réinitialisation de la liste des livres
                bookList.clear();
                // Boucle sur l'ensemble des noeuds
                for (DataSnapshot bookSnapShot : dataSnapshot.getChildren()){
                    //création d'une instance de book et hydratation  avec les données du snapshot
                    Book book = bookSnapShot.getValue(Book.class);
                    // ajout du livre à la liste
                    bookList.add(book);
                }
                Log.d("Main", "Fin de récupération des données");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("Main", "Fin de on Create");

    }

    private void addBooks() {
        // création d'un livre

        Author hugo = new Author("Hugo","Victore","Français");
        Author auster = new Author("Auster","Paul","Americain");

        String bookId = bookReference.push().getKey();
        Book book = new Book("Les misérables",12.0,hugo);
        bookReference.child(bookId).setValue(book);

        bookId = bookReference.push().getKey();
        book = new Book("Ruy Blas",11.0,hugo);
        bookReference.child(bookId).setValue(book);

        bookId = bookReference.push().getKey();
        book = new Book("New York",10.0,auster);
        bookReference.child(bookId).setValue(book);
    }

    private class bookArrayAdapter extends ArrayAdapter<Book> {
        private Activity context;
        int resource;
        List<Book> data;


        public bookArrayAdapter(Activity context, int resource, List<Book> data) {
            super(context, resource, data);
            this.context = context;
            this.resource = resource;
            this.data = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = context.getLayoutInflater().inflate(this.resource,parent,false);

            Book currentBook = bookList.get(position);
            TextView textView = view.findViewById(R.id.bookListText);
            textView.setText(
                    currentBook.getTitle()
                    + " par "
                    + currentBook.getAuthor().getName()
            );
            return view;
        }
    }

    ;

}
