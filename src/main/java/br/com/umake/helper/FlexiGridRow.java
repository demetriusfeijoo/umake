package br.com.umake.helper;

import br.com.umake.model.Gridable;

import com.google.gson.Gson;

public class FlexiGridRow{

	private  String id;
	private	 String cell;
	
	public FlexiGridRow(Gridable grid) {
		
		Gson gson = new Gson();
		
		this.id = grid.getIdentifier();
		this.cell = gson.toJson(grid);
		
	}


}
