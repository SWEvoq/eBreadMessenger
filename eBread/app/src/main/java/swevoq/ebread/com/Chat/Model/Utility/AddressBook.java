package swevoq.ebread.com.Chat.Model.Utility;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import swevoq.ebread.com.R;

/**
 * Created by Nicolae on 08/04/2017.
 */

public class AddressBook extends ArrayAdapter<String> {
    private SparseBooleanArray selectedItemsIds;
    private LayoutInflater inflater;
    private Context context;
    private List<String> list;

    public AddressBook (Context context, int resourceId, List<String> list){
        super(context,resourceId,list);
        selectedItemsIds = new SparseBooleanArray();
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    private static class ViewHolder{
        TextView itemName;
    }

    public View getView(int position, View view, ViewGroup parent){
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_textview,null);
            holder.itemName = (TextView) view.findViewById(R.id.custom_tv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.itemName.setText(list.get(position));
        return view;
    }

    @Override
    public void remove(String string){
        list.remove(string);
        notifyDataSetChanged();
    }

    public void toggleSelection(int position){
        selectView(position,!selectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value){
        if(value)
            selectedItemsIds.put(position,value);
        else
            selectedItemsIds.delete(position);
        notifyDataSetChanged();
    }
    public SparseBooleanArray getSelectedItemsIds(){
        return selectedItemsIds;
    }
}
