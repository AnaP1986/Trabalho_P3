package br.edu.fateczl.consultoria_informatica.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.consultoria_informatica.model.AcessoRelatorio;
import br.edu.fateczl.consultoria_informatica.model.Cliente;
import br.edu.fateczl.consultoria_informatica.model.Relatorio;

public class AcessoRelatorioDAO extends GenericDAO {

    public AcessoRelatorioDAO(Context context) {
        super(context);
    }

    public void insert(AcessoRelatorio acesso) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("numeroAcesso", acesso.getNumeroAcesso());
        values.put("dataAcesso", acesso.getDataAcesso());
        values.put("clienteId", acesso.getCliente().getId());
        values.put("relatorioId", acesso.getRelatorio().getId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("AcessoRelatorio", null, values);
    }

    public void update(AcessoRelatorio acesso) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("numeroAcesso", acesso.getNumeroAcesso());
        values.put("dataAcesso", acesso.getDataAcesso());
        values.put("clienteId", acesso.getCliente().getId());
        values.put("relatorioId", acesso.getRelatorio().getId());

        SQLiteDatabase db = getWritableDatabase();
        db.update("AcessoRelatorio", values, "id = ?", new String[]{String.valueOf(acesso.getId())});
    }

    public void delete(AcessoRelatorio acesso) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("AcessoRelatorio", "id = ?", new String[]{String.valueOf(acesso.getId())});
    }

    public List<AcessoRelatorio> selectAll() throws SQLException {
        List<AcessoRelatorio> acessos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("AcessoRelatorio", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int numeroAcesso = cursor.getInt(cursor.getColumnIndex("numeroAcesso"));
            String dataAcesso = cursor.getString(cursor.getColumnIndex("dataAcesso"));
            int clienteId = cursor.getInt(cursor.getColumnIndex("clienteId"));
            int relatorioId = cursor.getInt(cursor.getColumnIndex("relatorioId"));

            Cliente cliente = new Cliente(clienteId, "", "", "", "");
            Relatorio relatorio = new Relatorio(relatorioId, "", "", "", false, null);

            AcessoRelatorio acesso = new AcessoRelatorio(numeroAcesso, id, dataAcesso, cliente, relatorio);
            acessos.add(acesso);
        }
        cursor.close();
        return acessos;
    }
}
