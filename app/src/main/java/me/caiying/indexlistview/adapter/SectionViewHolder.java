package me.caiying.indexlistview.adapter;

import android.view.View;
import android.widget.TextView;

import me.caiying.indexlistview.R;
import me.caiying.library.indexlistview.IndexBaseCursorAdapter;

/**
 * Created by caiying on 04/02/2016.
 */
public class SectionViewHolder extends IndexBaseCursorAdapter.ViewHolder {
    public TextView textView;

    public SectionViewHolder(View sectionView) {
        super(sectionView);
        textView = (TextView) findWidgetById(R.id.text1);
    }
}
