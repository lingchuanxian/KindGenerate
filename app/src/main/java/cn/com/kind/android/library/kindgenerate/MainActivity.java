package cn.com.kind.android.library.kindgenerate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import cn.com.kind.android.library.generate.annotations.KindListActivity;

/**
 * @author lingchuanxian
 */
@KindListActivity(busikey = "collectdoc")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
