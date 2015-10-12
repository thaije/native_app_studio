package com.porsi.tjalling.ghostlyv2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tjalling on 10/11/15.
 */

public class HashMapAdapter extends BaseAdapter {

    public Map<String, Integer> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    String[] mapKeys;

    /*
     * Constructor for the treemap adapter
     */
    public HashMapAdapter(Activity activity, Map<String, Integer> list){
        super();
        this.activity=activity;
        Set<String> myset = list.keySet();
        mapKeys = myset.toArray(new String[myset.size()]);

        this.list = MapUtil.sortByValue( list );
    }


    /*
     * Get the size of the treemap
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /*
     * Get the value on the current position
     */
    @Override
    public String getItem(int position) {
        return mapKeys[position];
    }

    /*
     * Get the key on the current position
     */
    @Override
    public long getItemId(int position) {
        return list.get(mapKeys[position]);
    }


    /*
     * Override the getview method
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();

        // set the new textviews to the listview
        if(convertView == null){
            convertView=inflater.inflate(R.layout.listview_highscore, null);
            txtFirst=(TextView) convertView.findViewById(R.id.playerListView);
            txtSecond=(TextView) convertView.findViewById(R.id.scoreListView);
        }

        // Get the key and value at a certain position
        // Count - position to flip the listview and have the highest score on top
        String key = (list.keySet().toArray()[getCount() - position - 1]).toString();
        Integer value = list.get(key);

        // set the text values
        txtFirst.setText(key);
        txtSecond.setText(value.toString());

        return convertView;
    }

}


/*
 * Sorts a hash map
 */
class MapUtil
{
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}


