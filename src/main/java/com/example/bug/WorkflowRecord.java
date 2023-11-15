package com.example.bug;

import java.util.UUID;

public record WorkflowRecord(UUID id, String name) {
    public WorkflowRecord(Workflow workflow) {
        this(workflow.getId(), "workflow");
    }
}
