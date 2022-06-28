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
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
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

public class ErrorActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String fontName = "";
	private String typeace = "";
	private String errorMessage = "";
	private String madeErrorMessage = "";
	
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private TextView textview3;
	private ScrollView vscroll2;
	private TextView TextError;
	private Button button1;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.error);
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
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		textview3 = findViewById(R.id.textview3);
		vscroll2 = findViewById(R.id.vscroll2);
		TextError = findViewById(R.id.TextError);
		button1 = findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
			}
		});
	}
	
	private void initializeLogic() {
		vscroll2.setHorizontalScrollBarEnabled(false);
		vscroll2.setVerticalScrollBarEnabled(false);
		TextError.setText(getIntent().getStringExtra("error"));
		vscroll2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, 0xFFE0E0E0));
		String rmtext = TextError.getText().toString();
		
		ReadMore readMore = new ReadMore.Builder(ErrorActivity.this)
		.textLength(20)
		.moreLabel("بیشتر")
		.lessLabel("کمتر")
		.moreLabelColor(0xFF3696FF)
		.lessLabelColor(0xFF37375B)
		.labelUnderLine(false)
		.build();
		
		readMore.addReadMoreTo(TextError, rmtext);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_changeActivityFont("app_font");
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFF37375B);
			SketchUi.setCornerRadius(d*360);
			button1.setElevation(d*10);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			button1.setBackground(SketchUiRD);
			button1.setClickable(true);
		}
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
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
	
	
	public void _lib() {
	}
	
	public static class ReadMore {
		
		private Context context;
		private boolean labelUnderLine;
		private boolean textIsSelectable;
		private String lessLabel;
		private int lessLabelColor;
		private String moreLabel;
		private int moreLabelColor;
		private int textLength;
		
		public static class Builder {
			
			private Context context;
			private boolean labelUnderLine = false;
			private boolean textIsSelectable = false;
			private String lessLabel = "Less";
			private int lessLabelColor = Color.parseColor("#000000");
			private String moreLabel = "More";
			private int moreLabelColor = Color.parseColor("#000000");
			private int textLength = 100;
			
			public Builder(Context context) {
				context = context;
			}
			
			public Builder textLength(int length) {
				this.textLength = length;
				return this;
			}
			
			public Builder moreLabel(String moreLabel) {
				this.moreLabel = moreLabel;
				return this;
			}
			
			public Builder lessLabel(String lessLabel) {
				this.lessLabel = lessLabel;
				return this;
			}
			
			public Builder moreLabelColor(int moreLabelColor) {
				this.moreLabelColor = moreLabelColor;
				return this;
			}
			
			public Builder lessLabelColor(int lessLabelColor) {
				this.lessLabelColor = lessLabelColor;
				return this;
			}
			
			public Builder labelUnderLine(boolean labelUnderLine) {
				this.labelUnderLine = labelUnderLine;
				return this;
			}
			
			public Builder textIsSelectable(boolean textIsSelectable) {
				this.textIsSelectable = textIsSelectable;
				return this;
			}
			
			public ReadMore build() {
				return new ReadMore(this);
			}
		}
		
		private ReadMore(Builder builder) {
			
			context = builder.context;
			textLength = builder.textLength;
			moreLabel = builder.moreLabel;
			lessLabel = builder.lessLabel;
			moreLabelColor = builder.moreLabelColor;
			lessLabelColor = builder.lessLabelColor;
			labelUnderLine = builder.labelUnderLine;
			textIsSelectable = builder.textIsSelectable;
		}
		
		public void addReadMoreTo(final TextView textView, final String text) {
			textView.setTextIsSelectable(textIsSelectable);
			if (text.length() <= textLength) {
				textView.setText(text);
				return;
			}
			SpannableString ss = new SpannableString(text.substring(0, textLength) + "... " + moreLabel);
			ss.setSpan(new ClickableSpan() {
				public void onClick(View view) {
					addReadLess(textView, text);
				}
				
				public void updateDrawState(TextPaint ds) {
					super.updateDrawState(ds);
					ds.setUnderlineText(labelUnderLine);
					ds.setColor(moreLabelColor);
				}
			}, ss.length() - moreLabel.length(), ss.length(), 33);
			textView.setText(ss);
			textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
		}
		
		private void addReadLess(final TextView textView, final String text) {
			SpannableString ss = new SpannableString(text + " " + lessLabel);
			ss.setSpan(new ClickableSpan() {
				public void onClick(View view) {
					new Handler().post(new Runnable() {
						public void run() {
							addReadMoreTo(textView, text);
						}
					});
				}
				
				public void updateDrawState(TextPaint ds) {
					super.updateDrawState(ds);
					ds.setUnderlineText(labelUnderLine);
					ds.setColor(lessLabelColor);
				}
			}, ss.length() - lessLabel.length(), ss.length(), 33);
			textView.setText(ss);
			textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
		}
	}
	
	{
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