package saedc.example.com.View.SpendingList;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import saedc.example.com.Model.Entity.RawSpending;
import saedc.example.com.Model.Pojo.Spending;
import saedc.example.com.R;
import saedc.example.com.View.MainActivity;
import saedc.example.com.View.AddAndEditSpending.AddAndEditSpendingFragment;
import saedc.example.com.View.SpendingList.*;
import saedc.example.com.View.SpendingList.RecyclerVewItemClickListener;
import saedc.example.com.View.SpendingList.SpendingListViewModel;
import saedc.example.com.View.SpendingList.SpendingRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SpendingListFragment extends LifecycleFragment implements RecyclerVewItemClickListener {
    ArrayList<Spending> spendingList = new ArrayList<>();
    SpendingListViewModel viewModel;
    SpendingRecyclerViewAdapter adapter;
    View view;

    @BindView(R.id.spending_recyclerview)
    RecyclerView spendingRecyclerView;

    public static saedc.example.com.View.SpendingList.SpendingListFragment newInstance() {

        return new saedc.example.com.View.SpendingList.SpendingListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_spending_list, container, false);
        ButterKnife.bind(this, view);

        adapter = new SpendingRecyclerViewAdapter(getActivity(), spendingList, this);
        spendingRecyclerView.setAdapter(adapter);
        spendingRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SpendingListViewModel.class);
        subscribeSpendings();
    }

    private void subscribeSpendings() {
        viewModel.spendings.observe(this, new Observer<List<Spending>>() {
            @Override
            public void onChanged(final List<Spending> spendings) {
                adapter.updateItems(spendings);
            }
        });
    }

    @Override
    public void onItemClick(RawSpending clickedSpending) {
        ((MainActivity)getActivity()).showFragment(AddAndEditSpendingFragment.newInstance(clickedSpending));
    }

    @Override
    public void onItemLongClick(int longClickedSpendingId) {
        viewModel.deleteSpending(longClickedSpendingId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}
