package com.example.e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestPasswordResetCallback;

import cn.leancloud.LCUser;
import cn.leancloud.LeanCloud;
import cn.leancloud.types.LCNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Forgetpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        AVOSCloud.initialize(this,"0A1lYup3rvVr3iYFaA4TKT91-gzGzoHsz","Vr1c59fQx1XMUrLoINTVyupo");
        LeanCloud.initialize(this, "X4lt7nFkKwDBmAjMfFOx571R-gzGzoHsz", "mpE9vLctlF95Cfi3DVWlDkq0", "https://x4lt7nfk.lc-cn-n1-shared.com");

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font/全新硬笔楷书.ttf");
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        final EditText E_mail=(EditText)findViewById(R.id.email);
        Button Send=(Button)findViewById(R.id.send);
        TextView Forgetpassword_Tips=(TextView)findViewById(R.id.forgetpasswordtips);
        E_mail.setTypeface(typeFace);
        Send.setTypeface(typeFace);
        Forgetpassword_Tips.setTypeface(typeFace);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=E_mail.getText().toString();
                LCUser.requestPasswordResetInBackground(email).subscribe(new Observer<LCNull>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LCNull lcNull) {
                        Toast.makeText(Forgetpassword.this,"邮件已发送成功", Toast.LENGTH_SHORT).show();
                        Forgetpassword.this.finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(Forgetpassword.this,"请重新输入邮件", Toast.LENGTH_SHORT).show();
                        Forgetpassword.this.finish();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
    }
}
