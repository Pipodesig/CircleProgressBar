package mobi.pipo.circleprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    PipoCircleBar diagram;
    Button btn;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diagram = (PipoCircleBar)findViewById(R.id.diagram);
        btn = (Button)findViewById(R.id.button);
        text = (EditText)findViewById(R.id.testPercent);
        diagram.startAnimation(100);
        btn.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int getPercent = Integer.parseInt(text.getText().toString());
        diagram.startAnimation(getPercent);
    }
}
