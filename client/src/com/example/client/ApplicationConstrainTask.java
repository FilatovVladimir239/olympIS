package com.example.client;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import beans.ApplicationConstrain;
import com.example.client.exceptions.XmlGenerationException;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import com.googlecode.openbeans.XMLDecoder;

//TODO: завести отдельный класс для результата
//Что-то типа Application constrains
public class ApplicationConstrainTask extends AsyncTask<String, Integer, String> {
	public ApplicationConstrainTask(String country, String login, String password, URL serverURL) {
		this.country     = country;
		this.login       = login;
		this.password    = password;
		this.serverURL   = serverURL;
	}

	@Override
	public String doInBackground(String... data) {
		try {
			Client cl = new Client(serverURL);
			String requestXML = generateXML();
			String answerXML  = cl.execute(requestXML);

			XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(answerXML.getBytes("UTF-8")));
			ApplicationConstrain app = (ApplicationConstrain) decoder.readObject();

		} catch (XmlGenerationException e) {
			Log.d("ANL", "XML generation error!");
		} catch (UnsupportedEncodingException e) {
			Log.d("ANL", "Unsupported encoding error!");
		}

		return "";
	}

	protected String generateXML() throws XmlGenerationException {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer     = new StringWriter();

		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "application-constrain-request");
			serializer.attribute("", "country", country);
			serializer.attribute("", "login"   , login);
			serializer.attribute("", "password", password);
			serializer.endTag("", "application-constrain-request");

			serializer.endDocument();
			return writer.toString();

		} catch (IOException e) {
			throw new XmlGenerationException();
		}
	}

	private String country;
	private String login;
	private String password;
	private URL    serverURL;
}
