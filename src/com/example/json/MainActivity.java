	package com.example.json;
	
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
 
public class MainActivity extends Activity {
    List products;
    ListView lvProducts;
 
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        // populate data
        products = new ArrayList();
        products.add(new Product("Orange","http://farm5.staticflickr.com/4142/4787427683_3672f1db9a_s.jpg"));
        products.add(new Product("Apple","http://farm4.staticflickr.com/3139/2780642603_8d2c90e364_s.jpg"));
        products.add(new Product("Pineapple","http://farm2.staticflickr.com/1008/1420343003_13eeb0f9f3_s.jpg"));
 
        //
        lvProducts = (ListView) findViewById( R.id.list_products);
        lvProducts.setAdapter(new ProductListAdapterSimple(this, products) );
 
    }
}