package amir.sina.iz.doc.scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;
import me.pqpo.smartcropperlib.*;
import timber.log.*;
import com.github.florent37.viewtooltip.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.flask.colorpicker.*;
import com.github.angads25.filepicker.*;
import com.android.billingclient.*;
import com.anjlab.android.iab.v3.*;
import com.afollestad.materialdialogs.*;
import com.gitonway.lee.niftymodaldialogeffects.lib.*;
import arabware.forfree.pdfcreator.*;
import ja.burhanrashid52.photoeditor.*;
import com.lwb.piechart.*;
import uk.co.senab.photoview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class SplashActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private LinearLayout linear;
	private ImageView logo;
	
	private TimerTask timer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.splash);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear = findViewById(R.id.linear);
		logo = findViewById(R.id.logo);
	}
	
	private void initializeLogic() {
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						startActivity(new Intent(SplashActivity.this, MainActivity.class)); Animatoo.animateSlideUp(SplashActivity.this);
					}
				});
			}
		};
		_timer.schedule(timer, (int)(1500));
	}
	
	@Override
	public void onStart() {
		super.onStart();
		//Insert into oncreate()
		if(Build.VERSION.SDK_INT >= 21) {
			            Window window = getWindow();
			            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
			                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			            window.setStatusBarColor(Color.TRANSPARENT);
			            window.setNavigationBarColor(Color.TRANSPARENT);
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}