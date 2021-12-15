package com.example.e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.e.game.Vision;
import com.example.e.game.relax;

import cn.leancloud.LCObject;
import cn.leancloud.LCUser;
import cn.leancloud.LeanCloud;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeanCloud.initialize(this, "X4lt7nFkKwDBmAjMfFOx571R-gzGzoHsz", "mpE9vLctlF95Cfi3DVWlDkq0", "https://x4lt7nfk.lc-cn-n1-shared.com");
        setContentView(R.layout.activity_signup);
//        AVOSCloud.initialize(this,"0A1lYup3rvVr3iYFaA4TKT91-gzGzoHsz","Vr1c59fQx1XMUrLoINTVyupo");
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        Typeface typeFace1 = Typeface.createFromAsset(getAssets(), "font/全新硬笔楷书.ttf");
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "font/百度综艺简体.ttf");
        final TextView usrname=(TextView)findViewById(R.id.username);
        TextView password=(TextView)findViewById(R.id.password);
        TextView rePassword = (TextView)findViewById(R.id.rePassword);
        TextView email=(TextView)findViewById(R.id.email);
        TextView signinsignup=(TextView)findViewById(R.id.signupsignin);
        final EditText Username=(EditText)findViewById(R.id.et_lusername);
        final EditText Password=(EditText)findViewById(R.id.et_lpassword);
        final EditText RePassword=(EditText)findViewById(R.id.et_lrePassword);
        final EditText Email=(EditText)findViewById(R.id.et_email);
        final Intent SignupMain=new Intent(Signup.this,MainActivity.class);
        final Intent SignupVisiontest=new Intent(Signup.this, relax.class);
        //Visiontest 视力测试-许浩安
        usrname.setTypeface(typeFace2);
        password.setTypeface(typeFace2);
        email.setTypeface(typeFace2);
        signinsignup.setTypeface(typeFace1);
        //Username.setTypeface(typeFace);
        //Password.setTypeface(typeFace);
        //Email.setTypeface(typeFace);
        final AlertDialog.Builder SignupSuccess=new AlertDialog.Builder(Signup.this);
        final AlertDialog.Builder SignupFail=new AlertDialog.Builder(Signup.this);

        SignupSuccess.setTitle("提示");
        SignupSuccess.setMessage("您已经完成注册!\n" +
                "请前往视力测试模块，完成所以测试!\n"+
                "以便获取个性化的护眼措施！");
        SignupSuccess.setCancelable(false);
        SignupSuccess.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(SignupVisiontest);
                finish();
            }
        });
        SignupSuccess.setNegativeButton("暂不", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(SignupMain);
                finish();
            }
        });
        SignupFail.setTitle("提示");
        SignupFail.setMessage("注册失败！");
        SignupFail.setPositiveButton("重新注册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Username.setText("");
                Password.setText("");
                RePassword.setText("");
                Email.setText("");
            }
        });

        signinsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String psw = Password.getText().toString().trim();
                String re_psw = RePassword.getText().toString().trim();
                String emailInput = Email.getText().toString().trim();
                //注册事件
                getEditString();
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(psw) || TextUtils.isEmpty(re_psw) || TextUtils.isEmpty(emailInput)){
                    Toast.makeText(Signup.this,"请填写完整信息", Toast.LENGTH_SHORT).show();
                }else if(!psw.equals(re_psw)){
                    Toast.makeText(Signup.this,"输入的两次密码不一致，请重试", Toast.LENGTH_SHORT).show();
                }else{
                    LCUser user = new LCUser();
                    user.setUsername(username);
                    user.setPassword(psw);
                    user.setEmail(emailInput);

                    user.signUpInBackground().subscribe(new Observer<LCUser>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull LCUser lcUser) {
                            LCObject leaderboard = new LCObject("Leaderboard");
                            leaderboard.put("Username",username);
                            //基于用户名对相关数据查询
                            leaderboard.saveInBackground();
                            SignupSuccess.show();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            SignupFail.show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            }
        });


//        signinsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String User_name=Username.getText().toString();
//                String Pass_word=Password.getText().toString();
//                String E_mail=Email.getText().toString();
//                AVUser user=new AVUser();
//                user.setUsername(User_name);
//                user.setPassword(Pass_word);
//                user.setEmail(E_mail);
//                user.signUpInBackground(new SignUpCallback() {
//                    @Override
//                    public void done(AVException e) {
////                        if(e==null){
//                            AVObject Leader_Board=new AVObject("Leaderboard");
//                            Leader_Board.put("Username",User_name);
//                            //基于用户名对相关数据查询
//                            Leader_Board.saveInBackground();
//                            SignupSuccess.show();
////                        }else{
////                            SignupFail.show();
////                        }
//                    }
//                });
//            }
//        });
    }

    private void getEditString(){

    }
}
