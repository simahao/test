package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class Sender {

    public static final String TOPIC_NAME = "test";

    private  static Properties props = new Properties();

    static {
        props.put("bootstrap.servers", "wslhost:9092");
        props.put("client.id", "test");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
    }
    public static void main(String[] args) {
        Order.OrderMsg.Builder builder = Order.OrderMsg.newBuilder();
        builder.setId(5).setOrderName("c2105").setMatchNo(100);
        Order.OrderMsg order = builder.build();
        byte[] bytes = order.toByteArray();

        Producer<String, byte[]> producer = new KafkaProducer<>(props);

        for (int i = 100; i < 200; i++) {
            producer.send(new ProducerRecord<String, byte[]>(TOPIC_NAME, String.valueOf(i), bytes));
        }
        System.out.println("send completed");
        producer.close();
    }
}
