package com.example.sinemerdogan.wiredcom.fragments;
/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sinemerdogan.wiredcom.R;
import com.example.sinemerdogan.wiredcom.XMLParser;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




public class AnasayfaFragment extends Fragment {

    private String sonLinkBukucu;

    public AnasayfaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private int getWindowWidth()
    {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    private int getWidthPercent(Integer yuzde)
    {
        return (int) (getWindowWidth()*yuzde/100);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_anasayfa, container, false);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.start();
        String url = "https://www.wired.com/feed/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        final XMLParser parser = new XMLParser();
                        Document doc = parser.getDomElement(response);
                        NodeList nl = doc.getElementsByTagName("item");

                        // looping through all item nodes <item>

                        try {


//                            >Yeni

//                            @for
//                                    >Relative layout
//                                      >Textview(textsize:18px, bold, 260dp) => Başlık
//                                      >Textview(textsize:14px, 260dp) => Açıklama
//                                      >Textview(textsize:14px, 260dp) => Zaman
//                                      >ImageViw(250,250 mrgn:5dp,centercrop)
//                            @endfor

                            LinearLayout yeniLayout = (LinearLayout) (getActivity()).findViewById(R.id.yeni);
                            yeniLayout.setPadding(0,0,0,0);


                            for (int i = 0; i < 5; i++) {
                                final Element e = (Element) nl.item(i);

                                RelativeLayout haber = new RelativeLayout(getActivity());
                                LinearLayout.LayoutParams haber_params = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                );


                                final FrameLayout frame = new FrameLayout(getActivity());
                                FrameLayout.LayoutParams frame_params = new FrameLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                );

                                int frame_renk;
                                frame_renk = Color.parseColor("#575657");
                                frame.setBackgroundColor(frame_renk);
                                frame.setLayoutParams(frame_params);
                                frame.setPadding(10, 10, 10, 10);

                                haber_params.setMargins(10, 10, 10, 10);
                                int color;
                                color = Color.parseColor("#ffffff");
                                haber.setBackgroundColor(color);
                                haber.setLayoutParams(haber_params);
                                haber.setPadding(10, 10, 10, 10);


                                final LinearLayout baslik_dis = new LinearLayout(getActivity());
                                LinearLayout.LayoutParams baslik_dis_param = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                );
                                baslik_dis.setOrientation(LinearLayout.VERTICAL);
                                baslik_dis.setPadding(0,0,getWidthPercent(30),0);
                                baslik_dis.setLayoutParams(baslik_dis_param);



                                // baslik
                                final TextView baslik = new TextView(getActivity());
                                baslik.setTextSize(18);
                                baslik.setTypeface(null, Typeface.BOLD);
                                baslik.setTextColor(Color.parseColor("#000000"));
                                // baslik --> baslik_dis
                                baslik_dis.addView(baslik);


                                // aciklama
                                TextView aciklama = new TextView(getActivity());
                                aciklama.setTextSize(14);
                                aciklama.setPadding(0,10,0,0);
                                aciklama.setTextColor(Color.parseColor("#000000"));
                                aciklama.setGravity(Gravity.LEFT);
                                // aciklama -> baslik_dis
                                baslik_dis.addView(aciklama);



                                TextView zaman = new TextView(getActivity());
                                zaman.setPadding(25,25,0,0);
                                zaman.setTextColor(Color.parseColor("#000000"));
                                zaman.setTextSize(12);


                                ImageView clock = new ImageView(getActivity());
                                LinearLayout.LayoutParams clock_params = new LinearLayout.LayoutParams(
                                        25,
                                        25
                                );

                                clock.setLayoutParams(clock_params);

                                LinearLayout Lc_clock = new LinearLayout(getActivity());
                                LinearLayout.LayoutParams Lc_params = new LinearLayout.LayoutParams(
                                        25,
                                        25

                                );

                                clock.setPadding(0,0,0,0);
                                clock.setLayoutParams(Lc_params);
                                Lc_clock.addView(clock);

                                clock.setImageResource(R.drawable.clock);
                                Lc_clock.setPadding(0, 20, 0, 0);

                                final LinearLayout zaman_dis = new LinearLayout(getActivity());
                                LinearLayout.LayoutParams zaman_dis_param = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                );
                                zaman_dis.setOrientation(LinearLayout.HORIZONTAL);
                                zaman_dis.setLayoutParams(zaman_dis_param);

                                zaman_dis.addView(Lc_clock);
                                zaman_dis.addView(zaman);
                                baslik_dis.addView(zaman_dis);



                                ImageView resim = new ImageView(getActivity());
                                LinearLayout.LayoutParams resim_params = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        100
                                );

                                resim.setLayoutParams(resim_params);

                                LinearLayout LL_Inner = new LinearLayout(getActivity());
                                LL_Inner.setOrientation(LinearLayout.HORIZONTAL);
                                LinearLayout.LayoutParams LL_params = new LinearLayout.LayoutParams(
                                        getWidthPercent(20),
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                );

                                LL_Inner.setPadding(
                                        getWidthPercent(80)-75, 0, 0, 0
                                );
                                resim.setLayoutParams(LL_params);
                                LL_Inner.addView(resim);


                                final String sonLinkBukucuItem = parser.getValue(e, "link");
                                final String haber_baslik = parser.getValue(e, "title");


                                String tum_html = parser.getValue(e, "description");
                                String aranan = "<img src=\"";
                                int aranan_index = tum_html.indexOf(aranan) + aranan.length();
                                String resim_url = tum_html.substring(aranan_index, tum_html.indexOf("\"", aranan_index + 1));

                                final String haber_image=tum_html.substring(aranan_index, tum_html.indexOf("\"", aranan_index + 1));

                                Picasso.with(resim.getContext())
                                        .load(resim_url)
                                        .resize(getWidthPercent(20), getWidthPercent(20))
                                        .centerCrop()
                                        .into(resim);




                                baslik.setText(parser.getValue(e, "title"));
                                zaman.setText(parser.getValue(e,"pubDate"));
                                aciklama.setText(
                                        Html.fromHtml(parser.getValue(e, "description").replaceAll("<[^>]*>", ""))
                                );


                                haber.addView(baslik_dis);
                                haber.addView(LL_Inner);
                                frame.addView(haber);
                                yeniLayout.addView(frame);

                                haber.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {



                                        FragmentManager fm = getFragmentManager();
                                        FragmentTransaction ft = fm.beginTransaction();
                                        HaberDetayFragment f1 = new HaberDetayFragment(sonLinkBukucuItem,haber_baslik,haber_image);
                                        ft.add(R.id.drawer_layout,f1);
                                        ft.addToBackStack(null);
                                        ft.commit();




                            }
                        });



                            }

                        } catch (Throwable t) {
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);



        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
