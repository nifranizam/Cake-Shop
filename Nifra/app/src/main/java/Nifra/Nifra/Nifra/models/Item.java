package Nifra.Nifra.Nifra.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import Nifra.Nifra.Nifra.config.DbConfig;


@Entity(tableName = DbConfig.TABLE_NAME)
public class Item {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=DbConfig.COLUMN_ITEM_ID)
    private Long id;
    @ColumnInfo(name= DbConfig.COLUMN_ITEM_NAME)
    private String name;
    @ColumnInfo(name=DbConfig.COLUMN_ITEM_DESC)
    private String description;
    @ColumnInfo(name=DbConfig.COLUMN_ITEM_PRICE)
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
