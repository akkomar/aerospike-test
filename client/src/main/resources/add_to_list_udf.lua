local LIST_KEY = "br"

function add_to_list(rec, val_to_add, ttl_to_set)
    if aerospike:exists(rec) then
        local val_list = rec[LIST_KEY]

        list.append(val_list, val_to_add)

        rec[LIST_KEY] = val_list

        record.set_ttl(rec, ttl_to_set)
        aerospike:update(rec)
    else
        rec[LIST_KEY] = list { val_to_add }

        record.set_ttl(rec, ttl_to_set)
        aerospike:create(rec)
    end

    return map {
        br = rec[LIST_KEY]
    }
end
