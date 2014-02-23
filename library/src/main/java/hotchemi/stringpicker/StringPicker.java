package hotchemi.stringpicker;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class StringPicker extends LinearLayout {

    private Object mInstance;

    private Class<?> mClazz;

    private static final int SDK_VERSION;

    private static final String PICKER_CLASS;

    static {
        SDK_VERSION = Build.VERSION.SDK_INT;
        PICKER_CLASS = SDK_VERSION < Build.VERSION_CODES.FROYO ?
                "com.android.internal.widget.NumberPicker" : "android.widget.NumberPicker";
    }

    public StringPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public void setCurrent(final int current) {
        final String methodName = isUnderHoneyComb() ? "setCurrent" : "setValue";
        try {
            Method method = mClazz.getMethod(methodName, int.class);
            method.invoke(mInstance, current);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getCurrent() {
        final String methodName = isUnderHoneyComb() ? "getCurrent" : "getValue";
        try {
            Method method = mClazz.getMethod(methodName);
            return (Integer) method.invoke(mInstance);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setValues(final String[] values) {
        if (isUnderHoneyComb()) {
            try {
                Method method = mClazz.getMethod("setRange", int.class, int.class, String[].class);
                method.invoke(mInstance, 0, values.length - 1, values);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                mClazz.getMethod("setMaxValue", int.class).invoke(mInstance, values.length - 1);
                mClazz.getMethod("setMinValue", int.class).invoke(mInstance, 0);
                mClazz.getMethod("setDisplayedValues", String[].class)
                        .invoke(mInstance, new Object[]{values});
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setValues(final List<String> values) {
        setValues(values.toArray(new String[values.size()]));
    }

    private static boolean isUnderHoneyComb() {
        return SDK_VERSION < Build.VERSION_CODES.HONEYCOMB;
    }

    private void initialize(final Context context, final AttributeSet attrs) {
        try {
            final Class<?> clazz = context.getClassLoader().loadClass(PICKER_CLASS);
            final Constructor<?> constructor = clazz.getConstructor(Context.class, AttributeSet.class);
            mInstance = constructor.newInstance(context, attrs);
            mClazz = mInstance.getClass();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        addView((View) mInstance);
        setOrientation(VERTICAL);
    }

}