package Nifra.Nifra.Nifra.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;



import java.util.List;


import Nifra.Nifra.Nifra.models.Item;
import Nifra.Nifra.Nifra.models.ItemDAO;
import Nifra.Nifra.Nifra.database.THA_DB;

@SuppressWarnings("ALL")
public class ItemRepository {

    private final ItemDAO itemDao;
    private final LiveData<List<Item>> itemList;

    public ItemRepository(Application application){
        THA_DB database = THA_DB.getInstance(application);
        itemDao = database.DAO();
        itemList = itemDao.getItems();

    }

    public LiveData<List<Item>> getItemList() {
        return itemList;
    }

    public void insertItem(Item item){
        new InsertItem(itemDao).execute(item);
    }

    public void updateItem(Item item){
        new UpdateItem(itemDao).execute(item);
    }

    public void deleteItem(Item item){
        new DeleteItem(itemDao).execute(item);
    }

    private static class InsertItem extends AsyncTask<Item, Void, Void>{
        private final ItemDAO itemDao;

        private InsertItem(ItemDAO itemDao){
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.addItem(items[0]);
            return null;
        }
    }

    private static class UpdateItem extends AsyncTask<Item, Void, Void>{
        private ItemDAO itemDao;

        private UpdateItem(ItemDAO itemDao){
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.updateItem(items[0]);
            return null;
        }
    }

    private static class DeleteItem extends AsyncTask<Item, Void, Void>{
        private ItemDAO itemDao;

        private DeleteItem(ItemDAO itemDao){
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.deleteItem(items[0]);
            return null;
        }
    }
}
