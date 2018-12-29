# Transfer-Application
Simple transfer application that allow to create plain bank accounts and transfer money between them
Installation:
1. git clone https://github.com/AberonMan/Transfer-Application.git
2. cd Transfer-Application/
3. gradlew shadowJar
4. java -jar build/libs/transferServer.jar server  (Please note, that only java 8 is supported)
5. If you want to specify ports, please use:
java -Ddw.server.applicationConnectors[0].port=9090 -Ddw.server.adminConnectors[0].port=9091 -jar build/libs/transferServer.jar server

Endpoints:
1. Get: /accounts  - shaw all accounts in server

    Response example:
      ```json
    [{"id":1,"amount":"USD 100"},{"id":2,"amount":"USD 100"}]\
    ```
2. POST: /account/create - create account in service, for simplicity account is just id and amount

  Request example:
  ```json
  {
   "amount": 100
  }
  ```
    Response example:
  ```json
  {
    "id": 1
  }
  ```
  3. POST: /transfer - transfer amount from one account to another
  
  Request example:
  ```json
  {
    "from": 1,
    "to": 2,
    "amount":100
  }
  ```
  where from, to - accounts id
