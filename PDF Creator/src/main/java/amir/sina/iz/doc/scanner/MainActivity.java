package amir.sina.iz.doc.scanner;

import amir.sina.iz.doc.scanner.SplashActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
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
import java.util.HashMap;
import java.util.ArrayList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.graphics.Typeface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import com.github.angads25.filepicker.controller.DialogSelectionListener ;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private String fontName = "";
	private String typeace = "";
	private boolean isFab = false;
	private double Case = 0;
	private double position = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String dec_t = "";
	private String Y = "";
	private String M = "";
	private String D = "";
	private boolean isCamera = false;
	private double n1 = 0;
	private HashMap<String, Object> cacheMap = new HashMap<>();
	private String charSeq2 = "";
	private double files_count = 0;
	private double SLN = 0;
	private boolean deleteMode = false;
	private double Ppos = 0;
	private  BillingProcessor bp;
	private String myProduct = "donate_dev";
	private boolean readyPurchase = false;
	private String LOG_TAG = "";
	
	private ArrayList<String> path = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> PagesList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> cache = new ArrayList<>();
	private ArrayList<String> files = new ArrayList<>();
	
	private LinearLayout linear_Sbar;
	private SwipeRefreshLayout swiperefreshlayout1;
	private LinearLayout linearhopnf;
	private LinearLayout SearchBar;
	private SearchView Searchview;
	private ListView list_pdfs;
	private TextView tfdyjb;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear4;
	private LinearLayout _drawer_linear3;
	private LinearLayout _drawer_linear2;
	private LinearLayout _drawer_linear_exit;
	private ImageView _drawer_imageview1;
	private LinearLayout _drawer_linear5;
	private ImageView _drawer_close;
	private TextView _drawer_textview8;
	private LinearLayout _drawer_linear_aboutapp;
	private LinearLayout _drawer_linear_support;
	private LinearLayout _drawer_linear_rate;
	private LinearLayout _drawer_linear_other;
	private ImageView _drawer_aboutapp_img;
	private TextView _drawer_textview2;
	private ImageView _drawer_support_img;
	private TextView _drawer_textview4;
	private ImageView _drawer_rate_img;
	private TextView _drawer_textview6;
	private ImageView _drawer_other_img;
	private TextView _drawer_textview5;
	private ImageView _drawer_exit_img;
	private TextView _drawer_textview7;
	
	private SharedPreferences SHOWCASE;
	private NiftyDialogBuilder dp;
	private SharedPreferences per;
	private Calendar c = Calendar.getInstance();
	private Intent intent = new Intent();
	private TimerTask tmr;
	private Intent rate = new Intent();
	private Intent other = new Intent();
	private Intent view = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		
initializeLogic();
}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			
_callBack();
		}
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
		_fab = findViewById(R.id._fab);
		
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MainActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear_Sbar = findViewById(R.id.linear_Sbar);
		swiperefreshlayout1 = findViewById(R.id.swiperefreshlayout1);
		linearhopnf = findViewById(R.id.linearhopnf);
		SearchBar = findViewById(R.id.SearchBar);
		Searchview = findViewById(R.id.Searchview);
		list_pdfs = findViewById(R.id.list_pdfs);
		tfdyjb = findViewById(R.id.tfdyjb);
		_drawer_linear1 = _nav_view.findViewById(R.id.linear1);
		_drawer_linear4 = _nav_view.findViewById(R.id.linear4);
		_drawer_linear3 = _nav_view.findViewById(R.id.linear3);
		_drawer_linear2 = _nav_view.findViewById(R.id.linear2);
		_drawer_linear_exit = _nav_view.findViewById(R.id.linear_exit);
		_drawer_imageview1 = _nav_view.findViewById(R.id.imageview1);
		_drawer_linear5 = _nav_view.findViewById(R.id.linear5);
		_drawer_close = _nav_view.findViewById(R.id.close);
		_drawer_textview8 = _nav_view.findViewById(R.id.textview8);
		_drawer_linear_aboutapp = _nav_view.findViewById(R.id.linear_aboutapp);
		_drawer_linear_support = _nav_view.findViewById(R.id.linear_support);
		_drawer_linear_rate = _nav_view.findViewById(R.id.linear_rate);
		_drawer_linear_other = _nav_view.findViewById(R.id.linear_other);
		_drawer_aboutapp_img = _nav_view.findViewById(R.id.aboutapp_img);
		_drawer_textview2 = _nav_view.findViewById(R.id.textview2);
		_drawer_support_img = _nav_view.findViewById(R.id.support_img);
		_drawer_textview4 = _nav_view.findViewById(R.id.textview4);
		_drawer_rate_img = _nav_view.findViewById(R.id.rate_img);
		_drawer_textview6 = _nav_view.findViewById(R.id.textview6);
		_drawer_other_img = _nav_view.findViewById(R.id.other_img);
		_drawer_textview5 = _nav_view.findViewById(R.id.textview5);
		_drawer_exit_img = _nav_view.findViewById(R.id.exit_img);
		_drawer_textview7 = _nav_view.findViewById(R.id.textview7);
		SHOWCASE = getSharedPreferences("SHOWCASE", Activity.MODE_PRIVATE);
		per = getSharedPreferences("per", Activity.MODE_PRIVATE);
		
		Searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String _charSeq) {
				
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String _charSeq) {
				charSeq2 = _charSeq;
				if (charSeq2.length() > 0) {
					_endTimer(tmr);
					tmr = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_searchInListmap(charSeq2.toLowerCase(), cache, "name");
									list_pdfs.setAdapter(new List_pdfsAdapter(cache));
									if (cache.size() > 0) {
										list_pdfs.setVisibility(View.VISIBLE);
									}
									else {
										list_pdfs.setVisibility(View.GONE);
									}
								}
							});
						}
					};
					_timer.schedule(tmr, (int)(400));
				}
				else {
					list_pdfs.setAdapter(new List_pdfsAdapter(listmap));
				}
				return true;
			}
		});
		
		list_pdfs.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView abs, int _scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView abs, int _firstVisibleItem, int _visibleItemCount, int _totalItemCount) {
				
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (deleteMode) {
					swiperefreshlayout1.setRefreshing(true);
					Ppos = 0;
					//background executor create here
					java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
					
					final Handler handler = new Handler(Looper.getMainLooper());
					
					  executor.execute(new Runnable() {
							
							@Override
							
							public void run() {for(int _repeat28 = 0; _repeat28 < (int)(listmap.size()); _repeat28++) {
								if (listmap.get((int)Ppos).get("selectedItem").toString().equals("true")) {
									FileUtil.deleteFile(listmap.get((int)Ppos).get("path").toString());
									listmap.remove((int)(Ppos));
								}
								else {
									
								}
								Ppos++;
							}
							handler.post(new Runnable() {
											
											@Override
											
											public void run() {LayoutInflater Inflater = getLayoutInflater();
									View toastview= Inflater.inflate(R.layout.toast,null); 
									LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
									//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
									TextView txt =(TextView) toastview.findViewById(R.id.tot1);
									
									
									android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
									cc.setCornerRadius((int)24f);
									cc.setColor(Color.parseColor("#FF37375B"));
									lin1.setBackground(cc);
									
									/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
									
									txt.setText("حذف شد");
									txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
									
									Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
									toast.setView(toastview);
									toast.setGravity(Gravity.BOTTOM, 0, 130);
									toast.show();
									
									//By Amir sina | amirsina_iz
									deleteMode = false;
									setTitle("PDF Creator ");
									list_pdfs.setAdapter(new List_pdfsAdapter(listmap));
									((BaseAdapter)list_pdfs.getAdapter()).notifyDataSetChanged();
									list_pdfs.invalidateViews();
									_fab.setImageResource(R.drawable.ic_add_white);
									swiperefreshlayout1.setRefreshing(false);
									SLN = 0;
													
													//UI Thread work here
																	
											} });
									//background task start here
									
									
									
							} });
				}
				else {
					if (isFab) {
						isFab = false;
						_showFab(false);
					}
					else {
						isFab = true;
						_showFab(true);
					}
				}
			}
		});
		
		_drawer_linear_exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
			}
		});
		
		_drawer_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_linear_aboutapp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivity(new Intent(MainActivity.this, AboutappActivity.class)); Animatoo.animateInAndOut(MainActivity.this);
			}
		});
		
		_drawer_linear_support.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!readyPurchase) {
					LayoutInflater Inflater = getLayoutInflater();
					View toastview= Inflater.inflate(R.layout.toast,null); 
					LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
					//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
					TextView txt =(TextView) toastview.findViewById(R.id.tot1);
					
					
					android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
					cc.setCornerRadius((int)24f);
					cc.setColor(Color.parseColor("#FF37375B"));
					lin1.setBackground(cc);
					
					/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
					
					txt.setText("بعدا دوباره امتحان کنید");
					txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
					
					Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastview);
					toast.setGravity(Gravity.BOTTOM, 0, 130);
					toast.show();
					
					//By Amir sina | amirsina_iz
					return;
				}
				bp.purchase(MainActivity.this, myProduct);
			}
		});
		
		_drawer_linear_rate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				rate.setAction(Intent.ACTION_VIEW);
				rate.setData(Uri.parse("myket://comment?id=amir.sina.iz.doc.scanner"));
				startActivity(rate);
			}
		});
		
		_drawer_linear_other.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				other.setAction(Intent.ACTION_VIEW);
				other.setData(Uri.parse("https://myket.ir/developer/dev-70630"));
				startActivity(other);
			}
		});
	}
	
	private void initializeLogic() {
		if (per.getString("PR", "").equals("true")) {
			
		}
		else {
			dp=NiftyDialogBuilder.getInstance(MainActivity.this);
			
			dp 
			          .withDialogColor(0xFF37375B)
			          .withTitle("دسترسی حافظه")                                   
			          .withTitleColor(0xFFF0F1F8) 
			          .withMessage("برای ذخیره فایل ها دسترسی به حافظه را تایید کنید")                    
			          .withMessageColor(0xFFF0F1F8) 
			          .withDividerColor(0xFFF0F1F8)                     
			          .withIcon(getResources().getDrawable(R.drawable.ic_sd_card_white))  
			          .isCancelableOnTouchOutside(false)
			          .withDuration(200)                                        
			          .withEffect(Effectstype.Slidetop) 
			.withButton1Text("تایید")                                                                  
			    .setButton1Click(new View.OnClickListener() {
				        @Override
				        public void onClick(View v) {
					            _checkPermission();
					                    }
				    })
			    .withButton2Text("لغو")
			    .setButton2Click(new View.OnClickListener() {
				        @Override
				        public void onClick(View v) {
					            dp.dismiss();
					finishAffinity();
					        }
				    })
			.show();
		}
		
		_GetProjects();
		SLN = 0;
		list_pdfs.setHorizontalScrollBarEnabled(false);
		list_pdfs.setVerticalScrollBarEnabled(false);
		if(!BillingProcessor.isIabServiceAvailable(this)) {
			
			 
			
		}
		bp = new BillingProcessor(this, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHz2VpcwPqmej1iOfcN7e0Mq6ba/BCvTjvetcyV5eGCFsDza9RvcHZ1bo7nUSldxG7NCsOzky3+/pXWreIBd2avmVIF5AINHElPUpgtoQ37jJZfCanBu8y1WfZKjdgls1CUYoR6Yo6t0NOo+D9UUQpRu5VCaTmvRPH61NdFsH1KwIDAQAB", "4a4f38bd-6215-4bc5-9635-5f9e0e6c6480", new BillingProcessor.IBillingHandler() {
			
			@Override
			public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
				
				LayoutInflater Inflater = getLayoutInflater();
				View toastview= Inflater.inflate(R.layout.toast,null); 
				LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
				//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
				TextView txt =(TextView) toastview.findViewById(R.id.tot1);
				
				
				android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
				cc.setCornerRadius((int)24f);
				cc.setColor(Color.parseColor("#FF37375B"));
				lin1.setBackground(cc);
				
				/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
				
				txt.setText("ممنون از حمایت شما");
				txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
				
				Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(toastview);
				toast.setGravity(Gravity.BOTTOM, 0, 130);
				toast.show();
				
				//By Amir sina | amirsina_iz
				updateInfoBilling();
				
			}
			@Override
			public void onBillingError(int errorCode, @Nullable Throwable error) {
				
				final String MessageError = Integer.toString(errorCode);
				
				LayoutInflater Inflater = getLayoutInflater();
				View toastview= Inflater.inflate(R.layout.toast,null); 
				LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
				//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
				TextView txt =(TextView) toastview.findViewById(R.id.tot1);
				
				
				android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
				cc.setCornerRadius((int)24f);
				cc.setColor(Color.parseColor("#FF37375B"));
				lin1.setBackground(cc);
				
				/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
				
				txt.setText("خطا در پرداخت ");
				txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
				
				Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(toastview);
				toast.setGravity(Gravity.BOTTOM, 0, 130);
				toast.show();
				
				//By Amir sina | amirsina_iz
				updateInfoBilling();
				
			}
			@Override
			public void onBillingInitialized() {
				
				readyPurchase = true;
				
			}
			@Override
			public void onPurchaseHistoryRestored() {
				for(String sku : bp.listOwnedProducts())
				Log.d(LOG_TAG, "Owned Managed Product: " + sku);
				for(String sku : bp.listOwnedSubscriptions())
				Log.d(LOG_TAG, "Owned Subscription: " + sku);
				updateInfoBilling();
			}
			
		});
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		if (!bp.handleActivityResult(_requestCode, _resultCode, _data))super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		setTitle("PDF Creator");
		_changeActivityFont("app_font");
		SearchBar.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFEEEEEE));
		_ColorShadow_SDK28(_fab, "#FF37375B", 500);
		_ColorShadow_SDK28(linear_Sbar, "#FF37375B", 50);
		_onCreateFab();
		_fab1();
		_fab2();
		_showFab(false);
		final LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view); _nav_view.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
		_ICC(_drawer_exit_img, "#FFFFFF", "#FFFFFF");
		_drawer_textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		_drawer_textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		_drawer_textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		_drawer_textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		_drawer_textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		_drawer_textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFF37375B);
			SketchUi.setCornerRadius(d*360);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFFFFFFF}), SketchUi, null);
			_drawer_linear_exit.setBackground(SketchUiRD);
			_drawer_linear_exit.setClickable(true);
		}
		
		
		
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*80);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF37375B}), SketchUi, null);
			_drawer_linear_aboutapp.setBackground(SketchUiRD);
			_drawer_linear_aboutapp.setClickable(true);
		}
		
		
		
		
		
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*80);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF37375B}), SketchUi, null);
			_drawer_linear_support.setBackground(SketchUiRD);
			_drawer_linear_support.setClickable(true);
		}
		
		
		
		
		
		
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*80);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF37375B}), SketchUi, null);
			_drawer_linear_rate.setBackground(SketchUiRD);
			_drawer_linear_rate.setClickable(true);
		}
		
		
		
		
		
		
		
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*80);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF37375B}), SketchUi, null);
			_drawer_linear_other.setBackground(SketchUiRD);
			_drawer_linear_other.setClickable(true);
		}
		
		
		
		
	}
	
	@Override
	public void onStop() {
		super.onStop();
		finish();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (bp != null)
		bp.release();
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		}
		else {
			startActivity(new Intent(MainActivity.this, ExitActivity.class)); Animatoo.animateSlideDown(MainActivity.this);
		}
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
	
	
	public void _ColorShadow_SDK28(final View _view, final String _color, final double _number) {
		_view.setElevation((float)_number);
		
		_view.setOutlineAmbientShadowColor(Color.parseColor(_color));
		_view.setOutlineSpotShadowColor(Color.parseColor(_color));
	}
	
	
	public void _onCreateFab() {
		View cv = getLayoutInflater().inflate(R.layout.fabs, null);
		
		bg = (LinearLayout)cv.findViewById(R.id.linearaon);
		
		((ViewGroup)bg.getParent()).removeView(bg);
		
		((ViewGroup)_fab.getParent()).addView(bg);
	}
	
	
	public void _fab1() {
		LinearLayout fab1 = (LinearLayout)bg.findViewById(R.id.gallery);
		
		LinearLayout Description2 = (LinearLayout)bg.findViewById(R.id.linear4);
		TextView t1 = (TextView)bg.findViewById(R.id.textview1);
		fab1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF37375B));
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		Description2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF263238));
		Description2.setElevation((float)8);
		_ColorShadow_SDK28(fab1, "#FF37375B", 15);
		
		fab1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
						
				  isCamera = false;
				_MaterialDialog("اسم پی دی اف را بنویسید", "", "لغو", "تایید");
				
				}});
	}
	
	
	public void _fab2() {
		LinearLayout fab2 = (LinearLayout)bg.findViewById(R.id.camera);
		
		LinearLayout Description1 = (LinearLayout)bg.findViewById(R.id.linear5);
		TextView t2 = (TextView)bg.findViewById(R.id.textview2);
		fab2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF37375B));
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		Description1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF263238));
		Description1.setElevation((float)8);
		_ColorShadow_SDK28(fab2, "#FF37375B", 15);
		
		fab2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
						
				  isCamera = true;
				_MaterialDialog("اسم پی دی اف را بنویسید", "", "لغو", "تایید");
				
				}});
	}
	
	
	public void _showFab(final boolean _show) {
		if (_show) {
				bg.setVisibility(View.VISIBLE);
				bg.setTranslationY((int)getDip(0));
				
				
				bg.setAlpha(0);
				
				
				bg.animate().setDuration(0).alpha(1f).translationY(0);
				
				
				_fab.animate().setDuration(0).rotation(0);
		}
		else {
				bg.setVisibility(View.GONE);
		}
	}
	private LinearLayout bg;
	{
	}
	
	
	public void _ARGHOZALITapTarget(final View _view, final String _title, final String _msg, final String _bgcolor) {
		TapTargetView.showFor(MainActivity.this,
		TapTarget.forView(_view, _title, _msg)
		.outerCircleColorInt(Color.parseColor(_bgcolor))
		.outerCircleAlpha(0.96f)
		.targetCircleColorInt(Color.parseColor("#FFFFFF"))
		.titleTextSize(25)
		.titleTextColorInt(Color.parseColor("#FFFFFF"))
		.descriptionTextSize(18)
		.descriptionTextColor(android.R.color.white)
		.textColorInt(Color.parseColor("#FFFFFF"))
		//.textTypeface(Typeface.SANS_SERIF)
		.textTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"))
		.dimColor(android.R.color.black)
		.drawShadow(true)
		.cancelable(true)
		.tintTarget(true)
		.transparentTarget(true)
		//.icon(Drawable)
		.targetRadius(60),
		
		//LISTENER//
		
		new TapTargetView.Listener() {
			@Override
			public void onTargetClick(TapTargetView view) {
				//ON CLICKED//
				
				
				
				
				ViewTooltip
				        .on(MainActivity.this, Searchview)
				        .position(ViewTooltip.Position.BOTTOM)
				        .text("فایل هاتو جستجو کن !")
				        .shadowColor(0xFF37375B)
				        .color(0xFF37375B)
				        .corner(15)
				        .show();
				
				
				
				
				
				
				
				super.onTargetClick(view);
			}
		});
		//ARGHOZALIDEV
	}
	static class UiUtil {
		    UiUtil() {
			    }
		    static int dp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_DIP, val, context.getResources().getDisplayMetrics());
			    }
		    static int sp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_SP, val, context.getResources().getDisplayMetrics());
			    }
		    static int themeIntAttr(Context context, String attr) {
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        if (theme == null) {
				            return -1;
				        }
			        final TypedValue value = new TypedValue();
			        final int id = context.getResources().getIdentifier(attr, "attr", context.getPackageName());
			
			        if (id == 0) {
				            // Not found
				            return -1;
				        }
			        theme.resolveAttribute(id, value, true);
			        return value.data;
			    }
		    static int setAlpha(int argb, float alpha) {
			        if (alpha > 1.0f) {
				            alpha = 1.0f;
				        } else if (alpha <= 0.0f) {
				            alpha = 0.0f;
				        }
			        return ((int) ((argb >>> 24) * alpha) << 24) | (argb & 0x00FFFFFF);
			    }
	}
	static class FloatValueAnimatorBuilder {
		
		    private final ValueAnimator animator;
		
		    private EndListener endListener;
		
		    interface UpdateListener {
			        void onUpdate(float lerpTime);
			    }
		    interface EndListener {
			        void onEnd();
			    }
		    protected FloatValueAnimatorBuilder() {
			        this(false);
			    }
		    FloatValueAnimatorBuilder(boolean reverse) {
			        if (reverse) {
				            this.animator = ValueAnimator.ofFloat(1.0f, 0.0f);
				        } else {
				            this.animator = ValueAnimator.ofFloat(0.0f, 1.0f);
				        }
			    }
		    public FloatValueAnimatorBuilder delayBy(long millis) {
			        animator.setStartDelay(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder duration(long millis) {
			        animator.setDuration(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder interpolator(TimeInterpolator lerper) {
			        animator.setInterpolator(lerper);
			        return this;
			    }
		    public FloatValueAnimatorBuilder repeat(int times) {
			        animator.setRepeatCount(times);
			        return this;
			    }
		    public FloatValueAnimatorBuilder onUpdate(final UpdateListener listener) {
			        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                listener.onUpdate((float) animation.getAnimatedValue());
					            }
				        });
			        return this;
			    }
		    public FloatValueAnimatorBuilder onEnd(final EndListener listener) {
			        this.endListener = listener;
			        return this;
			    }
		    public ValueAnimator build() {
			        if (endListener != null) {
				            animator.addListener(new AnimatorListenerAdapter() {
					                @Override
					                public void onAnimationEnd(Animator animation) {
						                    endListener.onEnd();
						                }
					            });
				        }
			        return animator;
			    }
	}
	
	static class ReflectUtil {
		    ReflectUtil() {
			    }
		    static Object getPrivateField(Object source, String fieldName)
		            throws NoSuchFieldException, IllegalAccessException {
			        final java.lang.reflect.Field objectField = source.getClass().getDeclaredField(fieldName);
			        objectField.setAccessible(true);
			        return objectField.get(source);
			    }
	}
	static class TapTarget extends Activity {
		    final CharSequence title;
		    final CharSequence description;
		    float outerCircleAlpha = 0.96f;
		    int targetRadius = 44;
		    Rect bounds;
		    android.graphics.drawable.Drawable icon;
		    Typeface titleTypeface;
		    Typeface descriptionTypeface;
		
		
		    private int outerCircleColorRes = -1;
		    private int targetCircleColorRes = -1;
		    private int dimColorRes = -1;
		    private int titleTextColorRes = -1;
		    private int descriptionTextColorRes = -1;
		
		    private Integer outerCircleColor = null;
		    private Integer targetCircleColor = null;
		    private Integer dimColor = null;
		    private Integer titleTextColor = null;
		    private Integer descriptionTextColor = null;
		
		    private int titleTextDimen = -1;
		    private int descriptionTextDimen = -1;
		    private int titleTextSize = 20;
		    private int descriptionTextSize = 18;
		    int id = -1;
		    boolean drawShadow = false;
		    boolean cancelable = true;
		    boolean tintTarget = true;
		    boolean transparentTarget = false;
		    float descriptionTextAlpha = 0.54f;
		public static TapTarget forView(View view, CharSequence title) {
			        return forView(view, title, null);
			    }
		    public static TapTarget forView(View view, CharSequence title, CharSequence description) {
			        return new ViewTapTarget(view, title, description);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title) {
			        return forBounds(bounds, title, null);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title,  CharSequence description) {
			        return new TapTarget(bounds, title, description);
			    }
		    protected TapTarget(Rect bounds, CharSequence title,  CharSequence description) {
			        this(title, description);
			        if (bounds == null) {
				            throw new IllegalArgumentException("Cannot pass null bounds or title");
				        }
			        this.bounds = bounds;
			    }
		    protected TapTarget(CharSequence title,  CharSequence description) {
			        if (title == null) {
				            throw new IllegalArgumentException("Cannot pass null title");
				        }
			        this.title = title;
			        this.description = description;
			    }
		    public TapTarget transparentTarget(boolean transparent) {
			        this.transparentTarget = transparent;
			        return this;
			    }
		    public TapTarget outerCircleColor( int color) {
			        this.outerCircleColorRes = color;
			        return this;
			    }
		    public TapTarget outerCircleColorInt( int color) {
			        this.outerCircleColor = color;
			        return this;
			    }
		    public TapTarget outerCircleAlpha(float alpha) {
			        if (alpha < 0.0f || alpha > 1.0f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + alpha);
				        }
			        this.outerCircleAlpha = alpha;
			        return this;
			    }
		    public TapTarget targetCircleColor( int color) {
			        this.targetCircleColorRes = color;
			        return this;
			    }
		    public TapTarget targetCircleColorInt( int color) {
			        this.targetCircleColor = color;
			        return this;
			    }
		    public TapTarget textColor( int color) {
			        this.titleTextColorRes = color;
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget textColorInt( int color) {
			        this.titleTextColor = color;
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget titleTextColor( int color) {
			        this.titleTextColorRes = color;
			        return this;
			    }
		    public TapTarget titleTextColorInt( int color) {
			        this.titleTextColor = color;
			        return this;
			    }
		    public TapTarget descriptionTextColor( int color) {
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget descriptionTextColorInt( int color) {
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget textTypeface(Typeface typeface) {
			        if (typeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        titleTypeface = typeface;
			        descriptionTypeface = typeface;
			        return this;
			    }
		    public TapTarget titleTypeface(Typeface titleTypeface) {
			        if (titleTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.titleTypeface = titleTypeface;
			        return this;
			    }
		    public TapTarget descriptionTypeface(Typeface descriptionTypeface) {
			        if (descriptionTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.descriptionTypeface = descriptionTypeface;
			        return this;
			    }
		    public TapTarget titleTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.titleTextSize = sp;
			        return this;
			    }
		    public TapTarget descriptionTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.descriptionTextSize = sp;
			        return this;
			    }
		    public TapTarget titleTextDimen( int dimen) {
			        this.titleTextDimen = dimen;
			        return this;
			    }
		    public TapTarget descriptionTextAlpha(float descriptionTextAlpha) {
			        if (descriptionTextAlpha < 0 || descriptionTextAlpha > 1f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + descriptionTextAlpha);
				        }
			        this.descriptionTextAlpha = descriptionTextAlpha;
			        return this;
			    }
		    public TapTarget descriptionTextDimen( int dimen) {
			        this.descriptionTextDimen = dimen;
			        return this;
			    }
		    public TapTarget dimColor( int color) {
			        this.dimColorRes = color;
			        return this;
			    }
		    public TapTarget dimColorInt( int color) {
			        this.dimColor = color;
			        return this;
			    }
		    public TapTarget drawShadow(boolean draw) {
			        this.drawShadow = draw;
			        return this;
			    }
		    public TapTarget cancelable(boolean status) {
			        this.cancelable = status;
			        return this;
			    }
		    public TapTarget tintTarget(boolean tint) {
			        this.tintTarget = tint;
			        return this;
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon) {
			        return icon(icon, false);
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon, boolean hasSetBounds) {
			        if (icon == null) throw new IllegalArgumentException("Cannot use null drawable");
			        this.icon = icon;
			        if (!hasSetBounds) {
				            this.icon.setBounds(new Rect(0, 0, this.icon.getIntrinsicWidth(), this.icon.getIntrinsicHeight()));
				        }
			        return this;
			    }
		    public TapTarget id(int id) {
			        this.id = id;
			        return this;
			    }
		    public TapTarget targetRadius(int targetRadius) {
			        this.targetRadius = targetRadius;
			        return this;
			    }
		    public int id() {
			        return id;
			    }
		    public void onReady(Runnable runnable) {
			        runnable.run();
			    }
		    public Rect bounds() {
			        if (bounds == null) {
				            throw new IllegalStateException("Requesting bounds that are not set! Make sure your target is ready");
				        }
			        return bounds;
			    }
		    Integer outerCircleColorInt(Context context) {
			        return colorResOrInt(context, outerCircleColor, outerCircleColorRes);
			    }
		    Integer targetCircleColorInt(Context context) {
			        return colorResOrInt(context, targetCircleColor, targetCircleColorRes);
			    }
		    Integer dimColorInt(Context context) {
			        return colorResOrInt(context, dimColor, dimColorRes);
			    }
		    Integer titleTextColorInt(Context context) {
			        return colorResOrInt(context, titleTextColor, titleTextColorRes);
			    }
		
		    Integer descriptionTextColorInt(Context context) {
			        return colorResOrInt(context, descriptionTextColor, descriptionTextColorRes);
			    }
		    int titleTextSizePx(Context context) {
			        return dimenOrSize(context, titleTextSize, titleTextDimen);
			    }
		    int descriptionTextSizePx(Context context) {
			        return dimenOrSize(context, descriptionTextSize, descriptionTextDimen);
			    }
		
		    private Integer colorResOrInt(Context context, Integer value,  int resource) {
			        if (resource != -1) {
				            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					                return context.getColor(resource);
					            }
				        }
			        return value;
			    }
		    private int dimenOrSize(Context context, int size,  int dimen) {
			        if (dimen != -1) {
				            return context.getResources().getDimensionPixelSize(dimen);
				        }
			        return UiUtil.sp(context, size);
			    }
	}
	static class TapTargetView extends View {
		    private boolean isDismissed = false;
		    private boolean isDismissing = false;
		    private boolean isInteractable = true;
		
		    final int TARGET_PADDING;
		    final int TARGET_RADIUS;
		    final int TARGET_PULSE_RADIUS;
		    final int TEXT_PADDING;
		    final int TEXT_SPACING;
		    final int TEXT_MAX_WIDTH;
		    final int TEXT_POSITIONING_BIAS;
		    final int CIRCLE_PADDING;
		    final int GUTTER_DIM;
		    final int SHADOW_DIM;
		    final int SHADOW_JITTER_DIM;
		
		
		    final ViewGroup boundingParent;
		    final ViewManager parent;
		    final TapTarget target;
		    final Rect targetBounds;
		
		    final TextPaint titlePaint;
		    final TextPaint descriptionPaint;
		    final Paint outerCirclePaint;
		    final Paint outerCircleShadowPaint;
		    final Paint targetCirclePaint;
		    final Paint targetCirclePulsePaint;
		
		    CharSequence title;
		
		    StaticLayout titleLayout;
		
		    CharSequence description;
		
		    StaticLayout descriptionLayout;
		    boolean isDark;
		    boolean debug;
		    boolean shouldTintTarget;
		    boolean shouldDrawShadow;
		    boolean cancelable;
		    boolean visible;
		
		    // Debug related variables
		
		    SpannableStringBuilder debugStringBuilder;
		
		    DynamicLayout debugLayout;
		
		    TextPaint debugTextPaint;
		
		    Paint debugPaint;
		
		    // Drawing properties
		    Rect drawingBounds;
		    Rect textBounds;
		
		    Path outerCirclePath;
		    float outerCircleRadius;
		    int calculatedOuterCircleRadius;
		    int[] outerCircleCenter;
		    int outerCircleAlpha;
		
		    float targetCirclePulseRadius;
		    int targetCirclePulseAlpha;
		
		    float targetCircleRadius;
		    int targetCircleAlpha;
		
		    int textAlpha;
		    int dimColor;
		
		    float lastTouchX;
		    float lastTouchY;
		
		    int topBoundary;
		    int bottomBoundary;
		
		    Bitmap tintedTarget;
		
		    Listener listener;
		
		
		    ViewOutlineProvider outlineProvider;
		
		    public static TapTargetView showFor(Activity activity, TapTarget target) {
			        return showFor(activity, target, null);
			    }
		
		    public static TapTargetView showFor(Activity activity, TapTarget target, Listener listener) {
			        if (activity == null) throw new IllegalArgumentException("Activity is null");
			
			        final ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
			        final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
			                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			        final ViewGroup content = (ViewGroup) decor.findViewById(android.R.id.content);
			        final TapTargetView tapTargetView = new TapTargetView(activity, decor, content, target, listener);
			        decor.addView(tapTargetView, layoutParams);
			
			        return tapTargetView;
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target) {
			        return showFor(dialog, target, null);
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target, Listener listener) {
			        if (dialog == null) throw new IllegalArgumentException("Dialog is null");
			
			        final Context context = dialog.getContext();
			        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
			        params.format = PixelFormat.RGBA_8888;
			        params.flags = 0;
			        params.gravity = Gravity.START | Gravity.TOP;
			        params.x = 0;
			        params.y = 0;
			        params.width = WindowManager.LayoutParams.MATCH_PARENT;
			        params.height = WindowManager.LayoutParams.MATCH_PARENT;
			
			        final TapTargetView tapTargetView = new TapTargetView(context, windowManager, null, target, listener);
			        windowManager.addView(tapTargetView, params);
			
			        return tapTargetView;
			    }
		public static class Listener {
			        /** Signals that the user has clicked inside of the target **/
			        public void onTargetClick(TapTargetView view) {
				            view.dismiss(true);
				        }
			
			        /** Signals that the user has long clicked inside of the target **/
			        public void onTargetLongClick(TapTargetView view) {
				            onTargetClick(view);
				        }
			
			        /** If cancelable, signals that the user has clicked outside of the outer circle **/
			        public void onTargetCancel(TapTargetView view) {
				            view.dismiss(false);
				        }
			
			        /** Signals that the user clicked on the outer circle portion of the tap target **/
			        public void onOuterCircleClick(TapTargetView view) {
				            // no-op as default
				        }
			
			        /**
         * Signals that the tap target has been dismissed
         * @param userInitiated Whether the user caused this action
         *
         *
         */
			        public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
				        }
			    }
		
		    final FloatValueAnimatorBuilder.UpdateListener expandContractUpdateListener = new FloatValueAnimatorBuilder.UpdateListener() {
			        @Override
			        public void onUpdate(float lerpTime) {
				            final float newOuterCircleRadius = calculatedOuterCircleRadius * lerpTime;
				            final boolean expanding = newOuterCircleRadius > outerCircleRadius;
				            if (!expanding) {
					                // When contracting we need to invalidate the old drawing bounds. Otherwise
					                // you will see artifacts as the circle gets smaller
					                calculateDrawingBounds();
					            }
				
				            final float targetAlpha = target.outerCircleAlpha * 255;
				            outerCircleRadius = newOuterCircleRadius;
				            outerCircleAlpha = (int) Math.min(targetAlpha, (lerpTime * 1.5f * targetAlpha));
				            outerCirclePath.reset();
				            outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				
				            targetCircleAlpha = (int) Math.min(255.0f, (lerpTime * 1.5f * 255.0f));
				
				            if (expanding) {
					                targetCircleRadius = TARGET_RADIUS * Math.min(1.0f, lerpTime * 1.5f);
					            } else {
					                targetCircleRadius = TARGET_RADIUS * lerpTime;
					                targetCirclePulseRadius *= lerpTime;
					            }
				
				            textAlpha = (int) (delayedLerp(lerpTime, 0.7f) * 255);
				
				            if (expanding) {
					                calculateDrawingBounds();
					            }
				
				            invalidateViewAndOutline(drawingBounds);
				        }
			    };
		
		    final ValueAnimator expandAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .delayBy(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    pulseAnimation.start();
				                    isInteractable = true;
				                }
			            })
		            .build();
		
		    final ValueAnimator pulseAnimation = new FloatValueAnimatorBuilder()
		            .duration(1000)
		            .repeat(ValueAnimator.INFINITE)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float pulseLerp = delayedLerp(lerpTime, 0.5f);
				                    targetCirclePulseRadius = (1.0f + pulseLerp) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - pulseLerp) * 255);
				                    targetCircleRadius = TARGET_RADIUS + halfwayLerp(lerpTime) * TARGET_PULSE_RADIUS;
				
				                    if (outerCircleRadius != calculatedOuterCircleRadius) {
					                        outerCircleRadius = calculatedOuterCircleRadius;
					                    }
				
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .build();
		
		    final ValueAnimator dismissAnimation = new FloatValueAnimatorBuilder(true)
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private final ValueAnimator dismissConfirmAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float spedUpLerp = Math.min(1.0f, lerpTime * 2.0f);
				                    outerCircleRadius = calculatedOuterCircleRadius * (1.0f + (spedUpLerp * 0.2f));
				                    outerCircleAlpha = (int) ((1.0f - spedUpLerp) * target.outerCircleAlpha * 255.0f);
				                    outerCirclePath.reset();
				                    outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				                    targetCircleRadius = (1.0f - lerpTime) * TARGET_RADIUS;
				                    targetCircleAlpha = (int) ((1.0f - lerpTime) * 255.0f);
				                    targetCirclePulseRadius = (1.0f + lerpTime) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - lerpTime) * targetCirclePulseAlpha);
				                    textAlpha = (int) ((1.0f - spedUpLerp) * 255.0f);
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private ValueAnimator[] animators = new ValueAnimator[]
		            {expandAnimation, pulseAnimation, dismissConfirmAnimation, dismissAnimation};
		
		    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
		    public TapTargetView(final Context context,
		                         final ViewManager parent,
		                          final ViewGroup boundingParent,
		                         final TapTarget target,
		                          final Listener userListener) {
			        super(context);
			        if (target == null) throw new IllegalArgumentException("Target cannot be null");
			
			        this.target = target;
			        this.parent = parent;
			        this.boundingParent = boundingParent;
			        this.listener = userListener != null ? userListener : new Listener();
			        this.title = target.title;
			        this.description = target.description;
			
			        TARGET_PADDING = UiUtil.dp(context, 20);
			        CIRCLE_PADDING = UiUtil.dp(context, 40);
			        TARGET_RADIUS = UiUtil.dp(context, target.targetRadius);
			        TEXT_PADDING = UiUtil.dp(context, 40);
			        TEXT_SPACING = UiUtil.dp(context, 8);
			        TEXT_MAX_WIDTH = UiUtil.dp(context, 360);
			        TEXT_POSITIONING_BIAS = UiUtil.dp(context, 20);
			        GUTTER_DIM = UiUtil.dp(context, 88);
			        SHADOW_DIM = UiUtil.dp(context, 8);
			        SHADOW_JITTER_DIM = UiUtil.dp(context, 1);
			        TARGET_PULSE_RADIUS = (int) (0.1f * TARGET_RADIUS);
			
			        outerCirclePath = new Path();
			        targetBounds = new Rect();
			        drawingBounds = new Rect();
			
			        titlePaint = new TextPaint();
			        titlePaint.setTextSize(target.titleTextSizePx(context));
			        titlePaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
			        titlePaint.setAntiAlias(true);
			
			        descriptionPaint = new TextPaint();
			        descriptionPaint.setTextSize(target.descriptionTextSizePx(context));
			        descriptionPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
			        descriptionPaint.setAntiAlias(true);
			        descriptionPaint.setAlpha((int) (0.54f * 255.0f));
			
			        outerCirclePaint = new Paint();
			        outerCirclePaint.setAntiAlias(true);
			        outerCirclePaint.setAlpha((int) (target.outerCircleAlpha * 255.0f));
			
			        outerCircleShadowPaint = new Paint();
			        outerCircleShadowPaint.setAntiAlias(true);
			        outerCircleShadowPaint.setAlpha(50);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        outerCircleShadowPaint.setStrokeWidth(SHADOW_JITTER_DIM);
			        outerCircleShadowPaint.setColor(Color.BLACK);
			
			        targetCirclePaint = new Paint();
			        targetCirclePaint.setAntiAlias(true);
			
			        targetCirclePulsePaint = new Paint();
			        targetCirclePulsePaint.setAntiAlias(true);
			
			        applyTargetOptions(context);
			
			        globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                if (isDismissing) {
						                    return;
						                }
					                updateTextLayouts();
					                target.onReady(new Runnable() {
						                    @Override
						                    public void run() {
							                        final int[] offset = new int[2];
							
							                        targetBounds.set(target.bounds());
							
							                        getLocationOnScreen(offset);
							                        targetBounds.offset(-offset[0], -offset[1]);
							
							                        if (boundingParent != null) {
								                            final WindowManager windowManager
								                                    = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
								                            final DisplayMetrics displayMetrics = new DisplayMetrics();
								                            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
								
								                            final Rect rect = new Rect();
								                            boundingParent.getWindowVisibleDisplayFrame(rect);
								
								                            // We bound the boundaries to be within the screen's coordinates to
								                            // handle the case where the layout bounds do not match
								                            // (like when FLAG_LAYOUT_NO_LIMITS is specified)
								                            topBoundary = Math.max(0, rect.top);
								                            bottomBoundary = Math.min(rect.bottom, displayMetrics.heightPixels);
								                        }
							
							                        drawTintedTarget();
							                        requestFocus();
							                        calculateDimensions();
							
							                        startExpandAnimation();
							                    }
						                });
					            }
				        };
			
			        getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
			
			        setFocusableInTouchMode(true);
			        setClickable(true);
			        setOnClickListener(new OnClickListener() {
				            @Override
				            public void onClick(View v) {
					                if (listener == null || outerCircleCenter == null || !isInteractable) return;
					
					                final boolean clickedInTarget =
					                        distance(targetBounds.centerX(), targetBounds.centerY(), (int) lastTouchX, (int) lastTouchY) <= targetCircleRadius;
					                final double distanceToOuterCircleCenter = distance(outerCircleCenter[0], outerCircleCenter[1],
					                        (int) lastTouchX, (int) lastTouchY);
					                final boolean clickedInsideOfOuterCircle = distanceToOuterCircleCenter <= outerCircleRadius;
					
					                if (clickedInTarget) {
						                    isInteractable = false;
						                    listener.onTargetClick(TapTargetView.this);
						                } else if (clickedInsideOfOuterCircle) {
						                    listener.onOuterCircleClick(TapTargetView.this);
						                } else if (cancelable) {
						                    isInteractable = false;
						                    listener.onTargetCancel(TapTargetView.this);
						                }
					            }
				        });
			
			        setOnLongClickListener(new OnLongClickListener() {
				            @Override
				            public boolean onLongClick(View v) {
					                if (listener == null) return false;
					
					                if (targetBounds.contains((int) lastTouchX, (int) lastTouchY)) {
						                    listener.onTargetLongClick(TapTargetView.this);
						                    return true;
						                }
					
					                return false;
					            }
				        });
			    }
		
		    private void startExpandAnimation() {
			        if (!visible) {
				            isInteractable = false;
				            expandAnimation.start();
				            visible = true;
				        }
			    }
		
		    protected void applyTargetOptions(Context context) {
			        shouldTintTarget = target.tintTarget;
			        shouldDrawShadow = target.drawShadow;
			        cancelable = target.cancelable;
			
			        // We can't clip out portions of a view outline, so if the user specified a transparent
			        // target, we need to fallback to drawing a jittered shadow approximation
			        if (shouldDrawShadow && Build.VERSION.SDK_INT >= 21 && !target.transparentTarget) {
				            outlineProvider = new ViewOutlineProvider() {
					                @Override
					                public void getOutline(View view, Outline outline) {
						                    if (outerCircleCenter == null) return;
						                    outline.setOval(
						                            (int) (outerCircleCenter[0] - outerCircleRadius), (int) (outerCircleCenter[1] - outerCircleRadius),
						                            (int) (outerCircleCenter[0] + outerCircleRadius), (int) (outerCircleCenter[1] + outerCircleRadius));
						                    outline.setAlpha(outerCircleAlpha / 255.0f);
						                    if (Build.VERSION.SDK_INT >= 22) {
							                        outline.offset(0, SHADOW_DIM);
							                    }
						                }
					            };
				
				            setOutlineProvider(outlineProvider);
				            setElevation(SHADOW_DIM);
				        }
			
			        if (shouldDrawShadow && outlineProvider == null && Build.VERSION.SDK_INT < 18) {
				            setLayerType(LAYER_TYPE_SOFTWARE, null);
				        } else {
				            setLayerType(LAYER_TYPE_HARDWARE, null);
				        }
			
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        isDark = UiUtil.themeIntAttr(context, "isLightTheme") == 0;
			
			        final Integer outerCircleColor = target.outerCircleColorInt(context);
			        if (outerCircleColor != null) {
				            outerCirclePaint.setColor(outerCircleColor);
				        } else if (theme != null) {
				            outerCirclePaint.setColor(UiUtil.themeIntAttr(context, "colorPrimary"));
				        } else {
				            outerCirclePaint.setColor(Color.WHITE);
				        }
			
			        final Integer targetCircleColor = target.targetCircleColorInt(context);
			        if (targetCircleColor != null) {
				            targetCirclePaint.setColor(targetCircleColor);
				        } else {
				            targetCirclePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        if (target.transparentTarget) {
				            targetCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
				        }
			
			        targetCirclePulsePaint.setColor(targetCirclePaint.getColor());
			
			        final Integer targetDimColor = target.dimColorInt(context);
			        if (targetDimColor != null) {
				            dimColor = UiUtil.setAlpha(targetDimColor, 0.3f);
				        } else {
				            dimColor = -1;
				        }
			
			        final Integer titleTextColor = target.titleTextColorInt(context);
			        if (titleTextColor != null) {
				            titlePaint.setColor(titleTextColor);
				        } else {
				            titlePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        final Integer descriptionTextColor = target.descriptionTextColorInt(context);
			        if (descriptionTextColor != null) {
				            descriptionPaint.setColor(descriptionTextColor);
				        } else {
				            descriptionPaint.setColor(titlePaint.getColor());
				        }
			
			        if (target.titleTypeface != null) {
				            titlePaint.setTypeface(target.titleTypeface);
				        }
			
			        if (target.descriptionTypeface != null) {
				            descriptionPaint.setTypeface(target.descriptionTypeface);
				        }
			    }
		
		    @Override
		    protected void onDetachedFromWindow() {
			        super.onDetachedFromWindow();
			        onDismiss(false);
			    }
		
		    void onDismiss(boolean userInitiated) {
			        if (isDismissed) return;
			
			        isDismissing = false;
			        isDismissed = true;
			
			        for (final ValueAnimator animator : animators) {
				            animator.cancel();
				            animator.removeAllUpdateListeners();
				        }
			        ViewUtil.removeOnGlobalLayoutListener(getViewTreeObserver(), globalLayoutListener);
			        visible = false;
			
			        if (listener != null) {
				            listener.onTargetDismissed(this, userInitiated);
				        }
			    }
		
		    @Override
		    protected void onDraw(Canvas c) {
			        if (isDismissed || outerCircleCenter == null) return;
			
			        if (topBoundary > 0 && bottomBoundary > 0) {
				            c.clipRect(0, topBoundary, getWidth(), bottomBoundary);
				        }
			
			        if (dimColor != -1) {
				            c.drawColor(dimColor);
				        }
			
			        int saveCount;
			        outerCirclePaint.setAlpha(outerCircleAlpha);
			        if (shouldDrawShadow && outlineProvider == null) {
				            saveCount = c.save();
				            {
					                c.clipPath(outerCirclePath, Region.Op.DIFFERENCE);
					                drawJitteredShadow(c);
					            }
				            c.restoreToCount(saveCount);
				        }
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, outerCirclePaint);
			
			        targetCirclePaint.setAlpha(targetCircleAlpha);
			        if (targetCirclePulseAlpha > 0) {
				            targetCirclePulsePaint.setAlpha(targetCirclePulseAlpha);
				            c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
				                    targetCirclePulseRadius, targetCirclePulsePaint);
				        }
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
			                targetCircleRadius, targetCirclePaint);
			
			        saveCount = c.save();
			        {
				            c.translate(textBounds.left, textBounds.top);
				            titlePaint.setAlpha(textAlpha);
				            if (titleLayout != null) {
					                titleLayout.draw(c);
					            }
				
				            if (descriptionLayout != null && titleLayout != null) {
					                c.translate(0, titleLayout.getHeight() + TEXT_SPACING);
					                descriptionPaint.setAlpha((int) (target.descriptionTextAlpha * textAlpha));
					                descriptionLayout.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        saveCount = c.save();
			        {
				            if (tintedTarget != null) {
					                c.translate(targetBounds.centerX() - tintedTarget.getWidth() / 2,
					                        targetBounds.centerY() - tintedTarget.getHeight() / 2);
					                c.drawBitmap(tintedTarget, 0, 0, targetCirclePaint);
					            } else if (target.icon != null) {
					                c.translate(targetBounds.centerX() - target.icon.getBounds().width() / 2,
					                        targetBounds.centerY() - target.icon.getBounds().height() / 2);
					                target.icon.setAlpha(targetCirclePaint.getAlpha());
					                target.icon.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        if (debug) {
				            drawDebugInformation(c);
				        }
			    }
		
		    @Override
		    public boolean onTouchEvent(MotionEvent e) {
			        lastTouchX = e.getX();
			        lastTouchY = e.getY();
			        return super.onTouchEvent(e);
			    }
		
		    @Override
		    public boolean onKeyDown(int keyCode, KeyEvent event) {
			        if (isVisible() && cancelable && keyCode == KeyEvent.KEYCODE_BACK) {
				            event.startTracking();
				            return true;
				        }
			
			        return false;
			    }
		
		    @Override
		    public boolean onKeyUp(int keyCode, KeyEvent event) {
			        if (isVisible() && isInteractable && cancelable
			                && keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && !event.isCanceled()) {
				            isInteractable = false;
				
				            if (listener != null) {
					                listener.onTargetCancel(this);
					            } else {
					                new Listener().onTargetCancel(this);
					            }
				
				            return true;
				        }
			
			        return false;
			    }
		
		    /**
     * Dismiss this view
     * @param tappedTarget If the user tapped the target or not
     *                     (results in different dismiss animations)
     */
		    public void dismiss(boolean tappedTarget) {
			        isDismissing = true;
			        pulseAnimation.cancel();
			        expandAnimation.cancel();
			        if (tappedTarget) {
				            dismissConfirmAnimation.start();
				        } else {
				            dismissAnimation.start();
				        }
			    }
		
		    /** Specify whether to draw a wireframe around the view, useful for debugging **/
		    public void setDrawDebug(boolean status) {
			        if (debug != status) {
				            debug = status;
				            postInvalidate();
				        }
			    }
		
		    /** Returns whether this view is visible or not **/
		    public boolean isVisible() {
			        return !isDismissed && visible;
			    }
		
		    void drawJitteredShadow(Canvas c) {
			        final float baseAlpha = 0.20f * outerCircleAlpha;
			        outerCircleShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			        outerCircleShadowPaint.setAlpha((int) baseAlpha);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM, outerCircleRadius, outerCircleShadowPaint);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        final int numJitters = 7;
			        for (int i = numJitters - 1; i > 0; --i) {
				            outerCircleShadowPaint.setAlpha((int) ((i / (float) numJitters) * baseAlpha));
				            c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM ,
				                    outerCircleRadius + (numJitters - i) * SHADOW_JITTER_DIM , outerCircleShadowPaint);
				        }
			    }
		
		    void drawDebugInformation(Canvas c) {
			        if (debugPaint == null) {
				            debugPaint = new Paint();
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugPaint.setStyle(Paint.Style.STROKE);
				            debugPaint.setStrokeWidth(UiUtil.dp(getContext(), 1));
				        }
			
			        if (debugTextPaint == null) {
				            debugTextPaint = new TextPaint();
				            debugTextPaint.setColor(0xFFFF0000);
				            debugTextPaint.setTextSize(UiUtil.sp(getContext(), 16));
				        }
			
			        // Draw wireframe
			        debugPaint.setStyle(Paint.Style.STROKE);
			        c.drawRect(textBounds, debugPaint);
			        c.drawRect(targetBounds, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], 10, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], calculatedOuterCircleRadius - CIRCLE_PADDING, debugPaint);
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(), TARGET_RADIUS + TARGET_PADDING, debugPaint);
			
			        // Draw positions and dimensions
			        debugPaint.setStyle(Paint.Style.FILL);
			        final String debugText =
			                "Text bounds: " + textBounds.toShortString() + "\n" + "Target bounds: " + targetBounds.toShortString() + "\n" + "Center: " + outerCircleCenter[0] + " " + outerCircleCenter[1] + "\n" + "View size: " + getWidth() + " " + getHeight() + "\n" + "Target bounds: " + targetBounds.toShortString();
			
			        if (debugStringBuilder == null) {
				            debugStringBuilder = new SpannableStringBuilder(debugText);
				        } else {
				            debugStringBuilder.clear();
				            debugStringBuilder.append(debugText);
				        }
			
			        if (debugLayout == null) {
				            debugLayout = new DynamicLayout(debugText, debugTextPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        }
			
			        final int saveCount = c.save();
			        {
				            debugPaint.setARGB(220, 0, 0, 0);
				            c.translate(0.0f, topBoundary);
				            c.drawRect(0.0f, 0.0f, debugLayout.getWidth(), debugLayout.getHeight(), debugPaint);
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugLayout.draw(c);
				        }
			        c.restoreToCount(saveCount);
			    }
		
		    void drawTintedTarget() {
			        final android.graphics.drawable.Drawable icon = target.icon;
			        if (!shouldTintTarget || icon == null) {
				            tintedTarget = null;
				            return;
				        }
			
			        if (tintedTarget != null) return;
			
			        tintedTarget = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(),
			                Bitmap.Config.ARGB_8888);
			        final Canvas canvas = new Canvas(tintedTarget);
			        icon.setColorFilter(new PorterDuffColorFilter(
			                outerCirclePaint.getColor(), PorterDuff.Mode.SRC_ATOP));
			        icon.draw(canvas);
			        icon.setColorFilter(null);
			    }
		
		    void updateTextLayouts() {
			        final int textWidth = Math.min(getWidth(), TEXT_MAX_WIDTH) - TEXT_PADDING * 2;
			        if (textWidth <= 0) {
				            return;
				        }
			
			        titleLayout = new StaticLayout(title, titlePaint, textWidth,
			                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
			
			        if (description != null) {
				            descriptionLayout = new StaticLayout(description, descriptionPaint, textWidth,
				                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        } else {
				            descriptionLayout = null;
				        }
			    }
		
		    float halfwayLerp(float lerp) {
			        if (lerp < 0.5f) {
				            return lerp / 0.5f;
				        }
			
			        return (1.0f - lerp) / 0.5f;
			    }
		
		    float delayedLerp(float lerp, float threshold) {
			        if (lerp < threshold) {
				            return 0.0f;
				        }
			
			        return (lerp - threshold) / (1.0f - threshold);
			    }
		
		    void calculateDimensions() {
			        textBounds = getTextBounds();
			        outerCircleCenter = getOuterCircleCenterPoint();
			        calculatedOuterCircleRadius = getOuterCircleRadius(outerCircleCenter[0], outerCircleCenter[1], textBounds, targetBounds);
			    }
		
		    void calculateDrawingBounds() {
			        if (outerCircleCenter == null) {
				            // Called dismiss before we got a chance to display the tap target
				            // So we have no center -> cant determine the drawing bounds
				            return;
				        }
			        drawingBounds.left = (int) Math.max(0, outerCircleCenter[0] - outerCircleRadius);
			        drawingBounds.top = (int) Math.min(0, outerCircleCenter[1] - outerCircleRadius);
			        drawingBounds.right = (int) Math.min(getWidth(),
			                outerCircleCenter[0] + outerCircleRadius + CIRCLE_PADDING);
			        drawingBounds.bottom = (int) Math.min(getHeight(),
			                outerCircleCenter[1] + outerCircleRadius + CIRCLE_PADDING);
			    }
		
		    int getOuterCircleRadius(int centerX, int centerY, Rect textBounds, Rect targetBounds) {
			        final int targetCenterX = targetBounds.centerX();
			        final int targetCenterY = targetBounds.centerY();
			        final int expandedRadius = (int) (1.1f * TARGET_RADIUS);
			        final Rect expandedBounds = new Rect(targetCenterX, targetCenterY, targetCenterX, targetCenterY);
			        expandedBounds.inset(-expandedRadius, -expandedRadius);
			
			        final int textRadius = maxDistanceToPoints(centerX, centerY, textBounds);
			        final int targetRadius = maxDistanceToPoints(centerX, centerY, expandedBounds);
			        return Math.max(textRadius, targetRadius) + CIRCLE_PADDING;
			    }
		
		    Rect getTextBounds() {
			        final int totalTextHeight = getTotalTextHeight();
			        final int totalTextWidth = getTotalTextWidth();
			
			        final int possibleTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight;
			        final int top;
			        if (possibleTop > topBoundary) {
				            top = possibleTop;
				        } else {
				            top = targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING;
				        }
			
			        final int relativeCenterDistance = (getWidth() / 2) - targetBounds.centerX();
			        final int bias = relativeCenterDistance < 0 ? -TEXT_POSITIONING_BIAS : TEXT_POSITIONING_BIAS;
			        final int left = Math.max(TEXT_PADDING, targetBounds.centerX() - bias - totalTextWidth);
			        final int right = Math.min(getWidth() - TEXT_PADDING, left + totalTextWidth);
			        return new Rect(left, top, right, top + totalTextHeight);
			    }
		
		    int[] getOuterCircleCenterPoint() {
			        if (inGutter(targetBounds.centerY())) {
				            return new int[]{targetBounds.centerX(), targetBounds.centerY()};
				        }
			
			        final int targetRadius = Math.max(targetBounds.width(), targetBounds.height()) / 2 + TARGET_PADDING;
			        final int totalTextHeight = getTotalTextHeight();
			
			        final boolean onTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight > 0;
			
			        final int left = Math.min(textBounds.left, targetBounds.left - targetRadius);
			        final int right = Math.max(textBounds.right, targetBounds.right + targetRadius);
			        final int titleHeight = titleLayout == null ? 0 : titleLayout.getHeight();
			        final int centerY = onTop ?
			                targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight + titleHeight
			                :
			                targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING + titleHeight;
			
			        return new int[] { (left + right) / 2, centerY };
			    }
		
		    int getTotalTextHeight() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getHeight() + TEXT_SPACING;
				        }
			
			        return titleLayout.getHeight() + descriptionLayout.getHeight() + TEXT_SPACING;
			    }
		
		    int getTotalTextWidth() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getWidth();
				        }
			
			        return Math.max(titleLayout.getWidth(), descriptionLayout.getWidth());
			    }
		    boolean inGutter(int y) {
			        if (bottomBoundary > 0) {
				            return y < GUTTER_DIM || y > bottomBoundary - GUTTER_DIM;
				        } else {
				            return y < GUTTER_DIM || y > getHeight() - GUTTER_DIM;
				        }
			    }
		    int maxDistanceToPoints(int x1, int y1, Rect bounds) {
			        final double tl = distance(x1, y1, bounds.left, bounds.top);
			        final double tr = distance(x1, y1, bounds.right, bounds.top);
			        final double bl = distance(x1, y1, bounds.left, bounds.bottom);
			        final double br = distance(x1, y1, bounds.right, bounds.bottom);
			        return (int) Math.max(tl, Math.max(tr, Math.max(bl, br)));
			    }
		    double distance(int x1, int y1, int x2, int y2) {
			        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
			    }
		    void invalidateViewAndOutline(Rect bounds) {
			        invalidate(bounds);
			        if (outlineProvider != null && Build.VERSION.SDK_INT >= 21) {
				            invalidateOutline();
				        }
			    }
	}
	
	static class ViewUtil {
		
		    ViewUtil() {}
		
		    private static boolean isLaidOut(View view) {
			        return true;
			    }
		    static void onLaidOut(final View view, final Runnable runnable) {
			        if (isLaidOut(view)) {
				            runnable.run();
				            return;
				        }
			        final ViewTreeObserver observer = view.getViewTreeObserver();
			        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                final ViewTreeObserver trueObserver;
					                if (observer.isAlive()) {
						                    trueObserver = observer;
						                } else {
						                    trueObserver = view.getViewTreeObserver();
						                }
					                removeOnGlobalLayoutListener(trueObserver, this);
					                runnable.run();
					            }
				        });
			    }
		    @SuppressWarnings("deprecation")
		    static void removeOnGlobalLayoutListener(ViewTreeObserver observer,
		                                             ViewTreeObserver.OnGlobalLayoutListener listener) {
			        if (Build.VERSION.SDK_INT >= 16) {
				            observer.removeOnGlobalLayoutListener(listener);
				        } else {
				            observer.removeGlobalOnLayoutListener(listener);
				        }
			    }
		    static void removeView(ViewManager parent, View child) {
			        if (parent == null || child == null) {
				            return;
				        }
			        try {
				            parent.removeView(child);
				        } catch (Exception ignored) {
				        }
			    }
	}
	
	static class ViewTapTarget extends TapTarget {
		    final View view;
		
		    ViewTapTarget(View view, CharSequence title,  CharSequence description) {
			        super(title, description);
			        if (view == null) {
				            throw new IllegalArgumentException("Given null view to target");
				        }
			        this.view = view;
			    }
		
		    @Override
		    public void onReady(final Runnable runnable) {
			        ViewUtil.onLaidOut(view, new Runnable() {
				            @Override
				            public void run() {
					                // Cache bounds
					                final int[] location = new int[2];
					                view.getLocationOnScreen(location);
					                bounds = new Rect(location[0], location[1],
					                        location[0] + view.getWidth(), location[1] + view.getHeight());
					
					                if (icon == null && view.getWidth() > 0 && view.getHeight() > 0) {
						                    final Bitmap viewBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
						                    final Canvas canvas = new Canvas(viewBitmap);
						                    view.draw(canvas);
						                    icon = new android.graphics.drawable.BitmapDrawable(view.getContext().getResources(), viewBitmap);
						                    icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
						                }
					
					                runnable.run();
					            }
				        });
			    }
	}
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _checkPermission() {
				if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
						ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
				} else {
						
				}
	}
	
	
	public void _callBack() {
		if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED || androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
			LayoutInflater Inflater = getLayoutInflater();
			View toastview= Inflater.inflate(R.layout.toast,null); 
			LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
			//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
			TextView txt =(TextView) toastview.findViewById(R.id.tot1);
			
			
			android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
			cc.setCornerRadius((int)24f);
			cc.setColor(Color.parseColor("#FF37375B"));
			lin1.setBackground(cc);
			
			/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
			
			txt.setText(" ! درخواست تایید نشد");
			txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
			
			Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastview);
			toast.setGravity(Gravity.BOTTOM, 0, 130);
			toast.show();
			
			//By Amir sina | amirsina_iz
		}
		else {
			per.edit().putString("PR", "true").commit();
			dp.dismiss();
			LayoutInflater Inflater = getLayoutInflater();
			View toastview= Inflater.inflate(R.layout.toast,null); 
			LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
			//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
			TextView txt =(TextView) toastview.findViewById(R.id.tot1);
			
			
			android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
			cc.setCornerRadius((int)24f);
			cc.setColor(Color.parseColor("#FF37375B"));
			lin1.setBackground(cc);
			
			/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
			
			txt.setText("درخواست تایید شد");
			txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
			
			Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastview);
			toast.setGravity(Gravity.BOTTOM, 0, 130);
			toast.show();
			
			//By Amir sina | amirsina_iz
			//background executor create here
			java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
			
			final Handler handler = new Handler(Looper.getMainLooper());
			
			  executor.execute(new Runnable() {
					
					@Override
					
					public void run() {FileUtil.makeDir("/storage/emulated/0/.PDIZ");
					handler.post(new Runnable() {
									
									@Override
									
									public void run() { 
											
											//UI Thread work here
															
									} });
							//background task start here
							
							
							
					} });
			_GetProjects();
			if (SHOWCASE.getString("SD", "").equals("true")) {
				
			}
			else {
				_ARGHOZALITapTarget(_fab, "شروع", "برای ساخت پی دی اف اینجا کلیک کنید", "#FF37375B");
				SHOWCASE.edit().putString("SD", "true").commit();
			}
		}
	}
	
	
	public void _GetProjects() {
		swiperefreshlayout1.setRefreshing(true);
		//background executor create here
		java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
		
		final Handler handler = new Handler(Looper.getMainLooper());
		
		  executor.execute(new Runnable() {
				
				@Override
				
				public void run() {FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/.PDIZ/"), path);
				position = 0;
				for(int _repeat14 = 0; _repeat14 < (int)(path.size()); _repeat14++) {
					try {
						
						java.io.RandomAccessFile randomAccessFile = new java.io.RandomAccessFile(path.get((int)(position)).concat("/info"), "r");
						
						byte[] bArr = new byte[((int) randomAccessFile.length())];
						
						randomAccessFile.readFully(bArr);
						
						dec_t = new String(bArr);
						
					}
					catch(Exception e) {
						showMessage(e.toString());
					}
					map = new Gson().fromJson(dec_t, new TypeToken<HashMap<String, Object>>(){}.getType());
					map.put("selectedItem", "false");
					listmap.add(map);
					position++;
				}
				handler.post(new Runnable() {
								
								@Override
								
								public void run() {list_pdfs.setAdapter(new List_pdfsAdapter(listmap));
						((BaseAdapter)list_pdfs.getAdapter()).notifyDataSetChanged();
						if (listmap.size() == 0) {
							list_pdfs.setVisibility(View.GONE);
							linearhopnf.setVisibility(View.VISIBLE);
							swiperefreshlayout1.setVisibility(View.GONE);
						}
						else {
							list_pdfs.setVisibility(View.VISIBLE);
							linearhopnf.setVisibility(View.GONE);
							swiperefreshlayout1.setVisibility(View.VISIBLE);
						}
						swiperefreshlayout1.setRefreshing(false);
										
										//UI Thread work here
														
								} });
						//background task start here
						
						
						
				} });
	}
	
	
	public void _MaterialDialog(final String _title, final String _message, final String _button1text, final String _button2text) {
		final AlertDialog dialog1 = new AlertDialog.Builder(MainActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.material_dialog_input,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		com.google.android.material.textfield.TextInputLayout textinputlayout2 = (com.google.android.material.textfield.TextInputLayout) inflate.findViewById(R.id.textinputlayout2);
		
		
		final EditText edittext1= new EditText(MainActivity.this);
		
		LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		edittext1.setLayoutParams(lpar);
		
		textinputlayout2.addView(edittext1);
		
		
		TextView b1 = (TextView) inflate.findViewById(R.id.b1);
		
		TextView b2 = (TextView) inflate.findViewById(R.id.b2);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		LinearLayout linear3 = (LinearLayout) inflate.findViewById(R.id.linear3);
		edittext1.setBackgroundColor(Color.TRANSPARENT);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		
		b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		t1.setText(_title);
		/*
t2.setText(_message);
*/
		b1.setText(_button1text);
		b2.setText(_button2text);
		_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*48);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF9E9E9E}), SketchUi, null);
			b1.setBackground(SketchUiRD);
			b1.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*48);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF9E9E9E}), SketchUi, null);
			b2.setBackground(SketchUiRD);
			b2.setClickable(true);
		}
		b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog1.dismiss();
			}
		});
		b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				if (!(edittext1.getText().toString().equals("") || edittext1.getText().toString().equals("."))) {
					c = Calendar.getInstance();
					Y = new SimpleDateFormat("yyyy").format(c.getTime());
					M = new SimpleDateFormat("MM").format(c.getTime());
					D = new SimpleDateFormat("dd").format(c.getTime());
					int Py=Integer.parseInt(Y);  
					int Pm=Integer.parseInt(M);  
					int Pd=Integer.parseInt(D);  
					AmirSina jCal = new AmirSina();
					jCal.GregorianToPersian(Py, Pm, Pd);
					_newTable(edittext1.getText().toString(), listmap.size() + 1, jCal.toString());
					if (isCamera) {
						_CameraPermission();
					}
					else {
						_FilePickerDialog("یک عکس انتخاب کنید ", "انتخاب ", "png, jpg, jpeg");
					}
				}
				else {
					LayoutInflater Inflater = getLayoutInflater();
					View toastview= Inflater.inflate(R.layout.toast,null); 
					LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
					//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
					TextView txt =(TextView) toastview.findViewById(R.id.tot1);
					
					
					android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
					cc.setCornerRadius((int)24f);
					cc.setColor(Color.parseColor("#FF37375B"));
					lin1.setBackground(cc);
					
					/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
					
					txt.setText("! مقدار وارد شده صحیح نیست");
					txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
					
					Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastview);
					toast.setGravity(Gravity.BOTTOM, 0, 130);
					toast.show();
					
					//By Amir sina | amirsina_iz
				}
			}
		});
		dialog1.setCancelable(true);
		dialog1.show();
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _newTable(final String _name, final double _number, final String _date) {
		//background executor create here
		java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
		
		final Handler handler = new Handler(Looper.getMainLooper());
		
		  executor.execute(new Runnable() {
				
				@Override
				
				public void run() {FileUtil.writeFile("/storage/emulated/0/.PDIZ/".concat(String.valueOf((long)(_number)).concat("/info")), "{\"name\":\"".concat(_name.concat("\",\"date\":\"".concat(_date.concat("\",\"path\":\"".concat("/storage/emulated/0/.PDIZ/".concat(String.valueOf((long)(_number)).concat("\",\"number\":\"".concat(String.valueOf((long)(_number)).concat("\"}"))))))))));
				FileUtil.makeDir("/storage/emulated/0/.PDIZ/".concat(String.valueOf((long)(_number)).concat("/Images")));
				FileUtil.makeDir("/storage/emulated/0/.PDIZ/".concat(String.valueOf((long)(_number)).concat("/PDF")));
				handler.post(new Runnable() {
								
								@Override
								
								public void run() { 
										
										//UI Thread work here
														
								} });
						//background task start here
						
						
						
				} });
	}
	
	
	public void _CameraPermission() {
		if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == android.content.pm.PackageManager.PERMISSION_DENIED || androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == android.content.pm.PackageManager.PERMISSION_DENIED) {
			androidx.core.app.ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA, android.Manifest.permission.CAMERA}, 1001);
		}
		else {
			intent.putExtra("pathNum", String.valueOf((long)(listmap.size() + 1)));
			intent.setClass(getApplicationContext(), CameraActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
	
	public void _ScrollingText(final TextView _view) {
		_view.setSingleLine(true);
		_view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_view.setSelected(true);
	}
	
	
	public void _searchInListmap(final String _value, final ArrayList<HashMap<String, Object>> _listmap, final String _key) {
		//background executor create here
		java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
		
		final Handler handler = new Handler(Looper.getMainLooper());
		
		  executor.execute(new Runnable() {
				
				@Override
				
				public void run() {_listmap.clear();
				n1 = 0;
				for(int _repeat13 = 0; _repeat13 < (int)(listmap.size()); _repeat13++) {
					if (listmap.get((int)n1).get(_key).toString().toLowerCase().contains(_value)) {
						cacheMap = listmap.get((int)n1);
						_listmap.add(cacheMap);
					}
					n1++;
				}
				handler.post(new Runnable() {
								
								@Override
								
								public void run() { 
										
										//UI Thread work here
														
								} });
						//background task start here
						
						
						
				} });
	}
	
	
	public void _endTimer(final TimerTask _tmr) {
		try {
			_tmr.cancel();
		} catch (Exception e) {
		};
	}
	
	
	public void _FilePickerDialog(final String _title, final String _message, final String _format) {
		//Change activity of this code to your activity
		//add import
		//import com.github.angads25.filepicker.controller.DialogSelectionListener ;
		//import com.github.angads25.filepicker.model.DialogConfigs;
		//import com.github.angads25.filepicker.model.DialogProperties;
		//import com.github.angads25.filepicker.view.FilePickerDialog;
		//need filepicker_v.1.1.1 library
		files_count = 0;
		DialogProperties filePickerp = new DialogProperties();
		filePickerp.selection_mode = DialogConfigs.SINGLE_MODE;
		filePickerp.selection_type = DialogConfigs.FILE_SELECT;
		filePickerp.root = new java.io.File(FileUtil.getExternalStorageDir());
		filePickerp.error_dir = new java.io.File(FileUtil.getExternalStorageDir());
		filePickerp.offset = new java.io.File(FileUtil.getExternalStorageDir());
		//filePickerp.extensions = new String[] {_format};
		final FilePickerDialog filePicker = new FilePickerDialog((MainActivity.this),filePickerp);
		filePicker.setTitle(_title);
		filePicker.setPositiveBtnName(_message);
		filePicker.setNegativeBtnName("لغو");
		filePicker.setDialogSelectionListener(new DialogSelectionListener() {
					@Override public void onSelectedFilePaths(String[] _files) {
						files = new ArrayList<String>(Arrays.asList(_files));
										
									
				//Here add what you want to do after pick file 👇
				if (Arrays.asList(_files).get((int) 0).toString().endsWith(".jpeg") || (Arrays.asList(_files).get((int) 0).toString().endsWith(".png") || Arrays.asList(_files).get((int) 0).toString().endsWith(".jpg"))) {
					intent.putExtra("NumOfP", String.valueOf((long)(listmap.size() + 1)));
					intent.putExtra("pathOfPic", Arrays.asList(_files).get((int) 0).toString());
					intent.setClass(getApplicationContext(), EditActivity.class);
					startActivity(intent);
				}
				else {
					LayoutInflater Inflater = getLayoutInflater();
					View toastview= Inflater.inflate(R.layout.toast,null); 
					LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
					//LinearLayout lin2 =(LinearLayout) toastview.findViewById(R.id.linear2);
					TextView txt =(TextView) toastview.findViewById(R.id.tot1);
					
					
					android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
					cc.setCornerRadius((int)24f);
					cc.setColor(Color.parseColor("#FF37375B"));
					lin1.setBackground(cc);
					
					/*android.graphics.drawable.GradientDrawable dd = new android.graphics.drawable.GradientDrawable();
dd.setCornerRadius((int)22f);
dd.setColor(Color.parseColor(""));
lin2.setBackground(dd);*/
					
					txt.setText("فایل انتخاب شده عکس نیست");
					txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
					
					Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastview);
					toast.setGravity(Gravity.BOTTOM, 0, 130);
					toast.show();
					
					//By Amir sina | amirsina_iz
				}
			} 
		});
		filePicker.show();
	}
	
	
	public void _GetResponse() {
	}
	private void updateInfoBilling() {
		final String buyProduct = myProduct+" is "+(bp.isPurchased(myProduct) ? "" : " not")+" purchased";
		final String subcribeProduct = myProduct+" is "+(bp.isSubscribed(myProduct) ? "" : " not")+" subscribed";
		 
	}
	{
	}
	
	public class List_pdfsAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public List_pdfsAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.listpdf, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final LinearLayout thumbnail_bg = _view.findViewById(R.id.thumbnail_bg);
			final LinearLayout informations = _view.findViewById(R.id.informations);
			final LinearLayout linear = _view.findViewById(R.id.linear);
			final ImageView thumbnail = _view.findViewById(R.id.thumbnail);
			final LinearLayout linear_name = _view.findViewById(R.id.linear_name);
			final LinearLayout linear_date = _view.findViewById(R.id.linear_date);
			final LinearLayout linear_pages = _view.findViewById(R.id.linear_pages);
			final TextView Name = _view.findViewById(R.id.Name);
			final TextView Date = _view.findViewById(R.id.Date);
			final ImageView Image = _view.findViewById(R.id.Image);
			final TextView Pages = _view.findViewById(R.id.Pages);
			final ImageView Select = _view.findViewById(R.id.Select);
			
			{
				android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
				int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				SketchUi.setColor(0xFFFFFFFF);
				SketchUi.setCornerRadius(d*0);
				SketchUi.setStroke(d*2,0xFF9E9E9E);
				thumbnail_bg.setElevation(d*1);
				thumbnail_bg.setBackground(SketchUi);
			}
			{
				android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
				int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				SketchUi.setColor(0xFFFFFFFF);
				SketchUi.setCornerRadius(d*8);
				SketchUi.setStroke(d*1,0xFF37375B);
				main.setElevation(d*10);
				android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
				main.setBackground(SketchUiRD);
				main.setClickable(true);
			}
			Name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
			Date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
			Pages.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
			/*
Select.setVisibility(View.GONE);
*/
			_ICC(Select, "#FF37375B", "#FF37375B");
			try {
				Name.setText("Name : ".concat(_data.get((int)_position).get("name").toString()));
			} catch (Exception e) {
				Name.setText("بدون عنوان");
			}
			try {
				Date.setText("Date : ".concat(_data.get((int)_position).get("date").toString()));
			} catch (Exception e) {
				Name.setText("بدون تاریخ");
			}
			Pages.setText("Loading..");
			//background executor create here
			java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
			
			final Handler handler = new Handler(Looper.getMainLooper());
			
			  executor.execute(new Runnable() {
					
					@Override
					
					public void run() {FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(String.valueOf((long)(_position + 1)).concat("/Images"))), PagesList);
					handler.post(new Runnable() {
									
									@Override
									
									public void run() {Pages.setText(String.valueOf((long)(PagesList.size())));
											
											//UI Thread work here
															
									} });
							//background task start here
							
							
							
					} });
			_ScrollingText(Name);
			for(int _repeat63 = 0; _repeat63 < (int)(listmap.size()); _repeat63++) {
				try {
					thumbnail.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(PagesList.get((int)(0)), 1024, 1024));
				} catch (Exception e) {
					thumbnail.setImageResource(R.drawable.thumbnail);
				}
			}
			main.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					if (_data.get((int)_position).get("selectedItem").toString().equals("true")) {
						
					}
					else {
						if (SLN == listmap.size()) {
							
						}
						else {
							SLN++;
							_data.get((int)_position).put("selectedItem", "true");
							Select.setImageResource(R.drawable.ic_brightness_1_white);
							main.setBackgroundColor(0xFFBBDEFB);
							setTitle(String.valueOf((long)(SLN)).concat("/".concat(String.valueOf((long)(listmap.size())))));
							deleteMode = true;
							_fab.setImageResource(R.drawable.ic_delete_white);
						}
					}
					return true;
				}
			});
			main.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).get("selectedItem").toString().equals("true")) {
						if (SLN == 1) {
							setTitle("PDF Creator ");
							SLN--;
							_data.get((int)_position).put("selectedItem", "false");
							Select.setImageResource(R.drawable.ic_panorama_fisheye_white);
							main.setBackgroundColor(0xFFFFFFFF);
							deleteMode = false;
							_fab.setImageResource(R.drawable.ic_add_white);
						}
						else {
							SLN--;
							_data.get((int)_position).put("selectedItem", "false");
							Select.setImageResource(R.drawable.ic_panorama_fisheye_white);
							main.setBackgroundColor(0xFFFFFFFF);
							setTitle(String.valueOf((long)(SLN)).concat("/".concat(String.valueOf((long)(listmap.size())))));
						}
					}
					else {
						view.putExtra("ProjectNumber", _data.get((int)_position).get("number").toString());
						view.setClass(getApplicationContext(), CreatePdfActivity.class);
						startActivity(view);
						overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				}
			});
			
			return _view;
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