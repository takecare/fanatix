package org.vazteixeira.rui.fanatix.adapter.holder;

import android.view.View;
import android.widget.TextView;

import org.vazteixeira.rui.fanatix.R;

import butterknife.InjectView;

/**
 * Created by rmvt on 14/12/14.
 */
public class RecommendedViewHolder extends FriendViewHolder {

    @InjectView(R.id.list_row_recommended_team_TextView) public TextView teamTextView;

    public RecommendedViewHolder(View view) {

        super(view);
    }
}
