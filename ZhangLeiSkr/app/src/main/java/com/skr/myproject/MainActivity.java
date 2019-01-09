package com.skr.myproject;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.skr.myproject.bean.LoginBean;
import com.skr.myproject.presenter.LoginPresenter;
import com.skr.myproject.view.ILoginView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    @BindView(R.id.et_login_name)
    EditText et_login_name;
    @BindView(R.id.et_login_pwd)
    EditText et_login_pwd;
    @BindView(R.id.ck_rem_pwd)
    CheckBox ck_rem_pwd;
    @BindView(R.id.text_reg)
    TextView text_reg;
    @BindView(R.id.btn_login)
    Button btn_login;
    LoginPresenter loginPresenter;
    Unbinder Unbinder;
    @BindView(R.id.btn_qq)
    Button btn_qq;
    @BindView(R.id.btn_fx)
    Button btn_fx;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Unbinder = ButterKnife.bind(this);
        //初始化presenter
        loginPresenter = new LoginPresenter(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_login_name.getText().toString();
                String pwd = et_login_pwd.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    loginPresenter.loginPre(name,pwd);
                }
            }
        });
        text_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this,RegistActivity.class);
                 startActivity(intent);
            }
        });
        btn_qq.setOnClickListener(this);
        btn_fx.setOnClickListener(this);
    }

    @Override
    public void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(msg, LoginBean.class);
                String status = loginBean.getStatus();
                if (status.equals("0000")){
                    Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void jumpActivity() {

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_qq:
                    umengDeleteOauth(SHARE_MEDIA.QQ);
                    //开始授权
                    shareLoginUmeng(MainActivity.this, SHARE_MEDIA.QQ);
                    break;
                case R.id.btn_fx:
                    shareWeb(this, "http://www.baidu.com", "Hello Word", "Word", SHARE_MEDIA.QQ);
                    break;
            }
    }
    private void umengDeleteOauth(SHARE_MEDIA qq) {
        UMShareAPI.get(this).deleteOauth(this, qq, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //开始授权
                Log.e("sss", "onStart: ");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //取消授权成功 i=1
                Log.e("sss", "onComplete: ");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                //授权出错
                Log.e("sss", "onError: ");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                //取消授权
                Log.e("sss", "onCancel: ");
            }
        });

    }

    public  void shareLoginUmeng(final Activity activity, SHARE_MEDIA qq) {
        UMShareAPI.get(activity).getPlatformInfo(activity, qq, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.e("aaa", "onStart授权开始: ");
            }
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //sdk是6.4.5的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");//名称
                String gender = map.get("gender");//性别
                String iconurl = map.get("iconurl");//头像地址
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);
                Log.e("aaa", "onStart授权完成: " + openid);
                Log.e("aaa", "onStart授权完成: " + unionid);
                Log.e("aaa", "onStart授权完成: " + access_token);
                Log.e("aaa", "onStart授权完成: " + refresh_token);
                Log.e("aaa", "onStart授权完成: " + expires_in);
                Log.e("aaa", "onStart授权完成: " + uid);
                Log.e("aaa", "onStart授权完成: " + name);
                Log.e("aaa", "onStart授权完成: " + gender);
                Log.e("aaa", "onStart授权完成: " + iconurl);
            }
            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Toast.makeText(activity, "授权失败", Toast.LENGTH_LONG).show();
                Log.e("aaa", "onError: " + "授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Toast.makeText(activity, "授权取消", Toast.LENGTH_LONG).show();
                Log.e("aaa", "onError: " + "授权取消");
            }
        });
    }
    public static void shareWeb(final MainActivity activity, String s, String hello_word, String word, SHARE_MEDIA qq) {
        UMImage thumb = new UMImage(activity, R.mipmap.ic_launcher);
        UMWeb web = new UMWeb(s);//连接地址(注意链接开头必须包含http)
        web.setTitle(hello_word);//标题
        web.setDescription(word);//描述
        web.setThumb(thumb);//缩略图
        new ShareAction(activity)
                //分享的平台
                .setPlatform(qq)
                .withMedia(web)
                .setCallback(new UMShareListener() {


                    /**
                     * @descrption 分享开始的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.e(TAG, "onStart开始分享的平台: " + share_media);
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, " 分享成功 ", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onStart分享成功的平台: " + share_media);
                            }
                        });
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param share_media 平台类型
                     * @param throwable 错误原因
                     */
                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            //失败原因
                            Log.e(TAG, "throw:" + throwable.getMessage());
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param share_media 平台类型
                     */
                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, " 分享取消 ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .share();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Unbinder.unbind();
    }


}
