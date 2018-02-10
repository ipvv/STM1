package saedc.example.com.Di;

import saedc.example.com.Di.AppModule;
import saedc.example.com.View.AddAndEditSpending.AddAndEditSpendingViewModel;
import saedc.example.com.View.ChartList.chartViewModel;
import saedc.example.com.View.SpendingList.SpendingListViewModel;
import saedc.example.com.View.TotalSpendingQuantity.TotalSpendingQuantityViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(chartViewModel chartviewModel);
    void inject(SpendingListViewModel spendingListViewModel);
    void inject(TotalSpendingQuantityViewModel totalSpendingQuantityViewModel);
    void inject(AddAndEditSpendingViewModel addSpendingViewModel);
}
