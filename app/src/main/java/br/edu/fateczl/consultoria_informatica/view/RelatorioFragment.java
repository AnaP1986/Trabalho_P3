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
import br.edu.fateczl.consultoria_informatica.controllers.RelatorioController;
import br.edu.fateczl.consultoria_informatica.model.Relatorio;
import br.edu.fateczl.consultoria_informatica.model.Autor;

public class RelatorioFragment extends Fragment {
    private RelatorioController relatorioController;
    private EditText editTextTitulo;
    private EditText editTextResumo;
    private EditText editTextTexto;
    private TextView textViewOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relatorio, container, false);

        editTextTitulo = view.findViewById(R.id.editTextRelatorioTitulo);
        editTextResumo = view.findViewById(R.id.editTextRelatorioResumo);
        editTextTexto = view.findViewById(R.id.editTextRelatorioTexto);
        textViewOutput = view.findViewById(R.id.textViewRelatorioOutput);
        textViewOutput.setMovementMethod(new ScrollingMovementMethod());

        relatorioController = new RelatorioController(getContext());

        view.findViewById(R.id.buttonRelatorioRegister).setOnClickListener(v -> {
            Relatorio relatorio = new Relatorio(0, editTextTitulo.getText().toString(), editTextResumo.getText().toString(), editTextTexto.getText().toString(), false, new Autor());
            try {
                relatorioController.add(relatorio);
                Toast.makeText(getContext(), "Relatório registrado!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao registrar relatório!", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonRelatorioUpdate).setOnClickListener(v -> {
            Relatorio relatorio = new Relatorio(0, editTextTitulo.getText().toString(), editTextResumo.getText().toString(), editTextTexto.getText().toString(), false, new Autor());
            try {
                relatorioController.update(relatorio);
                Toast.makeText(getContext(), "Relatório atualizado!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao atualizar relatório!", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonRelatorioRemove).setOnClickListener(v -> {
            int relatorioId = Integer.parseInt(editTextTitulo.getText().toString());
            Relatorio relatorio = new Relatorio(relatorioId, "", "", "", false, new Autor());
            try {
                relatorioController.remove(relatorio);
                Toast.makeText(getContext(), "Relatório removido!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao remover relatório!", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonRelatorioList).setOnClickListener(v -> {
            try {
                List<Relatorio> relatorios = relatorioController.list();
                StringBuilder output = new StringBuilder();
                for (Relatorio relatorio : relatorios) {
                    output.append(relatorio.toString()).append("\n");
                }
                textViewOutput.setText(output.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao listar relatórios!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
