import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText editTextLiczbaKostek;
    Button buttonLosuj;
    Button buttonRosnaco;
    Button buttonMalejaco;
    GridLayout gridKostki;
    TextView textViewWyniki;
    ArrayList<Integer> wyniki = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);
        buttonLosuj.setOnClickListener(v -> losujKostki());
        buttonRosnaco.setOnClickListener(v -> sortujRosnaco());
        buttonMalejaco.setOnClickListener(v -> sortujMalejaco());
    }
    private void losujKostki() {
        String tekst = editTextLiczbaKostek.getText().toString().trim();
        if (tekst.isEmpty()) {
            Toast.makeText(this, "Podaj liczbę kostek!", Toast.LENGTH_SHORT).show();
            return;
        }

        int liczba;

        try {
            liczba = Integer.parseInt(tekst);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Podaj prawidłową liczbę całkowitą!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba <= 0 || liczba > 100) {
            Toast.makeText(this, "Liczba kostek musi być od 1 do 100!", Toast.LENGTH_SHORT).show();
            return;
        }

        wyniki.clear();

        for (int i = 0; i < liczba; i++) {
            int wynik = random.nextInt(6) + 1;
            wyniki.add(wynik);
        }
        odswiezWidok();
    }

    private void sortujRosnaco() {

        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
            return;
        }

        int n = wyniki.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (wyniki.get(j) > wyniki.get(j + 1)) {
                    int temp = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, temp);
                }
            }
        }

        odswiezWidok();
    }

    private void sortujMalejaco() {
        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
            return;
        }

        int n = wyniki.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (wyniki.get(j) < wyniki.get(j + 1)) {
                    int temp = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, temp);
                }
            }
        }

        odswiezWidok();
    }

    private void odswiezWidok() {
        StringBuilder tekstWynikow = new StringBuilder();
        for (int i = 0; i < wyniki.size(); i++) {
            tekstWynikow.append(wyniki.get(i));
            if (i < wyniki.size() - 1) {
                tekstWynikow.append(", ");
            }
        }

        textViewWyniki.setText(tekstWynikow.toString());
        gridKostki.removeAllViews();
        gridKostki.setColumnCount(5);

        for (int wynik : wyniki) {
            TextView kostka = new TextView(this);
            kostka.setText(String.valueOf(wynik));
            kostka.setTextSize(24);
            kostka.setTextColor(Color.BLACK);
            kostka.setGravity(Gravity.CENTER);
            kostka.setBackgroundColor(Color.LTGRAY);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 110;
            params.height = 110;
            params.setMargins(5, 5, 5, 5);
            kostka.setLayoutParams(params);
            gridKostki.addView(kostka);
        }
    }
}
