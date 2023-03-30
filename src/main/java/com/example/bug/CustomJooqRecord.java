package com.example.bug;

import org.jooq.Table;
import org.jooq.impl.CustomRecord;

public class CustomJooqRecord extends CustomRecord<CustomJooqRecord> {
    protected CustomJooqRecord() {
        super(new CustomJooqTable());
    }
}
