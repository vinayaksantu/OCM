package com.tetherfi.model.fax;

import java.util.Map;

public class FaxAddressBookDetails {
	private String firstName;
	private String lastName;
	private String Number;
	

	public FaxAddressBookDetails(Map<String, String> map) {
		firstName=readFirstName(map);
		lastName=readLastName(map);
		Number=readNumber(map);
	}

	private String readNumber(Map<String, String> map) {
		String Value=map.get("Number");
				return Value;
	}

	private String readLastName(Map<String, String> map) {
		String Value=map.get("Last Name");
		return Value;
	}

	private String readFirstName(Map<String, String> map) {
		String Value=map.get("First Name");
		return Value;
	}

	public String getfirstName() {
		return firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public String getNumber() {
		return Number;
	}

}
