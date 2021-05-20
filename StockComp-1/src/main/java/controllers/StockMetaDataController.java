package controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

public class StockMetaDataController {

	RestTemplate restTemplate = new RestTemplate();
	JSONObject job = null;
	String baseUrl = "https://www.alphavantage.co/query?";
	final String API_KEY = "28SGRB4RWVLUZBB9";

	@RequestMapping(value = "metadata/{id}", method = RequestMethod.GET, produces = "application/json")
	public String getMetadata(@PathVariable("id") String stockName) {
		restTemplate = new RestTemplate();
		String overviewUrl = baseUrl + "function=OVERVIEW&symbol=" + stockName + "&apikey=" + API_KEY;
		ResponseEntity<String> response = restTemplate.getForEntity(overviewUrl, String.class);
		try {
			job = new JSONObject(response.getBody());
			return job.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return job.toString();
	}

	@RequestMapping(value = "metadata/{id}/timeseries", method = RequestMethod.GET, produces = "application/json")
	public String getStockTimeSeries(@PathVariable("id") String itemid) {

		restTemplate = new RestTemplate();

		String timeSeriesUrl = baseUrl + "function=TIME_SERIES_DAILY_ADJUSTED&symbol=" + itemid + "&apikey=" + API_KEY;
		ResponseEntity<String> response = restTemplate.getForEntity(timeSeriesUrl, String.class);
		try {
			job = new JSONObject(response.getBody());
			return job.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return job.toString();
	}

}
