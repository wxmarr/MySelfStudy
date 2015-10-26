package gl.com.as;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageButton;


public class AnimationActivity extends AppCompatActivity {
    private Button button1,button2,button3,Circularbutton;

    private ImageButton imageSvg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initAnimation();
    }

    private void initAnimation() {
        Circularbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(Circularbutton,
                        Circularbutton.getWidth()/2,
                        Circularbutton.getHeight()/2,
                        Circularbutton.getWidth(),0);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
               // animator.setInterpolator(new PathInterpolator(0.4f,0f,1f,1f));
              //  animator.setInterpolator((TimeInterpolator) AnimatorInflater.loadStateListAnimator(AnimationActivity.this,R.drawable.statechange));
                animator.setDuration(5000);
                animator.start();
            }
        });

    }

    private void initView() {
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        Circularbutton= (Button) findViewById(R.id.CiecularReveal);
       // button1.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this,R.drawable.statechange));
        imageSvg= (ImageButton) findViewById(R.id.svgimagebutton);
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.vectorlist);
        imageSvg.setImageDrawable(drawable);
        if (drawable!=null){
            drawable.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
