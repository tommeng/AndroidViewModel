package com.tommeng.androidviewmodel;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class AndroidViewModel implements Parcelable {
    private Context context;

    private OnPropertyChangeListener changeListener;

    public AndroidViewModel() {
    }

    public AndroidViewModel(Parcel in) {
        this();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Raise changes in all properties.
     */
    public void notifyFullUpdate() {
    }

    public void setChangeListener(OnPropertyChangeListener listener) {
        changeListener = listener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Raise a change in a property.
     *
     * @param property
     */
    protected void raisePropertyChanged(String property) {
        if (changeListener != null) {
            changeListener.onPropertyChanged(property);
        }
    }

    public interface OnPropertyChangeListener {
        void onPropertyChanged(String property);
    }
}
