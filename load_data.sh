#!/bin/bash
COUNTER=0
while [  $COUNTER -lt 1000000 ]; do
    KEY=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)
    docker run --rm -ti --name aerospike-tools --link aerospike:aerospike -v $(pwd)/scripts:/scripts aerospike/aerospike-tools aql -c "insert into test_ns.s (PK, bin_1) values ('$KEY', 'JSON[$RANDOM]')"
    let COUNTER=COUNTER+1
done
