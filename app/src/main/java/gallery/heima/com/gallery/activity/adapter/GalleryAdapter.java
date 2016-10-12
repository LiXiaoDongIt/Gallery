package gallery.heima.com.gallery.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gallery.heima.com.gallery.R;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import network.GalleryBean;

/**
 * Created by hasee on 2016/10/12.
 */

public class GalleryAdapter extends MyBaseAdapter<GalleryBean.TngouBean> {
    public GalleryAdapter(Context context, List<GalleryBean.TngouBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_gallery, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GalleryBean.TngouBean bean = getItem(position);
        holder.mDes.setText(bean.getTitle());

        String imageUrl = "http://tnfs.tngou.net/image" + bean.getImg();
        Glide.with(mContext).load(imageUrl).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.mIcon);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_des)
        TextView mDes;
        @BindView(R.id.iv_icon)
        ImageView mIcon;

        public ViewHolder(View root) {
            ButterKnife.bind(this, root);
        }
    }
}
