package br.edu.fateczl.consultoria_informatica.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.model.Cliente;

public class ClienteDao extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "consultoria.db";
    private static final int DATABASE_VERSION = 1;

    public ClienteDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Cliente (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, sobrenome TEXT, cpf TEXT, endereco TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        onCreate(db);
    }

    public void insert(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "INSERT INTO Cliente (nome, sobrenome, cpf, endereco) VALUES (?, ?, ?, ?)";
        db.execSQL(insert, new Object[]{cliente.getNome(), cliente.getSobrenome(), cliente.getCpf(), cliente.getEndereco()});
        db.close();
    }

    public void update(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        String update = "UPDATE Cliente SET nome = ?, sobrenome = ?, endereco = ? WHERE cpf = ?";
        db.execSQL(update, new Object[]{cliente.getNome(), cliente.getSobrenome(), cliente.getEndereco(), cliente.getCpf()});
        db.close();
    }

    public void delete(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM Cliente WHERE cpf = ?";
        db.execSQL(delete, new Object[]{cliente.getCpf()});
        db.close();
    }

    public Cliente findById(String cpf) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM Cliente WHERE cpf = ?";
        Cursor cursor = db.rawQuery(select, new String[]{cpf});

        if (cursor.moveToFirst()) {
            Cliente cliente = new Cliente(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    cursor.getString(cursor.getColumnIndexOrThrow("sobrenome")),
                    cursor.getString(cursor.getColumnIndexOrThrow("cpf")),
                    cursor.getString(cursor.getColumnIndexOrThrow("endereco"))
            );
            cursor.close();
            db.close();
            return cliente;
        } else {
            cursor.close();
            db.close();
            return null;
        }
    }

    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM Cliente";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                        cursor.getString(cursor.getColumnIndexOrThrow("sobrenome")),
                        cursor.getString(cursor.getColumnIndexOrThrow("cpf")),
                        cursor.getString(cursor.getColumnIndexOrThrow("endereco"))
                );
                clientes.add(cliente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return clientes;
    }
}
