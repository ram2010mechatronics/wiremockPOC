package wiremock_POC_Try;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class stubbing {

    private WireMockServer wiremockserver;

    public void loadStubs(WireMockServer wireMockServer){
       wiremockserver = wireMockServer;

        wiremockserver.stubFor(get(urlEqualTo("/api/get-magic"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"currently\":{\"windSpeed\":12.34}}")));
        wiremockserver.stubFor(post(urlEqualTo("/response-transform-with-params")).willReturn(
                aResponse()
                        .withTransformerParameter("name", "John")
                        .withTransformerParameter("number", 66)
                        .withTransformerParameter("flag", true)
                        .withBody("Original body")
                        .withTransformer("wiremock-ext", "newValue", 66)));
        wiremockserver.stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", containing("application/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("<response>Some content</response>")));
   }



}
