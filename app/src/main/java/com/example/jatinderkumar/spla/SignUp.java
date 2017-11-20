package com.example.jatinderkumar.spla;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SizeF;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;

import Helpers.InputValidation;
import Model.User;
import Sql.DatabaseHolder;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText edtSignUpName,edtSignUpEmail,edtSignUpPhone,edtSignUpPassword,edtSignUpConfirmPassword;
    ActionBar actionBar;
    Typeface typeface;
    TextView txtViewActionBar,textViewLoginLink;
    //RelativeLayout.LayoutParams layoutParams;
    ScrollView.LayoutParams layoutParams;
    Button btnCreateAccount;
    private AppCompatActivity activity =SignUp.this;
    //RelativeLayout signUpActivity_main;
    ScrollView signUpActivity_main;

    InputValidation validation;
    DatabaseHolder databaseHolder;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        centerActionBar();

        AssetManager assetManager = getApplicationContext().getAssets();
        typeface = Typeface.createFromAsset( assetManager,
                String.format( Locale.ENGLISH, "font/%s", "CaviarDreams.ttf"));




        //AnimationDrawable animationDrawable =(AnimationDrawable)  signUpActivity_main.getBackground();
        //animationDrawable.setEnterFadeDuration(1000);
        //animationDrawable.setExitFadeDuration(3000);
        //animationDrawable.start();
      //  btnCreateAccount.setOnClickListener( this );

        initViews();
        initListeners();
        initObjects();

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void centerActionBar() {
        actionBar = getSupportActionBar();
       // int color =getColor( R.color.colorAccent );
        txtViewActionBar = new TextView(getApplicationContext());
        layoutParams = new ScrollView.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtViewActionBar.setLayoutParams(layoutParams);
        txtViewActionBar.setText("Sign Up");
        txtViewActionBar.setTypeface( typeface );
        //txtViewActionBar.setBackgroundColor(Color.CYAN);
     //   txtViewActionBar.setTextColor(color );
        txtViewActionBar.setGravity( Gravity.CENTER_HORIZONTAL );
        txtViewActionBar.setTextSize(20);
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(txtViewActionBar );

    }

    private void initViews()
    {
        btnCreateAccount =(Button)  findViewById(R.id.btnCreateAccount);
        signUpActivity_main =(ScrollView)  findViewById(R.id.signUpactivity_main);
        edtSignUpName = (EditText) findViewById(R.id.edtSignUpName);
        edtSignUpEmail =(EditText)  findViewById( R.id.edtSignUpEmail );
        textViewLoginLink =(TextView)  findViewById( R.id.textViewLoginLink );
        edtSignUpPhone =(EditText)  findViewById( R.id.edtSignUpPhone );
        edtSignUpPassword =(EditText)  findViewById( R.id.edtSignUpPassword );
        edtSignUpConfirmPassword =(EditText) findViewById( R.id.edtSignUpConfirmPassword );
        edtSignUpName.setTypeface( typeface );
        edtSignUpEmail.setTypeface(typeface  );
        edtSignUpPhone.setTypeface( typeface );
        edtSignUpPassword.setTypeface( typeface );
        edtSignUpConfirmPassword.setTypeface( typeface );
        btnCreateAccount.setTypeface( typeface );
        textViewLoginLink.setTypeface( typeface );

    }
    private void initListeners()
    {
        btnCreateAccount.setOnClickListener( this );
        textViewLoginLink.setOnClickListener( this );
    }

    private void initObjects()
    {
        validation =new InputValidation( activity );
        databaseHolder = new DatabaseHolder( activity );
        user =new User();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCreateAccount:
                postDataToSQLite();
                break;
            case R.id.textViewLoginLink:
                Intent in = new Intent( SignUp.this, SignIn.class );
                startActivity( in );
                break;

        }

    }
    private void postDataToSQLite()
    {
        if(!validation.isInputEditTextFilled( edtSignUpName,getString( R.string.error_message_name ) ))
        {
            return;
        }
        if(!validation.isInputEditTextFilled( edtSignUpEmail,getString( R.string.error_message_email ) ))
        {
            return;
        }
        if(!validation.isInputEditTextFilled( edtSignUpPhone,getString( R.string.error_message_phone ) ))
        {
            return;
        }
        if(!validation.isInputEditTextFilled( edtSignUpPassword,getString( R.string.error_message_password ) ))
        {
            return;
        }
        if(!validation.isInputEditTextFilled( edtSignUpConfirmPassword,getString( R.string.error_message_password ) ))
        {
            return;
        }
        if(!validation.isInputEditTextEmail( edtSignUpEmail,getString( R.string.error_invalid_email ) ))
        {
            return;
        }
        if(!validation.isInputEditTextPhone( edtSignUpPhone,getString( R.string.error_invalid_phone_no ) ))
        {
            return;
        }
        if(!validation.isInputEditTextMatches( edtSignUpPassword,edtSignUpConfirmPassword,getString( R.string.error_password_match ) ))
        {
            return;
        }
        if(!databaseHolder.checkUser( edtSignUpEmail.getText().toString().trim() )) {
            user.setName( edtSignUpName.getText().toString().trim() );
            user.setEmail( edtSignUpEmail.getText().toString().trim() );


            user.setPassword( edtSignUpPassword.getText().toString().trim() );
            databaseHolder.addUser( user );
            Snackbar.make( signUpActivity_main,getString( R.string.success_message ),Snackbar.LENGTH_LONG ).show();

            emptyInputEditText();
            Intent i = new Intent( SignUp.this,SignIn.class );
            startActivity( i );
        }
        else
        {
            Snackbar.make(signUpActivity_main,getString( R.string.error_email_exists ),Snackbar.LENGTH_LONG ).show();
        }
    }
    private void emptyInputEditText()
    {
        edtSignUpName.setText( null );
        edtSignUpEmail.setText( null );
        edtSignUpPhone.setText( null );
        edtSignUpPassword.setText( null );
        edtSignUpConfirmPassword.setText( null );
    }
}
