package wiremock_POC_Try;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

public class wiremock_ext extends ResponseTransformer {
    @Override
    public Response transform(Request request, Response response, FileSource fileSource, Parameters parameters) {
        String responsebody = "<response>Some content</response>";
        final Response.Builder builder = Response.Builder.like(response).but();
        builder.body(responsebody);
        return builder.build();
    }

    @Override
    public String getName() {
        return "wiremock-ext";
    }

    @Override
    public boolean applyGlobally() {
        return false;
    }

}
