package br.edu.fateczl.consultoria_informatica.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.model.Relatorio;
import br.edu.fateczl.consultoria_informatica.model.Autor;

public class RelatorioDAO extends GenericDAO {

    public RelatorioDAO(Context context) {
        super(context);
    }

    public void insert(Relatorio relatorio) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("titulo", relatorio.getTitulo());
        values.put("resumo", relatorio.getResumo());
        values.put("texto", relatorio.getTexto());
        values.put("isPublico", relatorio.isPublico());
        values.put("autorId", relatorio.getAutor().getId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("Relatorio", null, values);
    }

    public void update(Relatorio relatorio) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("titulo", relatorio.getTitulo());
        values.put("resumo", relatorio.getResumo());
        values.put("texto", relatorio.getTexto());
        values.put("isPublico", relatorio.isPublico());
        values.put("autorId", relatorio.getAutor().getId());

        SQLiteDatabase db = getWritableDatabase();
        db.update("Relatorio", values, "id = ?", new String[]{String.valueOf(relatorio.getId())});
    }

    public void delete(Relatorio relatorio) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Relatorio", "id = ?", new String[]{String.valueOf(relatorio.getId())});
    }

    public List<Relatorio> selectAll() throws SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Relatorio", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String resumo = cursor.getString(cursor.getColumnIndex("resumo"));
            String texto = cursor.getString(cursor.getColumnIndex("texto"));
            boolean isPublico = cursor.getInt(cursor.getColumnIndex("isPublico")) > 0;
            int autorId = cursor.getInt(cursor.getColumnIndex("autorId"));

            Autor autor = new Autor(autorId, "", "", false);

            Relatorio relatorio = new Relatorio(id, titulo, resumo, texto, isPublico, autor);
            relatorios.add(relatorio);
        }
        cursor.close();
        return relatorios;
    }
}
