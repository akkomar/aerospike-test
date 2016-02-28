import com.aerospike.client.Value.{IntegerValue, LongValue}
import com.aerospike.client.policy.WritePolicy
import com.aerospike.client.{AerospikeClient, Key, Language}

import scala.util.Random

object LoadData extends App {
  val client = new AerospikeClient("172.17.0.2", 3000)
  val writePolicy = new WritePolicy()
  writePolicy.sendKey = true

  val NAMESPACE = "test_ns"
  val SET_NAME = "s"

  client.register(writePolicy, getClass.getClassLoader, "add_to_list_udf.lua", "add_to_list_udf.lua", Language.LUA).waitTillComplete()

  val numberOfKeys = 100
  val keys = (1 to numberOfKeys).map(_ => Random.nextString(60))

  while (true) {
    val key = new Key(NAMESPACE, SET_NAME, keys(Random.nextInt(numberOfKeys)))
    client.execute(writePolicy, key, "add_to_list_udf", "add_to_list", new LongValue(Random.nextLong()), new IntegerValue(5))
    Thread.sleep(100)
  }

}
