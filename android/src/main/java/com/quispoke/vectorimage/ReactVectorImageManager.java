package com.quispoke.vectorImage;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.JavascriptException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactVectorImageManager extends ViewGroupManager<RelativeLayout> {
    public static final String REACT_CLASS = "RCTVectorImage";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected RelativeLayout createViewInstance(ThemedReactContext reactContext) {
        RelativeLayout res = new RelativeLayout(reactContext);
        return res;
    }

    @ReactProp(name="settings")
    public void setSettings(RelativeLayout view, ReadableMap settings) {

        ImageView img = null;

        if (view.getChildCount() > 0) {
            View child = view.getChildAt(0);

            if (child instanceof ImageView) {
                img = (ImageView) view.getChildAt(0);
                view.removeViewAt(0);

                img.clearColorFilter();
                img.destroyDrawingCache();
            }
        } else {
            img = new ImageView(view.getContext());
        }

        Drawable draw = createVectorDrawable(view, settings.getString("resourceName"));
        img.setImageDrawable(draw);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setLayoutParams(new ViewGroup.LayoutParams(
                settings.getInt("width"),
                settings.getInt("height")));

        try {
            ReadableArray colorArray = settings.getArray("color");

            if (null != colorArray) {
                int color = Color.argb(
                        colorArray.getInt(0),
                        colorArray.getInt(1),
                        colorArray.getInt(2),
                        colorArray.getInt(3));

                img.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            }
        } catch (NoSuchKeyException e) {
        }

        view.addView(img, 0);
    }

    private Drawable createVectorDrawable(RelativeLayout view, String resourceName) throws JavascriptException{

        int resourceIdent;
        if( (resourceIdent = view.getContext().getResources().getIdentifier( resourceName, "drawable", view.getContext().getPackageName())) == 0 )
            throw new JavascriptException("Invalid resourceName");

        Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceIdent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return  (VectorDrawable) drawable;
        } else {
            return (BitmapDrawable) drawable;
        }
    }
}
