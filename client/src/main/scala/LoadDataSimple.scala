import com.aerospike.client.Value.LongValue
import com.aerospike.client.policy.WritePolicy
import com.aerospike.client.{AerospikeClient, Bin, Key}

import scala.util.Random

object LoadDataSimple extends App {
  val client = new AerospikeClient("172.17.0.2", 3000)
  val writePolicy = new WritePolicy()
  writePolicy.sendKey = true

  val NAMESPACE = "test_ns"
  val SET_NAME = "s"

  val numberOfKeys = 100
  val keys = (1 to numberOfKeys).map(_ => Random.nextString(60))

  while (true) {
    val key = new Key(NAMESPACE, SET_NAME, keys(Random.nextInt(numberOfKeys)))

    client.put(writePolicy, key, new Bin("v", new LongValue(Random.nextLong())))

    Thread.sleep(100)
  }

}
