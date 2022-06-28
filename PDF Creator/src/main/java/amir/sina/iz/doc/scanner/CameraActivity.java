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
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
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
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class CameraActivity extends AppCompatActivity {
	
	public final int REQ_CD_DONTREMOVE = 101;
	
	private Timer _timer = new Timer();
	
	private String folderPath = "";
	private String path = "";
	private boolean cameraFacingBack = false;
	private boolean isTorch = false;
	
	private LinearLayout linear_base;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ImageView imageview2;
	private LinearLayout linear_takepicture;
	private ImageView imageview3;
	private ImageView imageview1;
	
	private Calendar cal = Calendar.getInstance();
	private Intent dontRemove = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_dontRemove;
	private Intent intentt = new Intent();
	private TimerTask timer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.camera);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		linear_base = findViewById(R.id.linear_base);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		imageview2 = findViewById(R.id.imageview2);
		linear_takepicture = findViewById(R.id.linear_takepicture);
		imageview3 = findViewById(R.id.imageview3);
		imageview1 = findViewById(R.id.imageview1);
		_file_dontRemove = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_dontRemove;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_dontRemove = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_dontRemove);
		} else {
			_uri_dontRemove = Uri.fromFile(_file_dontRemove);
		}
		dontRemove.putExtra(MediaStore.EXTRA_OUTPUT, _uri_dontRemove);
		dontRemove.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isTorch) {
					isTorch = false;
					android.hardware.camera2.CameraManager cameraManager = (android.hardware.camera2.CameraManager) getSystemService(Context.CAMERA_SERVICE);
					try { String cameraId = cameraManager.getCameraIdList()[0]; cameraManager.setTorchMode(cameraId, false); }
					catch (android.hardware.camera2.CameraAccessException e) { }
					
					imageview2.setImageResource(R.drawable.ic_flash_on_white);
				}
				else {
					isTorch = true;
					android.hardware.camera2.CameraManager cameraManager = (android.hardware.camera2.CameraManager) getSystemService(Context.CAMERA_SERVICE);
					try { String cameraId = cameraManager.getCameraIdList()[0]; cameraManager.setTorchMode(cameraId, true); }
					catch (android.hardware.camera2.CameraAccessException e) { }
					
					imageview2.setImageResource(R.drawable.ic_flash_off_white);
				}
			}
		});
		
		linear_takepicture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_savePicture(linear_takepicture);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (cameraFacingBack) {
					cameraFacingBack = false;
				}
				else {
					cameraFacingBack = true;
				}
				cameraView.close();
				cameraView.openAsync(CameraView.findCameraId(cameraFacingBack ? android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK : android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT));
			}
		});
	}
	
	private void initializeLogic() {
		_init();
		_setRoundOutline(linear_takepicture, SketchwareUtil.getDip(getApplicationContext(), (int)(30)));
		_removeView(linear1);
		folderPath = FileUtil.getExternalStorageDir().concat("/.PDIZ/".concat(getIntent().getStringExtra("pathNum").concat("/Images")));
		cameraFacingBack = true;
		//Amir Sina
		cameraView = new CameraView(this);
		
		cameraView.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
			cameraView.setUseOrientationListener(true);
		
		cameraView.setTapToFocus();
		android.widget.RelativeLayout rl = new android.widget.RelativeLayout(this);
		
		rl.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
		
		linear_base.addView(rl);
		
		rl.addView(cameraView);
		
		rl.addView(linear1);
		mPicture = new android.hardware.Camera.PictureCallback() {
			
			@Override
			public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
				
				java.io.File pictureFile = new java.io.File(path);
				
				try {
					java.io.FileOutputStream fos = new java.io.FileOutputStream(pictureFile);
					fos.write(data);
					fos.close();
				} catch (Exception e) {
					showMessage(e.toString());
				};
				
				camera.stopPreview();
				
				camera.startPreview();
				
			}
		};
		cameraView.setOnCameraListener(new CameraView.OnCameraListener() {
			
			public void onConfigureParameters(android.hardware.Camera.Parameters parameters) {
			}
			
			public void onCameraError() {
				showMessage("Camera Error");
			}
			
			public void onCameraReady(android.hardware.Camera camera) {
			}
			
			public void onPreviewStarted(android.hardware.Camera camera) {
			}
			
			public void onCameraStopping(android.hardware.Camera camera) {
			}
			
		});
	}
	
	@Override
	public void onBackPressed() {
		cameraView.close();
		finishAffinity();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		cameraView.openAsync(CameraView.findCameraId(cameraFacingBack ? android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK : android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		cameraView.openAsync(CameraView.findCameraId(cameraFacingBack ? android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK : android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT));
	}
	
	@Override
	public void onPause() {
		super.onPause();
		cameraView.close();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		cameraView.close();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		cameraView.close();
	}
	public void _initLibs() {
	}
	public static class CameraView extends android.widget.FrameLayout {
		
		public interface OnCameraListener {
			void onConfigureParameters(android.hardware.Camera.Parameters parameters);
			void onCameraError();
			void onCameraReady(android.hardware.Camera camera);
			void onPreviewStarted(android.hardware.Camera camera);
			void onCameraStopping(android.hardware.Camera camera);
		}
		
		public final android.graphics.Rect previewRect = new android.graphics.Rect();
		
		private final Runnable focusRunnable = new Runnable() {
			@Override
			public void run() {
				setFocusArea(null);
			}
		};
		
		private boolean isOpen = false;
		private boolean useOrientationListener = false;
		private OnCameraListener cameraListener;
		private HandlerThread cameraCallbackThread;
		private android.hardware.Camera cam;
		private android.view.OrientationEventListener orientationListener;
		private int tries = 0;
		private int viewWidth;
		private int viewHeight;
		private int frameWidth;
		private int frameHeight;
		private int frameOrientation;
		
		public static int findCameraId(int facing) {
			for (int i = 0, l = android.hardware.Camera.getNumberOfCameras(); i < l; ++i) {
				android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
				android.hardware.Camera.getCameraInfo(i, info);
				if (info.facing == facing) {
					return i;
				}
			}
			return -1;
		}
		
		public static int getRelativeCameraOrientation(Context context, int cameraId) {
			android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
			android.hardware.Camera.getCameraInfo(cameraId, info);
			int orientation = info.orientation;
			if (info.facing == android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT) {
				orientation -= 180;
			}
			return (orientation - getDeviceRotation(context) + 360) % 360;
		}
		
		public static int getDeviceRotation(Context context) {
			switch (((android.view.WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation()) {
				case Surface.ROTATION_90:
				return 90;
				case Surface.ROTATION_180:
				return 180;
				case Surface.ROTATION_270:
				return 270;
				case Surface.ROTATION_0:
				default: return 0;
			}
		}
		
		@android.annotation.TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
		public static boolean setAutoFocus(android.hardware.Camera.Parameters parameters) {
			
			String continuousPicture = android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE;
			
			String continuousVideo = android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO;
			
			String autoFocus = android.hardware.Camera.Parameters.FOCUS_MODE_AUTO;
			
			java.util.List<String> focusModes = parameters.getSupportedFocusModes();
			
			if (focusModes.contains(continuousPicture)) {
				parameters.setFocusMode(continuousPicture);
			} else if (focusModes.contains(continuousVideo)) {
				parameters.setFocusMode(continuousVideo);
			} else if (focusModes.contains(autoFocus)) {
				parameters.setFocusMode(autoFocus);
			} else {
				return false;
			}
			return true;
		}
		
		@android.annotation.SuppressLint("ClickableViewAccessibility")
		public void setTapToFocus() {
			setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getActionMasked() == MotionEvent.ACTION_UP && !focusTo(v, event.getX(), event.getY())) {
						v.setOnTouchListener(null);
						return false;
					}
					v.performClick();
					return true;
				}
			});
		}
		
		public boolean focusTo(final View v, float x, float y) {
			if (cam == null) {
				return false;
			}
			try {
				cam.cancelAutoFocus();
				if (!setFocusArea(calculateFocusRect(x, y, 100))) {
					return false;
				}
				cam.autoFocus(new android.hardware.Camera.AutoFocusCallback() {
					@Override
					public void onAutoFocus(boolean success, android.hardware.Camera camera) {
						v.removeCallbacks(focusRunnable);
						v.postDelayed(focusRunnable, 3000);
					}
				});
			} catch (RuntimeException e) {
				return false;
			}
			return true;
		}
		
		public static android.hardware.Camera.Size findBestPreviewSize(java.util.List<android.hardware.Camera.Size> sizes, int width, int height) {
			final double ASPECT_TOLERANCE = 0.1;
			double targetRatio = (double) width / height;
			double minDiff = Double.MAX_VALUE;
			double minDiffAspect = Double.MAX_VALUE;
			android.hardware.Camera.Size bestSize = null;
			android.hardware.Camera.Size bestSizeAspect = null;
			
			
			for (android.hardware.Camera.Size size : sizes) {
							double diff = (double) Math.abs(size.height - height) +
									Math.abs(size.width - width);
				
							if (diff < minDiff) {
									bestSize = size;
									minDiff = diff;
							}
				
							double ratio = (double) size.width / size.height;
				
							if (Math.abs(ratio - targetRatio) < ASPECT_TOLERANCE &&
									diff < minDiffAspect) {
									bestSizeAspect = size;
									minDiffAspect = diff;
							}
					}
			
					return bestSizeAspect != null ? bestSizeAspect : bestSize;
			}
		
			public CameraView(Context context) {
					super(context);
			}
		
			public CameraView(Context context, AttributeSet attrs) {
					super(context, attrs);
			}
		
			public CameraView(
					Context context,
					AttributeSet attrs,
					int defStyleAttr) {
					super(context, attrs, defStyleAttr);
			}
		
			public void setUseOrientationListener(boolean use) {
					useOrientationListener = use;
			}
		
			public void openAsync(final int cameraId) {
					if (isOpen || cameraCallbackThread != null) {
							return;
					}
					isOpen = true;
					cameraCallbackThread = new HandlerThread(
							"CameraCallbackHandlerThread");
					cameraCallbackThread.start();
					Handler callbackThreadHandler = new Handler(
							cameraCallbackThread.getLooper());
					callbackThreadHandler.post(new Runnable() {
							@Override
							public void run() {
									if (cam != null) {
											return;
									}
									final android.hardware.Camera camera = openCameraAndCatch(cameraId);
									CameraView.this.post(new Runnable() {
											@Override
											public void run() {
													initCamera(camera, cameraId);
											}
									});
							}
					});
			}
		
			public void close() {
					isOpen = false;
					if (orientationListener != null) {
							orientationListener.disable();
							orientationListener = null;
					}
					if (cam != null) {
							if (cameraListener != null) {
									cameraListener.onCameraStopping(cam);
							}
							cam.stopPreview();
							cam.setPreviewCallback(null);
							cam.release();
							cam = null;
					}
					if (cameraCallbackThread != null) {
							cameraCallbackThread.quit();
							try {
									cameraCallbackThread.join();
							} catch (InterruptedException ignore) {
							}
							cameraCallbackThread = null;
					}
					removeAllViews();
			}
		
			public void setOnCameraListener(OnCameraListener listener) {
					cameraListener = listener;
			}
		
			public android.hardware.Camera getCamera() {
					return cam;
			}
		
			public int getFrameWidth() {
					return frameWidth;
			}
		
			public int getFrameHeight() {
					return frameHeight;
			}
		
			public int getFrameOrientation() {
					return frameOrientation;
			}
		
			public android.graphics.Rect calculateFocusRect(float x, float y, int radius) {
					int cx = Math.round(2000f / viewWidth * x - 1000f);
					int cy = Math.round(2000f / viewHeight * y - 1000f);
					return new Rect(
							Math.max(-1000, cx - radius),
							Math.max(-1000, cy - radius),
							Math.min(1000, cx + radius),
							Math.min(1000, cy + radius));
			}
		
			@android.annotation.TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
			public boolean setFocusArea(Rect area) {
					if (cam == null || Build.VERSION.SDK_INT <
							Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
							return false;
					}
					try {
							android.hardware.Camera.Parameters parameters = cam.getParameters();
							if (parameters.getMaxNumFocusAreas() > 0) {
									if (area != null) {
											java.util.List<android.hardware.Camera.Area> focusAreas =
													new ArrayList<android.hardware.Camera.Area>();
											focusAreas.add(new android.hardware.Camera.Area(area, 1000));
											parameters.setFocusAreas(focusAreas);
											parameters.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_AUTO);
									} else {
											parameters.setFocusAreas(null);
											CameraView.setAutoFocus(parameters);
									}
							}
							cam.setParameters(parameters);
							return true;
					} catch (RuntimeException e) {
							return false;
					}
			}
		
			@Override
			protected void onLayout(
					boolean changed,
					int left,
					int top,
					int right,
					int bottom) {
					if (!changed) {
							return;
					}
					viewWidth = right - left;
					viewHeight = bottom - top;
					if (cam != null && getChildCount() == 0) {
							Context context = getContext();
							if (context == null) {
									return;
							}
							addPreview(context);
					}
			}
		
			private static android.hardware.Camera openCameraAndCatch(int cameraId) {
					try {
							return android.hardware.Camera.open(cameraId);
					} catch (RuntimeException e) {
							return null;
					}
			}
		
			private void initCamera(android.hardware.Camera camera, int cameraId) {
					if (!isOpen) {
							if (camera != null) {
									camera.release();
							}
							return;
					}
					if (camera == null) {
							if (cameraListener != null &&
									cam == null) {
									if (tries < 3) {
											isOpen = false;
											openAsync(cameraId);
											++tries;
									} else {
											cameraListener.onCameraError();
									}
							}
							return;
					}
					tries = 0;
					cam = camera;
					camera.setErrorCallback(new android.hardware.Camera.ErrorCallback() {
							public void onError(int error, android.hardware.Camera camera) {
									if (cameraListener != null) {
											cameraListener.onCameraError();
									}
							}
					});
					Context context = getContext();
					if (context == null) {
							close();
							return;
					}
					if (useOrientationListener) {
							enableOrientationListener(context, cameraId);
					}
					frameOrientation = getRelativeCameraOrientation(context, cameraId);
					if (viewWidth > 0) {
							addPreview(context);
					}
			}
		
			private void enableOrientationListener(Context context,
					final int cameraId) {
					final android.view.Display defaultDisplay = ((android.view.WindowManager) context
							.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
					final int defaultOrientation = defaultDisplay.getRotation();
					orientationListener = new android.view.OrientationEventListener(context,
							android.hardware.SensorManager.SENSOR_DELAY_NORMAL) {
							@Override
							public void onOrientationChanged(int orientation) {
									if (Math.abs(defaultOrientation -
											defaultDisplay.getRotation()) == 2) {
											close();
											openAsync(cameraId);
									}
							}
					};
					orientationListener.enable();
			}
		
			private void addPreview(Context context) {
					boolean transpose;
					try {
							transpose = setCameraParameters();
					} catch (RuntimeException e) {
							if (cameraListener != null) {
									cameraListener.onCameraError();
							}
							return;
					};
					int childWidth;
					int childHeight;
					if (transpose) {
							childWidth = frameHeight;
							childHeight = frameWidth;
					} else {
							childWidth = frameWidth;
							childHeight = frameHeight;
					}
					addSurfaceView(context, childWidth, childHeight);
					if (cameraListener != null) {
							cameraListener.onCameraReady(cam);
					}
			}
		
			private boolean setCameraParameters() throws RuntimeException {
					boolean transpose = frameOrientation == 90 || frameOrientation == 270;
					android.hardware.Camera.Parameters parameters = cam.getParameters();
					parameters.setRotation(frameOrientation);
					setPreviewSize(parameters, transpose);
					if (cameraListener != null) {
							cameraListener.onConfigureParameters(parameters);
					}
					android.hardware.Camera.Size size = parameters.getPreviewSize();
					if (size != null) {
							frameWidth = size.width;
							frameHeight = size.height;
					}
					cam.setParameters(parameters);
					cam.setDisplayOrientation(frameOrientation);
					return transpose;
			}
		
			private void setPreviewSize(
					android.hardware.Camera.Parameters parameters,
					boolean transpose) {
					if (transpose) {
							frameWidth = viewHeight;
							frameHeight = viewWidth;
					} else {
							frameWidth = viewWidth;
							frameHeight = viewHeight;
					}
					android.hardware.Camera.Size size = findBestPreviewSize(
							// will always return at least one item
							parameters.getSupportedPreviewSizes(),
							frameWidth,
							frameHeight);
					parameters.setPreviewSize(size.width, size.height);
			}
		
			private void addSurfaceView(
					Context context,
					int surfaceWidth,
					int surfaceHeight) {
					android.view.SurfaceView surfaceView = new android.view.SurfaceView(context);
					android.view.SurfaceHolder holder = surfaceView.getHolder();
					if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
							holder.setType(android.view.SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
					}
					holder.setKeepScreenOn(true);
					holder.addCallback(new android.view.SurfaceHolder.Callback() {
							@Override
							public void surfaceCreated(android.view.SurfaceHolder holder) {
									// wait until the surface has dimensions
							}
				
							@Override
							public void surfaceChanged(
									android.view.SurfaceHolder holder,
									int format,
									int width,
									int height) {
									if (cam == null) {
											return;
									}
									try {
											cam.setPreviewDisplay(holder);
									} catch (java.io.IOException e) {
											return;
									};
					_setParameters();
									cam.startPreview();
									if (cameraListener != null) {
											cameraListener.onPreviewStarted(cam);
									}
							}
				
							@Override
							public void surfaceDestroyed(android.view.SurfaceHolder holder) {
									close();
							}
					});
					addView(surfaceView);
					setChildLayout(
							viewWidth,
							viewHeight,
							surfaceView,
							surfaceWidth,
							surfaceHeight,
							previewRect);
			}
		
			private static void setChildLayout(
					int width,
					int height,
					View child,
					int childWidth,
					int childHeight,
					Rect childRect) {
					int widthByHeight = width * childHeight;
					int heightByWidth = height * childWidth;
					boolean dontScaleBeyondScreen = Build.VERSION.SDK_INT <
							Build.VERSION_CODES.ICE_CREAM_SANDWICH;
			
					if (dontScaleBeyondScreen ?
							// center within parent view
							widthByHeight > heightByWidth :
							// scale to cover parent view
							widthByHeight < heightByWidth) {
							childWidth = childWidth * height / childHeight;
							childHeight = height;
					} else {
							childHeight = childHeight * width / childWidth;
							childWidth = width;
					}
			
					int l = (width - childWidth) >> 1;
					int t = dontScaleBeyondScreen ?
							(height - childHeight) >> 1 :
							0;
			
					childRect.set(
							l,
							t,
							l + childWidth,
							t + childHeight);
			
					child.layout(
							childRect.left,
							childRect.top,
							childRect.right,
							childRect.bottom);
			}
	}
	
	{
	}
	
	
	public void _init() {
		picture_width = getDisplayWidthPixels();
		
		picture_height = getDisplayHeightPixels();
	}
	private static CameraView cameraView;
	private android.hardware.Camera.PictureCallback mPicture;
	private static int picture_quality = 100;
	private static int picture_width, picture_height;
	public static void _setParameters() {
		
		android.hardware.Camera.Parameters pm = cameraView.cam.getParameters()
		;
		pm.setJpegQuality(picture_quality)
		;
		//pm.setPreviewFpsRange(20,45);
		
		pm.setPictureFormat(ImageFormat.JPEG);
		
		//pm.setSceneMode(android.hardware.Camera.Parameters.SCENE_MODE_HDR);
		
		List<android.hardware.Camera.Size> sizes = pm.getSupportedPictureSizes();
		android.hardware.Camera.Size size = sizes.get(0);
		for (int i = 0; i < sizes.size(); i++) {
			if (sizes.get(i).width > size.width) size = sizes.get(i);
		};
		pm.setPictureSize(size.width, size.height);
		
		cameraView.cam.setParameters(pm);
		
		
	}
	{
	}
	
	
	public void _savePicture(final View _v) {
		_getRandomFileName("iz-scanner");
		if (!FileUtil.isExistFile(folderPath)) {
			FileUtil.makeDir(folderPath);
		}
		if (cameraView.isOpen) {
			
			cameraView.cam.takePicture(null, null, mPicture);
			
			timer = new TimerTask() {
					@Override
					public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
											intentt.putExtra("pathOfPic", path);
											intentt.putExtra("NumOfP", getIntent().getStringExtra("pathNum"));
											intentt.setClass(getApplicationContext(), EditActivity.class);
											startActivity(intentt);
									}
							});
					}
			};
			_timer.schedule(timer, (int)(2000));
			
		} else {
			
			showMessage("Camera is not ready yet");
			
		};
	}
	
	
	public void _getRandomFileName(final String _prefix) {
		cal = Calendar.getInstance();
		path = folderPath.concat("/".concat(_prefix.trim().concat(" - ".concat(String.valueOf((long)(cal.getTimeInMillis())).concat(".jpg")))));
	}
	
	
	public void _removeView(final View _v) {
		if (_v.getParent() != null) ((ViewGroup)_v.getParent()).removeView(_v);
	}
	
	
	public void _setRoundOutline(final View _view, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.TRANSPARENT);
		
		gd.setCornerRadius((int)_round);
		
		gd.setStroke((int)getDip(1), Color.WHITE);
		
		_view.setBackground(gd);
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