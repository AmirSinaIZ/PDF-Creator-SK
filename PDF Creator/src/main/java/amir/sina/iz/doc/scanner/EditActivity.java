package amir.sina.iz.doc.scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
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
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.HorizontalScrollView;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.graphics.Typeface;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import me.pqpo.smartcropperlib.SmartCropper;
import me.pqpo.smartcropperlib.view.CropImageView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import amir.sina.iz.doc.scanner.OpenCVCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import timber.log.Timber;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;

public class EditActivity extends AppCompatActivity {
	
	OpenCVCallback mOpenCVLoaderCallback;
	Bitmap mResult;
	private FloatingActionButton _fab;
	private String fontName = "";
	private String typeace = "";
	private String IMG = "";
	private String PathOfCroped = "";
	private boolean Mode2 = false;
	private String PickedColour = "FFFFFF";
	private String TtoE = "";
	private String SaveEditPic = "";
	
	private LinearLayout CropLinear;
	private LinearLayout LinearEditor;
	private ImageView ToSaveImage;
	private CropImageView CropView;
	private LinearLayout LinearDone;
	private TextView textview1;
	private LinearLayout EditView;
	private LinearLayout LinearTools;
	private LinearLayout LinearFilters;
	private LinearLayout PickColor;
	private LinearLayout AddText;
	private LinearLayout Brush;
	private ImageView imageview4;
	private ImageView imageview2;
	private ImageView imageview1;
	private ImageView imageview3;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear2;
	private ImageView normal;
	private ImageView brightness;
	private ImageView gray;
	private ImageView brown;
	private ImageView nativer;
	private ImageView magic;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.edit);
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
		_fab = findViewById(R.id._fab);
		
		CropLinear = findViewById(R.id.CropLinear);
		LinearEditor = findViewById(R.id.LinearEditor);
		ToSaveImage = findViewById(R.id.ToSaveImage);
		CropView = findViewById(R.id.CropView);
		LinearDone = findViewById(R.id.LinearDone);
		textview1 = findViewById(R.id.textview1);
		EditView = findViewById(R.id.EditView);
		LinearTools = findViewById(R.id.LinearTools);
		LinearFilters = findViewById(R.id.LinearFilters);
		PickColor = findViewById(R.id.PickColor);
		AddText = findViewById(R.id.AddText);
		Brush = findViewById(R.id.Brush);
		imageview4 = findViewById(R.id.imageview4);
		imageview2 = findViewById(R.id.imageview2);
		imageview1 = findViewById(R.id.imageview1);
		imageview3 = findViewById(R.id.imageview3);
		hscroll1 = findViewById(R.id.hscroll1);
		linear2 = findViewById(R.id.linear2);
		normal = findViewById(R.id.normal);
		brightness = findViewById(R.id.brightness);
		gray = findViewById(R.id.gray);
		brown = findViewById(R.id.brown);
		nativer = findViewById(R.id.nativer);
		magic = findViewById(R.id.magic);
		
		LinearDone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_MaterialDialog("", "", "", "");
			}
		});
		
		PickColor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_ColorPickerDialog("", "", "", "");
			}
		});
		
		AddText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_addT();
			}
		});
		
		Brush.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mPhotoEditor.setBrushDrawingMode(true);
				mPhotoEditor.setBrushSize(30);
				mPhotoEditor.setOpacity(100);
				mPhotoEditor.setBrushColor(Color.parseColor("#" + PickedColour));
			}
		});
		
		normal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mPhotoEditor.setFilterEffect(PhotoFilter.NONE);
			}
		});
		
		brightness.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mPhotoEditor.setFilterEffect(PhotoFilter.BRIGHTNESS);
			}
		});
		
		gray.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mPhotoEditor.setFilterEffect(PhotoFilter.GRAY_SCALE);
			}
		});
		
		brown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mPhotoEditor.setFilterEffect(PhotoFilter.SEPIA);
			}
		});
		
		nativer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mPhotoEditor.setFilterEffect(PhotoFilter.NEGATIVE);
			}
		});
		
		magic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mPhotoEditor.setFilterEffect(PhotoFilter.SATURATE);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SaveEditPic = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("NumOfP").concat("/Images/".concat(Uri.parse(getIntent().getStringExtra("pathOfPic")).getLastPathSegment()))));
				mPhotoEditor.saveAsFile(SaveEditPic, new ja.burhanrashid52.photoeditor.PhotoEditor.OnSaveListener() {
					@Override
					public void onSuccess(String imagePath) {
						
						intent.putExtra("ProjectNumber", getIntent().getStringExtra("NumOfP"));
						intent.putExtra("PicturePath", getIntent().getStringExtra("pathOfPic"));
						intent.setClass(getApplicationContext(), CreatePdfActivity.class);
						startActivity(intent);
						overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
					 @Override
					public void onFailure(Exception exception) {
						
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
						
						txt.setText("! مشکلی پیش آمده ");
						txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
						
						Toast toast = new Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_SHORT);
						toast.setView(toastview);
						toast.setGravity(Gravity.BOTTOM, 0, 130);
						toast.show();
						
						//By Amir sina | amirsina_iz
					}});
			}
		});
	}
	
	private void initializeLogic() {
		
		IMG = getIntent().getStringExtra("pathOfPic");
		Bitmap bitmap = BitmapFactory.decodeFile(IMG);
		SmartCropper.buildImageDetector(this);
		CropView.setImageToCrop(bitmap); 
		_CreateEDT();
		mOpenCVLoaderCallback = new OpenCVCallback(this) {
			            @Override
			            public void onManagerConnected(int status) {
				                switch (status) {
					                    case LoaderCallbackInterface.SUCCESS: {
						                        break;
						                    }
					
					                    default: {
						                        super.onManagerConnected(status);
						                    }
					                }
				            }
			        };
		_initOpenCV();
		hscroll1.setHorizontalScrollBarEnabled(false);
		hscroll1.setVerticalScrollBarEnabled(false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_changeActivityFont("app_font");
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFE0E0E0);
			SketchUi.setCornerRadius(d*3);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			LinearDone.setBackground(SketchUiRD);
			LinearDone.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFF37375B);
			SketchUi.setCornerRadius(d*360);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFFFFFFF}), SketchUi, null);
			Brush.setBackground(SketchUiRD);
			Brush.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFF37375B);
			SketchUi.setCornerRadius(d*360);
			AddText.setElevation(d*0);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFFFFFFF}), SketchUi, null);
			AddText.setBackground(SketchUiRD);
			AddText.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFF37375B);
			SketchUi.setCornerRadius(d*360);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFFFFFFF}), SketchUi, null);
			PickColor.setBackground(SketchUiRD);
			PickColor.setClickable(true);
		}
		_fab.setVisibility(View.GONE);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		_initOpenCV();
	}
	
	@Override
	public void onBackPressed() {
		startActivity(new Intent(EditActivity.this, MainActivity.class)); Animatoo.animateCard(EditActivity.this);
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
	
	
	public void _CreateEDT() {
		mPhotoEditorView = new ja.burhanrashid52.photoeditor.PhotoEditorView(this);
		mPhotoEditorView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.MATCH_PARENT));
		EditView.addView(mPhotoEditorView);
		mPhotoEditor = new ja.burhanrashid52.photoeditor.PhotoEditor.Builder(this, mPhotoEditorView).setPinchTextScalable(true).build();
		//set linear same as your linear view where you want to add image and all
	}
	
	
	public void _initOpenCV() {
		if (!OpenCVLoader.initDebug()) {
			            Timber.d("Internal OpenCV library not found. Using OpenCV Manager for initialization");
			            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mOpenCVLoaderCallback);
			        } else {
			            Timber.d("OpenCV library found inside package. Using it!");
			            mOpenCVLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
			        }
	}
	
	
	public void _applyThreshold() {
	}
	    private Bitmap applyThreshold(Mat src) {
		        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
		
		
		if (Mode2) {
			       Imgproc.GaussianBlur(src, src, new Size(5, 5), 0);
			       Imgproc.adaptiveThreshold(src, src, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 2);
		}
		else {
			       Imgproc.adaptiveThreshold(src, src, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 15);
			       Imgproc.threshold(src, src, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);
		}
		
		
		
		        Bitmap bm = Bitmap.createBitmap(src.width(), src.height(), Bitmap.Config.ARGB_8888);
		        org.opencv.android.Utils.matToBitmap(src, bm);
		
		        return bm;
	}
	
	
	public void _lib() {
	}
	ja.burhanrashid52.photoeditor.PhotoEditorView mPhotoEditorView;
	ja.burhanrashid52.photoeditor.PhotoEditor mPhotoEditor;
	{
	}
	
	
	public void _save(final ImageView _imageview, final String _path, final String _name, final double _quality) {
		
		try{
					BitmapDrawable _imageviewBD = (BitmapDrawable) _imageview.getDrawable();
					Bitmap _imageviewB = _imageviewBD.getBitmap();
					java.io.FileOutputStream _imageviewFOS = null;
					java.io.File _imageviewF = Environment.getExternalStorageDirectory();
					java.io.File _imageviewF2 = new java.io.File(_path);
					_imageviewF2.mkdirs();
					String _imageviewFN = _name;
					java.io.File _imageviewF3 = new java.io.File(_imageviewF2, _imageviewFN);
					_imageviewFOS = new java.io.FileOutputStream(_imageviewF3); 
					_imageviewB.compress(Bitmap.CompressFormat.JPEG, (int) _quality, _imageviewFOS);
					_imageviewFOS.flush();
					_imageviewFOS.close(); 
					Intent _imageviewI = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
					_imageviewI.setData(Uri.fromFile(_imageviewF)); sendBroadcast(_imageviewI);
		}catch(Exception e){
		}
		try {
				android.media.MediaScannerConnection.scanFile(EditActivity.this,new String[]{new java.io.File(_path + _name).getPath()}, new String[]{"image/jpeg"}, null);
		} catch(Exception e) {}
		try {
				android.media.MediaScannerConnection.scanFile(EditActivity.this,new String[]{new java.io.File(_path + _name).getPath()}, new String[]{"image/png"}, null);
		} catch (Exception e) {}
	}
	
	
	public void _MaterialDialog(final String _title, final String _message, final String _button1text, final String _button2text) {
		final AlertDialog dialog1 = new AlertDialog.Builder(EditActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.selecttype,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		TextView t2 = (TextView) inflate.findViewById(R.id.t2);
		
		TextView b1 = (TextView) inflate.findViewById(R.id.b1);
		
		TextView b2 = (TextView) inflate.findViewById(R.id.b2);
		
		TextView b3 = (TextView) inflate.findViewById(R.id.b3);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		LinearLayout linear3 = (LinearLayout) inflate.findViewById(R.id.linear3);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 0);
		b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		b3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*360);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			b1.setBackground(SketchUiRD);
			b1.setClickable(true);
		}
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			SketchUi.setColor(0xFFFFFFFF);
			SketchUi.setCornerRadius(d*360);
			android.graphics.drawable.RippleDrawable SketchUiRD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), SketchUi, null);
			b2.setBackground(SketchUiRD);
			b2.setClickable(true);
			b3.setBackground(SketchUiRD);
			b3.setClickable(true);
		}
		b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog1.dismiss();
				Mode2 = false;
				Bitmap crop = CropView.crop();  
				Mat orig = new Mat();
				org.opencv.android.Utils.bitmapToMat(crop, orig);
				mResult = applyThreshold(orig);
				ToSaveImage.setImageBitmap(mResult);
				CropLinear.setVisibility(View.GONE);
				_fab.setVisibility(View.VISIBLE);
				_save(ToSaveImage, FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("NumOfP").concat("/Images"))), Uri.parse(getIntent().getStringExtra("pathOfPic")).getLastPathSegment(), 100);
				PathOfCroped = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("NumOfP").concat("/Images/".concat(Uri.parse(getIntent().getStringExtra("pathOfPic")).getLastPathSegment()))));
				mPhotoEditorView.getSource().setImageURI(Uri.parse(PathOfCroped));
				dialog1.dismiss();
			}
		});
		b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog1.dismiss();
				Mode2 = true;
				Bitmap crop = CropView.crop();  
				Mat orig = new Mat();
				org.opencv.android.Utils.bitmapToMat(crop, orig);
				mResult = applyThreshold(orig);
				ToSaveImage.setImageBitmap(mResult);
				CropLinear.setVisibility(View.GONE);
				_fab.setVisibility(View.VISIBLE);
				_save(ToSaveImage, FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("NumOfP").concat("/Images"))), Uri.parse(getIntent().getStringExtra("pathOfPic")).getLastPathSegment(), 100);
				PathOfCroped = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("NumOfP").concat("/Images/".concat(Uri.parse(getIntent().getStringExtra("pathOfPic")).getLastPathSegment()))));
				mPhotoEditorView.getSource().setImageURI(Uri.parse(PathOfCroped));
				dialog1.dismiss();
			}
		});
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Bitmap crop = CropView.crop();  
				ToSaveImage.setImageBitmap(crop);
				CropLinear.setVisibility(View.GONE);
				_fab.setVisibility(View.VISIBLE);
				_save(ToSaveImage, FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("NumOfP").concat("/Images"))), Uri.parse(getIntent().getStringExtra("pathOfPic")).getLastPathSegment(), 100);
				PathOfCroped = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("NumOfP").concat("/Images/".concat(Uri.parse(getIntent().getStringExtra("pathOfPic")).getLastPathSegment()))));
				mPhotoEditorView.getSource().setImageURI(Uri.parse(PathOfCroped));
				dialog1.dismiss();
			}
		});
		dialog1.setCancelable(false);
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
	
	
	public void _ColorPickerDialog(final String _title, final String _message, final String _button1text, final String _button2text) {
		final AlertDialog dialog2 = new AlertDialog.Builder(EditActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.colorpicker,null); 
		dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog2.setView(inflate);
		TextView t12 = (TextView) inflate.findViewById(R.id.t12);
		
		ColorPickerView cp1 = (ColorPickerView) inflate.findViewById(R.id.color_picker_view);
		
		TextView b12 = (TextView) inflate.findViewById(R.id.b12);
		
		TextView b22 = (TextView) inflate.findViewById(R.id.b22);
		
		LinearLayout bg2 = (LinearLayout) inflate.findViewById(R.id.bg2);
		
		LinearLayout linear379975 = (LinearLayout) inflate.findViewById(R.id.linear3);
		t12.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		b12.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		b22.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/app_font.ttf"), 1);
		_rippleRoundStroke(bg2, "#FFFFFF", "#000000", 15, 0, "#000000");
				cp1.addOnColorChangedListener(new OnColorChangedListener() {
						@Override public void onColorChanged(int selectedColor) {
								// Handle on color change
								Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
								
						}
				});
				cp1.addOnColorSelectedListener(new OnColorSelectedListener() {
						@Override
						public void onColorSelected(int selectedColor) {
								
									PickedColour = Integer.toHexString(selectedColor).toUpperCase();
									
									
						}
				});
			
		b12.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog2.dismiss();
			}
		});
		b22.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog2.dismiss();
				_ICC(imageview4, "#".concat(PickedColour), "#".concat(PickedColour));
			}
		});
		dialog2.setCancelable(false);
		dialog2.show();
	}
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
	}
	
	
	public void _addT() {
		final AlertDialog dialog1 = new AlertDialog.Builder(EditActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.add_text,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		com.google.android.material.textfield.TextInputLayout textinputlayout2 = (com.google.android.material.textfield.TextInputLayout) inflate.findViewById(R.id.textinputlayout2);
		
		
		final EditText edittext1= new EditText(EditActivity.this);
		
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
				TtoE = edittext1.getText().toString();
				mPhotoEditor.addText(TtoE, Color.parseColor("#" + PickedColour));
				dialog1.dismiss();
			}
		});
		dialog1.setCancelable(true);
		dialog1.show();
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