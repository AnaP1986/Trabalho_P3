package br.edu.fateczl.consultoria_informatica.view;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

import br.edu.fateczl.consultoria_informatica.R;
import br.edu.fateczl.consultoria_informatica.controller.ContratoController;
import br.edu.fateczl.consultoria_informatica.model.Contrato;

public class ContratoFragment extends Fragment {
    private ContratoController contratoController;
    private EditText editTextDataInicio;
    private EditText editTextDataFim;
    private EditText editTextClienteId;
    private TextView textViewOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contrato, container, false);

        editTextDataInicio = view.findViewById(R.id.editTextContractStartDate);
        editTextDataFim = view.findViewById(R.id.editTextContractEndDate);
        editTextClienteId = view.findViewById(R.id.editTextContractClientId);
        textViewOutput = view.findViewById(R.id.textViewContractOutput);
        textViewOutput.setMovementMethod(new ScrollingMovementMethod());

        contratoController = new ContratoController(getContext());

        view.findViewById(R.id.buttonContractRegister).setOnClickListener(v -> {
            int clienteId = Integer.parseInt(editTextClienteId.getText().toString());
            Contrato contrato = new Contrato(0, clienteId, editTextDataInicio.getText().toString(), editTextDataFim.getText().toString());
            contratoController.insertContrato(contrato);
            Toast.makeText(getContext(), "Contrato registrado!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonContractUpdate).setOnClickListener(v -> {
            int clienteId = Integer.parseInt(editTextClienteId.getText().toString());
            Contrato contrato = new Contrato(0, clienteId, editTextDataInicio.getText().toString(), editTextDataFim.getText().toString());
            contratoController.updateContrato(contrato);
            Toast.makeText(getContext(), "Contrato atualizado!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonContractRemove).setOnClickListener(v -> {
            int contratoId = Integer.parseInt(editTextClienteId.getText().toString()); // Supondo que o ID do contrato está sendo usado temporariamente
            Contrato contrato = new Contrato(contratoId, 0, "", ""); // Supondo que apenas o ID do contrato é necessário
            contratoController.deleteContrato(contrato);
            Toast.makeText(getContext(), "Contrato removido!", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.buttonContractList).setOnClickListener(v -> {
            List<Contrato> contratos = contratoController.findAllContratos();
            StringBuilder output = new StringBuilder();
            for (Contrato contrato : contratos) {
                output.append(contrato.toString()).append("\n");
            }
            textViewOutput.setText(output.toString());
        });

        return view;
    }
}
