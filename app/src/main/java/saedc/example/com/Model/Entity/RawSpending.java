package saedc.example.com.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import saedc.example.com.Model.Database.Converters;
import saedc.example.com.Model.Entity.SpendingGroup;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "spending")
public class RawSpending implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ForeignKey(entity = SpendingGroup.class, parentColumns = "group-id", childColumns = "group-id")
    @ColumnInfo(name = "group_id")
    private int groupId;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "quantity")
    private Double quantity;

    @ColumnInfo(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
