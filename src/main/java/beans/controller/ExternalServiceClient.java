package beans.controller;

import feign.RequestLine;

public interface ExternalServiceClient {
    @RequestLine("GET /external/example")
    String invokeExternalService();
}
