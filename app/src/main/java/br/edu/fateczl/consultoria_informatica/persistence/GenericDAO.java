package br.edu.fateczl.consultoria_informatica.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "consultoria_informatica.db";
    private static final int DATABASE_VERSION = 1;

    public GenericDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação das tabelas
        db.execSQL("CREATE TABLE Autor (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, sobrenome TEXT, anonimo INTEGER)");
        db.execSQL("CREATE TABLE Cliente (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, sobrenome TEXT, cpf TEXT, endereco TEXT)");
        db.execSQL("CREATE TABLE Contrato (id INTEGER PRIMARY KEY AUTOINCREMENT, dataInicio TEXT, dataFim TEXT, clienteId INTEGER)");
        db.execSQL("CREATE TABLE Relatorio (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, resumo TEXT, texto TEXT, publico INTEGER, autorId INTEGER)");
        db.execSQL("CREATE TABLE AcessoRelatorio (id INTEGER PRIMARY KEY AUTOINCREMENT, numeroAcesso INTEGER, dataAcesso TEXT, clienteId INTEGER, relatorioId INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualização das tabelas
        db.execSQL("DROP TABLE IF EXISTS Autor");
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        db.execSQL("DROP TABLE IF EXISTS Contrato");
        db.execSQL("DROP TABLE IF EXISTS Relatorio");
        db.execSQL("DROP TABLE IF EXISTS AcessoRelatorio");
        onCreate(db);
    }
}
