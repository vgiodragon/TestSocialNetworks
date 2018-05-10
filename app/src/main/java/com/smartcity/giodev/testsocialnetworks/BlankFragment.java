package com.smartcity.giodev.testsocialnetworks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button btnShareIns,btnShareLink, btnSharePhoto,btnTwitter;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static final int PICK_IMAGE = 1000;
    public static final int PICK_IMAGE2 = 1234;


    private static String TAG = "GIODEBUG_2";
    //private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        /*
        ListView lv =  v.findViewById(R.id.mlistview);
        //lv.setEmptyView(findViewById(R.id.loading)); d'ont forget to add loading pic

        SearchTimeline searchTimeline = new SearchTimeline.Builder().query("QhatUNI").build();

        final TweetTimelineListAdapter timelineAdapter = new TweetTimelineListAdapter(getActivity(), searchTimeline);

        lv.setAdapter(timelineAdapter);
*/
/*        final RecyclerView recyclerView =  v.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("QhatUNI")
                .maxItemsPerRequest(100)
                .build();

        final TweetTimelineRecyclerViewAdapter adapter =
                new TweetTimelineRecyclerViewAdapter.Builder(getActivity())
                        .setTimeline(searchTimeline)
                        .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                        .build();

        recyclerView.setAdapter(adapter);
*/
/*         final LinearLayout myLayout
               = v.findViewById(R.id.mLinear);

        final long tweetId = 994240118218219520L;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                myLayout.addView(new TweetView(getContext(), result.data));
            }

            @Override
            public void failure(TwitterException exception) {
                // Toast.makeText(...).show();
            }
        });*/
        final LinearLayout myLayout
                = v.findViewById(R.id.mLinear);
        final long tweetId = 994240118218219520L;//GIO
        final long tweetId2 = 992177699308425217L;//Alf
        final long tweetId3 = 862733652836397057L;///CTIC
        LoadTweet(tweetId,myLayout);
        LoadTweet(tweetId2,myLayout);
        LoadTweet(tweetId3,myLayout);

        btnShareLink = v.findViewById(R.id.btnShareLink);
        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("#QhatUNI")
                        .setContentUrl(Uri.parse("https://youtube.com"))
                        .build();
                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(linkContent);
                }
            }
        });
        btnSharePhoto = v.findViewById(R.id.btnSharePhoto);
        btnSharePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        btnShareIns = v.findViewById(R.id.btnShareInstagram);
        btnShareIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture for Ins"), PICK_IMAGE2);
            }
        });

        btnTwitter = v.findViewById(R.id.btnTwitter);
        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TweetComposer.Builder builder = new TweetComposer.Builder(getActivity())
                        .text("#QhatUNI");
                        //.image(imageUri);
                builder.show();
            }
        });
        //Init FB
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(getActivity());

        return v;
    }

    public void LoadTweet(long tweetId, final LinearLayout myLayout){
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                CardView cardView = new CardView(getActivity());
                cardView.setRadius(4);
                cardView.setCardElevation(4f);
                cardView.setUseCompatPadding(true);
                cardView.addView(new TweetView(getContext(), result.data, R.style.CustomStyleUNI));
                myLayout.addView(cardView);

                //myLayout.addView(new di);
                //Log.d(TAG,"Mi resp"+result.data.());
            }

            @Override
            public void failure(TwitterException exception) {
                // Toast.makeText(...).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK){
            if (requestCode == PICK_IMAGE) {
                //TODO: action
                Uri selectedImage = data.getData();
                ArrayList<SharePhoto> mArrayPhotos = new ArrayList<>();
                mArrayPhotos.add(new SharePhoto.Builder()
                        .setImageUrl(selectedImage)
                        .build());
                SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                        .setRef("Mi reference")
                        .setPhotos(mArrayPhotos)
                        .setShareHashtag(new ShareHashtag
                                .Builder()
                                .setHashtag("#QhatUNI")
                                .build())
                        .build();
                if (shareDialog.canShow(SharePhotoContent.class)){
                    shareDialog.show(sharePhotoContent);

                }
            }else if (requestCode == PICK_IMAGE2) {
                // Create the new Intent using the 'Send' action.
                Intent share = new Intent(Intent.ACTION_SEND);

                // Set the MIME type
                share.setType("image/*");

                // Create the URI from the media
                //File media = new File(mediaPath);
                Uri  selectedImage = data.getData();

                // Add the URI to the Intent.
                share.putExtra(Intent.EXTRA_TEXT, "#QhatUNI");

                share.putExtra(Intent.EXTRA_STREAM, selectedImage);

                // Broadcast the Intent.
                startActivity(Intent.createChooser(share, "Share to"));
            }
        }
    }

/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
