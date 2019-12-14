public class GetTime {
    private String mHour;
    private String mMinute;
    private String mSecond;
    private String mMilisecond;

    public GetTime(String hour, String minute, String second, String milisecond){
        mHour = hour;
        mMinute = minute;
        mSecond = second;
        mMilisecond = milisecond;

    }

    public String getMilisecond() {
        return mMilisecond;
    }

    public String getTimes(){
        if(mHour.length() == 1) {
            return "0" + mHour + " : " + mMinute + " : " + mSecond;
        }
        else if(mMinute.length() == 1) {
            return mHour + " : 0" + mMinute + " : " + mSecond;
        }
        else if(mSecond.length() == 1){
            return mHour + " : " + mMinute + " : 0" + mSecond;
        }
        return mHour + " : " + mMinute + " : " + mSecond;

    }
}
