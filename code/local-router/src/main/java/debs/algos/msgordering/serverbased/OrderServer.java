package debs.algos.msgordering.serverbased;

public class OrderServer {
	private static int order;
	
	public static void init(){
		order = 0;
	}

	public static int getOrder() {
		return ++order;
	}

}
