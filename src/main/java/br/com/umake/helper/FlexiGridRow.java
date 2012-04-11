package br.com.umake.helper;

import com.google.gson.Gson;

public class FlexiGridRow<T>{

	private  String id;
	private	 String cell;
	
	public FlexiGridRow(T grid) {
		
		Gson gson = new Gson();
		
		this.id = null;
		this.cell = null;
		
	}


}
