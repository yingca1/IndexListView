package me.caiying.indexlistview.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

import me.caiying.indexlistview.R;
import me.caiying.indexlistview.database.User;
import me.caiying.library.indexlistview.IndexBaseCursorAdapter;

/**
 * Created by caiying on 03/02/2016.
 */
public class DemoCursorAdapter extends IndexBaseCursorAdapter<String, SectionViewHolder, ItemViewHolder> {

    public DemoCursorAdapter(Context context) {
        super(context, null, 0, R.layout.row_section, R.layout.row_text);
    }

    @Override
    protected String getSectionFromCursor(Cursor cursor) throws IllegalStateException {
        return User.objectFromCursor(cursor).name.substring(0, 1).toUpperCase();
    }

    @Override
    protected SectionViewHolder createSectionViewHolder(View sectionView, String section) {
        return new SectionViewHolder(sectionView);
    }

    @Override
    protected void bindSectionViewHolder(int position, SectionViewHolder sectionViewHolder, ViewGroup parent, String section) {
        sectionViewHolder.textView.setText(String.valueOf(section));
    }

    @Override
    protected ItemViewHolder createItemViewHolder(Cursor cursor, View itemView) {
        return new ItemViewHolder(itemView);
    }

    @Override
    protected void bindItemViewHolder(ItemViewHolder itemViewHolder, Cursor cursor, ViewGroup parent) {
        User user = User.objectFromCursor(cursor);
        itemViewHolder.textView.setText(user.name);
    }
}
