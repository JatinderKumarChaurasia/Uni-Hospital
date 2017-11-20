package com.example.jatinderkumar.spla;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.input.InputManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import Helpers.InputValidation;
import Sql.DatabaseHolder;

import static com.example.jatinderkumar.spla.R.color.colorPrimary;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    ActionBar actionBar;
    TextView txtViewActionBar;
    Button btnSignInSubmit;
    EditText edtSignInEmail,edtSignInPassword;
    TextView textViewLinkRegister;
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;
    ConstraintLayout.LayoutParams layoutParams;
    private final AppCompatActivity activity = SignIn.this;

    private InputValidation inputValidation;
    private DatabaseHolder databaseHolder;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        constraintLayout =(ConstraintLayout)  findViewById(R.id.signin_constraintLayout);
        //animationDrawable =(AnimationDrawable) constraintLayout.getBackground();
        //animationDrawable.setExitFadeDuration(3000);
        //animationDrawable.setEnterFadeDuration(1000);
        //animationDrawable.start();
        centerActionBar();
       // btnSignInSubmit.setOnClickListener(this );

        initViews();
        initObjects();
        initListeners();


    }

    private void initViews()
    {
        btnSignInSubmit =(Button)  findViewById( R.id.btnSignInSubmit );
        edtSignInEmail =(EditText)  findViewById( R.id.edtSignInEmail );
        edtSignInPassword =(EditText) findViewById( R.id.edtSignInPassword );
        textViewLinkRegister =(TextView) findViewById( R.id.textViewLinkRegister );
    }

    private void initListeners()
    {

        btnSignInSubmit.setOnClickListener( this );
        textViewLinkRegister.setOnClickListener( this );
    }
    private void initObjects()
    {
        databaseHolder = new DatabaseHolder( activity );
        inputValidation = new InputValidation(activity);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSignInSubmit:

                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intentSignUp = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intentSignUp);
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void centerActionBar() {
        actionBar = getSupportActionBar();
       // int color =getColor( R.color.colorAccent );
        txtViewActionBar = new TextView(getApplicationContext());
        layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtViewActionBar.setLayoutParams(layoutParams);
        txtViewActionBar.setText("Sign In");
        //txtViewActionBar.setBackgroundColor(Color.CYAN);
      //  txtViewActionBar.setTextColor(color );
        txtViewActionBar.setGravity( Gravity.CENTER_HORIZONTAL );
        txtViewActionBar.setTextSize(20);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(txtViewActionBar );

    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled( edtSignInEmail, getString( R.string.error_message_email ) )) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled( edtSignInPassword, getString( R.string.error_message_password ) )) {
            return;

        }
        if (databaseHolder.checkUser( edtSignInEmail.getText().toString().trim(),
                edtSignInPassword.getText().toString().trim() )) {
            Intent intentMain = new Intent( getApplicationContext(), MainActivity.class );
           intentMain.putExtra( "EMAIL", edtSignInEmail.getText().toString().trim() );
            emptyInputEditText();
            startActivity( intentMain );
        } else
        {
            Snackbar.make( constraintLayout,getString( R.string.error_valid_email_password ),Snackbar.LENGTH_LONG ).show();
         }

    }
    private void emptyInputEditText() {
        edtSignInEmail.setText(null);
        edtSignInPassword.setText(null);
    }




}
