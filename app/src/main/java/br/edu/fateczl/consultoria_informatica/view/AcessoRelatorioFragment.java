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
import br.edu.fateczl.consultoria_informatica.controllers.AcessoRelatorioController;
import br.edu.fateczl.consultoria_informatica.model.AcessoRelatorio;
import br.edu.fateczl.consultoria_informatica.model.Cliente;
import br.edu.fateczl.consultoria_informatica.model.Relatorio;
import br.edu.fateczl.consultoria_informatica.model.Autor;

public class AcessoRelatorioFragment extends Fragment {
    private AcessoRelatorioController acessoRelatorioController;
    private EditText editTextClienteId;
    private EditText editTextRelatorioId;
    private EditText editTextNumeroAcesso;
    private EditText editTextDataAcesso;
    private TextView textViewOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_access_report, container, false);

        editTextClienteId = view.findViewById(R.id.editTextAccessClientId);
        editTextRelatorioId = view.findViewById(R.id.editTextAccessReportId);
        editTextNumeroAcesso = view.findViewById(R.id.editTextAccessNumber);
        editTextDataAcesso = view.findViewById(R.id.editTextAccessDate);
        textViewOutput = view.findViewById(R.id.textViewAccessOutput);
        textViewOutput.setMovementMethod(new ScrollingMovementMethod());

        acessoRelatorioController = new AcessoRelatorioController(getContext());

        view.findViewById(R.id.buttonAccessRegister).setOnClickListener(v -> {
            try {
                Cliente cliente = new Cliente(Integer.parseInt(editTextClienteId.getText().toString()), "", "", "", "");
                Relatorio relatorio = new Relatorio(Integer.parseInt(editTextRelatorioId.getText().toString()), "", "", "", false, new Autor(0, "", "", false));
                AcessoRelatorio acesso = new AcessoRelatorio(Integer.parseInt(editTextNumeroAcesso.getText().toString()), 0, editTextDataAcesso.getText().toString(), cliente, relatorio);
                acessoRelatorioController.add(acesso);
                Toast.makeText(getContext(), "Acesso registrado!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao registrar acesso", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonAccessUpdate).setOnClickListener(v -> {
            // Atualização do acesso
            Toast.makeText(getContext(), "Acesso atualizado!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonAccessRemove).setOnClickListener(v -> {
            // Remoção do acesso
            Toast.makeText(getContext(), "Acesso removido!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonAccessList).setOnClickListener(v -> {
            try {
                List<AcessoRelatorio> acessos = acessoRelatorioController.list();
                StringBuilder output = new StringBuilder();
                for (AcessoRelatorio acesso : acessos) {
                    output.append(acesso.toString()).append("\n");
                }
                textViewOutput.setText(output.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao listar acessos", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
