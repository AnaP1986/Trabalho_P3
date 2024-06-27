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
import br.edu.fateczl.consultoria_informatica.controller.ClienteController;
import br.edu.fateczl.consultoria_informatica.model.Cliente;

public class ClienteFragment extends Fragment {
    private ClienteController clienteController;
    private EditText editTextNome;
    private EditText editTextSobrenome;
    private EditText editTextCpf;
    private EditText editTextEndereco;
    private TextView textViewOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        editTextNome = view.findViewById(R.id.editTextClientName);
        editTextSobrenome = view.findViewById(R.id.editTextClientLastName);
        editTextCpf = view.findViewById(R.id.editTextClientCPF);
        editTextEndereco = view.findViewById(R.id.editTextClientAddress);
        textViewOutput = view.findViewById(R.id.textViewClientOutput);
        textViewOutput.setMovementMethod(new ScrollingMovementMethod());

        clienteController = new ClienteController(getContext());

        view.findViewById(R.id.buttonClientRegister).setOnClickListener(v -> {
            Cliente cliente = new Cliente(0, editTextNome.getText().toString(), editTextSobrenome.getText().toString(), editTextCpf.getText().toString(), editTextEndereco.getText().toString());
            try {
                clienteController.add(cliente);
                Toast.makeText(getContext(), "Cliente registrado!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao registrar cliente", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonClientUpdate).setOnClickListener(v -> {
            Cliente cliente = new Cliente(0, editTextNome.getText().toString(), editTextSobrenome.getText().toString(), editTextCpf.getText().toString(), editTextEndereco.getText().toString());
            try {
                clienteController.update(cliente);
                Toast.makeText(getContext(), "Cliente atualizado!", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao atualizar cliente", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonClientRemove).setOnClickListener(v -> {
            String cpf = editTextCpf.getText().toString();
            try {
                Cliente cliente = clienteController.findById(cpf); // Assumindo que findById aceita String
                if (cliente != null) {
                    clienteController.remove(cliente);
                    Toast.makeText(getContext(), "Cliente removido!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Cliente não encontrado!", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao remover cliente", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.buttonClientList).setOnClickListener(v -> {
            try {
                List<Cliente> clientes = clienteController.findAll();
                StringBuilder output = new StringBuilder();
                for (Cliente cliente : clientes) {
                    output.append(cliente.toString()).append("\n");
                }
                textViewOutput.setText(output.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Erro ao listar clientes", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
