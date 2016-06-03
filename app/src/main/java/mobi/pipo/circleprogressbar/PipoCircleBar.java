package mobi.pipo.circleprogressbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Serhiy Polishchuk on 03.06.16.
 */
public class PipoCircleBar extends RelativeLayout  {
    private static final String PERCENT = "%";
    private final LayoutInflater mInflater;
    private ProgressBar progressBar;
    private ProgressBar point;
    private TextView percentText;
    private int pointSize = 8;
    private int prg = 0;

    public PipoCircleBar(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public PipoCircleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public PipoCircleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();

    }

    public void init() {
        mInflater.inflate(R.layout.pipo_circle, this, true);

        progressBar = (ProgressBar) findViewById(R.id.progressBarInvisible);
        point = (ProgressBar) findViewById(R.id.progressBarPoint);
        percentText = (TextView)findViewById(R.id.percent);
    }

    private int progress(int realProgress) {

        int progress = 100 - realProgress;
        return progress;
    }

    private float rotation(int realProgress) {

        float coef = 3.6f;
        float degree;
        degree = (realProgress * coef) - 90f;
        return degree;
    }

    public void startAnimation(int percent) {
        prg=percent;

        if (prg > 100 || prg < 0) {
            prg = 0;
        }

        ObjectAnimator animationDashCircle = ObjectAnimator.ofInt(progressBar, "progress", 100, progress(prg)); // see this max value coming back here, we animale towards that value
        animationDashCircle.setDuration(600); //in milliseconds
        animationDashCircle.setInterpolator(new DecelerateInterpolator());
        progressBar.setRotation(rotation(prg));
        animationDashCircle.start();

        ObjectAnimator animationPoint = ObjectAnimator.ofInt(point, "progress", 0, pointSize); // see this max value coming back here, we animale towards that value
        animationPoint.setDuration(600); //in milliseconds
        animationPoint.setInterpolator(new DecelerateInterpolator());
        point.setRotation(rotation(prg - 4));
        animationPoint.start();

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(550);

        progressBar.startAnimation(rotate);
        point.startAnimation(rotate);

        setPercentage(prg);
    }

    public void setPercentage (int percent){
        String textForPercent = Integer.toString(percent)+ PERCENT;
        percentText.setText(textForPercent);
    }

}
