package com.vsahin.moneycim.Model.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vsahin.moneycim.Model.Entity.RawSpending;
import com.vsahin.moneycim.Model.Pojo.Spending;

import java.util.List;

/**
 * Created by Volkan Şahin on 18.08.2017.
 */

@Dao
public interface SpendingDao {

    @Query("SELECT * FROM SPENDING INNER JOIN SPENDING_GROUP ON SPENDING.GROUP_ID = SPENDING_GROUP.GROUP_ID ORDER BY SPENDING.DATE DESC")
    LiveData<List<Spending>> getSpendingsWithGroups();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSpending(RawSpending s);

    @Query("SELECT SUM(QUANTITY) FROM SPENDING")
    LiveData<Double> getTotalSpendingQuantity();
    // i change it
    @Query("SELECT SUM(QUANTITY) FROM SPENDING where SPENDING.group_id=1")
    LiveData<Double> getTotalSpendingQuantity1();
    // i change it
    @Query("SELECT SUM(QUANTITY) FROM SPENDING where SPENDING.group_id=2")
    LiveData<Double> getTotalSpendingQuantity2();
    // i change it
    @Query("SELECT SUM(QUANTITY) FROM SPENDING where SPENDING.group_id=3")
    LiveData<Double> getTotalSpendingQuantity3();
    // i change it
    @Query("SELECT SUM(QUANTITY) FROM SPENDING where SPENDING.group_id=4")
    LiveData<Double> getTotalSpendingQuantity4();
    // i change it
    @Query("SELECT SUM(QUANTITY) FROM SPENDING,SPENDING_GROUP WHERE SPENDING.GROUP_ID = SPENDING_GROUP.GROUP_ID GROUP BY SPENDING_GROUP.GROUP_ID ORDER BY SPENDING.DATE DESC")
    List<Double> getTotalByGroub();

    @Query("DELETE FROM SPENDING WHERE ID = :id")
    void deleteSpending(int id);
}
