include(
    "apps:task-manager-app",
    "apps:task-viewer-app",
    "entities:task-entity",
    "entities:task-entity-reader",
    "entities:task-entity-writer",
    "gateways:postgresql-gateway",
    "use-cases:task-manage-use-case",
    "use-cases:task-view-use-case",
    "use-cases:task-view-use-case-query",

    "dev-infra",
)
