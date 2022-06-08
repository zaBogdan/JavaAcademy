package com.bnz.shared.constants;

public class RabbitMQConstants {
    public static String EXCHANGE = "quiz_exchange";
    public static String ROUTING_KEY_PROCESSING = "quiz_java_project_processing";
    public static String ROUTING_KEY_RESPONSE = "quiz_java_project_response";
    public static int TIME_TO_LIVE = 300000; // 5 minutes
}
