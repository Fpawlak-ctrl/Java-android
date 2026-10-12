package com.example.myapplication;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText imie = findViewById(R.id.imie);
    EditText nazwisko = findViewById(R.id.nazwisko);
    EditText Email = findViewById(R.id.email);
    EditText haslo = findViewById(R.id.haslo);
    Button przycisk = findViewById(R.id.rejestracaj);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        przycisk.setOnClickListener(v -> {
            if (!checkValues()) {
                return;
            }

            if (!checkEmail()) {
                return;
            }

            if (!checkPassword()) {
                return;
            }

            Toast.makeText(
                    this,
                    "Dane są poprawne!",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    private boolean checkValues(){
        String name = imie.getText().toString().trim();
        String surname = nazwisko.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = haslo.getText().toString().trim();

        if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(
                    this,
                    "Wypełnij Dane ",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }
        return true;
    }

    private boolean checkEmail(){
        String email = Email.getText().toString().trim();
        if(email.contains("@") || email.contains(".")){
            Toast.makeText(
                    this,
                    "Wpisz poprawny email!",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }
        return true;
    }

    private boolean checkPassword(){
        String password = haslo.getText().toString().trim();

        if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8}$")){
            return true;
        };
        return false;
    }

    private void saveToFile(){
        String name = imie.getText().toString().trim();
        String surname = nazwisko.getText().toString().trim();
        String email = Email.getText().toString().trim();

        String text = "Imie: " + name + "\n" + "Nazwisko: " + surname + "\n" + "Email: " + Email;
    try{
        FileOutputStream fos = openFileOutput(
                "dane.txt",
                MODE_APPEND
        );
    fos.write(text.getBytes());
    fos.close();
    } catch(Exception e){
        e.printStackTrace();
        Toast.makeText(
                this,
                "błąd podczas zapisywania",
                Toast.LENGTH_SHORT
        ).show();
    }


    };
}
