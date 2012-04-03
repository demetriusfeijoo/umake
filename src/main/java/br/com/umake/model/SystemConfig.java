package br.com.umake.model;


class SystemFactory {

	private static System sc = null;
	
	private SystemFactory(){}
	
	public static System getSystem(){
		
		return SystemFactory.sc;
		
	}
	
	
}

class System{
	
}


class Plugin{
	
	public Plugin(){
	}
	
	public void testa(){
		
		System sc = SystemFactory.getSystem();
		
	}
	
}
