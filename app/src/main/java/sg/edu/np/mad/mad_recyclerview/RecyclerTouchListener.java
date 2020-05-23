//Ignore this, this code was originally meant for the delete function through
//click on the recycler view but it was for the entire row including the checkbox.
//Leaving it here for reference.
/**package sg.edu.np.mad.mad_recyclerview;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    //Custom set touch listener.
    private GestureDetector gestureDetector;
    private RecyclerViewClickListener clickListener;

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerViewClickListener clickListener) {
        this.clickListener = clickListener;
        //Search up on the different motions, in this case its a single tap.
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }

}*/

