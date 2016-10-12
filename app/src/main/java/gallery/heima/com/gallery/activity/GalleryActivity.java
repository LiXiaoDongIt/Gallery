package gallery.heima.com.gallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gallery.heima.com.gallery.R;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import network.GalleryBean;
import network.NetworkManager;
import network.SexyGirlRequest;

/**
 * Created by hasee on 2016/10/12.
 */

public class GalleryActivity extends AppCompatActivity {
    @BindView(R.id.grllery_list_view)
    ListView mListView;
    private List<GalleryBean.TngouBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        loadServiceData();
    }

    private void loadServiceData() {
        int id = getIntent().getIntExtra("id", 0);
        String url = "http://apis.baidu.com/tngou/gallery/list?id=" + id + "&page=1&rows=20";
        SexyGirlRequest request = new SexyGirlRequest(url, GalleryBean.class, mSuccessListener, mErrorListener);
        NetworkManager.sendRequest(request);
    }

    private Response.Listener mSuccessListener = new Response.Listener<GalleryBean>() {
        @Override
        public void onResponse(GalleryBean response) {
            mData = response.getTngou();
            mListView.setAdapter(mBaseAdapter);
            mListView.setOnItemClickListener(mOnItemClickListener);
        }
    };

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int detailsId = mData.get(position).getId();
            Intent intent = new Intent(GalleryActivity.this,GalleryDetailsActivity.class);
            intent.putExtra("id",detailsId);
            startActivity(intent);
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(GalleryActivity.this, "网络错误", Toast.LENGTH_LONG).show();
        }
    };

    private BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (mData == null) {
                return 0;
            }
            return mData.size();
        }

        @Override
        public GalleryBean.TngouBean getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(GalleryActivity.this).inflate(R.layout.list_item_gallery, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            GalleryBean.TngouBean bean = getItem(position);
            holder.mDes.setText(bean.getTitle());

            String imageUrl = "http://tnfs.tngou.net/image" + bean.getImg();
            Glide.with(GalleryActivity.this).load(imageUrl).bitmapTransform(new CropCircleTransformation(GalleryActivity.this)).into(holder.mIcon);

            return convertView;
        }
    };

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
