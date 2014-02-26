package zhengfang;

public class Context {
	/**
	 * 正方服务器地址
	 */
	public static String[] hosts = { "202.116.160.166", "202.116.160.167",
			"202.116.160.173" };

	public static String[] viewStates = {
			"dDwtMTg3MTM5OTI5MTs7Pm3EYMABeWjEprmuXse%2FoURhr5WV",
			"dDwtMTg3MTM5OTI5MTs7PiXqg0GwJxzn4SLMWMrOOoJJHvHk",
			"dDwtMTg3MTM5OTI5MTs7PpThNct%2FWCRJmqE0Bbet1xB2o04M" };

	public static String getHost(int portal) {
		return hosts[portal];
	}

	public static String getViewState(int portal) {
		return viewStates[portal];
	}

}
