package face.com.yixiangyang.service.facerecognition;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yixiangyang.bootstrap.Application;
import com.yixiangyang.bootstrap.Base64ImageUtils;
import com.yixiangyang.service.BaiDuFaceService;
import com.yixiangyang.service.FaceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class ApplicationTests {

	@Resource
	private BaiDuFaceService faceService;
	
	@Resource
	private FaceService fService;
	
	@Test
	public void contextLoads() {
//		String path = "F:\\dd/me1.jpg";
		String path = "F:\\dd/face.png";
		String group = "group_1";
	    String image = Base64ImageUtils.GetImageStrFromPath(path);
	    System.out.println("------------:"+ image);
////		System.out.println(faceService.faceDetect(image));
		System.out.println(" 这个是:"+faceService.faceSearch(image, group));
////	    System.out.println(faceService.faceVerify(image, "BASE64", ""));
//	    System.out.println(faceService.addUser(image, "BASE64", "009", group, new HashMap<>()));
	}
	
//	@Test
//	public void get() {
//		String path = "F:\\dd/face.jpg";
//		File f = new File("F:\\dd/face.png");
//		byte[] imageFile = FaceUtil.getBytesFromFile(f);
//		fService.faceDetect(imageFile);
//	}

}

