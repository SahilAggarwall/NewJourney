```mermaid
    flowchart
    A[Start: Commands Consumer] --> B[handleNotificationCommand]
    B --> C{command Null?}
    C -- Yes --> [validate]
    
```
