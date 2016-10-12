package gallery.heima.com.gallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gallery.heima.com.gallery.R;
import gallery.heima.com.gallery.activity.adapter.GalleryAdapter;
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
            mListView.setAdapter(new GalleryAdapter(GalleryActivity.this,mData));
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
}
