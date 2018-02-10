package saedc.example.com.View.ChartList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import saedc.example.com.Model.Pojo.Spending;
import saedc.example.com.Model.Repository.SpendingRepository;
import saedc.example.com.MoneycimApp;

/**
 * Created by saedc on 09/02/18.
 */

public class chartViewModel extends AndroidViewModel {
    @Inject
    public SpendingRepository spendingRepository;
    final public LiveData<Double> totalSpendingQuantity;
    final public List<Spending> spendingdate;


    final public List<Double> totalSpendingforchart;

    public chartViewModel(Application application) {
        super(application);
        ((MoneycimApp) getApplication()).getAppComponent().inject(this);

        totalSpendingQuantity = getTotalSpendingQuantity();

        totalSpendingforchart = getTotalSpendingforchart();

        spendingdate = getDate();
    }

    public LiveData<Double> getTotalSpendingQuantity() {
        return spendingRepository.getTotalSpendingQuantity();
    }

    public List<Spending> getDate() {
        return spendingRepository.GetSpendingsdate();
    }

    public List<Double> getTotalSpendingforchart() {
        return spendingRepository.GetTotalSpendingforchart();
    }

}
