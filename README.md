### Start aerospike server in Docker container
```
docker run --rm -v $(pwd)/etc:/opt/aerospike/etc --name aerospike aerospike/aerospike-server --config-file /opt/aerospike/etc/aerospike.conf
```
and observe the log.
### Data loader
Loader is located in `/client` directory. It can be started by running `LoadData` class, or via console:
```
cd client
./sbt run
```
It will run in a loop. After starting, observe server's log, it will start printing:
```
WARNING (namespace): (namespace.c::774) set_id 1 - n_bytes_memory went negative!
```
