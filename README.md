# Transfer-Application
Simple transfer application that allow to create plain bank accounts and transfer money between them
Installation:
1. git clone https://github.com/AberonMan/Transfer-Application.git
2. cd Transfer-Application/
3. gradlew shadowJar
4. java -jar build/libs/transferServer.jar server 
Please note, that only java 8 is supported
4*. If you want to specify ports, please use:
java -Ddw.server.applicationConnectors[0].port=9090 -Ddw.server.adminConnectors[0].port=9091 -jar build/libs/transferServer.jar server
Endpoints:
1. Get: /accounts  - shaw all accounts in server
2. POST: /account/create - create account in service, account in that server is just id and amount
Request example:
{
"amount": 100
}

Response example:
{
  "id": 1
}
