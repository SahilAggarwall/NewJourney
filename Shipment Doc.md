# Shipment Page Overview (TMS Portal)


### 1. **Settings Integration:**
   - `/settings/alert-ui:`
     - This endpoint is used for overriding specific settings. The `overrideFor` parameter allows settings to be customized based on the object type, enabling tailored adjustments for different use cases.
   - `/counter:`
     - This endpoint manages shipment numbers, ensuring unique identification for each shipment created in the system.
   - `/view-setting/mis-template:`
     - Supports the creation and deletion of MIS report templates, enabling custom report generation based on shipment data.
   - `/settings/custom-field:`
     - Allows custom fields to be created at various stages of a shipment’s lifecycle, including creation, finalization, and completion. This adds flexibility for tracking specific shipment details.
   - `/shipment/notification/preference:`
     - Manages notification preferences, allowing the system to trigger alerts under specific conditions, such as changes in shipment status or delays.

---

### 2. **Types of Shipment Tracking:**
I have also learned about various tracking methods that can be integrated with the TMS for real-time tracking of shipments:
   - ***SIM Tracking:*** Uses cellular network information to locate vehicles or shipments via SIM cards.
   - ***VTS (Vehicle Tracking System):*** GPS-based system for tracking vehicle movements.
   - ***TrackNet:*** Multi-source tracking combining GPS, Wi-Fi, or cellular networks.
   - ***FASTag:*** Electronic toll collection system that also offers movement data at toll plazas.
   - ***PTL (Parcel Tracking Label):*** Barcode-based tracking to monitor the movement of parcels across the logistics chain.

---

### 3. **Activity Log Retrieval:** ( `/shipment/{shipmentId}/update-trail
   - I have learned how the activity logs related to shipments are retrieved. This helps in auditing and tracing each action taken on a shipment, such as status changes

---

### 4. **Changes in Itineraries (Editing Consignments):**
   - Shipment itineraries can be modified by editing consignments, which includes changing routes or modifying shipment details.

---

### 5. **Adding APIs and UI Buttons for Shipment Page:**
   - I worked on integrating new APIs into the shipment page, enabling the system to fetch data (adding Buttons)

---

### 6. **Status Changes in Shipments:**  ( `bulk/sync` is called )
   - The shipment moves through various stages, and each status change reflects the current state of the shipment, triggering corresponding actions in the system. These include sending notifications, updating activity logs, and modifying itineraries. The primary status and secondary status change accordingly.
     - **AT**: Once the Shipment is reached at a Stage, status remains "AT" throught the process, while `secondaryStatus` can differ like `WaitingForLoading`,`WaitingforUnloading`,`WaitingForGateIn`,etc 
     - **NEXT**: Status of the upcoming stage is "NEXT"
     - **COMPLETED**: Once the shipment is fully delivered, the status changes to "Completed", marking the conclusion of the shipment process. At this point, there is no "Next" stage. 


## Shipment Data Structure:

- **shipmentNumber**: `String`  
  _Description_: Unique shipment identifier.  
  _Example_: `"AANGARESH0000176"`

- **transportationMode**: `String`  
  _Description_: Mode of transport for the shipment.  
  _Example_: `"ByRoad"`

- **shipmentType**: `String`  
  _Description_: Type of shipment.  
  _Example_: `"DirectLeg"`

- **tripType**: `String`  
  _Description_: Type of trip for the shipment.  
  _Example_: `"Shipment"`

- **creationTime**: `Long`  
  _Description_: Timestamp when the shipment was created.  
  _Example_: `1689316815618`

- **completionTime**: `Long`  
  _Description_: Timestamp when the shipment was completed.  
  _Example_: `1726480682061`

- **shipmentStatus**: `String`  
  _Description_: Current status of the shipment.  
  _Example_: `"Completed"` , `"At"` , `"Next"` 

- **shipmentStages**: `List<ShipmentStage>`  
  _Description_: Array of stages in the shipment journey.  
  _Example_: 
  
  `0 [departureTime: 1689320991000, gateInTime: 1689317008000, actualActivityStartTime: 1689320991000,…]`
  
  `1 [departureTime: 1726480680284, gateInTime: 1726476383009, actualActivityStartTime: 1726476383010,…]`
`

- **trackingMode**: `String`  
  _Description_: Mode of tracking used for the shipment.  
  _Example_: `"VTS"` , `"SIM"` , `"PTL"`

- **currentLocation**: `String?`  
  _Description_: The current location of the shipment (nullable).  
  _Example_: `null` , `array of Lat Long`

- **billingStatus**: `String`  
  _Description_: Current billing status of the shipment.  
  _Example_: `"UnSettled"`

- **customFields**: `List<CustomField>`  
  _Description_: User-defined custom fields for the shipment.  
  _Example_: `[{fieldKey: "Material Type", value: "WIRE ROD", required: true}]`

- **driver**: `Driver`  
  _Description_: Details of the driver responsible for the shipment.  
  _Example_: `{isEmployee: false, attachedDocs: null}`

- **fleetInfo**: `FleetInfo`  
  _Description_: Information about the fleet assigned to the shipment.  
  _Example_: `{isTrackingEnable: null, documents: null}`

- **delayReason**: `String?`  
  _Description_: Reason for any delays (nullable).  
  _Example_: `null`

- **issues**: `String?`  
  _Description_: Any issues related to the shipment (nullable).  
  _Example_: `null`

- **uuid**: `String`  
  _Description_: Unique identifier for the shipment.  
  _Example_: `"0b1b5b49-19e7-4179-9da3-85c8d6a72a88"`

- **orgId**: `String?`  
  _Description_: Organization ID associated with the shipment (nullable).  
  _Example_: `"e13180e7-f849-4568-ad7e-1a237a1a8fb3"`

- **remarks**: `String?`  
  _Description_: Any remarks for the shipment (nullable).  
  _Example_: `null`

- **updates**: `UpdateInfo`  
  _Description_: Information on the most recent update for the shipment.  
  _Example_: `{traceID: "e45bd2db-a4c2-4971-9f09-273e56d586a2", resourceId: "0b1b5b49-19e7-4179-9da3-85c8d6a72a88"}`
