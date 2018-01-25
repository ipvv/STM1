package com.vsahin.moneycim.View.AddAndEditSpending;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vsahin.moneycim.Model.Entity.RawSpending;
import com.vsahin.moneycim.Model.Entity.SpendingGroup;
import com.vsahin.moneycim.Model.Repository.SpendingRepository;
import com.vsahin.moneycim.MoneycimApp;

import java.util.List;

import javax.inject.Inject;



public class AddAndEditSpendingViewModel extends AndroidViewModel {

    @Inject
    public SpendingRepository spendingRepository;

    final public LiveData<List<SpendingGroup>> spendingGroups;

    public AddAndEditSpendingViewModel(Application application) {
        super(application);
        ((MoneycimApp)getApplication()).getAppComponent().inject(this);

        spendingGroups = getSpendingGroups();
    }

    public LiveData<List<SpendingGroup>> getSpendingGroups() {
        return spendingRepository.getSpendingGroups();
    }

    public void addSpending(RawSpending s){
        spendingRepository.addSpending(s);
    }

    public void deleteSpending(int id){
        spendingRepository.deleteSpending(id);
    }
}
