package com.example.androidloadpicture;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity {

	ImageButton button00, button01;// button1, button0,
	Bitmap bmp;
	Bitmap bmp2;
	BitmapFactory.Options opts = new BitmapFactory.Options();
	int A = 0;
	// int isover = 0;
	RelativeLayout relativeLayout1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button00 = (ImageButton) findViewById(R.id.imageButton0);
		button01 = (ImageButton) findViewById(R.id.imageButton1);
//		button00.setOnClickListener(this);
//		button01.setOnClickListener(this);
		relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
		DisplayMetrics dm = new DisplayMetrics();
		MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);

		if (dm.widthPixels > 720 || dm.heightPixels > 1280) {
			opts.inSampleSize = 0;
		} else {
			opts.inSampleSize = 5;
		}

	}
	public void onClick(View v) {
        // Perform action on click
       switch(v.getId()) {
         case R.id.imageButton0:
           //Play voicefile
         
           break;
         case R.id.imageButton1:
           //Stop MediaPlayer
        	 
           break;
       }
    }
	// 非同步載入圖片
	public void TouchToDia() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				loadimage();
				Bundle countBundle = new Bundle();
				countBundle.putInt("count", 1);

				Message msg = new Message();
				msg.setData(countBundle);

				mHandler.sendMessage(msg);
			}

		}).start();
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (!MainActivity.this.isFinishing()) {
				button00.setImageBitmap(bmp);
				button01.setImageBitmap(bmp2);

				relativeLayout1.setVisibility(View.GONE);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Leave2();
			relativeLayout1.setVisibility(View.VISIBLE);
			TouchToDia();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// 提示窗
	private void Leave2() {

		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

		builder.setMessage("請點選哪一張比較喜歡?").setPositiveButton("是",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		AlertDialog about_dialog = builder.create();
		about_dialog.show();
		// }
	}

	public void loadimage() {
		if (A == 0) {
			A = 1;
			Resources res = getResources();
			bmp = BitmapFactory.decodeResource(res, R.drawable.a1, opts);
			bmp2 = BitmapFactory.decodeResource(res, R.drawable.b1, opts);

		} else {
			A = 0;
			Resources res = getResources();
			bmp = BitmapFactory.decodeResource(res, R.drawable.pic_choose_01,
					opts);
			bmp2 = BitmapFactory.decodeResource(res, R.drawable.pic_choose_02,
					opts);

		}

	}
}
