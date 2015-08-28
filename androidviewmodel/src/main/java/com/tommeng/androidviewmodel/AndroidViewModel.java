package com.tommeng.androidviewmodel;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public abstract class AndroidViewModel implements Parcelable {
    private Context context;
    private PublishSubject<ViewModelProperty> propertyChangeSubject;

    public AndroidViewModel() {
        propertyChangeSubject = PublishSubject.create();
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

    public abstract ViewModelProperty[] getViewModelProperties();

    public void notifySetChanged() {
        ViewModelProperty[] viewModelProperties = getViewModelProperties();
        for (ViewModelProperty viewModelProperty : viewModelProperties) {
            raisePropertyChanged(viewModelProperty);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Subscription bind(Action1<ViewModelProperty> action1) {
        return propertyChangeSubject.subscribe(action1);
    }

    public void raisePropertyChanged(ViewModelProperty viewModelProperty) {
        propertyChangeSubject.onNext(viewModelProperty);
    }

    public static class ViewModelProperty<T> {
        public T value;

        public ViewModelProperty() {
        }

        public ViewModelProperty(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ViewModelProperty<?> that = (ViewModelProperty<?>) o;
            return hashCode() == that.hashCode();
        }
    }
}
