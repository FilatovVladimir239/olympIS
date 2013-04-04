package com.example.client;

import android.os.AsyncTask;
import android.util.Log;
import beans.DayList;
import beans.Filter;
import beans.FilterListForDayList;
import com.googlecode.openbeans.XMLDecoder;
import utils.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

public class FilterDayListSendTask extends AsyncTask<String, Integer, Boolean> {
	public FilterDayListSendTask(ArrayList<Filter> filters, URL serverURL) {
		this.filters    = filters;
		this.serverURL = serverURL;
	}

	@Override
	public Boolean doInBackground(String... data) {
		try {
			Client cl = new Client(serverURL);
			FilterListForDayList fl = new FilterListForDayList(filters);
			String requestXML = fl.serialize();
			String answerXML  = cl.execute(requestXML);

			XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(answerXML.getBytes("UTF-8")));
			dayList = (DayList) decoder.readObject();

			return dayList != null;

		} catch (UnsupportedEncodingException e) {
			Log.d("ANL", "Unsupported encoding error!");
		} catch (IOException e) {
			Log.d("ANL", "FilterSendTask IOException error!");
		}

		return false;
	}

	@Override
	public void onPostExecute(Boolean result) {
		//TODO: сделать то, что нужно от вьюшки с dayList
	}

	private ArrayList<Filter> filters;
	private DayList           dayList;
	private URL               serverURL;
}
