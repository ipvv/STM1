package saedc.example.com.Model.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import saedc.example.com.Model.Database.AppDatabase;
import saedc.example.com.Model.Entity.RawSpending;
import saedc.example.com.Model.Entity.SpendingGroup;
import saedc.example.com.Model.Pojo.Spending;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class SpendingRepository {
    private AppDatabase appDatabase;
    @Inject
    public SpendingRepository(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public LiveData<List<Spending>> getSpendings(){
        return appDatabase.spendingDao().getSpendingsWithGroups();
    }
    public List<Spending> GetSpendingsdate(){
        return appDatabase.spendingDao().getSpendingsdate();
    }
    
// i change it
    public List<Double> GetTotalSpendingforchart(){
        return appDatabase.spendingDao().getTotalByGroub();

    }

    public LiveData<Double> getTotalSpendingQuantity(){
        return appDatabase.spendingDao().getTotalSpendingQuantity();
    }

    public LiveData<Double> getTotalSpendingQuantity1(){
        return appDatabase.spendingDao().getTotalSpendingQuantity1();
    }
    public LiveData<Double> getTotalSpendingQuantity2(){
        return appDatabase.spendingDao().getTotalSpendingQuantity2();
    }
    public LiveData<Double> getTotalSpendingQuantity3(){
        return appDatabase.spendingDao().getTotalSpendingQuantity3();
    }
    public LiveData<Double> getTotalSpendingQuantity4(){
        return appDatabase.spendingDao().getTotalSpendingQuantity4();
    }

    public void addSpending(RawSpending s){
        appDatabase.spendingDao().addSpending(s);
    }

    public LiveData<List<SpendingGroup>> getSpendingGroups(){
        return appDatabase.spendingGroupDao().getAllSpendingGroups();
    }

    public void deleteSpending(int id){
        appDatabase.spendingDao().deleteSpending(id);
    }
}
