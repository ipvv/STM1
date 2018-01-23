package com.vsahin.moneycim.Model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.vsahin.moneycim.Model.Dao.SpendingDao;
import com.vsahin.moneycim.Model.Dao.SpendingGroupDao;
import com.vsahin.moneycim.Model.Entity.RawSpending;
import com.vsahin.moneycim.Model.Entity.SpendingGroup;



@Database(version = 1, entities = {RawSpending.class, SpendingGroup.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase INSTANCE;
    public abstract SpendingDao spendingDao();
    public abstract SpendingGroupDao spendingGroupDao();


}
