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
import gallery.heima.com.gallery.activity.adapter.MainAdapter;
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
            mListView.setAdapter(new MainAdapter(MainActivity.this, mData));
            mListView.setOnItemClickListener(mOnItemClickListener);
        }
    };

    

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 跳转页面
            int mainId = mData.get(position).getId();
            Intent intent = new Intent(MainActivity.this,GalleryActivity.class);
            intent.putExtra("id",mainId);
            startActivity(intent);

        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
        }
    };


}
