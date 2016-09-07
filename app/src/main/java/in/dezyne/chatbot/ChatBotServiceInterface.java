package in.dezyne.chatbot;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by dheerajkaushik on 17/07/16.
 */
public interface ChatBotServiceInterface {
    // /converse?v=20160526&session_id=123abc&q=<query-string>
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"})
    @POST("/converse")
    Call<WitResponse> getResponse(@QueryMap Map<String, String> options);

    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"})
    @POST("/converse")
    Observable<WitResponse> getResponseObservable(@QueryMap Map<String, String> options);



}
