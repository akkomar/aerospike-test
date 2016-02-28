Start aerospike server:

    docker run --rm -v $(pwd)/etc:/opt/aerospike/etc --name aerospike aerospike/aerospike-server --config-file /opt/aerospike/etc/aerospike.conf

Start aql console:

    docker run --rm -ti --name aerospike-tools --link aerospike:aerospike aerospike/aerospike-tools aql


Register udf:

    docker run --rm -v $(pwd)/udf:/udf --link aerospike:aerospike aerospike/aerospike-tools ascli udf-put /udf/lifetime_user_counters.lua



insert into test_ns.s (PK, bin_1) values ('key', 'JSON[1,2,3]')
select * from test_ns.s


docker run --rm -ti --name aerospike-tools --link aerospike:aerospike -v $(pwd)/scripts:/scripts aerospike/aerospike-tools aql -c "insert into test_ns.s (PK, bin_1) values ('key', 'JSON[1,2,3]')"


docker run --rm -ti --name aerospike-tools --link aerospike:aerospike -v $(pwd)/scripts:/scripts aerospike/aerospike-tools aql -c "select * from test_ns.s"
