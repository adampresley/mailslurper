/**
 * Class: YAOF
 * Basic implementation of the Observer pattern.
 *
 * Author:
 * 	Adam Presley
 *
 * License (BSD 2-Clause):
 * 	Copyright 2012 Adam Presley. All rights reserved.
 * 	
 * 	Redistribution and use in source and binary forms, with or without
 * 	modification, are permitted provided that the following conditions are met:
 * 	
 * 	1. Redistributions of source code must retain the above copyright notice, this
 * 	   list of conditions and the following disclaimer.
 * 	
 * 	2. Redistributions in binary form must reproduce the above copyright notice,
 * 	   this list of conditions and the following disclaimer in the documentation
 * 	   and/or other materials provided with the distribution.
 * 	
 * 	THIS SOFTWARE IS PROVIDED BY Adam Presley "AS IS" AND ANY EXPRESS OR IMPLIED
 * 	WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * 	MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * 	EVENT SHALL Adam Presley OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * 	INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * 	LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * 	PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * 	LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * 	OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * 	ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
YAOF = function() {
	/**
	 * Function: subscribe
	 * This method registers a handler for a specified event.
	 *
	 * Author:
	 * 	Adam Presley
	 *
	 * Parameters:
	 * 	eventName - String name of the event to subscribe to
	 * 	handler - Function called to handle published events of this type
	 * 	scope - The scope in which to call the handler function
	 */
	this.subscribe = function(eventName, handler, scope) {
		var def = {
			eventName: eventName,
			handler: handler,
			scope: (scope || undefined)
		};

		if (eventName in YAOF.listeners) {
			YAOF.listeners[eventName].push(def);
		}
		else {
			YAOF.listeners[eventName] = [ def ];
		}
	};


	/**
	 * Function: publish
	 * Tells the object to publish an event by name, sending a series of parameters
	 * along with the message for any subscribers to pick up.
	 *
	 * Author:
	 * 	Adam Presley
	 *
	 * Parameters:
	 * 	eventName - String name of the event to publish
	 * 	params - An object of optional parameters to send with the message
	 */
	this.publish = function(eventName, params) {
		var i = 0, params = (params || {});

		if (eventName in YAOF.listeners) {
			for (i = 0; i < YAOF.listeners[eventName].length; i++) {
				if ("scope" in YAOF.listeners[eventName][i] && YAOF.listeners[eventName].scope !== undefined) {
					YAOF.listeners[eventName][i].handler.call(YAOF.listeners[eventName][i].scope, params);
				}
				else {
					YAOF.listeners[eventName][i].handler(params);
				}
			}
		}
	};

	var
		__this = this;
};


YAOF.listeners = {};

YAOF.attach = function(baseObject) {
	baseObject.prototype = new YAOF();
};
