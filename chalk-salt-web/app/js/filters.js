'use strict';
define([ './app.js', 'angular' ], function(filters, angular) {

    filters.filter('range', function() {
        return function(input, total) {
            total = parseInt(total);
            for (var i = 0; i < total; i++)
                input.push(i);
            return input;
        };
    });
    
    filters.filter('startFrom', function () {
    	return function (input, start) {
    		if (input) {
    			start = +start;
    			return input.slice(start);
    		}
    		return [];
    	};
    });
    
    return filters;
});