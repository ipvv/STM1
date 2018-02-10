package saedc.example.com.View.TotalSpendingQuantity;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import saedc.example.com.R;
import saedc.example.com.View.TotalSpendingQuantity.TotalSpendingQuantityViewModel;

import java.nio.CharBuffer;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TotalSpendingQuantityFragment extends LifecycleFragment {

    saedc.example.com.View.TotalSpendingQuantity.TotalSpendingQuantityViewModel viewModel;

    @BindView(R.id.quantity)
    TextView txtQuantity;

    @BindView(R.id.quantity1)
    TextView txtQuantity1;

    @BindView(R.id.quantity2)
    TextView txtQuantity2;

    @BindView(R.id.quantity3)
    TextView txtQuantity3;

    @BindView(R.id.quantity4)
    TextView txtQuantity4;

    @BindView(R.id.quantity1_1)
    TextView txtQuantity1_1;

    @BindView(R.id.quantity2_2)
    TextView txtQuantity2_2;

    @BindView(R.id.quantity3_3)
    TextView txtQuantity3_3;

    @BindView(R.id.quantity4_4)
    TextView txtQuantity4_4;


    //    progressBar
    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;

    @BindView(R.id.progressBar4)
    ProgressBar progressBar4;

    @BindView(R.id.progressBar5)
    ProgressBar progressBar5;

    @BindView(R.id.progressBar6)
    ProgressBar progressBar6;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(saedc.example.com.View.TotalSpendingQuantity.TotalSpendingQuantityViewModel.class);
        subscribeTotalQuantity();

        new LongOperation().execute();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_total_spending_quantity, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    private void subscribeTotalQuantity() {
        viewModel.totalSpendingQuantity.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(final Double quantity) {
                if (quantity != null) {




                    showTotalQuantityInUi(quantity, txtQuantity);



                    new LongOperation().execute();

                } else {
                    showTotalQuantityInUi(0.0, txtQuantity);
                }
            }
        });

        //i change it
        viewModel.GetTotalSpendingQuantity1().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(final Double quantity) {
                if (quantity != null) {
                    showTotalQuantityInUi1(quantity, txtQuantity1);
                } else {
                    showTotalQuantityInUi1(0.0, txtQuantity1);
                }
            }
        });
        viewModel.GetTotalSpendingQuantity2().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(final Double quantity) {
                if (quantity != null) {
                    showTotalQuantityInUi2(quantity, txtQuantity2);
                } else {
                    showTotalQuantityInUi2(0.0, txtQuantity2);
                }
            }
        });
        viewModel.GetTotalSpendingQuantity3().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(final Double quantity) {
                if (quantity != null) {
                    showTotalQuantityInUi3(quantity, txtQuantity3);
                } else {
                    showTotalQuantityInUi3(0.0, txtQuantity3);
                }
            }
        });
        viewModel.GetTotalSpendingQuantity4().observe(this, new Observer<Double>() {
                    @Override
                    public void onChanged(final Double quantity) {
                        if (quantity != null) {
                            showTotalQuantityInUi4(quantity, txtQuantity4);


                        } else {
                            showTotalQuantityInUi4(0.0, txtQuantity4);
                        }
                    }
                }
        );

    }


    private void showTotalQuantityInUi(@NonNull Double quantity, final TextView txtQuantity) {
        //round two places
        quantity = Math.round(quantity * 100.0) / 100.0;
        String text = getContext().getString(R.string.turkish_lira_symbol) + String.valueOf(quantity);
        txtQuantity.setText(text);
    }

    private void showTotalQuantityInUi1(@NonNull Double quantity, final TextView txtQuantity) {
        //round two places
        quantity = Math.round(quantity * 100.0) / 100.0;
        String text = getContext().getString(R.string.GAS) + " : " + String.valueOf(quantity);
        txtQuantity.setText(text);
    }

    private void showTotalQuantityInUi2(@NonNull Double quantity, final TextView txtQuantity) {
        //round two places
        quantity = Math.round(quantity * 100.0) / 100.0;
        String text = getContext().getString(R.string.FOOD) + " : " + String.valueOf(quantity);
        txtQuantity.setText(text);
    }

    private void showTotalQuantityInUi3(@NonNull Double quantity, final TextView txtQuantity) {
        //round two places
        quantity = Math.round(quantity * 100.0) / 100.0;
        String text = getContext().getString(R.string.CLOTHES) + " : " + String.valueOf(quantity);
        txtQuantity.setText(text);
    }

    private void showTotalQuantityInUi4(@NonNull Double quantity, final TextView txtQuantity) {
        //round two places
        quantity = Math.round(quantity * 100.0) / 100.0;
        String text = getContext().getString(R.string.OTHER) + " : " + String.valueOf(quantity);
        txtQuantity.setText(text);

    }









    private void CalculatePercentage(final TextView total, final TextView obtained, final TextView result, ProgressBar bar) {

        try {
            if (!obtained.getText().equals("")) {

                String obtained1 = obtained.getText().toString();

                obtained1 = obtained1.replaceAll("\\D", "");

//        Toast.makeText(getContext(),  obtained1, Toast.LENGTH_SHORT).show();

                double value = Double.parseDouble(obtained1);

                String total1 = total.getText().toString();

                total1 = total1.replaceAll("\\D", "");

                double value1 = Double.parseDouble(total1);

                double Percentage = ((value * 100.0) / value1);

                Percentage = Math.round(Percentage * 100.0) / 100.0;

                int rsult = (int) Percentage;

                String finalresult = String.valueOf(Percentage) + "%";

                result.setText(finalresult);


                bar.setProgress(rsult);

            } else {
                String finalresult =  "0.0%";

                result.setText(finalresult);
                bar.setProgress(0);
            }


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return;
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            CalculatePercentage(txtQuantity, txtQuantity1, txtQuantity1_1, progressBar3);



            CalculatePercentage(txtQuantity, txtQuantity2, txtQuantity2_2, progressBar4);



            CalculatePercentage(txtQuantity, txtQuantity3, txtQuantity3_3, progressBar5);



            CalculatePercentage(txtQuantity, txtQuantity4, txtQuantity4_4, progressBar6);

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
