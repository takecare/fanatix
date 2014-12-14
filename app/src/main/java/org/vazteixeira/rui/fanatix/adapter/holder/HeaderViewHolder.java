package org.vazteixeira.rui.fanatix.adapter.holder;

import android.view.View;
import android.widget.TextView;

import org.vazteixeira.rui.fanatix.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HeaderViewHolder {

    @InjectView(R.id.header_view_title_TextView)    public TextView titleTextView;

    public HeaderViewHolder(View view) {

        ButterKnife.inject(this, view);
    }
}
