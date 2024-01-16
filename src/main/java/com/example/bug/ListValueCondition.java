package com.example.bug;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListValueCondition extends Condition {
    private List<Condition> conditions;
}
