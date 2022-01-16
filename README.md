# G1
## G1 advantages

1. G1大部分时候具备并发回收，CMS（concurrent mark&sweep）只能并发回收老年代
2. 将堆划分为多个region（1M~32M），使用时可以是非连续，适合需要大内存使用的程序。如果是CMS，大堆分成edgen,survisor,old，回收会较慢
3. 支持混合回收，edgen和survisor同时（mixed garbage collect）

## G1回收过程

1. initial mark：g1会做一些记账工作，并暂停应用运行，很短时间
2. concurrent mark：g1会跟踪所有对象，并标记存活对象，这个过程结束后，g1会再次暂停程序执行final mark
3. final mark：final cleanup
4. evacuation：选取一些region并回收他们，因为evacuation对小的region回收，非常快，同其他gc相比，对程序暂停的影响非常小
5. 如果要回收整个堆（full gc），g1和其他垃圾回收期没有区别

90%的对象在第一次回收时就被销毁，old object有98%的机会继续存活，13%的字符串对象是重复的

```sh
-Xmx4G -Xms4G
java -XX:+UseG1GC -XX:InitialHeapSize=2g -XX:MaxHeapSize=2g -XX:MaxGCPauseMillis=200 -XX:+DisableExplicitGC -XX:+UseStringDeduplication -XX:+ParallelRefProcEnabled -XX:MaxMetaspaceSize=256m -XX:MaxTenuringThreshold=15  -Xlog:gc=info:file=gc.log:time,uptime,level,tags:filecount=5,filesize=10m   -cp ./test.jar gc.G1Test


-Xlog:gc*=debug:file=gc.log:time,uptime,level,tags:filecount=5,filesize=10m 

-Xlog:gc*,safepoint,age*,ergo*:file=/opt/gclogs/gc-%p-%t.log:tags,uptime,time,level:filecount=10,filesize=50m
```
