package javier.com.stormy.asynctasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javier.com.stormy.url.ForecastClient;
import teamtreehouse.com.stormy.model.Forecast;

/**
 * Created by Javi on 11/10/2016.
 */

public class ForecastAsyncTask extends AsyncTask<ForecastClient, Void, Forecast> {

    public interface ForecastListener {

        void onForecastRetrieved(Forecast forecast);
    }

    private ForecastListener mListener;

    public ForecastAsyncTask(ForecastListener listener) {

        mListener = listener;
    }

    @Override
    protected Forecast doInBackground(ForecastClient... params) {

        String url = params[0].toString();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            .url(url)
            .build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            String jsonData = response.body().string();

            Gson gson = new GsonBuilder().create();
            Forecast forecast = gson.fromJson(jsonData, Forecast.class);

            return forecast;
        }
        catch (IOException e) {

            // TODO implement a custom excpetion type to send back to the activity in case it fails
            // to retrieve the correct data
        }

        return null;
    }

    @Override
    protected void onPostExecute(Forecast forecast) {

        mListener.onForecastRetrieved(forecast);
    }
}
