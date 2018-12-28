package com.bawei.zhanglei.xiaoleitwo.home.model;

import android.util.Log;

import com.bawei.zhanglei.xiaoleitwo.network.MyTask;

public class CModerl implements IModel{
    private ModuleInterface moduleInterface;
    public CModerl(ModuleInterface moduleInterface){
        this.moduleInterface = moduleInterface;
    }
    @Override
    public void getModelData(final String url) {
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
    public  interface ModuleInterface{
        void LoadSuccess(String data);
        void LoadFailed();
    }
}
