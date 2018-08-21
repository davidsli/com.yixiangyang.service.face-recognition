package face.com.yixiangyang.service.facerecognition;

import com.yixiangyang.bootstrap.Base64ImageUtils;

public class Test {

	public static void main(String[] args) {
		String path = "F:\\dd/me2.jpg";
	    String image = Base64ImageUtils.GetImageStrFromPath(path);
	    System.out.println("               -------------------------           ");
	    System.out.print("gggg:"+image);

	}

}
