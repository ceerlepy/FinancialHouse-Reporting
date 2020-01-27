package uk.financial.reporting.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uk.financial.reporting.handler.RestTemplateResponseErrorHandler;

@Service
public class RestServiceImpl<T, R> implements RestService<T, R> {

	private RestTemplate restTemplate;

	@Autowired
	public RestServiceImpl(RestTemplateBuilder restTemplateBuilder,
			RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {

		this.restTemplate = restTemplateBuilder.errorHandler(restTemplateResponseErrorHandler).build();
	}

	@Override
	public R sendRequest(T request, Class<R> returnClass, String url, boolean getTokenReq, String token) throws Exception{

		HttpEntity<T> httpRequest = null;
		if (!getTokenReq) {

			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			List<MediaType> mediaList = new ArrayList<>();
			mediaList.add(MediaType.TEXT_HTML);
			mediaList.add(MediaType.APPLICATION_JSON);
			converter.setSupportedMediaTypes(mediaList);
			restTemplate.getMessageConverters().add(converter);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", token);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			httpRequest = new HttpEntity<>(request, headers);
		} else {
			httpRequest = new HttpEntity<>(request);
		}

		return (R) restTemplate.postForObject(url, httpRequest, returnClass);
	}

}
