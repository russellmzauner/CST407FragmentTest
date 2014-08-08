package org.russell.fragmenttest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;

public class CaptureActivity extends Activity {

	   private final static String TAG = "CaptureActivity";
	   private Camera cameraObject;
	   private CameraHelper cameraHelper;
	 //  private ImageView pic;

	   private PictureCallback capturedIt = new PictureCallback() {

	      @Override
	      public void onPictureTaken(byte[] data, Camera camera) {

	      Bitmap bitmap = BitmapFactory.decodeByteArray(data , 0, data .length);
	      if(bitmap==null){
	         Toast.makeText(getApplicationContext(), "not taken", Toast.LENGTH_SHORT).show();
	      }
	      else
	      {
	         Toast.makeText(getApplicationContext(), "taken", Toast.LENGTH_SHORT).show();    	
	      }
	      //cameraObject.release();
	   }
	};

	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_capture);
	  //    pic = (ImageView)findViewById(R.id.imageView1);
	      cameraObject = isCameraAvailiable();
	      cameraHelper = new CameraHelper(this, cameraObject);
	      FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
	      preview.addView(cameraHelper);
	   }

	   @Override
	   protected void onDestroy() {
		   if(cameraObject != null) {
			   cameraObject.release();
			   cameraObject = null;
		   }
		   super.onDestroy();
	   }
	   
	   public static Camera isCameraAvailiable(){
		      Camera object = null;
		      try {
		         object = Camera.open(); 
		      }
		      catch (Exception e){
			      Log.e(TAG, "Camera open exception: " + e);
		      }
		      return object; 
		   }
	   
	   public void snapIt(View view){
	      cameraObject.takePicture(null, null, capturedIt);
	   }


}
