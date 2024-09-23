graph TD
    %% Start of the process
    A[Start: handleNotificationCommand] --> B{Is command null?}
    B -- Yes --> C[Log warning: Null notification command received] --> Z[End]
    
    B -- No --> D[Validate and enrich command]
    D --> D1{Is command valid?}
    D1 -- No --> D2[Throw exception: Invalid command] --> Z[End]
    D1 -- Yes --> D3[Check debounce time]

    %% Debounce time decision
    D3 --> E{Debounce time > 0?}
    E -- Yes --> F[handleNewCommandWithDebounce]
    E -- No --> G[handleNewCommandWithoutDebounce]

    %% handleNewCommandWithDebounce process
    F --> F1[Validate Quota Limits]
    F1 --> F2{Is quota valid?}
    F2 -- No --> F3[Throw NotAllowedException: Error validating quota] --> Z[End]
    F2 -- Yes --> F4{All quotas exceeded?}
    F4 -- Yes --> F5[Set status to SKIPPED and save command]
    F4 -- No --> F6[Set status to QUEUED and save command]
    F6 --> F7[Map command to events]
    F7 --> F8[Produce events] --> Z[End]

    %% handleNewCommandWithoutDebounce process
    G --> G1[Validate Quota Limits]
    G1 --> G2{All quotas exceeded?}
    G2 -- Yes --> G3[Set status to SKIPPED and save command] --> Z[End]
    G2 -- No --> G4[Send messages to available channels] --> Z[End]

    %% Removing throttled channels
    F1 --> H[Remove throttled channels]
    G1 --> H
    H --> H1{Are there throttled channels?}
    H1 -- Yes --> H2[Send warning and remove channels]
    H1 -- No --> H3[Proceed with remaining channels]
    H3 --> returnBack[Return to calling function]

    %% Handling old commands
    D --> I[Get events related to command UUID]
    I --> I1{Events list empty?}
    I1 -- Yes --> J[Handle new command]
    I1 -- No --> K[Handle old command]

    K --> K1[Validate Quota Limits for old command]
    K1 --> K2{Any subscribers left?}
    K2 -- No --> L[Log: All subscribers read notification] --> Z[End]
    K2 -- Yes --> M{Command status SKIPPED?}
    M -- Yes --> N[Reproduce command]
    M -- No --> O[Send messages to available channels] --> Z[End]
