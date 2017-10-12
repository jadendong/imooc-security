package com.jadendong.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;


public class MockServer {

	public static void main(String[] args) {
		configureFor(8888);
		removeAllMappings();
		
		stubFor(get(urlPathEqualTo("/order/1")).willReturn(aResponse().withBody("{\"id\":1}").withStatus(200)));
		
	}
}
