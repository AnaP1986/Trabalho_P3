package br.edu.fateczl.consultoria_informatica.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.model.Autor;

public class AutorDAO extends GenericDAO implements IAutorDAO {
    public AutorDAO(Context context) {
        super(context);
    }

    @Override
    public void insert(Autor autor) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", autor.getNome());
        values.put("sobrenome", autor.getSobrenome());
        values.put("anonimo", autor.isAnonimo() ? 1 : 0);
        db.insert("Autor", null, values);
        db.close();
    }

    @Override
    public void update(Autor autor) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", autor.getNome());
        values.put("sobrenome", autor.getSobrenome());
        values.put("anonimo", autor.isAnonimo() ? 1 : 0);
        db.update("Autor", values, "id = ?", new String[]{String.valueOf(autor.getId())});
        db.close();
    }

    @Override
    public void delete(Autor autor) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Autor", "id = ?", new String[]{String.valueOf(autor.getId())});
        db.close();
    }

    @Override
    public Autor findById(int id) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Autor WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            Autor autor = new Autor(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3) == 1);
            cursor.close();
            db.close();
            return autor;
        }
        cursor.close();
        db.close();
        return null;
    }

    @Override
    public List<Autor> findAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Autor", null);
        List<Autor> autores = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                autores.add(new Autor(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3) == 1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return autores;
    }
}
