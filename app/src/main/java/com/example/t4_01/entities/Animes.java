package com.example.t4_01.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "animes")
public class Animes {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "descripcion")
    public String descripcion;
    @ColumnInfo(name = "url")
    public String url;

    public Animes() {
    }

    public Animes(int id, String nombre, String descripcion, String url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }
}
