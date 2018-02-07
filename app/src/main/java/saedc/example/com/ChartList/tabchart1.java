package saedc.example.com.ChartList;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.tapadoo.alerter.Alerter;
import saedc.example.com.R;
import saedc.example.com.View.TotalSpendingQuantity.TotalSpendingQuantityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * barch1
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


    private void showTotalQuantityInUi(Double quantity) {
        final List<PieEntry> entries = new ArrayList<>();
        List<Double> pie = viewModel.getTotalSpendingforchart();


        final ArrayList<String> labels = new ArrayList<String>();
        ArrayList<Double> entrie = new ArrayList<Double>();


        labels.add(getContext().getString(R.string.GAS));
        labels.add(getContext().getString(R.string.FOOD));
        labels.add(getContext().getString(R.string.CLOTHES));
        labels.add(getContext().getString(R.string.OTHER));


        for (Double piech : pie) {

            double Percentage = ((piech * 100.0) / quantity);

            entrie.add(Percentage);


        }
        for (int i=0;i<labels.size();i++){
            double it =entrie.get(i);
            entries.add(new PieEntry((float) it, labels.get(i)));
        }



        PieDataSet set = new PieDataSet(entries, "CHART");
        PieData data = new PieData(set);

        set.setColors(ColorTemplate.VORDIPLOM_COLORS);

        set.setSelectionShift(15);
        Description Descriptio = new Description();
        Descriptio.setTextSize(50);
        Descriptio.setText(getResources().getString(R.string.tab_text_1));

        data.setValueTextSize(14f);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.animateY(2000);
        pieChart.setCenterText("100%");
        pieChart.setCenterTextSize(45);
        pieChart.setCenterTextColor(R.color.centertext);
        pieChart.setEntryLabelColor(R.color.centertext);
        pieChart.setHoleRadius(50);
        pieChart.getDescription().setEnabled(true);
        pieChart.setDescription(Descriptio);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.notifyDataSetChanged();

        pieChart.invalidate();
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {


            @Override
            public void onValueSelected(Entry e, Highlight h) {

                PieEntry pe = (PieEntry) e;



                Alerter.create(getActivity())
                        .setTitle("Alert Title")
                        .setText(pe.getLabel()+" "+pe.getValue())
                        .enableProgress(false)
                        .setIcon(R.drawable.about)
                        .setProgressColorRes(R.color.colorAccent)
                        .setBackgroundColorRes(R.color.colorPrimaryDark)
                        .show();
//                Toast.makeText(getContext(),h.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
}
