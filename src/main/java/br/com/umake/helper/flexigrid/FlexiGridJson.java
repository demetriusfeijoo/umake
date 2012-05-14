package br.com.umake.helper.flexigrid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FlexiGridJson<T>{

	private final Integer page;
	private final Integer total;
	private List<FlexiGridRow> rows = new ArrayList<FlexiGridRow>();
	
	public FlexiGridJson(Integer page, Integer total, List<T> rows) throws Exception {
		
		super();
		this.page = page;
		this.total = total;
		
		if( rows.size() > 0 ){

			List<String> allAttInOrder = this.getAttWillBeListed(rows);
			String columnId = this.getNameColumnId(rows);

			for (T row : rows) {
				
				List<String> cell = new ArrayList<String>(); 
				
				for (String fieldStringInOrder : allAttInOrder) {
																			
					Field field = row.getClass().getDeclaredField(fieldStringInOrder);
					field.setAccessible(true);
					cell.add(field.get(row).toString() );

				}
				
				String valueId = "";
				
				if( !columnId.equals("") ){
					
					Field fieldId = row.getClass().getDeclaredField(columnId);
					fieldId.setAccessible(true);	
					valueId = fieldId.get(row).toString();	
					
				}
				
				this.rows.add(new FlexiGridRow(valueId, cell));
				
			}
			
		}


	}
	
	private int getTotalFieldsAnnotedWithColumn(Field[] fields){
		
		int totalFieldsWithAnnotationColumn = 0;
		
		
		for (Field field : fields){
			
			if(field.isAnnotationPresent(br.com.umake.helper.flexigrid.Column.class))					
				totalFieldsWithAnnotationColumn++;	
			
		}
	
		return totalFieldsWithAnnotationColumn;
		
	}
	
	private String getNameColumnId(List<T> rows){
		
		Field[] fields = ((T)rows.get(0)).getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			if(field.isAnnotationPresent(br.com.umake.helper.flexigrid.Id.class)){
				
				return field.getName();
				
			}
		}
		
		return "";
	}
	
    private List<String> getAttWillBeListed(List<T> rows) throws Exception{
    	
		Field[] fields = ((T)rows.get(0)).getClass().getDeclaredFields();
		List<String> fieldsMappeds = new ArrayList<String>();
		
		int totalFieldsWithAnnotationColumn = this.getTotalFieldsAnnotedWithColumn(fields);
		
		if(totalFieldsWithAnnotationColumn < 1 )
			
			throw new Exception("Annotation Column.class not exists");
		
		do{
			
			String fieldName = "";
			
			int minOfTheLoop = Integer.MAX_VALUE;
			
			for (Field field : fields){
				
				if(field.isAnnotationPresent(br.com.umake.helper.flexigrid.Column.class)){
				
					Column column = field.getAnnotation(br.com.umake.helper.flexigrid.Column.class);
					
					if( column.position() <= minOfTheLoop && !fieldsMappeds.contains(field.getName()) ){

						minOfTheLoop = column.position();
						fieldName = field.getName();
						
					}
					
					
				}
					
				
			}

			fieldsMappeds.add(fieldName);
			
		}while(fieldsMappeds.size() < totalFieldsWithAnnotationColumn); 
    
		return fieldsMappeds;
		
    }

}
