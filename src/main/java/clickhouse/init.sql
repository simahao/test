CREATE TABLE queue2 (\
total UInt64,\
level String,\
message String\
) ENGINE = Kafka SETTINGS kafka_broker_list = '192.168.128.128:9092',\
                        kafka_topic_list = 'clickhouse',\
                        kafka_group_name = 'group1',\
                        kafka_format = 'JSONEachRow',\
                        kafka_num_consumers = 1;

CREATE TABLE daily (\
    message String,\
    level String,\
    total UInt64\
  ) ENGINE = StripeLog;

CREATE MATERIALIZED VIEW consumer TO daily\
AS SELECT message, level, total\
FROM queue2;\