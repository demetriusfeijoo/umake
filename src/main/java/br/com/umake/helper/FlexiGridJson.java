package br.com.umake.helper;

import java.util.ArrayList;
import java.util.List;

import br.com.umake.model.Gridable;

public class FlexiGridJson<T extends Gridable>{

	private final Integer page;
	private final Integer total;
	private final List<FlexiGridRow> rows = new ArrayList<FlexiGridRow>();
	
	public FlexiGridJson(Integer page, Integer total, List<T> rows) {
		
		super();
		this.page = page;
		this.total = total;
		rows.get(0);
				
		for (T row : rows) {
			
			this.rows.add( new FlexiGridRow(row) );
			
		}
	}

}
