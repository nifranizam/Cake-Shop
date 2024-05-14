package Nifra.Nifra.Nifra;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import Nifra.Nifra.Nifra.adapters.ItemListAdapter;
import Nifra.Nifra.Nifra.models.Item;
import Nifra.Nifra.Nifra.models.ItemViewModel;
import uom.aysha.tha_204013d.R;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemListAdapter itemListAdapter;
    FloatingActionButton floatingActionButton;
    ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton =findViewById(R.id.fab_addItembtn);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CakeFormActivity.class);
            intent.putExtra("type",1);
            startActivityForResult(intent,1);
        });

        recyclerView = findViewById(R.id.rv_cakeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemListAdapter = new ItemListAdapter(MainActivity.this);
        recyclerView.setAdapter(itemListAdapter);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        itemViewModel.getItemList().observe(this, items -> {
            itemListAdapter.submitList(items);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent i = data;
        if (i != null){
            if (resultCode == 1){
                Item item = new Item();
                item.setName(i.getExtras().getString("ProductTitle").toString());
                item.setDescription(i.getExtras().getString("ProductDesc").toString());
                item.setPrice( i.getExtras().getDouble("ProductPrice"));
                itemViewModel.insertItem(item);
                recyclerView.smoothScrollToPosition(itemViewModel.getItemList().getValue().size());
            }else if (resultCode==2){
                Item item = new Item();
                item.setId(i.getExtras().getLong("ID"));
                item.setName(i.getExtras().getString("ProductTitle").toString());
                item.setDescription(i.getExtras().getString("ProductDesc").toString());
                item.setPrice( i.getExtras().getDouble("ProductPrice"));
                itemViewModel.updateItem(item);
            }else {
                itemViewModel.deleteItem(itemListAdapter.getItemAt(i.getExtras().getInt("Position")));
            }
        }
    }
}