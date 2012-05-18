package br.com.umake.helper.utils;

public final class TextHelper {

	public static String createSlug( String originalForm ){
		
		String generateSlug = originalForm.replaceAll("[ÁáÀàÃãÂâÄä]", "a");
		generateSlug = generateSlug.replaceAll("[ÉéÈèÊêËë]", "e");
		generateSlug = generateSlug.replaceAll("[ÍíÌìÎîÏï]", "i");
		generateSlug = generateSlug.replaceAll("[ÓóÒòÕõÔôÖö]", "o");
		generateSlug = generateSlug.replaceAll("[ÚúÙùÛûÜü]", "u");
		generateSlug = generateSlug.replaceAll("[Ññ]", "n");
		generateSlug = generateSlug.replaceAll("[Çç]", "c");
		generateSlug = generateSlug.replaceAll("[ÿÝý]", "y");
		generateSlug = generateSlug.replaceAll("[ª]", "a.");
		generateSlug = generateSlug.replaceAll("[º]", "o.");
		generateSlug = generateSlug.replaceAll("(\\s)++", "-");		
		
		return generateSlug;
		
	}
	
}