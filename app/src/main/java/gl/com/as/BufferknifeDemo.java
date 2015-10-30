package gl.com.as;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindInt;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mac on 15-10-30.
 */
public class BufferknifeDemo extends AppCompatActivity {

    @BindInt(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.image)
    public void clickImage(){

    }
}
