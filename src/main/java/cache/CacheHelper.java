package cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;


public class CacheHelper {

    private CacheManager cacheManager;

    // private Cache<Integer, Integer> squareNumCache;

    public CacheHelper() {

        URL url = getClass().getClassLoader().getResource("ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(url);
        cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();

        // cacheManager.createCache("squareNumCache", CacheConfigurationBuilder
        //     .newCacheConfigurationBuilder(
        //         Integer.class, Integer.class, ResourcePoolsBuilder.heap(10)));
    }

    public Cache<Integer, Integer> getSquareNumCacheFromCacheManager() {
        return cacheManager.getCache("squareNumCache", Integer.class, Integer.class);
    }

}
