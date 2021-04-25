package clickhouse;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class ClickHouse {
    public static void main(String[] args) throws JsonProcessingException, InterruptedException, ExecutionException {
        Properties pros = new Properties();
        pros.setProperty("bootstrap.servers", "192.168.128.128:9092");

        pros.setProperty("key.serializer", StringSerializer.class.getName());
        pros.setProperty("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String, String> kp = new KafkaProducer<String, String>(pros);

        ObjectMapper om = new ObjectMapper();
        for (int i = 0; i < 10; i++) {
            Data data = new Data();
            data.setLevel(String.valueOf(i));
            data.setTotal(Long.valueOf(i + 1));
            data.setMessage(String.valueOf(i) + "hz");
            String value = om.writeValueAsString(data);
            ProducerRecord<String, String> pr = new ProducerRecord<String, String>("clickhouse", 0, String.valueOf(i), value);
            Future<RecordMetadata> future = kp.send(pr);
            RecordMetadata rm = future.get();
            System.out.println(rm);
        }
        kp.close();
    }
}
