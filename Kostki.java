package com.example.myapplication;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextLiczbaKostek;
    Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    TextView textViewWyniki;
    LinearLayout linearLayoutKostki;

    ArrayList<Integer> wyniki = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextLiczbaKostek =
                findViewById(R.id.editTextLiczbaKostek);

        buttonLosuj =
                findViewById(R.id.buttonLosuj);

        buttonRosnaco =
                findViewById(R.id.buttonRosnaco);

        buttonMalejaco =
                findViewById(R.id.buttonMalejaco);

        textViewWyniki =
                findViewById(R.id.textViewWyniki);

        linearLayoutKostki =
                findViewById(R.id.linearLayoutKostki);


        buttonLosuj.setOnClickListener(v -> losuj());

        buttonRosnaco.setOnClickListener(v -> sortujRosnaco());

        buttonMalejaco.setOnClickListener(v -> sortujMalejaco());
    }


    private void losuj() {

        String tekst = editTextLiczbaKostek.getText()
                .toString().trim();

        if (tekst.isEmpty()) {
            Toast.makeText(this,
                    "Wpisz liczbe kostek!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int liczba;

        try {
            liczba = Integer.parseInt(tekst);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Nieprawidlowe dane!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba < 1 || liczba > 100) {
            Toast.makeText(this,
                    "Podaj liczbe od 1 do 100!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        wyniki.clear();

        for (int i = 0; i < liczba; i++) {
            wyniki.add(random.nextInt(6) + 1);
        }

        wyswietlWyniki();
    }


    private void sortujRosnaco() {

        if (wyniki.isEmpty()) {
            Toast.makeText(this,
                    "Najpierw wylosuj kostki!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < wyniki.size() - 1; i++) {

            for (int j = 0; j < wyniki.size() - i - 1; j++) {

                if (wyniki.get(j) > wyniki.get(j + 1)) {

                    int temp = wyniki.get(j);

                    wyniki.set(j, wyniki.get(j + 1));

                    wyniki.set(j + 1, temp);
                }
            }
        }

        wyswietlWyniki();
    }


    private void sortujMalejaco() {

        if (wyniki.isEmpty()) {
            Toast.makeText(this,
                    "Najpierw wylosuj kostki!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < wyniki.size() - 1; i++) {

            for (int j = 0; j < wyniki.size() - i - 1; j++) {

                if (wyniki.get(j) < wyniki.get(j + 1)) {

                    int temp = wyniki.get(j);

                    wyniki.set(j, wyniki.get(j + 1));

                    wyniki.set(j + 1, temp);
                }
            }
        }

        wyswietlWyniki();
    }


    private void wyswietlWyniki() {

        StringBuilder tekst = new StringBuilder("Wyniki: ");

        for (int i = 0; i < wyniki.size(); i++) {

            tekst.append(wyniki.get(i));

            if (i < wyniki.size() - 1) {
                tekst.append(", ");
            }
        }

        textViewWyniki.setText(tekst.toString());


        linearLayoutKostki.removeAllViews();


        LinearLayout wiersz = null;

        for (int i = 0; i < wyniki.size(); i++) {



            if (i % 5 == 0) {

                wiersz = new LinearLayout(this);

                wiersz.setOrientation(
                        LinearLayout.HORIZONTAL);

                wiersz.setGravity(Gravity.CENTER);

                linearLayoutKostki.addView(wiersz);
            }


            TextView kostka = new TextView(this);

            kostka.setText(String.valueOf(wyniki.get(i)));

            kostka.setTextSize(24);

            kostka.setTextColor(Color.BLACK);

            kostka.setTypeface(null, Typeface.BOLD);

            kostka.setGravity(Gravity.CENTER);


            GradientDrawable tlo = new GradientDrawable();

            tlo.setColor(Color.WHITE);

            tlo.setCornerRadius(dp(10));

            tlo.setStroke(dp(2), Color.BLACK);

            kostka.setBackground(tlo);


            LinearLayout.LayoutParams parametry =
                    new LinearLayout.LayoutParams(
                            dp(50),
                            dp(50));

            parametry.setMargins(
                    dp(5), dp(5), dp(5), dp(5));

            kostka.setLayoutParams(parametry);



            wiersz.addView(kostka);
        }
    }

    private int dp(int wartosc) {

        return (int) (wartosc *
                getResources().getDisplayMetrics().density
                + 0.5f);
    }
}
