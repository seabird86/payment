# payment

A simple service with default features:
 + Global error Handler
 + Multi language
 + Load resource from Centralize
 + Support a distributed transaction (Try-Confirm/Cancel) with APIs:
  * Try: POST /customers/{userId}/first-transaction
  * Confirm: PUT /customers/{userId}/first-transaction
  * Cancel: DELETE /customers/{userId}/first-transaction
