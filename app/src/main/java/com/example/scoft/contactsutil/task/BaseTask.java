package com.example.scoft.contactsutil.task;

import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Scoft on 2016/3/24.
 * Email linbing1992@126.com
 */
public abstract  class BaseTask extends Handler{
    private Handler mWorkTaskHandle;
    private HandlerThread mWorkTaskThread;
    private static final int MSG_START_INSERT = 0X01;
    private static final int MSG_START_DELETE = 0X02;
    public BaseTask(){
        super(Looper.getMainLooper());
        if(mWorkTaskThread == null){
            mWorkTaskThread = new HandlerThread("workThread");
            if(mWorkTaskHandle ==null){
                mWorkTaskHandle = new WorkTaskHandler(mWorkTaskThread.getLooper());
            }
        }
    }
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case MSG_START_INSERT:

        }
    }
    public void startInsert(Uri uri){
        this.obtainMessage(MSG_START_INSERT,uri).sendToTarget();
    }
    protected  abstract void insert();
    protected abstract void delete();
    private class WorkTaskHandler extends Handler{
        WorkTaskHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_START_INSERT:
                insert();
                    break;
                case MSG_START_DELETE:
                    delete();
                    break;
            }
        }
    }
}
