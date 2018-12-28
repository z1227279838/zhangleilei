package com.bawei.zhanglei.leishen.home.model;

import android.util.Log;

import com.bawei.zhanglei.leishen.network.MyTask;

import java.util.List;


//实现接口
public class HomeListModeInter implements IHomeListModelnter{
    ModuleInterface moduleInterface;
    public HomeListModeInter(ModuleInterface moduleInterface) {
        this.moduleInterface=moduleInterface;
    }
    @Override
    public void getDate(final String url) {
        new Runnable(){
            @Override
            public void run() {
                new MyTask<String>(url,"GET").setTaskListener(new MyTask.TaskListeners() {
                    @Override
                    public void result(String t) {
                        Log.i("", "result:=== "+t);
                        if(t!=null){
                            moduleInterface.LoadSuccess(t);
                        }else {
                            moduleInterface.LoadFailed();
                        }

                    }
                }).execute();
            }
        }.run();
        //获取数据
    }
    public interface ModuleInterface {
        //获取数据状态回调的接口
        void LoadSuccess(String data);

        void LoadFailed();
    }
}
