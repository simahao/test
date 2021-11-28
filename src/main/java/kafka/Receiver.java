package kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.google.protobuf.InvalidProtocolBufferException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * Receiver
 * @author hz
 */
public class Receiver {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "wslhost:9092");
        props.setProperty("group.id", "test2");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("max.poll.records", "40000");
        props.setProperty("max.partition.fetch.bytes", "209715200");
        props.setProperty(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_uncommitted");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        String topic = "test1";
        TopicPartition topicPartition = new TopicPartition(topic, 0);
        List<TopicPartition> topicPartitionList = Arrays.asList(topicPartition);
        consumer.assign(topicPartitionList);
        consumer.seekToBeginning(topicPartitionList);

        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(2000));
            for (ConsumerRecord<String, byte[]> record : records) {
                Order.OrderMsg msg = Order.OrderMsg.parseFrom(record.value());
                System.out.println("key:" + record.key());
                System.out.println("record:\r\n" + msg.toString());
            }
        }
    }
    
}
