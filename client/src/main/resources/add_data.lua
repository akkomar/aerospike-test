local VAL_KEY = "v"

function add_data(rec, val_to_add, ttl_to_set)
    if aerospike:exists(rec) then
        rec[VAL_KEY] = val_to_add

        aerospike:update(rec)
    else
        rec[VAL_KEY] = val_to_add

        aerospike:create(rec)
    end

end
