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

HEAP_MIN="-Xms2G"
HEAP_MAX="-Xmx2G"

JMX_PORT=5031
JMX_OPTS="-Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=${JMX_PORT}" -Djava.rmi.server.hostname=172.29.198.81
JMX_FLAG=1
JSTATD="jstatd -p 1099 -J-Djava.security.policy=/root/jstatd.all.policy -J-Djava.rmi.server.hostname=172.29.198.81"
MAX_METASPACESIZE="-XX:MaxMetaspaceSize=256m"
MAX_TENURING_THRESHOLD="-XX:MaxTenuringThreshold=15"
LOG_SIZE="100m"
LOG_COUNTS=5
LOG_NAME="gc.log"
LOG_LEVEL=info
LOG_OPTS="-Xlog:gc=${LOG_LEVEL}:file=${LOG_NAME}:time,uptime,level,tags:filecount=${LOG_COUNTS},filesize=${LOG_SIZE}"
OPTS="-XX:+UseG1GC ${HEAP_MIN} ${HEAP_MAX} -XX:+DisableExplicitGC -XX:+UseStringDeduplication -XX:+ParallelRefProcEnabled"

while getopts ":ljc:" OPT
do
    case ${OPT} in
        l)
            OPTS="${OPTS} ${LOG_OPTS}"
        j)
            OPTS="${OPTS} ${JMX_OPTS}"
        c)
            OPTS="${OPTS} -cp ${OPTARG}"
        ?)
            echo "error parameter"
            exit 1;;
    esac
done

echo "${OPTS}"
java ${OPTS}

grant {
  permission java.security.AllPermission;
};


-Xlog:gc*=debug:file=gc.log:time,uptime,level,tags:filecount=5,filesize=10m

-Xlog:gc*,safepoint,age*,ergo*:file=/opt/gclogs/gc-%p-%t.log:tags,uptime,time,level:filecount=10,filesize=50m
```
