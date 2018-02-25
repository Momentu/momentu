package com.momentu.momentuandroid.Data;

/**
 * Created by ibm_a on 11/14/2017.
 */

public class EndPoints {
    final public static String LOCATION_ENDPOINT = "http://www.momentu.xyz:8080/locations";
    final public static String LOGIN_ENDPOINT = "http://www.momentu.xyz:8080/login";
    final public static String REGISTER_ENDPOINT = "http://www.momentu.xyz:8080/register";
    final public static String MEDIA_ENDPOINT = "http://www.momentu.xyz:8080/media";
    final public static String MEDIA_UPLOAD_ENDPOINT = "http://www.momentu.xyz:8080/media_upload";
    final public static String STATES_ENDPOINT = "http://www.momentu.xyz:8080/states";
    final public static String HASHTAGS_ENDPOINT = "http://www.momentu.xyz:8080/hashtags";
    final public static String MEDIA_RETRIVE ="http://www.momentu.xyz:8080/mediaMetas/search/findByStateCityLabel";
    final public static String MEIDALIKE = "http://www.momentu.xyz:8080/mediaLike";
    final public static String MEIDAUNLIKE = "http://www.momentu.xyz:8080/mediaUnlike";
    final  public static String RETRIEVECOMMENTS = "http://www.momentu.xyz:8080/mediaComments/search/findByMediaMetaId";
    final  public static String POSTCOMMENT = "http://www.momentu.xyz:8080/postComment";

//    //the following are equivilant addresses mapped to the local host
//    public static String LOCATION_ENDPOINT = "http://10.0.2.2:8080/locations";
//    public static String LOGIN_ENDPOINT = "http://10.0.2.2:8080/login";
//    public static String REGISTER_ENDPOINT = "http://10.0.2.2:8080/register";
//    public static String MEDIA_ENDPOINT = "http://10.0.2.2:8080/media";
//    public static String MEDIA_UPLOAD_ENDPOINT = "http://10.0.2.2:8080/media_upload";
//    public static String STATES_ENDPOINT = "http://10.0.2.2:8080/states";
//    public static String HASHTAGS_ENDPOINT = "http://10.0.2.2:8080/hashtags";
}
