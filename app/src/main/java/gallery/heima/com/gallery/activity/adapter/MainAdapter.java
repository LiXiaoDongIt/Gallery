package gallery.heima.com.gallery.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gallery.heima.com.gallery.R;
import network.GalleryHomeBean;

/**
 * Created by hasee on 2016/10/12.
 */

public class MainAdapter extends MyBaseAdapter<GalleryHomeBean.TngouBean> {


    public MainAdapter(Context context,  List<GalleryHomeBean.TngouBean> data) {
        super(context, data);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_gallery_home, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GalleryHomeBean.TngouBean bean = getItem(position);
        holder.mTextView.setText(bean.getTitle());
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.home_tv_title)
        TextView mTextView;

        public ViewHolder(View root) {
            ButterKnife.bind(this, root);
        }
    }

}
