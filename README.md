mfinance - JEE demo project


#### Webapp Deployment:

##### 1. build application containers

$ cd /docker

$ docker-compose up --build

##### 2. add Google+ registered app domain to your docker host env.

go to this file \etc\hosts
 
and add this line:

127.0.0.1 mfinance

-------------------------------------------------------------------------------------
##### 3. Welcome Page (Google+ auth):
 
http://mfinance:8080/login.jsp   

------------------------------------------------------------------------------------
Note!
Set proper <user_name> in Configuration.json path properties (file inside jar dependency)

------------------------------------------------------------------------------------
Note!
Default application directories:

/home/<user_name>/mfinance/bossa/currencies/

/home/<user_name>/mfinance/bossa/funds/
 
/home/<user_name>/mfinance/bossa/backup/currencies/  (zip files location)

/home/<user_name>/mfinance/bossa/backup/funds/       (zip files location)

------------------------------------------------------------------------------------
Note! 
Add smtpconfig.json file to location: "/home/<user_name>/mfinance/smtpconfig.json"
Add webconfiguration.json file to location: "/home/<user_name>/mfinance/webconfiguration.json"

------------------------------------------------------------------------------------

------smtpconfig.json file example content:------------------------------------------

{
  "email":"xxxxx@gmail.com",
  
  "login":"xxxxx@gmail.com",
  
  "password":"xxxxxxxx",
  
  "smtpHost":"smtp.gmail.com",
  
  "smtpPort":"465",
  
  "targetEmail":"xxxxxx@xxxx.com"
}

------------------------------------------------------------------------------------


------webconfiguration.json file example content:-----------------------------------

{
  "slave":false,
  "masterModeAPIServiceTargetURI":"http://localhost:8080/api",
  "slaveModeAPIServiceTargetURI":"http://192.168.1.104:8080/api",
  "defaultAdminAccountLogin":"defaultadmin@gmail.com"
}

------------------------------------------------------------------------------------

