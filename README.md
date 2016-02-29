### Start aerospike server in Docker container
```
docker run --rm -v $(pwd)/etc:/opt/aerospike/etc --name aerospike aerospike/aerospike-server --config-file /opt/aerospike/etc/aerospike.conf
```
and observe the log.
### Data loader
Loaders are located in `/client` directory. There are two implementations: first uses udf, second saves data directly using Java API.
First one can be started by running `LoadData` class, or via console:
```
cd client
./sbt "run-main LoadData"
```
It will run in a loop. After starting, observe server's log, it will start printing:
```
WARNING (namespace): (namespace.c::774) set_id 1 - n_bytes_memory went negative!
```

Second loader can be started with:

```
./sbt "run-main LoadDataSimple"
```
this one does not cause negative n_bytes_memory errors (restart server before starting if there were errors)