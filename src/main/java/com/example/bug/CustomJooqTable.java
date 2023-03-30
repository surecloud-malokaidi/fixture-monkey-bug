package com.example.bug;

import org.jooq.Name;
import org.jooq.impl.CustomTable;
import org.jooq.impl.DSL;

public class CustomJooqTable extends CustomTable<CustomJooqRecord> {
    protected CustomJooqTable() {
        super(DSL.name("custom_jooq_table"));
    }

    @Override
    public Class<? extends CustomJooqRecord> getRecordType() {
        return CustomJooqRecord.class;
    }
}
