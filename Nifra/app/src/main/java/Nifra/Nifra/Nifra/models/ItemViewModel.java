package Nifra.Nifra.Nifra.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import Nifra.Nifra.Nifra.database.ItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private final ItemRepository itemRepository;
    private final LiveData<List<Item>> itemList;

    public ItemViewModel(@NonNull Application application){
        super(application);
        itemRepository = new ItemRepository(application);
        itemList = itemRepository.getItemList();
    }

    public void insertItem(Item item){
        itemRepository.insertItem(item);
    }

    public void updateItem(Item item){
        itemRepository.updateItem(item);
    }

    public void deleteItem(Item item){
        itemRepository.deleteItem(item);
    }

    public LiveData<List<Item>> getItemList(){
        return itemList;
    }
}
