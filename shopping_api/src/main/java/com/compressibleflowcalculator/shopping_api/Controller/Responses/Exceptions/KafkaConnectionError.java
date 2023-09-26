package com.compressibleflowcalculator.shopping_api.Controller.Responses.Exceptions;

public class KafkaConnectionError extends RuntimeException {
    public KafkaConnectionError() {
        super("Failed To Connect To Kaka");
    }
}
