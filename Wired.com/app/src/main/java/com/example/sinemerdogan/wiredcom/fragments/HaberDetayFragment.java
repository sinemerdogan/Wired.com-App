package com.example.sinemerdogan.wiredcom.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sinemerdogan.wiredcom.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by sinem erdoğan on 27.1.2017.
 */

public class HaberDetayFragment extends Fragment {


    private String linkimiz;
    private String news_title;
    private String news_image;

    public HaberDetayFragment(){}

    @SuppressLint("ValidFragment")
    public HaberDetayFragment(String url,String title,String image){
        super();
        this.linkimiz = url;
        this.news_title = title;
        this.news_image = image;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private GoogleApiClient client;
    private JsonObjectRequest jsonObjectRequest;
    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_haber_detay, container, false);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.start();


        // Toast.makeText(getActivity(), linkimiz, Toast.LENGTH_SHORT).show();

        String link = "http://sinemerdogan.com/wired/get.php?url=" + linkimiz;
        StringRequest strRequest = new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response)
                    {
                        try {

                            TextView news_content_title= (TextView)(getActivity()).findViewById(R.id.title_id);
                            news_content_title.setText(news_title);

                            ImageView news_content_image = (ImageView)(getActivity()).findViewById(R.id.image_id);

                            Picasso.with(news_content_image.getContext())
                                    .load(news_image)
                                    .resize(400, 200)
                                    .centerCrop()
                                    .into(news_content_image);

                            JSONObject jsonObjesi = new JSONObject(response);
                            String icerikObjesi = jsonObjesi.getString("html");
                            TextView icerik= (TextView)(getActivity()).findViewById(R.id.textView2);
                            icerik.setText(Html.fromHtml(icerikObjesi));

                            JSONArray kelimeler = jsonObjesi.getJSONArray("words");
                            final TextView kelime1 = (TextView)(getActivity()).findViewById(R.id.news_word);
                            final TextView kelime1_trans = (TextView)(getActivity()).findViewById(R.id.news_translate);

                            final TextView kelime2 = (TextView)(getActivity()).findViewById(R.id.news_word1);
                            final TextView kelime2_trans = (TextView)(getActivity()).findViewById(R.id.news_translate1);

                            final TextView kelime3 = (TextView)(getActivity()).findViewById(R.id.news_word2);
                            final TextView kelime3_trans = (TextView)(getActivity()).findViewById(R.id.news_translate2);

                            final TextView kelime4 = (TextView)(getActivity()).findViewById(R.id.news_word3);
                            final TextView kelime4_trans = (TextView)(getActivity()).findViewById(R.id.news_translate3);

                            final TextView kelime5 = (TextView)(getActivity()).findViewById(R.id.news_word4);
                            final TextView kelime5_trans = (TextView)(getActivity()).findViewById(R.id.news_translate4);

                            final String kelime1_str = kelimeler.getJSONObject(0).getString("word");
                            final String kelime1_translate = kelimeler.getJSONObject(0).getString("translated");

                            final String kelime2_str = kelimeler.getJSONObject(1).getString("word");
                            final String kelime2_translate = kelimeler.getJSONObject(1).getString("translated");

                            final String kelime3_str = kelimeler.getJSONObject(2).getString("word");
                            final String kelime3_translate = kelimeler.getJSONObject(2).getString("translated");

                            final String kelime4_str = kelimeler.getJSONObject(3).getString("word");
                            final String kelime4_translate = kelimeler.getJSONObject(3).getString("translated");

                            final String kelime5_str = kelimeler.getJSONObject(4).getString("word");
                            final String kelime5_translate = kelimeler.getJSONObject(4).getString("translated");

                            kelime1.setText(kelime1_str);
                            kelime2.setText(kelime2_str);
                            kelime3.setText(kelime3_str);
                            kelime4.setText(kelime4_str);
                            kelime5.setText(kelime5_str);

                            kelime1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    kelime1_trans.setText(kelime1_translate);


                                }
                            });

                            kelime2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    kelime2_trans.setText(kelime2_translate);


                                }
                            });

                            kelime3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    kelime3_trans.setText(kelime3_translate);


                                }
                            });

                            kelime4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    kelime4_trans.setText(kelime4_translate);


                                }
                            });

                            kelime5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    kelime5_trans.setText(kelime5_translate);


                                }
                            });



                        } catch (Throwable t) {
                        }


                    }
                },

                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }

        );

        {
        };



        queue.add(strRequest);


        return v;


    }


    }


