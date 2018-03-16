package com.mycalela.um.controller;


import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycalela.um.email.EmailClient;
import com.mycalela.um.models.Users;
import com.mycalela.um.models.Address;
import com.mycalela.um.models.EmailInfo;
import com.mycalela.um.models.FirmInfo;
import com.mycalela.um.models.Image;
import com.mycalela.um.models.OTP;
import com.mycalela.um.models.PhoneInfo;
import com.mycalela.um.models.SessionInfo;
import com.mycalela.um.models.UserSetting;
import com.mycalela.um.models.ActivityLog;
import com.mycalela.um.models.PasswordResetLink;
import com.mycalela.um.service.DBDao;
import com.mycalela.um.service.DBService;

@RestController
public class UserManagingController {
	 @Autowired
	   DBService dbService;
	   
	   @Autowired
	   DBDao dbDao;
	   
	    @Autowired
	    EmailClient emailClient;


	    static Gson gson = new GsonBuilder().create();

	    public static final Logger logger = (Logger) LoggerFactory.getLogger(UserManagingController.class);
	    
	    @GetMapping("/users")
		public JSONObject getUsers() {
			return dbService.getUsers();
		}

		
		@GetMapping("/users/{id}")
		public ResponseEntity<JSONObject> getUsers(@PathVariable("id") Long id) {

			Users user = new Users();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(user);
		    JSONObject request;
			try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request  = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}

		
		@PostMapping(value = "/users")
		public ResponseEntity<Users> createUser(@RequestBody Users users) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<Users>(users, HttpStatus.OK);
		}

		
		
		@DeleteMapping("/users/{id}")
		public ResponseEntity<Long> deleteUser(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		@PutMapping("/users/{id}")
		public ResponseEntity<JSONObject> updateUser(@PathVariable Long id, @RequestBody Users users) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(users);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}

		
		
		@GetMapping("/users/{id}/address")
		public ResponseEntity<JSONObject> getAddress(@PathVariable("id") Long id) {
			
			Address address = new Address();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(address);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@PostMapping(value = "/users/address")
		public ResponseEntity<Address> insert(@RequestBody Address address) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<Address>(address, HttpStatus.OK);
		}
		
		
		
		@DeleteMapping("/users/{id}/address")
		public ResponseEntity<Long> deleteAddress(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		
		@PutMapping("/users/{id}/address")
		public ResponseEntity<JSONObject> updateAddress(@PathVariable Long id, @RequestBody Address address) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(address);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/email")
		public ResponseEntity<JSONObject> getEmail(@PathVariable("id") Long id) {
			
			EmailInfo email = new EmailInfo();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(email);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
			
		}
		
		
		
		@PostMapping(value = "/users/email")
		public ResponseEntity<EmailInfo> insert(@RequestBody EmailInfo email) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<EmailInfo>(email, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/users/{id}/email")
		public ResponseEntity<Long> deleteEmail(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		@PutMapping("/users/{id}/email")
		public ResponseEntity<JSONObject> updateEmail(@PathVariable Long id, @RequestBody EmailInfo email) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(email);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/firm")
		public ResponseEntity<JSONObject> getFirmInfo(@PathVariable("id") Long id) {

			FirmInfo firm = new FirmInfo();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(firm);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
			
		}
		
		
		@PostMapping(value = "/users/firm")
		public ResponseEntity<FirmInfo> insert(@RequestBody FirmInfo firm) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<FirmInfo>(firm, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/users/{id}/firm")
		public ResponseEntity<Long> deletefirm(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		@PutMapping("/users/{id}/firm")
		public ResponseEntity<JSONObject> updateFirm(@PathVariable Long id, @RequestBody FirmInfo firm) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(firm);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/image")
		public ResponseEntity<JSONObject> getImage(@PathVariable("id") Long id) {

			Image image = new Image();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(image);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
			
		}
		
		
		@PostMapping(value = "/users/image")
		public ResponseEntity<Image> insert(@RequestBody Image image) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<Image>(image, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/users/{id}/image")
		public ResponseEntity<Long> deleteImage(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		@PutMapping("/users/{id}/image")
		public ResponseEntity<JSONObject> updateImage(@PathVariable Long id, @RequestBody Image image) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(image);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/otp")
		public ResponseEntity<JSONObject> getOTP(@PathVariable("id") Long id) {
			
			OTP otp = new OTP();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(otp);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		    request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
			
		}
		
		
		
		@PostMapping(value = "/users/otp")
		public ResponseEntity<OTP> insert(@RequestBody OTP otp) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<OTP>(otp, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/users/{id}/otp")
		public ResponseEntity<Long> deleteOTP(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		@PutMapping("/users/{id}/otp")
		public ResponseEntity<JSONObject> updateOTP(@PathVariable Long id, @RequestBody OTP otp) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(otp);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/phone")
		public ResponseEntity<JSONObject> getPhoneInfo(@PathVariable("id") Long id) {
			
			PhoneInfo phone = new PhoneInfo();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(phone);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
			
		}
		
		
		
		@PostMapping(value = "/users/phone")
		public ResponseEntity<PhoneInfo> insert(@RequestBody PhoneInfo phone) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<PhoneInfo>(phone, HttpStatus.OK);
		}
		
		
		
		@DeleteMapping("/users/{id}/phone")
		public ResponseEntity<Long> deletePhone(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		
		@PutMapping("/users/{id}/phone")
		public ResponseEntity<JSONObject> updatePhone(@PathVariable Long id, @RequestBody PhoneInfo phone) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(phone);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		
		@GetMapping("/users/{id}/session")
		public ResponseEntity<JSONObject> getSessionInfo(@PathVariable("id") Long id) {

			SessionInfo session = new SessionInfo();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(session);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
			
		}
		
		
		@PostMapping(value = "/users/session")
		public ResponseEntity<SessionInfo> insert(@RequestBody SessionInfo session) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<SessionInfo>(session, HttpStatus.OK);
		}
		
		
		
		@DeleteMapping("/users/{id}/session")
		public ResponseEntity<Long> deleteSession(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		@PutMapping("/users/{id}/session")
		public ResponseEntity<JSONObject> updateSession(@PathVariable Long id, @RequestBody SessionInfo session) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(session);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/usersettings")
		public ResponseEntity<JSONObject> getUserSetting(@PathVariable("id") Long id) {
			
			UserSetting userSetting = new UserSetting();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(userSetting);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			request = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
			
		}
		
		
		@PostMapping(value = "/users/setting")
		public ResponseEntity<UserSetting> insert(@RequestBody UserSetting setting) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<UserSetting>(setting, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/users/{id}/usersettings")
		public ResponseEntity<Long> deleteSetting(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		@PutMapping("/users/{id}/usersettings")
		public ResponseEntity<JSONObject> updateUserSetting(@PathVariable Long id, @RequestBody UserSetting userSetting) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(userSetting);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/activitylog")
		public ResponseEntity<JSONObject> getActivityLog(@PathVariable("id") Long id) {

			ActivityLog activityLog = new ActivityLog();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(activityLog);
		    JSONObject request;
			try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request  = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}

		
		
		@PostMapping(value = "/users/activity")
		public ResponseEntity<ActivityLog> createActivityLog(@RequestBody ActivityLog activityLog) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<ActivityLog>(activityLog, HttpStatus.OK);
		}

		
		
		@DeleteMapping("/users/{id}/activitylog")
		public ResponseEntity<Long> deleteActivityLog(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		
		@PutMapping("/users/{id}/activitylog")
		public ResponseEntity<JSONObject> updateActivityLog(@PathVariable Long id, @RequestBody ActivityLog activityLog) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(activityLog);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
		
		@GetMapping("/users/{id}/passwordResetLink")
		public ResponseEntity<JSONObject> getPAsswordResetLink(@PathVariable("id") Long id) {

			PasswordResetLink passwordReset = new PasswordResetLink();
			Gson gson = new Gson();
		    String jsonString = gson.toJson(passwordReset);
		    JSONObject request;
			try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request  = dbService.find(DBService.dbCollectionName);
			if (request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}

		
		
		@PostMapping(value = "/users/passwordResetLink")
		public ResponseEntity<PasswordResetLink> createPasswordResetLink(@RequestBody PasswordResetLink passwordResetLink) {

			dbService.insert(DBService.dbCollectionName, DBService.member);

			return new ResponseEntity<PasswordResetLink>(passwordResetLink, HttpStatus.OK);
		}

		
		
		@DeleteMapping("/users/{id}/passwordResetLink")
		public ResponseEntity<Long> deletePasswordResetLink(@PathVariable Long id) {

			if (null == dbService.remove(DBService.dbCollectionName, DBService.query)) {
				return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Long>(id, HttpStatus.OK);

		}

		
		
		@PutMapping("/users/{id}/passwordResetLink")
		public ResponseEntity<JSONObject> updatePasswordResetLink(@PathVariable Long id, @RequestBody PasswordResetLink passwordResetLink) {

			Gson gson = new Gson();
		    String jsonString = gson.toJson(passwordResetLink);
		    JSONObject request;
		    try {
				request = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			request = dbService.update(DBService.dbCollectionName, DBService.query, DBService.member, DBService.upsert);

			if ( request == null) {
				return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<JSONObject>(request, HttpStatus.OK);
		}
		
		
}



