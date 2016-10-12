package gallery.heima.com.gallery.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gallery.heima.com.gallery.R;

/**
 * Created by hasee on 2016/10/12.
 */

public class GalleryDetailsActivity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView mWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_details);
        ButterKnife.bind(this);
        int id = getIntent().getIntExtra("id", 0);
        String url = "http://www.tngou.net/tnfs/show/" + id;
        mWebview.loadUrl(url);
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
