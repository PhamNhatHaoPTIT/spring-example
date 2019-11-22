package com.gaurav.kafka.constants;

public interface IKafkaConstants {
	String KAFKA_BROKERS = "localhost:9092, localhost:9093, localhost:9094";
	Integer MESSAGE_COUNT=10;
	String CLIENT_ID="client2";
	String TOPIC_NAME="demo";
	String GROUP_ID_CONFIG="consumerGroup10";
	Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
	String OFFSET_RESET_LATEST="latest";
	String OFFSET_RESET_EARLIER="earliest";
	Integer MAX_POLL_RECORDS=1;
}
