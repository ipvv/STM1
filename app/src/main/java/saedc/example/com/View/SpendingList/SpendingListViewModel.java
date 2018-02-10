package saedc.example.com.View.SpendingList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import saedc.example.com.Model.Entity.RawSpending;
import saedc.example.com.Model.Pojo.Spending;
import saedc.example.com.Model.Repository.SpendingRepository;
import saedc.example.com.MoneycimApp;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;


public class SpendingListViewModel extends AndroidViewModel {

    @Inject public SpendingRepository spendingRepository;
    final public LiveData<List<Spending>> spendings;


    public SpendingListViewModel(Application application) {
        super(application);
        ((MoneycimApp)getApplication()).getAppComponent().inject(this);

        spendings = getSpendings();
    }

    public LiveData<List<Spending>> getSpendings(){
        return spendingRepository.getSpendings();
    }


    public void deleteSpending(int id){
        spendingRepository.deleteSpending(id);
    }
}
