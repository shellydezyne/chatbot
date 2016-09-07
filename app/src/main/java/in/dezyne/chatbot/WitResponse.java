package in.dezyne.chatbot;

/**
 * Created by dheerajkaushik on 17/07/16.
 */
class WitIntent {
    public long confidence;
    public String value;
}

public class WitResponse {
    public String type;
    public String msg;

    Iterable<WitIntent> wIntent;


    public String getMsg(){return msg;}

    @Override
    public String toString() {
        String s = null;
        //while(wIntent.iterator().hasNext())
        //    s += " Intent:" + wIntent.iterator().next().value +  " Confidence(" + wIntent.iterator().next().confidence + ")";
        return "Type:"+type;
    }
}
