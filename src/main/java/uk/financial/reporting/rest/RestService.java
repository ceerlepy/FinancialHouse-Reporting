package uk.financial.reporting.rest;

public interface RestService<T,R> {

    R sendRequest(T request, Class<R> returnClass, String url, boolean getTokenReq, String token) throws Exception;
}
