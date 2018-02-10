package saedc.example.com.Model.Pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

import saedc.example.com.Model.Entity.RawSpending;
import saedc.example.com.Model.Entity.Saving;

/**
 * Created by saedc on 10/02/18.
 */

public class saving_pojo {
    @Embedded
    private Saving rawSaving;



    public Saving getRawSaving() {
        return rawSaving;
    }

    public void setRawSpending(Saving spending) {
        this.rawSaving = spending;
    }





    public boolean isSame(saving_pojo s1){
        return s1.getRawSaving().getItem_price().equals(this.getRawSaving().getItem_price())
                && s1.getRawSaving().getItem_saveing().equals(this.getRawSaving().getItem_saveing());

    }
}
