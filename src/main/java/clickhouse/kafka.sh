# modify kafka offset
kafka-consumer-groups.sh --reset-offsets --to-earliest --topic clickhouse --group group1 --execute --bootstrap-server 192.168.128.128:9092

# show the offset of topic
kafka-consumer-groups.sh --bootstrap-server 192.168.128.128:9092 --group group1 --describe