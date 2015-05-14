package cz.muni.fi.sdipr.producer;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.muni.fi.sdipr.utils.Utils;


public class KafkaCharactersProducer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaCharactersProducer.class);

	private static final String HOST = "147.251.43.240";
	private static final String PORT = "9092";

	private static String topic;

	public static void main(String[] args) {

		if (args.length < 1) {
            System.err
                    .println("USAGE: First parameter is the Kafka topic to which you want to write the data");
            System.exit(2);
        }
        topic = args[0];

		Random rnd = new Random();

		Properties props = new Properties();
		//		props.put("metadata.broker.list", "broker0:9092");
		props.put("metadata.broker.list", HOST + ":" + PORT);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		//		props.put("partitioner.class", "example.producer.SimplePartitioner");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new Producer<String, String>(config);

		//TODO: Uncomment if you want to set exact number of generated messages through command line
		//		for (long nEvents = 0; nEvents < events; nEvents++) {
		//			String msg = Utils.generateRandomCharacter(rnd);
		//			TODO: later when it will not be reqguired can be concatenation of strings removed
		//			to see from which topic the message is
		//			msg = msg + topic;
		//			KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, msg);
		//			producer.send(data);
		//			System.out.println("Sent data: " + data);
		//		}

		try {
			while (System.in.available() == 0) {
				String msg = Utils.generateRandomCharacter(rnd);
				//TODO: later when it will not be reqguired can be concatenation of strings removed
				//to see from which topic the message is
				msg = msg + topic;
				KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, msg);
				producer.send(data);

				System.out.println("Sent data: " + data);

				try {
					Thread.sleep(rnd.nextInt(1000));
				}
				catch (InterruptedException ex) {
					logger.error("Sleep method failed.", ex);
				}
			}
		}
		catch (IOException e) {
			logger.error("Error occurred while working with console input.", e);
		}

		producer.close();
	}
}
