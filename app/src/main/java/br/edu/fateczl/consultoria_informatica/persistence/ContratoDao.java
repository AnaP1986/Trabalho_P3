package br.edu.fateczl.consultoria_informatica.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.model.Contrato;

public class ContratoDao extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "consultoria.db";
    private static final int DATABASE_VERSION = 1;

    public ContratoDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Contrato (id INTEGER PRIMARY KEY AUTOINCREMENT, idCliente INTEGER, dataInicio TEXT, dataFim TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contrato");
        onCreate(db);
    }

    public void insert(Contrato contrato) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insert = "INSERT INTO Contrato (idCliente, dataInicio, dataFim) VALUES (?, ?, ?)";
        db.execSQL(insert, new Object[]{contrato.getIdCliente(), contrato.getDataInicio(), contrato.getDataFim()});
        db.close();
    }

    public void update(Contrato contrato) {
        SQLiteDatabase db = this.getWritableDatabase();
        String update = "UPDATE Contrato SET idCliente = ?, dataInicio = ?, dataFim = ? WHERE id = ?";
        db.execSQL(update, new Object[]{contrato.getIdCliente(), contrato.getDataInicio(), contrato.getDataFim(), contrato.getId()});
        db.close();
    }

    public void delete(Contrato contrato) {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM Contrato WHERE id = ?";
        db.execSQL(delete, new Object[]{contrato.getId()});
        db.close();
    }

    public Contrato findById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM Contrato WHERE id = ?";
        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            Contrato contrato = new Contrato(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("idCliente")),
                    cursor.getString(cursor.getColumnIndexOrThrow("dataInicio")),
                    cursor.getString(cursor.getColumnIndexOrThrow("dataFim"))
            );
            cursor.close();
            db.close();
            return contrato;
        } else {
            cursor.close();
            db.close();
            return null;
        }
    }

    public List<Contrato> findAll() {
        List<Contrato> contratos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM Contrato";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Contrato contrato = new Contrato(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("idCliente")),
                        cursor.getString(cursor.getColumnIndexOrThrow("dataInicio")),
                        cursor.getString(cursor.getColumnIndexOrThrow("dataFim"))
                );
                contratos.add(contrato);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contratos;
    }
}
