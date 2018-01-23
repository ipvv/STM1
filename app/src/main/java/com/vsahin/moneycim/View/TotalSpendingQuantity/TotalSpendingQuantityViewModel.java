package com.vsahin.moneycim.View.TotalSpendingQuantity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vsahin.moneycim.Model.Repository.SpendingRepository;
import com.vsahin.moneycim.MoneycimApp;

import java.util.List;

import javax.inject.Inject;



public class TotalSpendingQuantityViewModel extends AndroidViewModel{
    @Inject public SpendingRepository spendingRepository;
    final public LiveData<Double> totalSpendingQuantity;

    final public LiveData<Double> totalSpendingQuantity1;

    final public LiveData<Double> totalSpendingQuantity2;

    final public LiveData<Double> totalSpendingQuantity3;
    final public LiveData<Double> totalSpendingQuantity4;

    final public List<Double> totalSpendingforchart;

    public TotalSpendingQuantityViewModel(Application application) {
        super(application);
        ((MoneycimApp)getApplication()).getAppComponent().inject(this);

        totalSpendingQuantity = getTotalSpendingQuantity();

        totalSpendingQuantity1 = GetTotalSpendingQuantity1();
        totalSpendingQuantity2 = GetTotalSpendingQuantity2();
        totalSpendingQuantity3 = GetTotalSpendingQuantity3();
        totalSpendingQuantity4 = GetTotalSpendingQuantity4();

        totalSpendingforchart =getTotalSpendingforchart();
    }


    public LiveData<Double> getTotalSpendingQuantity(){
        return  spendingRepository.getTotalSpendingQuantity();
    }

    public LiveData<Double> GetTotalSpendingQuantity1(){
        return  spendingRepository.getTotalSpendingQuantity1();
    }
    public LiveData<Double> GetTotalSpendingQuantity2(){
        return  spendingRepository.getTotalSpendingQuantity2();
    }
    public LiveData<Double> GetTotalSpendingQuantity3(){
        return  spendingRepository.getTotalSpendingQuantity3();
    }
    public LiveData<Double> GetTotalSpendingQuantity4(){
        return  spendingRepository.getTotalSpendingQuantity4();
    }

    public List<Double> getTotalSpendingforchart(){
        return  spendingRepository.GetTotalSpendingforchart();
    }
}
