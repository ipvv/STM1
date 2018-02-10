package saedc.example.com.Model.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import saedc.example.com.Model.Entity.Saving;


@Dao
public interface SavingDao {

    @Query("SELECT * FROM saving")
    LiveData<List<Saving>> getsaving();
}
