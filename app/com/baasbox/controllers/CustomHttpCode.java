/*
 * Copyright (c) 2014.
 *
 * BaasBox - info-at-baasbox.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baasbox.controllers;

import play.mvc.Results.Status;
import play.mvc.Results;

public enum CustomHttpCode {
			DOCUMENT_VERSION(40001,400,"You are attempting to update a database object with older data. Versions is not the same","error"),
			ACL_JSON_FIELD_MALFORMED(40002,400,"The 'acl' field is not a valid JSON string","error"),
			ACL_PERMISSION_UNKNOWN(40003,400,"The specified 'permission' is unknown. Valid ones are 'read','update','delete',all'","error"),
			ACL_USER_OR_ROLE_KEY_UNKNOWN(40004,400,"Only 'users' and 'roles' can be used","error"),
			ACL_USER_DOES_NOT_EXIST(40005,400,"The specified user does not exist","error"),
			ACL_ROLE_DOES_NOT_EXIST(40006,400,"The specified role does not exist","error"),
			JSON_VALUE_MUST_BE_ARRAY(40010,400,"The expected JSON value must be an array '[.., .., ..]'","error"),
			SESSION_TOKEN_EXPIRED (40101,401,"Authentication info not valid or not provided. HINT: is your session expired?","error"),
			PUSH_CONFIG_INVALID (50301,503,"Push settings are not properly configured. HINT: go to administration console and check the settings","error"),
			PUSH_HOST_UNREACHABLE(50302,503,"Could not resolve host. HINT: check your internet connection","error"),
			PUSH_INVALID_REQUEST (50303,503,"Could not send push notifications. HINT: Check your API Key(Google)","error");

			;
			private String type;
			private int bbCode;
			private int httpCode;
			private String description;

			private CustomHttpCode(int bbCode, int httpCode, String description, String type){
				this.bbCode=bbCode;
				this.httpCode=httpCode;
				this.description=description;
				this.type=type;
			}
			
			public String getType() {
				return type;
			}
			
			public int getBbCode(){
				return this.bbCode;
			}
			
			public int getHttpCode(){
				return this.httpCode;
			}
			
			public String getDescription(){
				return this.description;
			}
			
			public Status getStatus(){
				return Results.status(this.bbCode, this.description);
			}
			
			public static CustomHttpCode getFromBbCode(int bBcode){
				for (CustomHttpCode v: values()){
					if (v.getBbCode()==bBcode) return v;
				}
				return null;
			}
			
}
