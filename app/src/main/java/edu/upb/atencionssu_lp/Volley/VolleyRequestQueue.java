package edu.upb.atencionssu_lp.Volley;

/**
 * Created by Adrian on 5/7/2018.
 */

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleyRequestQueue {
    private static VolleyRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleyRequestQueue(Context context){
        // Specify the application context
        mContext = context;
        // Get the request queue
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestQueue getInstance(Context context){
        // If Instance is null then initialize new Instance
        if(mInstance == null){
            mInstance = new VolleyRequestQueue(context);
        }
        // Return MySingleton new Instance
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        // If RequestQueue is null the initialize new RequestQueue
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        // Return RequestQueue
        return mRequestQueue;
    }

    public<T> void addToRequestQueue(CustomRequest request){
        // Add the specified request to the request queue
        getRequestQueue().add(request);
    }

}
