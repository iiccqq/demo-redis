package demo.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisTest {

	public static void main(String[] args) {

		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		// Jedis Cluster will attempt to discover cluster nodes automatically
		jedisClusterNodes.add(new HostAndPort("192.168.56.102", 7000));
		jedisClusterNodes.add(new HostAndPort("192.168.56.102", 7001));
		jedisClusterNodes.add(new HostAndPort("192.168.56.102", 7002));
		jedisClusterNodes.add(new HostAndPort("192.168.56.103", 7003));
		jedisClusterNodes.add(new HostAndPort("192.168.56.103", 7004));
		jedisClusterNodes.add(new HostAndPort("192.168.56.103", 7005));
		// JedisCluster jc = new JedisCluster(jedisClusterNodes);
		JedisCluster jc = new JedisCluster(jedisClusterNodes, 1000, 1000, 1, "123456", new GenericObjectPoolConfig());
		int a = 0;
		while (true) {
			try {
			jc.set("foo", "bar");
			String value = jc.get("foo");
			System.out.println("foo=" + value);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			a++;
			if (a > 100000)
				break;
			}
			catch(Exception e) {
				e.printStackTrace();	
			}
		}
		try {
			jc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
