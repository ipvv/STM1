package com.vsahin.moneycim.View.SpendingList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vsahin.moneycim.Model.Entity.RawSpending;
import com.vsahin.moneycim.Model.Pojo.Spending;
import com.vsahin.moneycim.Model.Repository.SpendingRepository;
import com.vsahin.moneycim.MoneycimApp;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;



public class SpendingListViewModel extends AndroidViewModel {

    @Inject public SpendingRepository spendingRepository;
    final public LiveData<List<Spending>> spendings;
    final public List<Spending> spendingdate;


    public SpendingListViewModel(Application application) {
        super(application);
        ((MoneycimApp)getApplication()).getAppComponent().inject(this);

        spendings = getSpendings();
        spendingdate = getDate();
    }

    public LiveData<List<Spending>> getSpendings(){
        return spendingRepository.getSpendings();
    }
    public List<Spending> getDate(){
        return spendingRepository.GetSpendingsdate();
    }

    public void deleteSpending(int id){
        spendingRepository.deleteSpending(id);
    }
}
