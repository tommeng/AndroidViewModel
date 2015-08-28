package com.tommeng.androidviewmodelsample;

import android.os.Parcel;

import com.tommeng.androidviewmodel.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {
    public static ViewModelProperty<String> firstNameProperty = new ViewModelProperty<>();
    public static ViewModelProperty<String> lastNameProperty = new ViewModelProperty<>();

    private static ViewModelProperty[] viewModelProperties = new ViewModelProperty[]{
            firstNameProperty,
            lastNameProperty
    };

    public MainViewModel() {
    }

    public MainViewModel(Parcel in) {
        super(in);
        firstNameProperty.value = in.readString();
        lastNameProperty.value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(firstNameProperty.value);
        dest.writeString(lastNameProperty.value);
    }

    @Override
    public ViewModelProperty[] getViewModelProperties() {
        return viewModelProperties;
    }

    public String getFirstName() {
        return firstNameProperty.value;
    }

    public void setFirstName(String firstName) {
        firstNameProperty.value = firstName;
        raisePropertyChanged(firstNameProperty);
    }

    public String getLastName() {
        return lastNameProperty.value;
    }

    public void setLastName(String lastName) {
        lastNameProperty.value = lastName;
        raisePropertyChanged(lastNameProperty);
    }
}
