package br.edu.fateczl.consultoria_informatica.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.edu.fateczl.consultoria_informatica.R;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            loadFragment(bundle);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutFragment, new InitialPageFragment());
            fragmentTransaction.commit();
        }
    }

    private void loadFragment(Bundle bundle) {
        String itemType = bundle.getString("type");

        assert itemType != null;
        switch (itemType) {
            case "author":
                fragment = new AutorFragment();
                break;
            case "client":
                fragment = new ClienteFragment();
                break;
            case "contract":
                fragment = new ContratoFragment();
                break;
            default:
                fragment = new InitialPageFragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

        if (id == R.id.itemAuthor) {
            bundle.putString("type", "author");
        } else if (id == R.id.itemClient) {
            bundle.putString("type", "client");
        } else if (id == R.id.itemContract) {
            bundle.putString("type", "contract");
        } else {
            bundle.putString("type", "initial");
        }

        intent.putExtras(bundle);
        this.startActivity(intent);
        this.finish();
        return true;
    }
}
