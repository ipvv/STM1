package saedc.example.com.Model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import saedc.example.com.Model.Dao.SpendingDao;
import saedc.example.com.Model.Dao.SpendingGroupDao;
import saedc.example.com.Model.Database.Converters;
import saedc.example.com.Model.Entity.RawSpending;
import saedc.example.com.Model.Entity.SpendingGroup;


@Database(version = 1, entities = {RawSpending.class, SpendingGroup.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{
    private static saedc.example.com.Model.Database.AppDatabase INSTANCE;
    public abstract SpendingDao spendingDao();
    public abstract SpendingGroupDao spendingGroupDao();


}
