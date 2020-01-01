package tutorial;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.consumer.*;



public class Consumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Properties configs = new Properties();
        //commit
        // ȯ�� ���� ����
        configs.put("bootstrap.servers", "localhost:9092");		// kafka server host �� port
        configs.put("session.timeout.ms", "10000");				// session ����
        configs.put("group.id", "test191031");					// topic ����
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");	// key deserializer
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  // value deserializer
        
        @SuppressWarnings("resource")
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);	// consumer ����
        consumer.subscribe(Arrays.asList("test191031"));		// topic ����
        
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

        while (true) {	// ��� loop�� ���鼭 producer�� message�� ����.
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
            	
            	Date time = new Date();
            	String time1 = format1.format(time);
            	
                String s = record.topic();
                if ("test191031".equals(s)) {
                    System.out.println(time1 + " | " + record.value());
                } else {
                    throw new IllegalStateException("get message on topic " + record.topic());

                }
            }
        }
        
	}

}
