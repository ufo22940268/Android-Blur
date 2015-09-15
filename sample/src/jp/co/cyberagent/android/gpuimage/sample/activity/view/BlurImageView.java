package jp.co.cyberagent.android.gpuimage.sample.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import jp.co.cyberagent.android.gpuimage.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * Created by cc on 9/15/15.
 */
public class BlurImageView extends GPUImageView {

    private int mActionIndex;

    public BlurImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mActionIndex = event.getActionIndex();
                return true;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY(mActionIndex);
                float x = event.getX(mActionIndex);
                focus(x, y);
                requestRender();
                return true;
        }

        return super.onTouchEvent(event);
    }

    private void focus(float x, float y) {
        GPUImageFilter filter = getFilter();
        if (filter != null && filter instanceof GPUImageBoxBlurFilter) {
            GPUImageBoxBlurFilter blurFilter = (GPUImageBoxBlurFilter) filter;
            blurFilter.focus(x, y);
        }
    }
}
