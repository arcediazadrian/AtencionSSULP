package edu.upb.atencionssu_lp.Volley;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.upb.atencionssu_lp.R;

/**
 * Created by Adrian on 5/5/2018.
 */

public class RunVolley extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private LinearLayout mCLayout;
    private Button mButtonDo;
    private TextView mTextView;
    private String mJSONURLString = "http://192.168.82.87/ssulp/get_all_beneficiarios.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = RunVolley.this;

        // Get the widget reference from XML layout
       // mCLayout = findViewById(R.id.coordinator_layout);
       // mButtonDo = (Button) findViewById(R.id.btn_do);
       // mTextView = (TextView) findViewById(R.id.tv);

        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Empty the TextView
                mTextView.setText("");

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                Map<String, String> params = new HashMap<String, String>();
                params.put("id_beneficiario", "1");
                // Initialize a new JsonObjectRequest instance
                CustomRequest jsonObjectRequest = new CustomRequest(
                        mJSONURLString,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Do something with response
                                //mTextView.setText(response.toString());
                                Log.d("Response: ", response.toString());

                                // Process the JSON
                                try{
                                    // Get the JSON array
                                    JSONArray array = response.getJSONArray("beneficiarios");


                                    // Loop through the array elements
                                    for(int i=0;i<array.length();i++){
                                        // Get current json object
                                        JSONObject student = array.getJSONObject(i);

                                        // Get the current student (json object) data

                                        String firstName = student.getString("id_beneficiario");
                                        String lastName = student.getString("primer_nombre");

                                        // Display the formatted json data in text view
                                        mTextView.append("id: " + firstName +"\n nombre: " + lastName);
                                        mTextView.append("\n\n");
                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred
                                mTextView.setText("Error");
                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}