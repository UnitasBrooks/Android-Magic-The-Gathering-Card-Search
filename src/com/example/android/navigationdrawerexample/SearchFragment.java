package com.example.android.navigationdrawerexample;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: Joseph Yuhas
 * Date: 5/25/14
 * Time: 8:37 PM
 *
 */
public  class SearchFragment extends Fragment {

    public static final String ARG_FRAG_NUMBER = "frag_number";
    final private String ID_SEARCH = "http://api.mtgdb.info/content/hi_res_card_images/";
    final private String NAME_SEARCH = "http://api.mtgdb.info/search/";//"http://api.mtgapi.com/v1/card/name/";
    protected ImageView cardImage = null;
    LinkedList list = null;
    View rootView = null;

    public SearchFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // When this  fragment is called into creation we inflate the xml associated with it
        rootView = inflater.inflate(R.layout.search, container, false);

        // Get the title from the array and display it in the action bar
        String title = getResources().getStringArray(R.array.fragments)[0];
        getActivity().setTitle(title);

        // Get the currently empty card image
        cardImage =  (ImageView) rootView.findViewById(R.id.imageView1);

        // Create a new LL
        list = new LinkedList();

        // Make the progress bar invisible
        invisibleBar();

        // Button listeners
        Button search = (Button) rootView.findViewById(R.id.search);
        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       handleClick(view);
                    }
                }
        );

        Button random = (Button) rootView.findViewById(R.id.random);
        random.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handleClick(view);
                    }
                }
        );

        Button next = (Button) rootView.findViewById(R.id.next);
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handleClick(view);
                    }
                }
        );

        Button previous = (Button) rootView.findViewById(R.id.previous);
        previous.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handleClick(view);
                    }
                }
        );

        return rootView;
    }


    private void handleClick(View v) {
        switch (v.getId()) {
            case  R.id.search:
                searchClick();
                break;
            case R.id.random:
                randomClick();
                break;
            case R.id.previous:
                displayPrevious();
                break;
            case R.id.next:
                displayNext();
                break;
        }

    }

    public void searchClick() {
        EditText searchField = (EditText) getView().findViewById(R.id.editText1);
        String searchFor = searchField.getText().toString();
        searchFor = searchFor.replaceAll(" ","+");
        String url = NAME_SEARCH + searchFor + "?start=0&limit=1";
        visibleBar();
        disableButtons();
        new ConnectToURL().execute(url);
        searchField.setText("");
    }

    private class ConnectToURL extends AsyncTask<String,Void,CardNode> {
        @Override
        protected CardNode doInBackground(String... params) {
            String url = params[0];
            JsonReader reader = new JsonReader();

            return  reader.getCard(url);
        }

        protected void onPostExecute(CardNode card) {
            String url = ID_SEARCH + card.id + ".jpg";
            card.url = url;
            downloadCardImage(card);
        }
    }


    public void randomClick() {
        visibleBar();
        disableButtons();
        new ConnectToURL().execute("http://api.mtgdb.info/cards/random");
    }



    public void displayPrevious() {
        Bitmap previous = list.getPrevious();
        if(previous != null)
            setImageFromList(previous);
    }

    public void displayNext() {
        Bitmap next = list.getNext();
        if(next != null)
            setImageFromList(next);
    }


    public void downloadCardImage(CardNode card) {
        new DownloadImage().execute(card);

    }

    public void noCardFound() {
        TextView rawText = (TextView) getView().findViewById(R.id.text);
        rawText.setText("No card found");
        cardImage.setImageResource(android.R.color.transparent);
    }

    public void setImageFromList(Bitmap bits) {
        cardImage.setImageBitmap(bits);
    }

    public void setImage(CardNode card) {
        cardImage.setImageBitmap(card.bits);
        list.push(card);
    }

    public class DownloadImage extends AsyncTask <CardNode,Void,CardNode> {

        @Override
        protected CardNode doInBackground(CardNode... params) {
            CardNode card = params[0];
            Bitmap bits = null;
            try {
                InputStream getImage = new java.net.URL(card.url).openStream();
                bits = BitmapFactory.decodeStream(getImage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            card.setBits(bits);
            return card;
        }

        protected void onPostExecute(CardNode card) {
            invisibleBar();
            enableButtons();
            if(card.bits != null)
                setImage(card);
            else
                noCardFound();
        }

    }


    public void disableButtons() {
        Button search = (Button) getView().findViewById(R.id.search);
        Button random = (Button) getView().findViewById(R.id.random);

        search.setEnabled(false);
        random.setEnabled(false);
    }

    public void enableButtons() {
        Button search = (Button) getView().findViewById(R.id.search);
        Button random = (Button) getView().findViewById(R.id.random);

        search.setEnabled(true);
        random.setEnabled(true);
    }

    public void visibleBar() {
        ProgressBar pb = (ProgressBar) getView().findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);
    }

    public void invisibleBar() {
        ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
    }

    public String getCurrent() {
        return list.getCurrent().name;
    }


}