package amir.sina.iz.doc.scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import android.content.Intent;
import android.net.Uri;
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
import arabware.forfree.pdfcreator.*;

public class CreatePdfActivity extends AppCompatActivity {
	
	private LinearLayout bg;
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private String subtitle = "";
	private String Folder = "";
	private double position = 0;
	private boolean isCamera = false;
	private double files_count = 0;
	private String JsonName = "";
	private HashMap<String, Object> NOP = new HashMap<>();
	private String SavePDF = "";
	private String fontName = "";
	private String typeace = "";
	private String SharePath = "";
	private String NewJson = "";
	private HashMap<String, Object> map = new HashMap<>();
	private double drag = 0;
	private double drop = 0;
	private double position2 = 0;
	private boolean isFab = false;
	
	private ArrayList<String> liststring = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> File_map = new ArrayList<>();
	private ArrayList<String> files = new ArrayList<>();
	
	private GridView gridview1;
	
	private Intent intent = new Intent();
	private AlertDialog dialog;
	private AlertDialog dialog2;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.create_pdf);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
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
		
		gridview1 = findViewById(R.id.gridview1);
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				dialog2 = new AlertDialog.Builder(CreatePdfActivity.this).create();
				LayoutInflater dialog2LI = getLayoutInflater();
				View dialog2CV = (View) dialog2LI.inflate(R.layout.preview, null);
				dialog2.setView(dialog2CV);
				final PhotoView photo_view = (PhotoView)
				dialog2CV.findViewById(R.id.photo_view);
				dialog2.setCancelable(true);
				dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				photo_view.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(File_map.get((int)_position).get("file").toString(), 1024, 1024));
				dialog2.show();
			}
		});
		
		gridview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				ClipData.Item item = new ClipData.Item((String) _param2.getTag()); 
				ClipData clipData = new ClipData((CharSequence) _param2.getTag(), 
				new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item); 
				_param2.startDrag(clipData, new View.DragShadowBuilder(_param2), null, 0); 
				
				draggedIndex = (int)_position; 
				return true;
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isFab) {
					isFab = false;
					_showFab(false);
				}
				else {
					isFab = true;
					_showFab(true);
				}
			}
		});
	}
	
	private void initializeLogic() {
		
		Folder = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("ProjectNumber").concat("/Images")));
		setTitle("Create PDF");
		//background executor create here
		java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
		
		final Handler handler = new Handler(Looper.getMainLooper());
		
		  executor.execute(new Runnable() {
				
				@Override
				
				public void run() {JsonName = FileUtil.readFile(FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("ProjectNumber").concat("/info"))));
				NOP = new Gson().fromJson(JsonName, new TypeToken<HashMap<String, Object>>(){}.getType());
				handler.post(new Runnable() {
								
								@Override
								
								public void run() {setTitle(NOP.get("name").toString());
										
										//UI Thread work here
														
								} });
						//background task start here
						
						
						
				} });
		StrictMode.setVmPolicy(new
		StrictMode.VmPolicy.Builder().build());
		if(Build. VERSION.SDK_INT>=24){
			try{
				java.lang.reflect.Method
				m=StrictMode.class.getMethod(
				"disableDeathOnFileUriExposure");
				m.invoke(null);
			}
			catch(Exception e) {
				showMessage(e.toString());
			}
		}
		_changeActivityFont("app_font");
		_RefreshData();
		_onCreateFab();
		_fab1();
		_fab2();
		_showFab(false);
	}
	
	@Override
	public void onBackPressed() {
		startActivity(new Intent(CreatePdfActivity.this, MainActivity.class)); Animatoo.animateZoom(CreatePdfActivity.this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("ساخت پی دی اف")
		.setIcon(R.drawable.pdfcr)
		 .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.add("اشتراک گذاری")
		.setIcon(R.drawable.share)
		 .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.add("تغییر نام")
		.setIcon(R.drawable.rename2)
		 .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		return true;
//@amirsina_iz
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int _id = item.getItemId();
		final String _title = (String) item.getTitle();
		if (_title.equals("ساخت پی دی اف")) {
			liststring.clear();
			position2 = 0;
			//background executor create here
			java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
			
			final Handler handler = new Handler(Looper.getMainLooper());
			
			  executor.execute(new Runnable() {
					
					@Override
					
					public void run() {for(int _repeat23 = 0; _repeat23 < (int)(File_map.size()); _repeat23++) {
						liststring.add(File_map.get((int)position2).get("file").toString());
						position2++;
					}
					handler.post(new Runnable() {
									
									@Override
									
									public void run() {_CreatePdf();
											
											//UI Thread work here
															
									} });
							//background task start here
							
							
							
					} });
		}
		else {
			if (_title.equals("تغییر نام")) {
				_RenameShow();
			}
			else {
				if (_title.equals("اشتراک گذاری")) {
					if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("ProjectNumber").concat("/PDF/".concat(NOP.get("name").toString().concat(".pdf"))))))) {
						SharePath = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("ProjectNumber").concat("/PDF/".concat(NOP.get("name").toString().concat(".pdf")))));
						Intent share = new Intent(Intent.ACTION_SEND);
									share.setType("*/*");
									share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new java.io.File(SharePath)));
									startActivity(Intent.createChooser(share, "Share File"));
					}
					else {
						liststring.clear();
						position2 = 0;
						//background executor create here
						java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
						
						final Handler handler = new Handler(Looper.getMainLooper());
						
						  executor.execute(new Runnable() {
								
								@Override
								
								public void run() {for(int _repeat61 = 0; _repeat61 < (int)(File_map.size()); _repeat61++) {
									liststring.add(File_map.get((int)position2).get("file").toString());
									position2++;
								}
								handler.post(new Runnable() {
												
												@Override
												
												public void run() {_CreatePdf();
														
														//UI Thread work here
																		
												} });
										//background task start here
										
										
										
								} });
					}
				}
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void _RefreshData() {
		File_map.clear();
		subtitle = Folder;
		FileUtil.listDir(Folder, liststring);
		Collections.sort(liststring, String.CASE_INSENSITIVE_ORDER);
		position = 0;
		for(int _repeat14 = 0; _repeat14 < (int)(liststring.size()); _repeat14++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("file", liststring.get((int)(position)));
				File_map.add(_item);
			}
			
			position++;
		}
		gridview1.setAdapter(new Gridview1Adapter(File_map));
		((BaseAdapter)gridview1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _ColorShadow_SDK28(final View _view, final String _color, final double _number) {
		_view.setElevation((float)_number);
		
		_view.setOutlineAmbientShadowColor(Color.parseColor(_color));
		_view.setOutlineSpotShadowColor(Color.parseColor(_color));
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
		final FilePickerDialog filePicker = new FilePickerDialog((CreatePdfActivity.this),filePickerp);
		filePicker.setTitle(_title);
		filePicker.setPositiveBtnName(_message);
		filePicker.setNegativeBtnName("لغو");
		filePicker.setDialogSelectionListener(new DialogSelectionListener() {
					@Override public void onSelectedFilePaths(String[] _files) {
						files = new ArrayList<String>(Arrays.asList(_files));
										
									
				//Here add what you want to do after pick file 👇
				if (Arrays.asList(_files).get((int) 0).toString().endsWith(".jpeg") || (Arrays.asList(_files).get((int) 0).toString().endsWith(".png") || Arrays.asList(_files).get((int) 0).toString().endsWith(".jpg"))) {
					intent.putExtra("NumOfP", getIntent().getStringExtra("ProjectNumber"));
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
						
				  _FilePickerDialog("یک عکس انتخاب کنید", "انتخاب", "");
				
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
						
				  intent.putExtra("pathNum", getIntent().getStringExtra("ProjectNumber"));
				intent.setClass(getApplicationContext(), CameraActivity.class);
				startActivity(intent);
				finish();
				
				}});
	}
	
	
	public void _CreatePdf() {
		dialog = new AlertDialog.Builder(CreatePdfActivity.this).create();
		LayoutInflater dialogLI = getLayoutInflater();
		View dialogCV = (View) dialogLI.inflate(R.layout.loading, null);
		dialog.setView(dialogCV);
		dialog.setCancelable(false);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialog.show();
		try {
			SavePDF = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("ProjectNumber").concat("/PDF/".concat(NOP.get("name").toString().concat(".pdf")))));
			PdfCreator pc = new PdfCreator();
			pc.setSavePath(SavePDF);
			pc.setListener(new PdfCreator.PdfCreatingListener() {
				@Override
				public void onWorking(int progress) {
				}
				@Override
				public void onDone() {
					dialog.dismiss();
					LayoutInflater Inflater = getLayoutInflater();
					View toastview= Inflater.inflate(R.layout.toast,null); 
					LinearLayout lin1 =(LinearLayout) toastview.findViewById(R.id.linear_to1);
					TextView txt =(TextView) toastview.findViewById(R.id.tot1);
					
					
					android.graphics.drawable.GradientDrawable cc = new android.graphics.drawable.GradientDrawable();
					cc.setCornerRadius((int)24f);
					cc.setColor(Color.parseColor("#FF37375B"));
					lin1.setBackground(cc);
					
					
					txt.setText("با موفقیت انجام شد ");
					txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
					
					Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
					toast.setView(toastview);
					toast.setGravity(Gravity.BOTTOM, 0, 130);
					toast.show();
					
					//By Amir sina | amirsina_iz
					SharePath = SavePDF;
					Intent share = new Intent(Intent.ACTION_SEND);
					share.setType("*/*");
					share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new java.io.File(SharePath)));
					startActivity(Intent.createChooser(share, "Share File"));
				}});
			pc.createPdf(liststring);
		} catch(Exception e){
			//on Error
			/* use e.getMessage() or e.getMessage().toString to get the error message*/
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
	
	
	public void _Dragger(final View _view) {
		_view.setOnDragListener( new OnDragListener(){
			 @Override public boolean onDrag(View v, DragEvent dragEvent) { switch (dragEvent.getAction()) { 
					case DragEvent.ACTION_DRAG_STARTED: 
					
					if(dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
						return true;
					}
					return false;
					
					case DragEvent.ACTION_DRAG_ENTERED:
					v.setBackgroundColor(0xFFECEFF1);
					v.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)55, 0xFFECEFF1));
					return true;
					
					case DragEvent.ACTION_DRAG_EXITED: 
					v.setBackgroundColor(0xFFFFFFFF);
					v.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)55, 0xFFFFFFFF));
					return true;
					
					case DragEvent.ACTION_DRAG_LOCATION:
					return true; 
					
					case DragEvent.ACTION_DROP:
					v.setBackgroundColor(0xFFFFFFFF);
					v.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)55, 0xFFFFFFFF));
					
					droppedIndex = gridview1.getPositionForView(v); 
					
					drag = draggedIndex;
					drop = droppedIndex;
					map = File_map.get((int)drag);
					File_map.remove((int)(drag));
					File_map.add((int)drop, map);
					((BaseAdapter)gridview1.getAdapter()).notifyDataSetChanged();
					draggedIndex = -1; 
					droppedIndex = -1; 
					
					case DragEvent.ACTION_DRAG_ENDED: 
					return true; 
				} 
				return false; 
			}
		});
		
	}
	int draggedIndex = -1; 
	int droppedIndex = -1;
	{
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
	
	
	public void _RenameShow() {
		final AlertDialog dialog1 = new AlertDialog.Builder(CreatePdfActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.rename_dialog,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		com.google.android.material.textfield.TextInputLayout textinputlayout2 = (com.google.android.material.textfield.TextInputLayout) inflate.findViewById(R.id.textinputlayout2);
		
		
		final EditText edittext1= new EditText(CreatePdfActivity.this);
		
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
		bg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFFFFFFFF));
		b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog1.dismiss();
			}
		});
		b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				//background executor create here
				java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
				
				final Handler handler = new Handler(Looper.getMainLooper());
				
				  executor.execute(new Runnable() {
						
						@Override
						
						public void run() {NOP.put("name", edittext1.getText().toString());
						NewJson = new Gson().toJson(NOP);
						FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("ProjectNumber").concat("/info"))), NewJson);
						handler.post(new Runnable() {
										
										@Override
										
										public void run() {setTitle(NOP.get("name").toString());
												
												//UI Thread work here
																
										} });
								//background task start here
								
								
								
						} });
				dialog1.dismiss();
			}
		});
		dialog1.setCancelable(true);
		dialog1.show();
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.image_items, null);
			}
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout ripple = _view.findViewById(R.id.ripple);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final androidx.cardview.widget.CardView cardview3 = _view.findViewById(R.id.cardview3);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			try {
				imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_data.get((int)_position).get("file").toString(), 1024, 1024));
			} catch (Exception e) {
				imageview1.setImageResource(R.drawable.thumbnail);
			}
			textview2.setText(String.valueOf((long)(_position + 1)));
			_ColorShadow_SDK28(cardview1, "#FF37375B", 8);
			_ColorShadow_SDK28(cardview3, "#FF37375B", 2);
			_Dragger(cardview1);
			cardview3.setPreventCornerOverlap(true);
			cardview1.setPreventCornerOverlap(true);
			
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