package saedc.example.com.Model.Pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

import saedc.example.com.Model.Entity.RawSpending;


public class Spending {

    @Embedded
    private RawSpending rawSpending;

    @ColumnInfo(name = "group_name")
    private String groupName;

    public RawSpending getRawSpending() {
        return rawSpending;
    }

    public void setRawSpending(RawSpending spending) {
        this.rawSpending = spending;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isSame(saedc.example.com.Model.Pojo.Spending s1){
        return s1.getRawSpending().getDescription().equals(this.getRawSpending().getDescription())
                && s1.getRawSpending().getQuantity().equals(this.getRawSpending().getQuantity())
                && s1.getRawSpending().getGroupId() == this.getRawSpending().getGroupId()
                && s1.getGroupName().equals(this.getGroupName());

    }
}
