package wiremock_POC_Try;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.common.ListOrSingle;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.RequestTemplateModel;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

import java.util.Map;

public class wiremock_ext extends ResponseTransformer {
    private Map<String,ListOrSingle<String>> requestParams;
    DB_Result db = new DB_Result();
    private String responsebody = null;
    @Override
    public Response transform(Request request, Response response, FileSource fileSource, Parameters parameters) {
        requestParams = RequestTemplateModel.from(request).getQuery();

        try {
            responsebody = db.executequery(requestParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
