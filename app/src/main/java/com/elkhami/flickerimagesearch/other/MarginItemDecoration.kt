package com.elkhami.flickerimagesearch.other

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elkhami.flickerimagesearch.other.Constants.GRID_SPAN_COUNT

/**
 * Created by A.Elkhami on 16,July,2021
 */

class MarginItemDecoration(
    private val spaceSize: Int,
    private val spanCount: Int = GRID_SPAN_COUNT,
    private val orientation: Int = GridLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (orientation == GridLayoutManager.VERTICAL) {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    top = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    left = spaceSize
                }
            } else {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    left = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    top = spaceSize
                }
            }

            right = spaceSize
            bottom = spaceSize
        }
    }
}