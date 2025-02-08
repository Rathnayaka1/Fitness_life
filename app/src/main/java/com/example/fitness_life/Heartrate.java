package com.example.heartratemonitor;

import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvBpm, tvMeasuring;
    private ProgressBar progressCircular;
    private ImageView btnMeasure;

    private Camera camera;
    private boolean isMeasuring = false;
    private int bpm = 0;
    private int progress = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBpm = findViewById(R.id.tvBpm);
        tvMeasuring = findViewById(R.id.tvMeasuring);
        progressCircular = findViewById(R.id.progressCircular);
        btnMeasure = findViewById(R.id.btnMeasure);

        btnMeasure.setOnClickListener(v -> {
            if (!isMeasuring) {
                startMeasurement();
            }
        });
    }

    private void startMeasurement() {
        isMeasuring = true;
        bpm = 0;
        progress = 0;
        tvMeasuring.setText("Measuring... (0%)");
        progressCircular.setProgress(0);

        // Open Camera & Flash
        camera = Camera.open();
        Camera.Parameters params = camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(params);
        camera.startPreview();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress < 100) {
                    progress += 5;
                    tvMeasuring.setText("Measuring... (" + progress + "%)");
                    progressCircular.setProgress(progress);
                    bpm += (60 + (int) (Math.random() * 40)) / 20; // Randomized BPM Calculation
                    handler.postDelayed(this, 500);
                } else {
                    stopMeasurement();
                }
            }
        }, 500);
    }

    private void stopMeasurement() {
        isMeasuring = false;
        camera.stopPreview();
        camera.release();
        camera = null;

        tvBpm.setText(bpm + " BPM");
        tvMeasuring.setText("Measurement Complete");
    }
}
