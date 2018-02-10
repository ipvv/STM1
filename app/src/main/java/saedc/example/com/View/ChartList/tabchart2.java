package saedc.example.com.View.ChartList;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import saedc.example.com.Model.Pojo.Spending;
import saedc.example.com.R;
import saedc.example.com.View.SpendingList.SpendingListViewModel;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class tabchart2 extends LifecycleFragment {

    @BindView(R.id.listView1)
    ListView lv;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM"); //you can add dd/MM/yyyy for date
    ArrayList<Spending> spendingList = new ArrayList<>();
    chartViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.tabchart2, container, false);
        ButterKnife.bind(this, rootview);

        return rootview;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(chartViewModel.class);


        ArrayList<BarData> list = new ArrayList<BarData>();

        // 20 items
        for (int i = 0; i < 12; i++) {
            list.add(subscribeSpendings(i + 1));

        }

        ChartDataAdapter cda = new ChartDataAdapter(getContext(), list);
        lv.setAdapter(cda);



    }

//    private void subscribeTotalQuantity() {
//        viewModel.getSpendings().observe(this, new Observer<List<Spending>>() {
//
//            @Override
//            public void onChanged(@Nullable List<Spending> quantity ) {
//                if (quantity != null) {
//                    for (int i = 0; i < 12; i++) {
//
//                        subscribeSpendings(i + 1,quantity);
//                    }
//
//                }
//            }
//        });
//
//    }


    private BarData subscribeSpendings( int cnt ) {
        List<Spending> Spendingdata = viewModel.getDate();


//        LiveData<List<Spending>> youngUsers1 = viewModel.getSpendings();
        StringBuilder tmp = new StringBuilder(); // Using default 16 character size
        StringBuilder tmd = new StringBuilder(); // Using default 16 character size
        ArrayList<Date> dates = new ArrayList<Date>();
        ArrayList<Double> quantity = new ArrayList<Double>();
        ArrayList<String> graph = new ArrayList<String>();

        String[] months = new DateFormatSymbols().getMonths();

        for (Spending group: Spendingdata){

            dates.add(group.getRawSpending().getDate());
            quantity.add(group.getRawSpending().getQuantity());
            graph.add(group.getGroupName());
        }


//        for (int i = 0; i < grub.size(); i++) {
//             String d = dateFormat.format(dates.get(i));
//
//            if (d == null){
//                d="0";
//            }
//            String month = months[i];
////            Toast.makeText(getContext(),name.get(i)+d + month,Toast.LENGTH_LONG).show();
//
//        }


        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < dates.size(); i++) {
            double pr =quantity.get(i);
            entries.add(new BarEntry(i, (float)(pr) ));
        }

        BarDataSet d = new BarDataSet(entries, "MONTHE " + cnt);
        d.setColors(ColorTemplate.MATERIAL_COLORS);
        d.setBarShadowColor(Color.rgb(203, 203, 203));

        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        sets.add(d);

        BarData cd = new BarData(sets);
        cd.setBarWidth(0.9f);
        return cd;


    }



    private class ChartDataAdapter extends ArrayAdapter<BarData> {

        public ChartDataAdapter(Context context, List<BarData> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            BarData data = getItem(position);

            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();

                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item_barchart, null);
                holder.chart = (BarChart) convertView.findViewById(R.id.chart);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // apply styling

            data.setValueTextColor(Color.BLACK);
            holder.chart.getDescription().setEnabled(false);
            holder.chart.setDrawGridBackground(false);

            XAxis xAxis = holder.chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setDrawGridLines(false);

            YAxis leftAxis = holder.chart.getAxisLeft();

            leftAxis.setLabelCount(5, false);
            leftAxis.setSpaceTop(15f);

            YAxis rightAxis = holder.chart.getAxisRight();

            rightAxis.setLabelCount(5, false);
            rightAxis.setSpaceTop(15f);

            // set data
            holder.chart.setData(data);
            holder.chart.setFitBars(true);

            // do not forget to refresh the chart
//            holder.chart.invalidate();
            holder.chart.animateY(700);

            return convertView;
        }

        private class ViewHolder {

            BarChart chart;
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateData(int cnt ) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new BarEntry(i, (float) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "MONTHE " + cnt);
        d.setColors(ColorTemplate.MATERIAL_COLORS);
        d.setBarShadowColor(Color.rgb(203, 203, 203));

        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        sets.add(d);

        BarData cd = new BarData(sets);
        cd.setBarWidth(0.9f);
        return cd;
    }


}
