package gallery.heima.com.gallery.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gallery.heima.com.gallery.R;
import network.GalleryHomeBean;
import network.NetworkManager;
import network.SexyGirlRequest;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_listview)
    ListView mListView;

    private List<GalleryHomeBean.TngouBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadServiceData();
    }

    /**
     * 发送网络数据
     */
    private void loadServiceData() {
        String url = "http://apis.baidu.com/tngou/gallery/classify";
        SexyGirlRequest request = new SexyGirlRequest(url, GalleryHomeBean.class, mSuccessListener, mErrorListener);
        NetworkManager.sendRequest(request);
    }

    private Response.Listener mSuccessListener = new Response.Listener<GalleryHomeBean>() {
        @Override
        public void onResponse(GalleryHomeBean response) {
            mData = response.getTngou();
            mListView.setAdapter(mBaseAdapter);
            mListView.setOnItemClickListener(mOnItemClickListener);
        }
    };

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 跳转页面
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

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
        public GalleryHomeBean.TngouBean getItem(int position) {
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
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item_gallery_home, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            GalleryHomeBean.TngouBean bean = getItem(position);
            holder.mTextView.setText(bean.getTitle());
            return convertView;
        }
    };

    class ViewHolder {

        @BindView(R.id.home_tv_title)
        TextView mTextView;

        public ViewHolder(View root) {
            ButterKnife.bind(this, root);
        }
    }
}
