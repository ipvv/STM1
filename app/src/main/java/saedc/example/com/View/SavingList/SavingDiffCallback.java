package saedc.example.com.View.SavingList;

import android.support.v7.util.DiffUtil;

import java.util.List;

import saedc.example.com.Model.Pojo.saving_pojo;

/**
 * Created by saedc on 10/02/18.
 */

public class SavingDiffCallback extends DiffUtil.Callback{
    private final List<saving_pojo> oldSavingList;
    private final List<saving_pojo> newSavingList;

    public SavingDiffCallback(List<saving_pojo> oldEmployeeList, List<saving_pojo> newEmployeeList) {
        this.oldSavingList = oldEmployeeList;
        this.newSavingList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return oldSavingList.size();
    }

    @Override
    public int getNewListSize() {
        return newSavingList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldSavingList.get(oldItemPosition).getRawSaving().getId() ==
                newSavingList.get(newItemPosition).getRawSaving().getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final saving_pojo oldSpending = oldSavingList.get(oldItemPosition);
        final saving_pojo newSpending = newSavingList.get(newItemPosition);

        return oldSpending.isSame(newSpending);
    }
}
