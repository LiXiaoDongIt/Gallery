package gallery.heima.com.gallery.activity.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by hasee on 2016/10/12.
 */

public abstract  class MyBaseAdapter<T> extends BaseAdapter {

    protected   Context mContext;
    protected List<T> mData;

    public MyBaseAdapter(Context context, List<T> data){
        mContext = context;
        mData = data;
    }


    @Override
    public int getCount() {
        if(mData == null){
            return  0;
        }
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
