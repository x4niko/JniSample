package cn.autoref.jnisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    static {
        //名字需要跟build.gradle的ndk节点下的名字一样
        System.loadLibrary("nativeLib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.show_text_view);
        NativeMethod nativeMethod = new NativeMethod();
        textView.setText(nativeMethod.getHelloJniText());
    }
}
