package me.caiying.indexlistview.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.caiying.indexlistview.R;
import me.caiying.indexlistview.database.User;
import me.caiying.library.indexlistview.IndexBaseCursorAdapter;

/**
 * Created by caiying on 03/02/2016.
 */
public class DemoCursorAdapter extends IndexBaseCursorAdapter {

    public DemoCursorAdapter(Context context) {
        super(context, null, 0, R.layout.row_section, R.layout.row_text);
    }

    @Override
    protected Object getSectionFromCursor(Cursor cursor) throws IllegalStateException {
        return User.objectFromCursor(cursor).name.substring(0, 1).toUpperCase();
    }

    @Override
    protected ViewHolder createSectionViewHolder(View sectionView, Object section) {
        return new SectionViewHolder(sectionView);
    }

    @Override
    protected void bindSectionViewHolder(int position, ViewHolder viewHolder, ViewGroup parent, Object section) {
        SectionViewHolder sectionViewHolder = (SectionViewHolder) viewHolder;
        sectionViewHolder.textView.setText(String.valueOf(section));
    }

    @Override
    protected ViewHolder createItemViewHolder(Cursor cursor, View itemView) {
        return new ItemViewHolder(itemView);
    }

    @Override
    protected void bindItemViewHolder(ViewHolder viewHolder, Cursor cursor, ViewGroup parent) {
        User user = User.objectFromCursor(cursor);
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.textView.setText(user.name);
    }

    class SectionViewHolder extends ViewHolder {
        public TextView textView;

        public SectionViewHolder(View sectionView) {
            super(sectionView);
            textView = (TextView) findWidgetById(R.id.text1);
        }
    }

    private class ItemViewHolder extends ViewHolder {
        public TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) findWidgetById(R.id.text);
        }
    }
}
