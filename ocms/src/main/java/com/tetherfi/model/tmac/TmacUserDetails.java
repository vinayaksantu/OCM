package com.tetherfi.model.tmac;

import java.util.Map;

public class TmacUserDetails {

	    private String LanID;
	    private String Station;
	    public  TmacUserDetails(Map<String,String> map){
	       LanID=readLanID(map);
	        Station=readStation(map);
	    }
		private String readStation(Map<String, String> map) {

			String value=map.get("Station");
			return value;
		}
		private String readLanID(Map<String, String> map) {
			String value=map.get("Lan Id");
			return value;
		}

		
		public String getLanID() {
			return LanID;
		}
		
		public String getStation() {
			return Station;
		}
}