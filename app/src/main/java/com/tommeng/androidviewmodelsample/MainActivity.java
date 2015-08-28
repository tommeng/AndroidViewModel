package com.tommeng.androidviewmodelsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.tommeng.androidviewmodel.AndroidViewModel;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private TextView tvFirstName;
    private EditText etFirstName;
    private TextView tvLastName;
    private EditText etLastName;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new MainViewModel();
        viewModel.bind(viewModelAction1);

        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        etLastName = (EditText) findViewById(R.id.etLastName);

        etFirstName.addTextChangedListener(firstNameTextWatcher);
        etLastName.addTextChangedListener(lastNameTextWatcher);
    }

    private Action1<AndroidViewModel.ViewModelProperty> viewModelAction1 = new Action1<AndroidViewModel.ViewModelProperty>() {
        @Override
        public void call(AndroidViewModel.ViewModelProperty viewModelProperty) {
            if (MainViewModel.firstNameProperty.equals(viewModelProperty)) {
                tvFirstName.setText(viewModel.getFirstName());
            } else if (MainViewModel.lastNameProperty.equals(viewModelProperty)) {
                tvLastName.setText(viewModel.getLastName());
            }
        }
    };

    private TextWatcher firstNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            viewModel.setFirstName(etFirstName.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher lastNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            viewModel.setLastName(etLastName.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
