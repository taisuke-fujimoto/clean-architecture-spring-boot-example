include(
    "apps:task-manager-app",
    "apps:task-viewer-app",
    "entities:task-entity",
    "gateways:postgresql-gateway",
    "use-cases:task-manage-use-case",
    "use-cases:task-view-use-case",
    "use-cases:task-view-use-case-query",

    "dev-infra",
)
