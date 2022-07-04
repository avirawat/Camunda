package com.camunda.training.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author AvinashRavat
 *
 */
@Named
public class AccessExternalAPI implements JavaDelegate {

	@Value("${gitHubURL}")
	private String gitUrl;

	/**
	 * @author AvinashRavat
	 * @param execution
	 * @return This Service is calling external API and using API's value is our
	 *         Process Engine
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		HttpResponse<String> response = get(gitUrl);

		if (response.statusCode() != 200) {

			// create incidence if Status code is not 200
			throw new Exception("Error from REST call, Response code: " + response.statusCode());

		} else {
			String body = response.body();
			JSONObject obj = new JSONObject(body);
			// parse for downloads
			Boolean downloads = obj.getBoolean("has_downloads");

			if (!downloads) {

				// Throw BPMN error
				throw new BpmnError("NO_DOWNLOAD_OPTION", "Repo can't be downloaded");

			} else {

				// parse for forks
				String forks = obj.getString("forks");
				// String id=obj.getString("camunda.bpm.admin-user.id");
				int forksAsNumber = Integer.parseInt(forks);
				// Set variables to the process
				execution.setVariable("forks", forksAsNumber);
				// execution.setVariable("id", id);

			}

		}

	}

	public HttpResponse<String> get(String uri) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.body());

		return response;
	}

}
