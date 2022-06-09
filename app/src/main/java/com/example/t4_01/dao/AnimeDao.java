package com.example.t4_01.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.t4_01.entities.Animes;

import java.util.List;

@Dao
public interface AnimeDao {
    @Query("SELECT * FROM animes")
    List<Animes> getAll();

    @Query("SELECT * FROM animes WHERE id=:id")
    Animes find(int id);
    @Insert
    void create(Animes anime);
}
