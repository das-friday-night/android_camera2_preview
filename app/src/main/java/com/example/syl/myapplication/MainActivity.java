package com.example.syl.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;


public class MainActivity extends AppCompatActivity {

    private TextureView mTextureView;
    private CameraPreview cameraPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextureView = findViewById(R.id.textureView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraPreview = new CameraPreview(this, mTextureView);

        // When the screen is turned off and turned back on, the SurfaceTexture is already
        // available, and "onSurfaceTextureAvailable" will not be called. In that case, we can open
        // a camera and start preview from here (otherwise, we wait until the surface is ready in
        // the SurfaceTextureListener).
        if (mTextureView.isAvailable()) {
            cameraPreview.openCamera(mTextureView.getWidth(), mTextureView.getHeight());
        } else {
            mTextureView.setSurfaceTextureListener(cameraPreview.getSurfaceTextureListener());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraPreview.closeCamera();
    }

}
