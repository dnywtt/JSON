package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.List;
 
 
public class ProductListAdapterSimple extends ArrayAdapter<Product>{
 
    List<Product> mylist;
 
    public ProductListAdapterSimple(Context _context, List<Product> _mylist) {
        super(_context, R.layout.list_item, _mylist);
 
        this.mylist = _mylist;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        convertView = vi.inflate(R.layout.list_item, parent, false);
 
 
        // Product object
        Product product = getItem(position);
 
 
        //
        TextView txtTitle = (TextView) convertView.findViewById(R.id.name1);
        txtTitle.setText(product.name);
 
        // show image
        ImageView img = (ImageView)convertView.findViewById(R.id.image1);
 
        // download image
        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.download(product.img_url, img);
 
        return convertView;
    }
 
 
}