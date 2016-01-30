"use strict";

 define(["angular"], function(angular) { 
  angular.module("System.configuration", [])

.constant("CHALKNDUST", {
	"NAME": "Chalk And Dust",
	"EMAIL": "abhishek.kumar627@gmail.com",
	"VERSION": "3",
	"BUILD": "0.0",
	"RELEASE_DATE": "November 2015",
	"SECURUUID": "securUuid",
	"USERFULLNAME": "userFullName",
	"EDITFLAG": "Flag",
	"USERNAME": "Username",
	"CLASSID": "classId"
})

.constant("ENV", {
	"NAME": "development",
	"API_END_POINT": "http://localhost:8080/chalk-salt-api/"
})

; 

});