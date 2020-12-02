package com.example.everybooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.everybooks.data.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity
{
    // 뷰 요소 선언
    ImageView imageView_add_photo;
    TextInputEditText textInputEditText_nickname;
    TextInputEditText textInputEditText_email;
    TextInputEditText textInputEditText_password;
    TextInputEditText textInputEditText_confirm_password;
    Button button_register;

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 화면 생성
        setContentView(R.layout.activity_signup);

        // 뷰 요소 초기화
        imageView_add_photo = findViewById(R.id.add_photo);
        textInputEditText_nickname = findViewById(R.id.nickname);
        textInputEditText_email = findViewById(R.id.email);
        textInputEditText_password = findViewById(R.id.password);
        textInputEditText_confirm_password = findViewById(R.id.confirm_password);
        button_register = findViewById(R.id.btn_register);

        // register 버튼 클릭 시 이메일 정보를 인텐트에 담아서 로그인 화면으로 이동한다
        button_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Drawable img = imageView_add_photo.getDrawable();
                String nickname = textInputEditText_nickname.getText().toString();
                String email = textInputEditText_email.getText().toString();
                String password = textInputEditText_password.getText().toString();
                String confirmPassword = textInputEditText_confirm_password.getText().toString();

                // 비밀번호와 비밀번호 확인에 입력한 값이 같지 않으면 비밀번호가 일치하지 않는다는 안내를 한다.
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    textInputEditText_confirm_password.setText("");
                }
                else
                {
                    // 정상적으로 입력하고 클릭한 경우 유저 객체를 생성해서 사용자가 입력한 값을 담은 다음
                    User user = new User();
                    user.setImg(img);
                    user.setNickname(nickname);
                    user.setEmail(email);
                    user.setPassword(password);
                    
                    // 객체를 json 형태로 바꾼다.
                    String userString = user.toJSON();

                    // userInfo 라는 SharedPreferences 파일에
                    // 키 : 입력받은 email
                    // 값 : img, nickname, email, password 데이터를 저장한다.
                    SharedPreferences userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userInfo.edit();
                    editor.putString(email, userString);
                    editor.commit();

                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("email",textInputEditText_email.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}
