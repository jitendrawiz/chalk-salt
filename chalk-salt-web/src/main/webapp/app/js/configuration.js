"use strict";

 define(["angular"], function(angular) { 
  angular.module("System.configuration", [])

.constant("CHALKNDUST", {
	"NAME": "Chalk N Dust",
	"EMAIL": "mr.jitendrapareek@gmail.com",
	"VERSION": "3",
	"BUILD": "0.0",
	"RELEASE_DATE": "November 2015"
})

.constant("ENV", {
	"NAME": "development",
	"API_END_POINT": "http://localhost:8080/chalk-salt-api/"
});
});