package Nifra.Nifra.Nifra.models;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {
    @Insert
    void addItem(Item item);
    @Query("SELECT * FROM Item_Infor")
    LiveData<List<Item>> getItems();
    @Update
    void updateItem(Item item);
    @Delete
    void deleteItem(Item item);
}
