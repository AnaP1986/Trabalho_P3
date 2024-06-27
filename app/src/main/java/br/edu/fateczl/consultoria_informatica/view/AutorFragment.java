package br.edu.fateczl.consultoria_informatica.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.R;
import br.edu.fateczl.consultoria_informatica.controller.AutorController;
import br.edu.fateczl.consultoria_informatica.model.Autor;

public class AutorFragment extends Fragment {
    private AutorController autorController;
    private EditText editTextNome;
    private EditText editTextSobrenome;
    private TextView textViewOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autor, container, false);

        editTextNome = view.findViewById(R.id.editTextAuthorName);
        editTextSobrenome = view.findViewById(R.id.editTextAuthorLastName);
        textViewOutput = view.findViewById(R.id.textViewAuthorOutput);
        textViewOutput.setMovementMethod(new ScrollingMovementMethod());

        autorController = new AutorController(getContext());

        view.findViewById(R.id.buttonAuthorRegister).setOnClickListener(v -> {
            try {
                Autor autor = new Autor(0, editTextNome.getText().toString(), editTextSobrenome.getText().toString(), false);
                autorController.add(autor);
                Toast.makeText(getContext(), "Autor registrado!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao registrar autor", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonAuthorUpdate).setOnClickListener(v -> {
            // Atualização do autor
            Toast.makeText(getContext(), "Autor atualizado!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonAuthorRemove).setOnClickListener(v -> {
            // Remoção do autor
            Toast.makeText(getContext(), "Autor removido!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonAuthorList).setOnClickListener(v -> {
            try {
                List<Autor> autores = autorController.findAll();
                StringBuilder output = new StringBuilder();
                for (Autor autor : autores) {
                    output.append(autor.toString()).append("\n");
                }
                textViewOutput.setText(output.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao listar autores", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
