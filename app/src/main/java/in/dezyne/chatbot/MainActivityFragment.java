package in.dezyne.chatbot;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.view.RxView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private Button mButton;
    private EditText mInput;
    private EditText mResponse;
    private static ChatBotServiceInterface userService =
            ChatBotService.createService(ChatBotServiceInterface.class, "Bearer 4PBO5SHS7DWWFTSW5O4OP5C6QYJVTVR5");


    public MainActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        Button b = (Button)v.findViewById(R.id.okButton);
        final EditText mInput = (EditText)v.findViewById(R.id.inputText);
        final EditText mResponse = (EditText)v.findViewById(R.id.responseText);

        Subscription buttonSub =
                RxView.clicks(b).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Map<String, String> data = new HashMap<>();
                        WitResponse response = null;
                        data.put("v", "20160526");
                        data.put("session_id", "123abc");
                        data.put("q",mInput.getText().toString());
                        // this code is part of your activity/fragment
                        Observable<WitResponse> observable = userService.getResponseObservable(data);

                        observable
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Subscriber<WitResponse>() {
                                    @Override
                                    public void onCompleted() {
                                        // handle completed
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d("MY OBSERVER",e.getMessage());
                                        // handle error
                                    }

                                    @Override
                                    public void onNext(WitResponse response) {
                                        // handle response
                                        if(response.type.equals("msg"))
                                            mResponse.setText(response.msg);
                                        else
                                            mResponse.setText(response.type);
                                        Log.d("MY OBSERVER",response.toString());
                                    }
                                });
                    }
                });
        return v;

    }
}
