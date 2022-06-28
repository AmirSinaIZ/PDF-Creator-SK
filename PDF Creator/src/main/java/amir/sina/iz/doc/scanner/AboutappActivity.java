package amir.sina.iz.doc.scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
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
import android.widget.ScrollView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.*;
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

public class AboutappActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ScrollView vscroll1;
	private PieChartView Pie;
	private TextView textview2;
	private LinearLayout linear3;
	private TextView textview1;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private TextView textview3;
	private CircleImageView circleimageview1;
	private CircleImageView circleimageview2;
	private CircleImageView circleimageview3;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.aboutapp);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		vscroll1 = findViewById(R.id.vscroll1);
		Pie = findViewById(R.id.Pie);
		textview2 = findViewById(R.id.textview2);
		linear3 = findViewById(R.id.linear3);
		textview1 = findViewById(R.id.textview1);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		textview3 = findViewById(R.id.textview3);
		circleimageview1 = findViewById(R.id.circleimageview1);
		circleimageview2 = findViewById(R.id.circleimageview2);
		circleimageview3 = findViewById(R.id.circleimageview3);
	}
	
	private void initializeLogic() {
		_changeActivityFont("app_font");
		//com.lwb.piechart.PieChartView
		PieChartView pieChartView = findViewById(R.id.Pie);
		pieChartView.addItemType(new PieChartView.ItemType("Java", 74, 0xFFAF7219));
		pieChartView.addItemType(new PieChartView.ItemType("Python", 4, 0xFF3573A6));
		pieChartView.addItemType(new PieChartView.ItemType("C++", 18, 0xFFF34B7E));
		pieChartView.addItemType(new PieChartView.ItemType("Shell", 1, 0xFF89E051));
		pieChartView.addItemType(new PieChartView.ItemType("CMake", 1, 0xFFDA3434));
		pieChartView.addItemType(new PieChartView.ItemType("C", 2, 0xFF555555));
		pieChartView.addItemType(new PieChartView.ItemType("Other", 0, 0xFFEDEDED));
		pieChartView.setPieCell(7);
		pieChartView.setCell(7);
		pieChartView.setInnerRadius(0.50f);
		pieChartView.setBackGroundColor(Color.TRANSPARENT);
		pieChartView.setTextPadding(5);
		pieChartView.setItemTextSize(25);
	}
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
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