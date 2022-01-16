```sh
java -XX:+UseG1GC -XX:InitialHeapSize=2g -XX:MaxHeapSize=2g -XX:MaxGCPauseMillis=200 -XX:+DisableExplicitGC -XX:+UseStringDeduplication -XX:+ParallelRefProcEnabled -XX:MaxMetaspaceSize=256m -XX:MaxTenuringThreshold=15  -Xlog:gc=info:file=gc.log:time,uptime,level,tags:filecount=5,filesize=10m   -cp ./test.jar gc.G1Test
```
