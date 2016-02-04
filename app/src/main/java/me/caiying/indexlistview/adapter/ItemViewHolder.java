package me.caiying.indexlistview.adapter;

import android.view.View;
import android.widget.TextView;

import me.caiying.indexlistview.R;
import me.caiying.library.indexlistview.IndexBaseCursorAdapter;

/**
 * Created by caiying on 04/02/2016.
 */
public class ItemViewHolder extends IndexBaseCursorAdapter.ViewHolder {
    public TextView textView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) findWidgetById(R.id.text);
    }
}
