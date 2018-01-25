package com.vsahin.moneycim;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.vsahin.moneycim.Model.Database.AppDatabase;
import com.vsahin.moneycim.Model.Entity.RawSpending;
import com.vsahin.moneycim.Model.Entity.SpendingGroup;
import com.vsahin.moneycim.R;
import com.vsahin.moneycim.View.AddAndEditSpending.AddAndEditSpendingViewModel;
import com.vsahin.moneycim.View.TotalSpendingQuantity.TotalSpendingQuantityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**barch1
 * Created by saedc on 24/01/18.
 */

public class tabchart1 extends LifecycleFragment {


    @BindView(R.id.barch1)
    PieChart pieChart;

    TotalSpendingQuantityViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.tabchart1, container, false);

        ButterKnife.bind(this, rootview);

        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TotalSpendingQuantityViewModel.class);


        subscribeTotalQuantity();
    }

    private void subscribeTotalQuantity() {
        viewModel.totalSpendingQuantity.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(final Double quantity) {
                if (quantity != null) {
                    showTotalQuantityInUi(quantity);


                } else {
                    showTotalQuantityInUi(0.0);
                }
            }
        });

    }


private void showTotalQuantityInUi(Double quantity){
    List<PieEntry> entries = new ArrayList<>();
    LiveData<Double> youngUsers1 = viewModel.getTotalSpendingQuantity();
    List<Double> youngUsers = viewModel.getTotalSpendingforchart();
    ArrayList<String> labels = new ArrayList<String>();
    labels.add("January");
    labels.add("February");
    labels.add("March");
    labels.add("April");
    for (Double youngUser : youngUsers) {


        double Percentage = ((youngUser * 100.0) / quantity);
int i=0;
        entries.add(new PieEntry((float)Percentage,labels.get(i)));
i++;
    }


    PieDataSet set = new PieDataSet(entries, "CHART");
    set.setColors(ColorTemplate.VORDIPLOM_COLORS);

    set.setSelectionShift(15);
    PieData data = new PieData(set);

    data.setValueTextSize(14f);
    data.setValueFormatter(new PercentFormatter());
    pieChart.setData(data);
    pieChart.animateY(2000);
    pieChart.setCenterText("100%");
    pieChart.setCenterTextSize(45);
    pieChart.setCenterTextColor(R.color.centertext);
    pieChart.setEntryLabelColor(R.color.centertext);
    pieChart.setHoleRadius(50);

    pieChart.invalidate();

    }
}
